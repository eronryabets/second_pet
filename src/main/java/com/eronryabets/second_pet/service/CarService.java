package com.eronryabets.second_pet.service;

import com.eronryabets.second_pet.entity.Car;
import com.eronryabets.second_pet.entity.User;
import com.eronryabets.second_pet.exceptions.UserNotFoundException;
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

    //TODO
    public void saveCar(Car car, String carBrand, String model, int year, String carNumber, Long ownerId) {
        Optional<User> userFromDb = ownerId == null
                ? Optional.empty()
                : userRepository.findById(ownerId);
        User user = userFromDb.orElseThrow(() -> new UserNotFoundException("User not found"));

        car.setCarBrand(carBrand);
        car.setModel(model);
        if(year != 0){
            car.setYear(year);
        }
        car.setCarNumber(carNumber);
        car.setUser(user);
        carRepository.save(car);


    }

    public void addCar(String carBrand, String carModel, int year, String carNumber, Long ownerId) {

        Optional<User> userFromDb = ownerId == null
                ? Optional.empty()
                : userRepository.findById(ownerId);
        User user = userFromDb.orElseThrow(() -> new UserNotFoundException("User not found"));

        Car car = Car.builder()
                .carBrand(carBrand)
                .model(carModel)
                .year(year)
                .carNumber(carNumber)
                .user(user)
                .build();

        carRepository.save(car);

    }

    public void deleteCar(Car car) {
        carRepository.delete(car);
    }
}

