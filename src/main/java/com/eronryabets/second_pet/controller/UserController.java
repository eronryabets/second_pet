package com.eronryabets.second_pet.controller;

import com.eronryabets.second_pet.entity.User;
import com.eronryabets.second_pet.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/show_users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());

        userService.findAll().forEach(user -> log.debug("User : {}", user.toString()));

        return "show_users";
    }

    @GetMapping("/user/edit/{user}")
    public String userEdit(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        return "user_edit";
    }

    @PostMapping("/userSave")
    public String saveUser(
            @RequestParam("surname") String surname,
            @RequestParam("name") String name,
            @RequestParam("userId") User user
    ){
        userService.saveUser(user,surname,name);
        return "redirect:/show_users";
    }

    @PostMapping("/userAdd")
    public String addUser(
            @RequestParam("surname") String surname,
            @RequestParam("name") String name
    ){
        userService.addUser(surname,name);
        return "redirect:/show_users";
    }

    @RequestMapping(value = "/user/delete/{user}",
            method={RequestMethod.DELETE, RequestMethod.GET})
    public String deleteUser(@PathVariable User user){
        userService.deleteUser(user);
        return "redirect:/show_users";
    }



}
