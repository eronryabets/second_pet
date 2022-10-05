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
import static org.assertj.core.api.BDDAssertions.then;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
@AutoConfigureTestEntityManager
@TestPropertySource("/application-test.properties")
@Transactional
@ExtendWith(MockitoExtension.class)
class CarServiceTest {

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
    void findAll() {
        List<Car> carList = carRepository.findAll();
        Assert.assertEquals(5, carList.size());
    }

    @Test
    void saveCar() {
        userRepository.save(user1);
        carRepository.save(car1);
        Long currentId = carRepository.getCurrentLastCarId().get();

        Car carFromDB = carRepository.findById(currentId).get();
        Assert.assertEquals("Test 8D", carFromDB.getModel());

        then(carFromDB).matches(c ->
                c.getId().equals(currentId)
                        && c.getCarBrand().equalsIgnoreCase("Honda")
                        && c.getModel().equalsIgnoreCase("Test 8D")
        );
    }

    @Test
    void addCar() {
        User userFromDB = userRepository.findById(1L).get();
        Car newCar = Car.builder()
                .carBrand("Subaru")
                .model("Test WRX")
                .year(2012)
                .carNumber("TT 7777 TT")
                .user(userFromDB)
                .build();
        carRepository.save(newCar);

        Long currentId = carRepository.getCurrentLastCarId().get();
        Car carFromDB = carRepository.findById(currentId).get();

        then(carFromDB).matches(c ->
                c.getId().equals(currentId)
                        && c.getCarBrand().equalsIgnoreCase("Subaru")
                        && c.getModel().equalsIgnoreCase("Test WRX")
                        && c.getCarNumber().equalsIgnoreCase("TT 7777 TT")
                        && c.getUser().getSurname().equalsIgnoreCase("Ryabets")
        );

    }

    @Test
    void deleteCar() {
        Car carFromDB = carRepository.findById(1L).get();
        carRepository.delete(carFromDB);

        NoSuchElementException thrown = Assertions.assertThrows(NoSuchElementException.class, () -> {
            Car car = carRepository.findById(1L).get();
        });
        System.out.println(thrown.getMessage());
        Assertions.assertEquals("No value present", thrown.getMessage());
    }
}