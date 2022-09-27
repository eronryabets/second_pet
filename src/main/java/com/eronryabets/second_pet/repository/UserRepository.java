package com.eronryabets.second_pet.repository;

import com.eronryabets.second_pet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllBySurname(String surname);
}
