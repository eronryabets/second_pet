package com.eronryabets.second_pet.controller;

import com.eronryabets.second_pet.entity.Car;
import com.eronryabets.second_pet.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/show_cars")
    public String cars(Model model,
                       @RequestParam(required = false, value = "message") String message
    ){
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("message",message);

        carService.findAll().forEach(car -> log.debug("Car : {}", car.toString()));

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

    @PostMapping("/show_cars")
    public String carAdd(
            @RequestParam("carBrand") String carBrand,
            @RequestParam("carModel") String carModel,
            @RequestParam("year") int year,
            @RequestParam("carNumber") String carNumber,
            @RequestParam(required = false, defaultValue = "0", value = "ownerId") Long ownerId,

            RedirectAttributes redirectAttributes

    ){
        if(!carService.carAdd(carBrand,carModel,year,carNumber, ownerId)){
            redirectAttributes.addAttribute("message", "Owner id " + ownerId + " is not exists!");
            return "redirect:/show_cars";
        }

        return "redirect:/show_cars";
    }

    @RequestMapping(value = "/car/delete/{car}",
            method={RequestMethod.DELETE, RequestMethod.GET})
    public String carDelete(@PathVariable Car car){
        carService.carDelete(car);
        return "redirect:/show_cars";
    }




}
