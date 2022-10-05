package com.eronryabets.second_pet.service;

import com.eronryabets.second_pet.entity.Car;
import com.eronryabets.second_pet.entity.User;
import com.eronryabets.second_pet.repository.CarRepository;
import com.eronryabets.second_pet.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@AutoConfigureTestEntityManager
@TestPropertySource("/application-test.properties")
@Transactional
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CarRepository carRepository;

    private static Car car1;
    private static User user1;

    @BeforeAll
    public static void globalSetUpStart() {
        System.out.println("Start Test...");

        user1 = User.builder()
                .name("Tester")
                .surname("First")
                .build();

        car1 = Car.builder()
                .carBrand("Honda")
                .model("Test 8D")
                .year(2020)
                .carNumber("TT 4444 TT")
                .user(user1)
                .build();
    }

    @AfterAll
    public static void globalSetUpEnd() {
        System.out.println("End Test...");

    }

    @Test
    void addUser() {
        int dbSize = userRepository.findAll().size();
        User user = User.builder()
                .name("Logan")
                .surname("Daviro")
                .build();
        userRepository.save(user);
        List<User> users = userRepository.findAll();
        Assert.assertEquals(dbSize + 1, users.size());

        User userNew = userRepository.getById(21L);
        Long idUser = userNew.getId();
        Long checkId = 21L;
        Assert.assertEquals(checkId, idUser);


    }

    @Test
    public void findUserById() {
        User user = userRepository.findById(21L).get();
        then(user).matches(u ->
                u.getId() == 21L
                        && u.getName().equalsIgnoreCase("Tester")
                        && u.getSurname().equalsIgnoreCase("Test")
        );
    }


    @Test
    public void currentLastUserId() {
        Long ld = userRepository.getCurrentLastUserId().get();
        System.out.println(ld);
    }

    @Test
    public void saveUser() {
        User user = userRepository.findById(1L).get();
        then(user.getSurname()).isEqualTo("Ryabets");
        user.setSurname("Dark");
        userRepository.save(user);
        user = userRepository.findById(1L).get();
        then(user.getSurname()).isEqualTo("Dark");
    }

    @Test
    public void deleteUser() {
        User user = userRepository.findById(21L).get();
        userRepository.delete(user);

        NoSuchElementException thrown = Assertions.assertThrows(NoSuchElementException.class, () -> {
            User user2 = userRepository.findById(21L).get();
        });
        System.out.println(thrown.getMessage());
        Assertions.assertEquals("No value present", thrown.getMessage());

    }

    @Test
    void detachCar() {
        user1.addCar(car1);
        userRepository.save(user1);

        Long idUser = userRepository.getCurrentLastUserId().get();
        System.out.println("Current user ID = " + idUser);
        Long idCar = carRepository.getCurrentLastCarId().get();
        System.out.println("Current car ID = " + idCar);

        Car carFromDB = carRepository.findById(idCar).get();
        System.out.println(carFromDB);

        User userFromDB = userRepository.findById(idUser).get();
        System.out.println(userFromDB);
        System.out.println("Car list size = " + userFromDB.getCarList().size());
        userFromDB.getCarList().forEach(System.out::println);

        System.out.println("AFTER");

        List<Car> carList = userFromDB.getCarList();
        carList.forEach(c -> {
            c.setUser(null);
            carRepository.save(c);
        });
        userFromDB.setCarList(null);

        System.out.println(carFromDB);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            userFromDB.getCarList().size();
        });

        NullPointerException thrown2 = Assertions.assertThrows(NullPointerException.class, () -> {
            carFromDB.getUser().getId();
        });

        Assertions.assertNull(thrown.getMessage());
        Assertions.assertNull(thrown2.getMessage());





    }


}
