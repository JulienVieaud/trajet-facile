package com.poe.trajetfacile.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.poe.trajetfacile.form.UserCreationForm;

@Controller
public class UserCreationController extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@GetMapping("/")
	public String showForm(UserCreationForm form) {
		return "signup";
	}

	@PostMapping("/")
	public String checkPersonInfo(@Valid UserCreationForm form, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "signup";
		}

		return "redirect:/results";
	}

}
