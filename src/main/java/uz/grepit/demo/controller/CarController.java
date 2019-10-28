package uz.grepit.demo.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.grepit.demo.exception.ResourceNotFoundException;
import uz.grepit.demo.model.Car;
import uz.grepit.demo.service.CarService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAll() throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok(carService.findAll());
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("There is any problem executing the query with database");
        } catch (Exception e) {
            throw new ResourceNotFoundException("There is any problem");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> get(@PathVariable(value = "id") String carId) throws ResourceNotFoundException {
        try {
            Car car = carService.findOne(carId);
            return ResponseEntity.ok().body(car);
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("Car not found for this id :: " + carId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("There is any problem");
        }
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Car car) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok(carService.insert(car));
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("There is any problem with database");
        } catch (Exception e) {
            throw new ResourceNotFoundException("There is any problem");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(@PathVariable(value = "id") String carId,
                                          @Valid @RequestBody Car car) throws ResourceNotFoundException {
        try {
            carService.findOne(carId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Car not found for this id :: " + carId);
        }

        try {
            return ResponseEntity.ok(carService.update(car));
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("There is any problem with database");
        } catch (Exception e) {
            throw new ResourceNotFoundException("There is any problem");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") String carId) throws ResourceNotFoundException {
        try {
            carService.findOne(carId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Car not found for this id :: " + carId);
        }

        try {
            return ResponseEntity.ok(carService.delete(carId));
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("There is any problem with database");
        } catch (Exception e) {
            throw new ResourceNotFoundException("There is any problem");
        }
    }

    @PostMapping("/search")
    public ResponseEntity search(@RequestParam(name = "make") String make) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok(carService.search(make));
        } catch (Exception e) {
            throw new ResourceNotFoundException("There is any problem");
        }
    }
}
