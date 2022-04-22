package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;
import java.lang.InterruptedException;
import java.io.IOException;

import com.example.demo.service.CovidService;
import com.example.demo.entities.ByParams;

@RestController
@RequestMapping("/api")
public class CovidController {

    @Autowired
    public CovidService covidService;

    @GetMapping("/date/{date}")
    public ByParams getByDate(@PathVariable(value = "date") String date, Model model)
            throws IOException, URISyntaxException, InterruptedException {
        ByParams byParams = covidService.getByDate(date);
        model.addAttribute("infected", byParams.getConfirmed());
        model.addAttribute("recovered", byParams.getRecovered());
        model.addAttribute("deaths", byParams.getDeaths());
        model.addAttribute("active", byParams.getActive());
        return byParams;
    }

    @GetMapping("/country/{country}")
    public ByParams getByCountry(@PathVariable(value = "country") String country)
            throws IOException, URISyntaxException, InterruptedException {
        return covidService.getByCountry(country);
    }

    @GetMapping("/{date}/{country}/{province}")
    public ByParams getByParams(@PathVariable(value = "date") String date,
            @PathVariable(value = "country") String country, @PathVariable(value = "province") String province)
            throws IOException, URISyntaxException, InterruptedException {
        return covidService.getByParams(date, country, province);
    }

}