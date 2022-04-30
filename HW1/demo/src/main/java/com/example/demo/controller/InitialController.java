package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.demo.entities.ByParams;
import com.example.demo.entities.forms.DateCountryTemplate;
import com.example.demo.entities.forms.ForTemplate;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.service.CovidService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InitialController {
	private static final String COUNTRIES = "countries";  // Compliant
	private static final String INDEX = "index";  // Compliant
	private static final String INFECTED = "infected";  // Compliant
	private static final String RECOVERED = "recovered";  // Compliant
	private static final String DEATHS = "deaths";  // Compliant
	private static final String ACTIVE = "active";  // Compliant
	private static final String LASTUPDATED = "last_updated";  // Compliant
	private static final String SELECTED = "selected";  // Compliant
	private static final String HITS = "hits";  // Compliant
	private static final String MISSES = "misses";  // Compliant
	List<String> countrisList = new ArrayList<>();

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

	@GetMapping("/")
	public String getFirst(Model model) throws InterruptedException, IOException {
		countrisList = covidService.getCountries();

		model.addAttribute(COUNTRIES, countrisList);
		return INDEX;
	}

	@GetMapping("/index")
	public String getIndex(Model model) throws InterruptedException, IOException {
		return getFirst(model);
	}

	@PostMapping("/index")
	public String submitSearch(@ModelAttribute ForTemplate forTemplate, Model model)
			throws InterruptedException, BadRequestException, IOException {
		model.addAttribute(COUNTRIES, countrisList);

		ByParams byParams = covidService.getByCountry(forTemplate.getName());

		model.addAttribute(INFECTED, byParams.getConfirmed());
		model.addAttribute(RECOVERED, byParams.getRecovered());
		model.addAttribute(DEATHS, byParams.getDeaths());
		model.addAttribute(ACTIVE, byParams.getActive());
		model.addAttribute(LASTUPDATED, byParams.getLastUpdated());
		model.addAttribute(SELECTED, forTemplate.getName());
		return INDEX;
	}

	@GetMapping("/date")
	public String getDate() {
		return "date";
	}

	@PostMapping("/date")
	public String submitSearchDate(@ModelAttribute ForTemplate forTemplate, Model model)
			throws InterruptedException, BadRequestException, IOException {

		ByParams byParams = covidService.getByDate(forTemplate.getName());

		model.addAttribute(INFECTED, byParams.getConfirmed());
		model.addAttribute(RECOVERED, byParams.getRecovered());
		model.addAttribute(DEATHS, byParams.getDeaths());
		model.addAttribute(ACTIVE, byParams.getActive());
		model.addAttribute(LASTUPDATED, byParams.getLastUpdated());
		model.addAttribute(SELECTED, forTemplate.getName());
		return "date";
	}

	@GetMapping("/countrydate")
	public String getCountryAndDate(Model model) throws InterruptedException, IOException {
		countrisList = covidService.getCountries();
		model.addAttribute(COUNTRIES, countrisList);

		return "countrydate";
	}

	@PostMapping("/countrydate")
	public String submitSearchDateCountry(@ModelAttribute DateCountryTemplate forTemplate, Model model)
			throws InterruptedException, BadRequestException, IOException {

		model.addAttribute(COUNTRIES, countrisList);

		ByParams byParams = covidService.getByParams(forTemplate.getDate(), forTemplate.getCountryName());

		model.addAttribute(INFECTED,
				byParams.getConfirmed());
		model.addAttribute(RECOVERED,
				byParams.getRecovered());
		model.addAttribute(DEATHS,
				byParams.getDeaths());
		model.addAttribute(ACTIVE,
				byParams.getActive());
		model.addAttribute(LASTUPDATED,
				byParams.getLastUpdated());
		model.addAttribute(SELECTED, forTemplate.getCountryName());
		return "countrydate";
	}

	@GetMapping("/cache")
	public String getCache() {
		return "cache";
	}

	@PostMapping("/cache")
	public String submitCache(Model model){

		Map<String, Integer> cacheDetails = covidService.getCacheDetails();

		model.addAttribute(HITS, cacheDetails.get(HITS));
		model.addAttribute(MISSES, cacheDetails.get(MISSES));
		model.addAttribute("requests", cacheDetails.get(HITS) + cacheDetails.get(MISSES));
		return "cache";
	}

}