package com.eronryabets.second_pet;

import com.eronryabets.second_pet.entity.Car;
import com.eronryabets.second_pet.repository.CarRepository;
import com.eronryabets.second_pet.repository.UserRepository;
import com.eronryabets.second_pet.service.CarService;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class CarTest {

    private final CarRepository carRepository = mock(CarRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final CarService carService = new CarService(carRepository,userRepository );

    @Test
    public void testCreateCar(){
        Car car = new Car("Honda","Test 8D",2020,"TT 4444 TT");
        carRepository.save(car);
        Mockito.verify(carRepository, Mockito.times(1))
                .save(car);
    }

}
