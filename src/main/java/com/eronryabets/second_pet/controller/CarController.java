package com.eronryabets.second_pet.controller;

import com.eronryabets.second_pet.entity.Car;
import com.eronryabets.second_pet.exceptions.UserNotFoundException;
import com.eronryabets.second_pet.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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

    @GetMapping("/car/edit/{carID}")
    public String editCar(@PathVariable Car carID,
                          @RequestParam(required = false, value = "message") String message,
                          Model model

    ){
        model.addAttribute("car",carID);
        model.addAttribute("message",message);
        return "car_edit";
    }
    //TODO
    //@PostMapping("/saveCar/{carPath}")
    @RequestMapping(value = "/car/edit/{carID}", method = RequestMethod.POST)
    public String saveCar(
            @Valid @ModelAttribute("car") Car car,
            BindingResult bindingResult,
            Model model,
            @RequestParam("carBrand") String carBrand,
            @RequestParam("carModel") String carModel,
            @RequestParam(required = false, defaultValue = "0", value = "year") int year,
            @RequestParam("carNumber") String carNumber,
            @RequestParam(required = false, value = "ownerId")  Long ownerId,

            @PathVariable Car carID
            //RedirectAttributes redirectAttributes

    ){
        if (bindingResult.hasErrors()) {
            System.out.println("ERROR");
            return "car_edit";
        }

        try{carService.saveCar(car,carBrand,carModel,year,carNumber, ownerId);}
        catch (UserNotFoundException e) {
            model.addAttribute("message",
                    "Owner id " + ownerId + " is not exists!");
            return "car_edit";
        }

        return "car_edit";
    }

    @PostMapping("/show_cars")
    public String addCar(
            @RequestParam("carBrand") String carBrand,
            @RequestParam("carModel") String carModel,
            @RequestParam("year") int year,
            @RequestParam("carNumber") String carNumber,
            @RequestParam(required = false, value = "ownerId") Long ownerId,

            RedirectAttributes redirectAttributes

    ){
        try {carService.addCar(carBrand,carModel,year,carNumber, ownerId);
        }catch (UserNotFoundException e) {
            redirectAttributes.addAttribute("message",
                    "Owner id " + ownerId + " is not exists!");
        }


        return "redirect:/show_cars";
    }

    @RequestMapping(value = "/car/delete/{car}",
            method={RequestMethod.DELETE, RequestMethod.GET})
    public String deleteCar(@PathVariable Car car){
        carService.deleteCar(car);
        return "redirect:/show_cars";
    }




}