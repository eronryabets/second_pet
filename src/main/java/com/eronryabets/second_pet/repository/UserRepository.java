package com.eronryabets.second_pet.repository;

import com.eronryabets.second_pet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllBySurname(String surname);

    @Query("SELECT  MAX(id) FROM User ")
    Optional<Long> getCurrentLastUserId();

}
