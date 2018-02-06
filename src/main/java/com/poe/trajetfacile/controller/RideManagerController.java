package com.poe.trajetfacile.controller;

import com.poe.trajetfacile.domain.Ride;
import com.poe.trajetfacile.form.OfferARideForm;
import com.poe.trajetfacile.repository.RideRepository;
import com.poe.trajetfacile.service.RideService;
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

    @Autowired
    private RideRepository rideRepository;

    @GetMapping
    public String showForm(OfferARideForm form) {
        return "ride/create";
    }

    @PostMapping
    public String offerARide(@Valid OfferARideForm form, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "ride/create";
        }
//        Date convertedDateMinutePrecision = DateUtils.convert(form.getFullDate(), form.getStartHours(), form.getStartMinutes());
        Date convertedDateMinutePrecision = form.getFullDate();
        rideService.offerARide(convertedDateMinutePrecision, form.getFromCity(), form.getToCity(), form.getCost(), form.getSeats(), form.getUserId());
        model.addAttribute("message", "Votre trajet a bien été pris en compte.");
        return "ride/create";
    }

    @GetMapping("list")
    public String list(Model model) {
        Iterable<Ride> rides = rideRepository.findAll();

        model.addAttribute("rides", rides);
        return "ride/list";
    }
}
