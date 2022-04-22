package com.example.demo.Controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entities.ByParams;
import com.example.demo.entities.ForTemplate;
import com.example.demo.service.CovidService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InitialController {

	@Autowired
	private CovidService covidService;

	@ModelAttribute("filterForm")
	public ForTemplate getSelectedCountryData() {
		return new ForTemplate();
	}

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

	@PostMapping("/index")
	public String submitSearch(@ModelAttribute ForTemplate forTemplate, Model model) {
		try {
			model.addAttribute("infected", covidService.getByCountry(forTemplate.getName()).getConfirmed());
			model.addAttribute("recovered", covidService.getByCountry(forTemplate.getName()).getRecovered());
			model.addAttribute("deaths", covidService.getByCountry(forTemplate.getName()).getDeaths());
			model.addAttribute("active", covidService.getByCountry(forTemplate.getName()).getActive());
			model.addAttribute("last_updated", covidService.getByCountry(forTemplate.getName()).getLast_updated());
			model.addAttribute("selected", forTemplate.getName());
		} catch (IOException | URISyntaxException | InterruptedException e) {
			e.printStackTrace();
		}
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