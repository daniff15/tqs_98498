package com.example.demo.Controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entities.forms.DateCountryTemplate;
import com.example.demo.entities.forms.ForTemplate;
import com.example.demo.service.CovidService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InitialController {

	List<String> countries = new ArrayList<>();

	@Autowired
	private CovidService covidService;

	@ModelAttribute("getCountryForm")
	public ForTemplate getSelectedCountryData() {
		return new ForTemplate();
	}

	@ModelAttribute("getDateForm")
	public ForTemplate getSelectedDateData() {
		return new ForTemplate();
	}

	@ModelAttribute("getDateCountryForm")
	public DateCountryTemplate getSelectedDateCountryData() {
		return new DateCountryTemplate();
	}

	@GetMapping("/index")
	public String getIndex(Model model) {
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
		model.addAttribute("countries", countries);
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

	@PostMapping("/date")
	public String submitSearchDate(@ModelAttribute ForTemplate forTemplate, Model model) {

		try {
			model.addAttribute("infected", covidService.getByDate(forTemplate.getName()).getConfirmed());
			model.addAttribute("recovered", covidService.getByDate(forTemplate.getName()).getRecovered());
			model.addAttribute("deaths", covidService.getByDate(forTemplate.getName()).getDeaths());
			model.addAttribute("active", covidService.getByDate(forTemplate.getName()).getActive());
			model.addAttribute("last_updated", covidService.getByDate(forTemplate.getName()).getLast_updated());
			model.addAttribute("selected", forTemplate.getName());
		} catch (IOException | URISyntaxException | InterruptedException e) {
			e.printStackTrace();
		}
		return "date";
	}

	@PostMapping("/countrydate")
	public String submitSearchDateCountry(@ModelAttribute DateCountryTemplate forTemplate, Model model) {

		model.addAttribute("countries", countries);

		try {
			model.addAttribute("infected",
					covidService.getByParams(forTemplate.getCountryName(), forTemplate.getDate()).getConfirmed());
			model.addAttribute("recovered",
					covidService.getByParams(forTemplate.getCountryName(), forTemplate.getDate()).getRecovered());
			model.addAttribute("deaths",
					covidService.getByParams(forTemplate.getCountryName(), forTemplate.getDate()).getDeaths());
			model.addAttribute("active",
					covidService.getByParams(forTemplate.getCountryName(), forTemplate.getDate()).getActive());
			model.addAttribute("last_updated",
					covidService.getByParams(forTemplate.getCountryName(), forTemplate.getDate()).getLast_updated());
			model.addAttribute("selected", forTemplate.getCountryName());
		} catch (IOException | URISyntaxException | InterruptedException e) {
			e.printStackTrace();
		}
		return "countrydate";
	}

	@GetMapping("/date")
	public String getDate() {
		return "date";
	}

	@GetMapping("/countrydate")
	public String getCountryAndDate(Model model) {
		try {
			countries = covidService.getCountries();
		} catch (IOException | URISyntaxException | InterruptedException e) {
			e.printStackTrace();
		}
		model.addAttribute("countries", countries);

		return "countrydate";
	}

	@GetMapping("/cache")
	public String getCache() {
		return "cache";
	}

}