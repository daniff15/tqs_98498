package com.example.demo.service;


import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;

import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.InterruptedException;

import com.example.demo.Controller.CovidController;

@Service
public class CovidService{

    private static final String baseURL =  "https://covid-19-statistics.p.rapidapi.com";

    //private static final Logger logger = Logger.getLogger(CovidService.class);

    public String getRegions() throws URISyntaxException, IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create(baseURL + "/regions"))
		.header("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com")
		.header("X-RapidAPI-Key", "fb7bb9a35emshc06230b446762ddp1ed435jsn883017bf1d34")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        return "null";
    }

}
