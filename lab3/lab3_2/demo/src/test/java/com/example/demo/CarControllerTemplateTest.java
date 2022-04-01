package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import java.util.List;

import com.example.demo.Car;
import com.example.demo.CarRepository;

import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@AutoConfigureTestDatabase
public class CarControllerTemplateTest {
    // will need to use the server port for the invocation url
    @LocalServerPort
    int randomServerPort;

    // a REST client that is test-friendly
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    void whenValidInput_thenCreateCar() {
        Car car = new Car("Nissan", "GT-R");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/newcar", car, Car.class);

        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getMaker).containsOnly("Nissan");
    }

    @Test
    void givenCars_whenGetCars_thenStatus200() {
        createTestCar("Nissan", "GT-R");
        createTestCar("Bugatti", "Centodieci");

        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("Nissan", "Bugatti");

    }

    private void createTestCar(String maker, String model) {
        Car car = new Car(maker, model);
        repository.saveAndFlush(car);
    }

}
