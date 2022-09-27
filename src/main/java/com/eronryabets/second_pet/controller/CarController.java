package com.eronryabets.second_pet.controller;

import com.eronryabets.second_pet.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/show_cars")
    public String cars(Model model){
        model.addAttribute("cars", carService.findAll());
        return "show_cars";
    }



}
