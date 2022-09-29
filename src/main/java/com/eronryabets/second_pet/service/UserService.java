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


    public void userSave(User user, String surname, String name) {
        user.setSurname(surname);
        user.setName(name);
        userRepository.save(user);
    }

    public void userAdd(String surname, String name) {
        User user = new User();
        user.setSurname(surname);
        user.setName(name);
        userRepository.save(user);
    }

    public void userDelete(User user) {
        carDetach(user);
        userRepository.delete(user);
    }

    public void carDetach(User user){
        List<Car> carList = user.getCarList();
        carList.forEach(car -> {
            car.setUser(null);
            carRepository.save(car);
        });
        user.setCarList(null);
    }
}
