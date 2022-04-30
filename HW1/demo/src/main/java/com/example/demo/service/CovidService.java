package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.cache.Cache;
import com.example.demo.entities.ByParams;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.repository.CovidRepository;
import com.example.demo.resolver.Resolver;

@Service
public class CovidService {

    @Autowired
    Cache cache;

    @Autowired
    CovidRepository covidRepository;

    @Autowired
    Resolver resolver;

    private static final Logger logger = LoggerFactory.getLogger(CovidService.class);
    private static final String DATANOTINCACHE = "Data not in cache";
    private static final String DATAFROMCACHE = "Data retrieved from cache";

    public ByParams getByDate(String dateURL) throws InterruptedException, BadRequestException, IOException {
        logger.info("Getting Data for date {} in the cache", dateURL);

        ByParams getPelaDate = cache.getByDate(dateURL);

        if (getPelaDate == null) {
            cache.addMiss();
            logger.info(DATANOTINCACHE);
            String url = "/reports/total?date=" + dateURL;
            String response = resolver.getResponse(url);

            ByParams byParams = resolver.convertJSONbyDatetoByParams(response);
            covidRepository.save(byParams);

            return byParams;
        } else {
            logger.info(DATAFROMCACHE);
            cache.addHit();
            return getPelaDate;
        }

    }

    public ByParams getByCountry(String country) throws InterruptedException, BadRequestException, IOException {

        ByParams getPeloCountry = cache.getByCountry(country);

        if (getPeloCountry == null) {
            cache.addMiss();
            logger.info(DATANOTINCACHE);
            String url = "/reports?region_name=" + country;
            String response = resolver.getResponse(url);

            ByParams byParams = resolver.convertJSONbyCountrytoByParams(response, country);
            covidRepository.save(byParams);
            return byParams;

        } else {
            cache.addHit();
            logger.info(DATAFROMCACHE);
            return getPeloCountry;
        }
    }

    public ByParams getByParams(String dateURL, String countryURL)
            throws InterruptedException, BadRequestException, IOException {

        ByParams getPeloCountryDate = cache.getByParametros(dateURL, countryURL);

        if (getPeloCountryDate == null) {
            cache.addMiss();
            logger.info(DATANOTINCACHE);
            String url = "/reports?region_name=" + countryURL
                    + "&date=" + dateURL;
            String response = resolver.getResponse(url);

            ByParams byParams = resolver.convertJSONbyParamstoByParams(response, countryURL);
            covidRepository.save(byParams);
            return byParams;
        } else {
            cache.addHit();
            logger.info(DATAFROMCACHE);
            return getPeloCountryDate;
        }
    }

    public List<String> getCountries() throws InterruptedException, IOException {
        String url = "/regions";
        String response = resolver.getResponse(url);

        return resolver.convertToCountries(response);
    }

    public Map<String, Integer> getCacheDetails() {
        HashMap<String, Integer> response = new HashMap<>();
        response.put("hits", cache.getHits());
        response.put("misses", cache.getMisses());
        response.put("requests", cache.getHits() + cache.getMisses());
        return response;
    }

    public List<ByParams> getAllByParams() {
        return covidRepository.findAll();
    }
}
