package example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CarManagerService {
    @Autowired
    private CarManagerService carRepository;

    public Car save(Car c) {
        // return carRepository.save(c);
        return null;
    }

    public Car getCarById(Long id) {
        // return carRepository.findById(id);
        return null;

    }

    public List<Car> getAllCars() {
        // return carRepository.findAll();
        return null;
    }
}
