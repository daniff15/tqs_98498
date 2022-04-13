package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import com.example.demo.CarRepository;

import java.util.List;

@Service
@Transactional
public class CarManagerService {
    @Autowired
    private CarRepository carRepository;

    public Car save(Car c) {
        return carRepository.save(c);
    }

    public <Optional> Car getCarDetails(Long carID) {
        return carRepository.findByCarID(carID);

    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
}
