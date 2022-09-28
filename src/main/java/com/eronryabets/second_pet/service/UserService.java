package com.eronryabets.second_pet.service;

import com.eronryabets.second_pet.entity.User;
import com.eronryabets.second_pet.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
