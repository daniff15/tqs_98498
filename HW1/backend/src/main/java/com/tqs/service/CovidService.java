package com.tqs.service;

import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.InterruptedException;

import com.tqs.controller.CovidController;
import com.tqs.connection.HTTPClient;

@Service
public class CovidService{

    private static final String baseURL =  "https://covid-19-statistics.p.rapidapi.com";

    //private static final Logger logger = Logger.getLogger(CovidService.class);

    @Autowired
    private HTTPClient httpClient;

    public String getRegions() throws URISyntaxException, IOException, InterruptedException {

        URIBuilder builder = new URIBuilder(baseURL + "/regions");

        String apiResponse = httpClient.makeRequest(builder.build().toString());

        System.out.println(apiResponse);
        return "null";
    }

}
