package uz.grepit.demo.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uz.grepit.demo.model.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepository {
    private final JdbcTemplate jdbcTemplate;

    public CarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    class CarRowMapper implements RowMapper<Car> {
        @Override
        public Car mapRow(ResultSet resultSet, int i) throws SQLException {
            Car car = new Car();
            car.setId((java.util.UUID) resultSet.getObject("id"));
            car.setMake(resultSet.getString("make"));
            car.setModel(resultSet.getString("model"));
            car.setYear(resultSet.getShort("year"));
            car.setGeneration(resultSet.getString("generation"));
            car.setColor(resultSet.getString("color"));
            car.setCountry(resultSet.getString("country"));
            return car;
        }
    }

    public List<Car> findAll() {
        return jdbcTemplate.query("SELECT * FROM car", new CarRowMapper());
    }

    public Car findOne(UUID id) {
        return jdbcTemplate.queryForObject("SELECT * FROM car WHERE id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Car.class));
    }

    public int delete(UUID id) {
        return jdbcTemplate.update("DELETE FROM car WHERE id = ?", id);
    }

    public int insert(Car car) {
        return jdbcTemplate.update("INSERT INTO car (make, model, year, generation, color, country) VALUES (?, ?, ?, ?, ?, ?)",
                car.getMake(), car.getModel(), car.getYear(), car.getGeneration(), car.getColor(), car.getCountry());
    }

    public int update(Car car) {
        return jdbcTemplate.update("UPDATE car SET make = ?, model = ?, year = ?, generation = ?, color = ?, country = ? WHERE id = ?",
                car.getMake(), car.getModel(), car.getYear(), car.getGeneration(), car.getColor(), car.getCountry(), car.getId());
    }

    public List<Car> search(String make) {
        return jdbcTemplate.query("SELECT * FROM car WHERE make = ?",
                new Object[]{make},
                new CarRowMapper()
        );
    }
}
