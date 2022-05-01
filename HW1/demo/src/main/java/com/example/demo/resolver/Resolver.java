package com.example.demo.resolver;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entities.ByParams;
import com.example.demo.exceptions.BadRequestException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Resolver {
    private static final Logger logger = LoggerFactory.getLogger(Resolver.class);
    private static final String CONFIRMEDDIFF = "confirmed_diff";
    private static final String ACTIVEDIFF = "active_diff";
    private static final String DEATHSDIFF = "deaths_diff";
    private static final String RECOVERED = "recovered";
    private static final String RECOVEREDDIFF = "recovered_diff";
    private static final String FATALITYRATE = "fatality_rate";
    private static final String LASTUPDATED = "last_update";
    private static final String ACTIVE = "active";
    private static final String CONFIRMED = "confirmed";
    private static final String DEATHS = "deaths";

    public String getResponse(String url) throws InterruptedException, IOException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://covid-19-statistics.p.rapidapi.com" + url))
                    .header("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "fb7bb9a35emshc06230b446762ddp1ed435jsn883017bf1d34")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (InterruptedException e) {
            logger.error("Bad URL given");
            Thread.currentThread().interrupt();
            throw new InterruptedException();
        }

    }

    public ByParams convertJSONbyDatetoByParams(String response) throws BadRequestException {
        JSONObject jo = new JSONObject(response);
        if (jo.has("data")) {
            Object item = jo.get("data");
            String itemToString = item.toString();
            // Se isto acontece é pq se procurou por uma data mais avançada
            if (itemToString.equals("[]")) {
                logger.error("Searched for an advanced Date. Invalid!!!");
                throw new BadRequestException("There is no data for that Date!");
            } else {
                String date = jo.getJSONObject("data").getString("date");
                int confirmedDiff = jo.getJSONObject("data").getInt(CONFIRMEDDIFF);
                int activeDiff = jo.getJSONObject("data").getInt(ACTIVEDIFF);
                int deathsDiff = jo.getJSONObject("data").getInt(DEATHSDIFF);
                int recovered = jo.getJSONObject("data").getInt(RECOVERED);
                int recoveredDiff = jo.getJSONObject("data").getInt(RECOVEREDDIFF);
                Double fatalityRate = jo.getJSONObject("data").getDouble(FATALITYRATE);
                String lastUpdated = jo.getJSONObject("data").getString(LASTUPDATED);
                int active = jo.getJSONObject("data").getInt(ACTIVE);
                int confirmed = jo.getJSONObject("data").getInt(CONFIRMED);
                int deaths = jo.getJSONObject("data").getInt(DEATHS);

                return new ByParams(date, lastUpdated, confirmed, confirmedDiff, deaths, deathsDiff, recovered,
                        recoveredDiff, active, activeDiff, fatalityRate, "all");
            }
        } else {
            String message = jo.getJSONObject("error").getJSONArray("date").getString(0);
            logger.error("Invalid format given!");
            throw new BadRequestException(message);
        }

    }

    public ByParams convertJSONbyCountrytoByParams(String response, String country) throws BadRequestException {
        JSONObject jo = new JSONObject(response);
        JSONArray item = (JSONArray) jo.get("data");
        int confirmedDiff = 0;
        int activeDiff = 0;
        int deathsDiff = 0;
        int recovered = 0;
        int recoveredDiff = 0;
        Double fatalityRate= 0.0;
        int active = 0;
        int confirmed = 0;
        int deaths = 0;

        if (item.length() != 0) {
            String date = jo.getJSONArray("data").getJSONObject(0).getString("date");
            String lastUpdated = jo.getJSONArray("data").getJSONObject(0).getString(LASTUPDATED);
            for (int i = 0; i < jo.getJSONArray("data").length() ; i++) {
                confirmedDiff += jo.getJSONArray("data").getJSONObject(i).getInt(CONFIRMEDDIFF);
                activeDiff += jo.getJSONArray("data").getJSONObject(i).getInt(ACTIVEDIFF);
                deathsDiff += jo.getJSONArray("data").getJSONObject(i).getInt(DEATHSDIFF);
                recovered += jo.getJSONArray("data").getJSONObject(i).getInt(RECOVERED);
                recoveredDiff += jo.getJSONArray("data").getJSONObject(i).getInt(RECOVEREDDIFF);
                fatalityRate += jo.getJSONArray("data").getJSONObject(i).getDouble(FATALITYRATE);
                active += jo.getJSONArray("data").getJSONObject(i).getInt(ACTIVE);
                confirmed += jo.getJSONArray("data").getJSONObject(i).getInt(CONFIRMED);
                deaths += jo.getJSONArray("data").getJSONObject(i).getInt(DEATHS);
            }

            fatalityRate = fatalityRate/jo.getJSONArray("data").length();

            return new ByParams(date, lastUpdated, confirmed, confirmedDiff, deaths, deathsDiff, recovered,
                    recoveredDiff, active, activeDiff, fatalityRate, country);
        } else {
            logger.error("Invalid Country!");
            throw new BadRequestException("Searched for an invalid Country!");
        }

    }

    public ByParams convertJSONbyParamstoByParams(String response, String country) throws BadRequestException {
        JSONObject jo = new JSONObject(response);

        if (jo.has("data")) {
            Object item = jo.get("data");
            String itemToString = item.toString();
            if (itemToString.contains("date")) {
                String date = jo.getJSONArray("data").getJSONObject(0).getString("date");
                int confirmedDiff = jo.getJSONArray("data").getJSONObject(0).getInt(CONFIRMEDDIFF);
                int activeDiff = jo.getJSONArray("data").getJSONObject(0).getInt(ACTIVEDIFF);
                int deathsDiff = jo.getJSONArray("data").getJSONObject(0).getInt(DEATHSDIFF);
                int recovered = jo.getJSONArray("data").getJSONObject(0).getInt(RECOVERED);
                int recoveredDiff = jo.getJSONArray("data").getJSONObject(0).getInt(RECOVEREDDIFF);
                Double fatalityRate = jo.getJSONArray("data").getJSONObject(0).getDouble(FATALITYRATE);
                String lastUpdated = jo.getJSONArray("data").getJSONObject(0).getString(LASTUPDATED);
                int active = jo.getJSONArray("data").getJSONObject(0).getInt(ACTIVE);
                int confirmed = jo.getJSONArray("data").getJSONObject(0).getInt(CONFIRMED);
                int deaths = jo.getJSONArray("data").getJSONObject(0).getInt(DEATHS);

                return new ByParams(date, lastUpdated, confirmed, confirmedDiff, deaths, deathsDiff, recovered,
                        recoveredDiff, active, activeDiff, fatalityRate, country);
            } else if (itemToString.equals("[]")) {
                logger.error("Searched for an advanced Date. Invalid!!!");
                throw new BadRequestException("There is no data for that Date!");
            } else {
                logger.error("Invalid Parameters given!");
                throw new BadRequestException("Searched for an invalid Country or for an advanced Date!");
            }
        } else {
            String message = jo.getJSONObject("error").getJSONArray("date").getString(0);
            logger.error("Invalid format given!");
            throw new BadRequestException(message);
        }

    }

    public List<String> convertToCountries(String response) {
        JSONObject jo = new JSONObject(response);

        List<String> paises = new ArrayList<>();

        for (int i = 0; i < jo.getJSONArray("data").length(); i++) {
            if (!jo.getJSONArray("data").getJSONObject(i).getString("name").contains(" ")) {
                paises.add(jo.getJSONArray("data").getJSONObject(i).getString("name"));
            }
        }

        return paises;

    }
}
