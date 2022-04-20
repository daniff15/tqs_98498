package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InitialController {

	@GetMapping("/index")
	public String getIndex() {
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
	@GetMapping("/region")
	public String getRegion() {
		return "region";
	}

}