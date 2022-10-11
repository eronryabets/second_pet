package com.eronryabets.second_pet.controller;

import com.eronryabets.second_pet.entity.User;
import com.eronryabets.second_pet.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/user/edit/{userID}")
    public String userEdit(@PathVariable User userID, Model model){
        model.addAttribute("user",userID);
        return "user_edit";
    }

    @RequestMapping(value = "/user/edit/{userID}", method = RequestMethod.POST)
    public String saveUser(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult,
            @RequestParam("surname") String surname,
            @RequestParam("name") String name,
            @PathVariable User userID
    ){

        if(bindingResult.hasErrors()){
            System.out.println("ERROR");
            return "user_edit";
        }

        userService.saveUser(user,surname,name);
        return "user_edit";
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