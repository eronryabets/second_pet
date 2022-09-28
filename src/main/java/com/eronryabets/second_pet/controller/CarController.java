package com.eronryabets.second_pet.controller;

import com.eronryabets.second_pet.entity.Car;
import com.eronryabets.second_pet.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String carEdit(@PathVariable Car car,
                          @RequestParam(required = false, value = "message") String message,
                          Model model

    ){
        model.addAttribute("car",car);
        model.addAttribute("message",message);
        return "car_edit";
    }

    @PostMapping("/carSave/{carPath}")
    public String carSave(
            @RequestParam("carBrand") String carBrand,
            @RequestParam("carModel") String carModel,
            @RequestParam(required = false, defaultValue = "0", value = "year") int year,
            @RequestParam("carNumber") String carNumber,
            @RequestParam("ownerId") Long userId,
            @RequestParam("carId") Car car,

            @PathVariable Car carPath,
            RedirectAttributes redirectAttributes

    ){
        if(!carService.carSave(car,carBrand,carModel,year,carNumber, userId)){
            redirectAttributes.addAttribute("message", "Owner id " + userId + " is not exists!");
            return "redirect:/car/edit/{carPath}";
        }

        return "redirect:/car/edit/{carPath}";
    }




}
