package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarRestController {
    @Autowired
    private CarManagerService carManagerService;

    @PostMapping("/newcar")
    public ResponseEntity<Car> createCar(@RequestBody CarDTO car) {
        HttpStatus status = HttpStatus.CREATED;
        Car carPersistent = new Car();
        carPersistent.setCarID(car.getCarID());
        carPersistent.setMaker(car.getMaker());
        carPersistent.setModel(car.getModel());
        Car saved = carManagerService.save(carPersistent);
        return new ResponseEntity<>(saved, status);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping("/car/{carID}")
    public Car getCarDetailsBycarID(@PathVariable(name = "carID") Long carID) {
        return carManagerService.getCarDetails(carID);
    }
}
