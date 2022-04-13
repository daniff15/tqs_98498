package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CarRepoTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    void whenFindByModel_thenCar() {

        Car car = new Car("Nissan", "GT-R");

        testEntityManager.persistAndFlush(car);

        Car response_car = carRepository.findByCarID(car.getCarID());

        assertThat(response_car.getCarID()).isEqualTo(car.getCarID());
    }

    @Test
    void whenInvalidModel_thenNoCar() {

        Car car = carRepository.findByCarID(-500L);

        assertThat(car).isNull();
    }
}
