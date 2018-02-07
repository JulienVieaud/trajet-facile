package com.poe.trajetfacile.controller;

import com.poe.trajetfacile.domain.Ride;
import com.poe.trajetfacile.domain.User;
import com.poe.trajetfacile.form.OfferARideForm;
import com.poe.trajetfacile.repository.RideRepository;
import com.poe.trajetfacile.repository.UserRepository;
import com.poe.trajetfacile.service.RideService;
import com.poe.trajetfacile.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/ride")
public class RideManagerController {

    @Autowired
    private RideService rideService;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String showForm(User user, OfferARideForm form, @RequestParam(name = "ride", required = false) String rideId, Model model) {
        if (rideId != null && !rideId.isEmpty()) {
            Ride ride = rideRepository.findOne(Long.valueOf(rideId));
            model.addAttribute("ride", ride);
        }
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "ride/create";
    }

    @PostMapping
    public String offerARide(@Valid OfferARideForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "ride/create";
        }
        Date convertedDateMinutePrecision = DateUtils.convert(form.getStartDate(), form.getStartHours(), form.getStartMinutes());
        Ride ride = rideService.offerARide(convertedDateMinutePrecision, form.getFromCity(), form.getToCity(), form.getCost(), form.getSeats(), form.getUserId());
        redirectAttributes.addAttribute("ride", ride.getId());
        return "redirect:/ride";
    }

    @GetMapping("list")
    public String list(Model model) {
        Iterable<Ride> rides = rideRepository.findAll();

        model.addAttribute("rides", rides);
        return "ride/list";
    }
}
