package com.eronryabets.second_pet;

import com.eronryabets.second_pet.entity.User;
import com.eronryabets.second_pet.repository.UserRepository;
import com.eronryabets.second_pet.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@DataJpaTest
public class UserTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setName("Ivan");
        user.setSurname("Tester");
        userRepository.save(user);
    }


}
