package uz.grepit.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Car {
    private UUID id;
    private String make;
    private String model;
    private Short year;
    private String generation;
    private String color;
    private String country;

    public Car(UUID id, String make, String model, Short year, String generation, String color, String country) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.generation = generation;
        this.color = color;
        this.country = country;
    }

    public Car(String make, String model, Short year, String generation, String color, String country) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.generation = generation;
        this.color = color;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", generation='" + generation + '\'' +
                ", color='" + color + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
