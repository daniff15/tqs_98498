package com.example.demo.Controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.service.CovidService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitialController {

	@Autowired
	private CovidService covidService;

	@GetMapping("/index")
	public String getIndex(Model model) {
		List<String> countries = new ArrayList<>();

		try {
			countries = covidService.getCountries();
		} catch (IOException | URISyntaxException | InterruptedException e) {
			e.printStackTrace();
		}

		model.addAttribute("countries", countries);
		return "index";
	}

	@GetMapping("/date")
	public String getDate() {
		return "date";
	}

	@GetMapping("/province")
	public String getProvince() {
		return "province";
	}

	@GetMapping("/cache")
	public String getCache() {
		return "cache";
	}

}