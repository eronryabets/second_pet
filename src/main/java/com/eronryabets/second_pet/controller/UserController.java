package com.eronryabets.second_pet.controller;

import com.eronryabets.second_pet.entity.User;
import com.eronryabets.second_pet.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/edit/{user}")
    public String userEdit(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        return "user_edit";
    }

    @PostMapping("/userSave")
    public String userSave(
            @RequestParam("surname") String surname,
            @RequestParam("name") String name,
            @RequestParam("userId") User user
    ){
        userService.userSave(user,surname,name);
        return "redirect:/show_users";
    }

    @PostMapping("/userAdd")
    public String userAdd(
            @RequestParam("surname") String surname,
            @RequestParam("name") String name
    ){
        userService.userAdd(surname,name);
        return "redirect:/show_users";
    }

    @RequestMapping(value = "/user/delete/{user}",
            method={RequestMethod.DELETE, RequestMethod.GET})
    public String userDelete(@PathVariable User user){
        userService.userDelete(user);
        return "redirect:/show_users";
    }



}
