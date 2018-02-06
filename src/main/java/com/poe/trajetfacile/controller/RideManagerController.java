package com.poe.trajetfacile.controller;

import com.poe.trajetfacile.form.OfferARideForm;
import com.poe.trajetfacile.service.RideService;
import com.poe.trajetfacile.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/ride")
public class RideManagerController {

    @Autowired
    private RideService rideService;

    @GetMapping
    public String showForm(OfferARideForm form) {
        return "offerARide";
    }

    @PostMapping
    public String offerARide(@Valid OfferARideForm form, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "offerARide";
        }
        Date convertedDateMinutePrecision = DateUtils.convert(form.getStartDate(), form.getStartHours(), form.getStartMinutes());
        rideService.offerARide(convertedDateMinutePrecision, form.getFromCity(), form.getToCity(), form.getCost(), form.getSeats(), form.getUserId());
        model.addAttribute("message", "Votre trajet a bien été pris en compte.");
        return "offerARide";
    }

}
