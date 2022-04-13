package com.example.demo;

import com.example.demo.CarManagerService;
import com.example.demo.CarRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;

    @BeforeEach
    public void setUp() {
        Car car1 = new Car("Bugatti", "Centodieci");
        car1.setCarID(1L);

        Mockito.when(carRepository.findByCarID(car1.getCarID())).thenReturn(car1);
        Mockito.when(carRepository.findByCarID(-500L)).thenReturn(null);
    }

    @Test
    void whenSearchValidID_thenCarShouldBeFound() {
        Long carToFind = 1L;
        Car found = carService.getCarDetails(carToFind);

        assertThat(found.getCarID()).isEqualTo(carToFind);
    }

    @Test
    void whenSearchInvalidCarId_thenCarShouldNotBeFound() {
        Car fromDb = carService.getCarDetails(-500L);
        assertThat(fromDb).isNull();

        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarID(-500L);

        Mockito.reset(carRepository);
    }

}
