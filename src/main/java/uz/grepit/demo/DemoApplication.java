package uz.grepit.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.grepit.demo.model.Car;
import uz.grepit.demo.service.CarService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CarService carService;

    public DemoApplication(CarService carService) {
        this.carService = carService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("all cars -> {}", carService.findAll());

        Car car = new Car("Lada", "XRay", new Short("2019"), "x generation", "yellow", "Russia Federation");
        logger.info("insert car -> {}", carService.insert(car));

        logger.info("all cars -> {}", carService.findAll());

        logger.info("search car that model is Lada -> {}", carService.search("Lada"));
    }
}
