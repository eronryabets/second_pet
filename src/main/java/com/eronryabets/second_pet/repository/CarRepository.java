package com.eronryabets.second_pet.repository;

import com.eronryabets.second_pet.entity.Car;
import com.eronryabets.second_pet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByUser(User user);
}
