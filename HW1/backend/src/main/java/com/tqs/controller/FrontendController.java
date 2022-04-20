package com.tqs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import java.net.URISyntaxException;
import java.lang.InterruptedException;
import java.io.IOException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.stereotype.Controller;

import com.tqs.service.CovidService;

@Controller
public class FrontendController {

    @GetMapping("date")
	public String date(Model model) {
        return "date";
	}

}