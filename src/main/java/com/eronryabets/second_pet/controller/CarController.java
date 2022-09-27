package com.eronryabets.second_pet.controller;

import com.eronryabets.second_pet.entity.Car;
import com.eronryabets.second_pet.entity.User;
import com.eronryabets.second_pet.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/car/edit/{car}")
    public String carEdit(@PathVariable Car car, Model model){
        model.addAttribute("car",car);
        return "car_edit";
    }

    @PostMapping("/carSave")
    public String carSave(
            @RequestParam("carBrand") String carBrand,
            @RequestParam("model") String model,
            @RequestParam(required = false, defaultValue = "0", value = "year") int year,
            @RequestParam("carNumber") String carNumber,
            @RequestParam("carId") Car car
    ){
        carService.carSave(car,carBrand,model,year,carNumber);
        return "redirect:/show_cars";
    }



}
