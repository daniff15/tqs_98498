package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;
import java.lang.InterruptedException;
import java.io.IOException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import com.example.demo.service.CovidService;

@RestController
@RequestMapping("/api")
public class CovidController {

    @Autowired
    public CovidService covidService;

    @GetMapping("/regions")
    public String getRegions() throws IOException, URISyntaxException, InterruptedException {
        return covidService.getRegions();
    }

}