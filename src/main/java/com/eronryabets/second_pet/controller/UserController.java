package com.eronryabets.second_pet.controller;

import com.eronryabets.second_pet.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/show_users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "show_users";
    }

}
