package com.example.demo.resolver;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entities.ByParams;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class Resolver {

    public String getResponse(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://covid-19-statistics.p.rapidapi.com" + url))
                .header("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com")
                .header("X-RapidAPI-Key", "fb7bb9a35emshc06230b446762ddp1ed435jsn883017bf1d34")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public ByParams convertJSONbyDatetoByParams(String response) {
        JSONObject jo = new JSONObject(response);

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

    public ByParams convertJSONbyCountrytoByParams(String response, String country) {
        JSONObject jo = new JSONObject(response);

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

    public ByParams convertJSONbyParamstoByParams(String response, String country) {
        JSONObject jo = new JSONObject(response);

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
