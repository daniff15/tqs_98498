package com.example.demo.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import java.net.URISyntaxException;
import java.lang.InterruptedException;

import com.example.demo.cache.Cache;
import com.example.demo.entities.ByParams;
import com.example.demo.repository.CovidRepository;

@Service
@Transactional
public class CovidService {

    @Autowired
    Cache cache;

    @Autowired
    CovidRepository covidRepository;

    private static final String baseURL = "https://covid-19-statistics.p.rapidapi.com";

    private static final Logger logger = LoggerFactory.getLogger(CovidService.class);

    public ByParams getByDate(String dateURL) throws URISyntaxException, IOException, InterruptedException {
        logger.info("Getting Data for date {} in the cache", dateURL);

        if (cache.getByDate(dateURL) == null) {
            logger.info("Data not in cache");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseURL + "/reports/total?date=" + dateURL))
                    .header("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "fb7bb9a35emshc06230b446762ddp1ed435jsn883017bf1d34")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            JSONObject jo = new JSONObject(response.body());
            ByParams byParams = convertJSONbyDatetoByParams(jo);
            covidRepository.save(byParams);
            return byParams;
        } else {
            logger.info("Data retrieved from cache");
            return cache.getByDate(dateURL);
        }
    }

    public ByParams getByCountry(String country) throws URISyntaxException, IOException, InterruptedException {

        if (cache.getByCountry(country) == null) {
            logger.info("Data not in cache");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseURL + "/reports?region_name=" + country))
                    .header("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "fb7bb9a35emshc06230b446762ddp1ed435jsn883017bf1d34")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            JSONObject jo = new JSONObject(response.body());
            ByParams byParams = convertJSONbyCountrytoByParams(jo, country);
            covidRepository.save(byParams);
            return byParams;

        } else {
            logger.info("Data retrieved from cache");
            return cache.getByCountry(country);
        }
    }

    public ByParams getByParams(String dateURL, String countryURL)
            throws URISyntaxException, IOException, InterruptedException {

        if (cache.getByParametros(dateURL, countryURL) == null) {
            logger.info("Data not in cache");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(
                            "https://covid-19-statistics.p.rapidapi.com/reports?region_name=" + countryURL
                                    + "&date=" + dateURL))
                    .header("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "fb7bb9a35emshc06230b446762ddp1ed435jsn883017bf1d34")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());
            JSONObject jo = new JSONObject(response.body());

            ByParams byParams = convertJSONbyParamstoByParams(jo, countryURL);
            covidRepository.save(byParams);
            return byParams;
        } else {
            logger.info("Data retrieved from cache");
            return cache.getByParametros(dateURL, countryURL);
        }
    }

    public List<String> getCountries() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://covid-19-statistics.p.rapidapi.com/regions"))
                .header("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com")
                .header("X-RapidAPI-Key", "fb7bb9a35emshc06230b446762ddp1ed435jsn883017bf1d34")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jo = new JSONObject(response.body());

        List<String> countries = convertToCountries(jo);

        return countries;
    }

    public List<String> convertToCountries(JSONObject jo) {
        List<String> paises = new ArrayList<>();

        for (int i = 0; i < jo.getJSONArray("data").length(); i++) {
            if (!jo.getJSONArray("data").getJSONObject(i).getString("name").contains(" ")) {
                paises.add(jo.getJSONArray("data").getJSONObject(i).getString("name"));
            }
        }

        return paises;

    }

    public ByParams convertJSONbyCountrytoByParams(JSONObject jo, String country) {
        String date = jo.getJSONArray("data").getJSONObject(0).getString("date");
        String last_updated = jo.getJSONArray("data").getJSONObject(0).getString("last_update");
        int confirmed_diff = 0;
        int active_diff = 0;
        int deaths_diff = 0;
        int recovered = 0;
        int recovered_diff = 0;
        Double fatality_rate = 0.0;
        int active = 0;
        int confirmed = 0;
        int deaths = 0;

        for (int i = 0; i < jo.getJSONArray("data").length(); i++) {
            confirmed_diff += jo.getJSONArray("data").getJSONObject(i).getInt("confirmed_diff");
            active_diff += jo.getJSONArray("data").getJSONObject(i).getInt("active_diff");
            deaths_diff += jo.getJSONArray("data").getJSONObject(i).getInt("deaths_diff");
            recovered += jo.getJSONArray("data").getJSONObject(i).getInt("recovered");
            recovered_diff += jo.getJSONArray("data").getJSONObject(i).getInt("recovered_diff");
            fatality_rate += jo.getJSONArray("data").getJSONObject(i).getDouble("fatality_rate");
            active += jo.getJSONArray("data").getJSONObject(i).getInt("active");
            confirmed += jo.getJSONArray("data").getJSONObject(i).getInt("confirmed");
            deaths += jo.getJSONArray("data").getJSONObject(i).getInt("deaths");
        }

        fatality_rate = fatality_rate / jo.getJSONArray("data").length();

        return new ByParams(date, last_updated, confirmed, confirmed_diff, deaths, deaths_diff, recovered,
                recovered_diff, active, active_diff, fatality_rate, country);
    }

    public ByParams convertJSONbyDatetoByParams(JSONObject jo) {
        String date = jo.getJSONObject("data").getString("date");
        int confirmed_diff = jo.getJSONObject("data").getInt("confirmed_diff");
        int active_diff = jo.getJSONObject("data").getInt("active_diff");
        int deaths_diff = jo.getJSONObject("data").getInt("deaths_diff");
        int recovered = jo.getJSONObject("data").getInt("recovered");
        int recovered_diff = jo.getJSONObject("data").getInt("recovered_diff");
        Double fatality_rate = jo.getJSONObject("data").getDouble("fatality_rate");
        String last_updated = jo.getJSONObject("data").getString("last_update");
        int active = jo.getJSONObject("data").getInt("active");
        int confirmed = jo.getJSONObject("data").getInt("confirmed");
        int deaths = jo.getJSONObject("data").getInt("deaths");

        return new ByParams(date, last_updated, confirmed, confirmed_diff, deaths, deaths_diff, recovered,
                recovered_diff, active, active_diff, fatality_rate, "all");
    }

    public ByParams convertJSONbyParamstoByParams(JSONObject jo, String country) {
        String date = jo.getJSONArray("data").getJSONObject(0).getString("date");
        int confirmed_diff = jo.getJSONArray("data").getJSONObject(0).getInt("confirmed_diff");
        int active_diff = jo.getJSONArray("data").getJSONObject(0).getInt("active_diff");
        int deaths_diff = jo.getJSONArray("data").getJSONObject(0).getInt("deaths_diff");
        int recovered = jo.getJSONArray("data").getJSONObject(0).getInt("recovered");
        int recovered_diff = jo.getJSONArray("data").getJSONObject(0).getInt("recovered_diff");
        Double fatality_rate = jo.getJSONArray("data").getJSONObject(0).getDouble("fatality_rate");
        String last_updated = jo.getJSONArray("data").getJSONObject(0).getString("last_update");
        int active = jo.getJSONArray("data").getJSONObject(0).getInt("active");
        int confirmed = jo.getJSONArray("data").getJSONObject(0).getInt("confirmed");
        int deaths = jo.getJSONArray("data").getJSONObject(0).getInt("deaths");

        return new ByParams(date, last_updated, confirmed, confirmed_diff, deaths, deaths_diff, recovered,
                recovered_diff, active, active_diff, fatality_rate, country);
    }

    public ByParams save(ByParams any) {
        return covidRepository.save(any);
    }
}
