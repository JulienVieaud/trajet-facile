package com.poe.trajetfacile.controller;

import com.poe.trajetfacile.domain.User;
import com.poe.trajetfacile.form.UserCreationForm;
import com.poe.trajetfacile.repository.UserRepository;
import com.poe.trajetfacile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserCreationController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String showForm(UserCreationForm form) {
        return "signup";
    }

    @PostMapping("/")
    public String save(@Valid UserCreationForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "signup";
        }
        User user = new User();
        user.setLogin(form.getLogin());
        user.setPassword(form.getPassword());
        userService.signup(user);

        redirectAttributes.addAttribute("userId", user.getId());
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(@ModelAttribute("userId") Long userId, Model model) {
        User user = userRepository.findOne(userId);
        model.addAttribute("user", user);
        return "home";
    }

}
