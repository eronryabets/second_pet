package com.eronryabets.second_pet.service;

import com.eronryabets.second_pet.entity.Car;
import com.eronryabets.second_pet.entity.User;
import com.eronryabets.second_pet.repository.CarRepository;
import com.eronryabets.second_pet.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public UserService(UserRepository userRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }


    public void saveUser(User user, String surname, String name) {
        user.setSurname(surname);
        user.setName(name);
        userRepository.save(user);
    }

    public void addUser(String surname, String name) {

        User user = User.builder()
                .surname(surname)
                .name(name)
                .build();

        userRepository.save(user);
    }

    public void deleteUser(User user) {
        detachCar(user);
        userRepository.delete(user);
    }

    public void detachCar(User user){
        List<Car> carList = user.getCarList();
        carList.forEach(car -> {
            car.setUser(null);
            carRepository.save(car);
        });
        user.setCarList(null);
    }
}
