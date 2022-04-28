package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.cache.Cache;
import com.example.demo.entities.ByParams;
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

    public ByParams getByDate(String dateURL) throws Exception {
        logger.info("Getting Data for date {} in the cache", dateURL);

        ByParams getPelaDate = cache.getByDate(dateURL);

        if (getPelaDate == null) {
            cache.addMiss();
            logger.info("Data not in cache");
            String url = "/reports/total?date=" + dateURL;
            String response = resolver.getResponse(url);

            ByParams byParams = resolver.convertJSONbyDatetoByParams(response);
            covidRepository.save(byParams);

            return byParams;
        } else {
            logger.info("Data retrieved from cache");
            cache.addHit();
            return getPelaDate;
        }

    }

    public ByParams getByCountry(String country) throws Exception {

        ByParams getPeloCountry = cache.getByCountry(country);

        if (getPeloCountry == null) {
            cache.addMiss();
            logger.info("Data not in cache");
            String url = "/reports?region_name=" + country;
            String response = resolver.getResponse(url);

            ByParams byParams = resolver.convertJSONbyCountrytoByParams(response, country);
            covidRepository.save(byParams);
            return byParams;

        } else {
            cache.addHit();
            logger.info("Data retrieved from cache");
            return getPeloCountry;
        }
    }

    public ByParams getByParams(String dateURL, String countryURL)
            throws Exception {

        ByParams getPeloCountryDate = cache.getByParametros(dateURL, countryURL);

        if (getPeloCountryDate == null) {
            cache.addMiss();
            logger.info("Data not in cache");
            String url = "/reports?region_name=" + countryURL
                    + "&date=" + dateURL;
            String response = resolver.getResponse(url);

            ByParams byParams = resolver.convertJSONbyParamstoByParams(response, countryURL);
            covidRepository.save(byParams);
            return byParams;
        } else {
            cache.addHit();
            logger.info("Data retrieved from cache");
            return getPeloCountryDate;
        }
    }

    public List<String> getCountries() throws Exception {
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
