package uz.grepit.demo.service;

import org.springframework.stereotype.Service;
import uz.grepit.demo.model.Car;
import uz.grepit.demo.repository.CarRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findOne(String id) {
        return carRepository.findOne(UUID.fromString(id));
    }

    public int delete(String id) {
        return carRepository.delete(UUID.fromString(id));
    }

    public int insert(Car car) {
        return carRepository.insert(car);
    }

    public int update(Car car) {
        return carRepository.update(car);
    }

    public List<Car> search(String make) {
        return carRepository.search(make);
    }
}
