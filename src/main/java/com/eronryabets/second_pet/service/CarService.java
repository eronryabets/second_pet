package com.eronryabets.second_pet.service;

import com.eronryabets.second_pet.entity.Car;
import com.eronryabets.second_pet.entity.User;
import com.eronryabets.second_pet.repository.CarRepository;
import com.eronryabets.second_pet.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public CarService(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public List<Car> findAll(){
        return carRepository.findAll();
    }

    public boolean carSave(Car car, String carBrand, String model, int year, String carNumber, Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        if(!userFromDb.isPresent()){
            return false;
        }
        car.setCarBrand(carBrand);
        car.setModel(model);
        if(year != 0){
            car.setYear(year);
        }
        car.setCarNumber(carNumber);
        car.setUser(userFromDb.get());
        carRepository.save(car);
        return true;


    }

    public boolean carAdd(String carBrand, String carModel, int year, String carNumber, Long ownerId) {
        Optional<User> userFromDb = userRepository.findById(ownerId);
        if(!userFromDb.isPresent()){
            return false;
        }
        Car car = new Car();
        car.setCarBrand(carBrand);
        car.setModel(carModel);
        car.setYear(year);
        car.setCarNumber(carNumber);
        car.setUser(userFromDb.get());

        car.setId(carRepository.getMaxCarId()+1);
        carRepository.save(car);

        return true;
    }
}
