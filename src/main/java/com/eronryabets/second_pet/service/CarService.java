package com.eronryabets.second_pet.service;

import com.eronryabets.second_pet.entity.Car;
import com.eronryabets.second_pet.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll(){
        return carRepository.findAll();
    }

    public void carSave(Car car, String carBrand, String model, int year, String carNumber) {
        car.setCarBrand(carBrand);
        car.setModel(model);
        if(year != 0){
            car.setYear(year);
        }
        car.setCarNumber(carNumber);
        carRepository.save(car);
    }
}
