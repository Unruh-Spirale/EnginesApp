package pl.cars.authenticationapp;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.cars.authenticationapp.domain.entity.Car;
import pl.cars.authenticationapp.domain.entity.Engine;
import pl.cars.authenticationapp.repository.EngineRepository;
import pl.cars.authenticationapp.service.CarService;
import pl.cars.authenticationapp.service.EngineService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DataInitializer {
    private static final String DATA_ENGINES_CSV = "data/engines.csv";
    private static final String DATA_CARS_CSV = "data/cars.csv";
    private final CarService carService;
    private final EngineService engineService;
    private final EngineRepository engineRepository;

    @Transactional
    public void initialize() {
        initData();
    }

    private void initData() {
        ClassPathResource resourceEngines = new ClassPathResource(DATA_ENGINES_CSV);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceEngines.getInputStream()))) {
            CsvToBean<CsvEngine> build = new CsvToBeanBuilder<CsvEngine>(reader)
                    .withType(CsvEngine.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            build.stream().forEach(this::initEngine);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to parse engines CSV file", e);
        }

        ClassPathResource resourceCars = new ClassPathResource(DATA_CARS_CSV);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceCars.getInputStream()))) {
            CsvToBean<CsvCar> build = new CsvToBeanBuilder<CsvCar>(reader)
                    .withType(CsvCar.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            build.stream().forEach(this::initCar);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to parse cars CSV file", e);
        }
    }

    private void initCar(CsvCar csvCar) {
        List<Engine> enginesList = Arrays.stream(csvCar.engines.split(","))
                .filter(Objects::nonNull)
                .map(String::trim)
                .map(this::getEngine)
                .collect(Collectors.toList());
        Car car = new Car(
                csvCar.mark,
                csvCar.model,
                csvCar.generation,
                csvCar.yearOfProduction
        );
        car.setEngines(enginesList);
        carService.saveCar(car);
    }

    private Engine getEngine(String name) {
        return engineRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new IllegalStateException("Engine does not exists in database"));
    }

    private void initEngine(CsvEngine csvEngine) {
        Engine engine = new Engine(
                csvEngine.company,
                csvEngine.name,
                Double.parseDouble(csvEngine.volume),
                csvEngine.fuel,
                csvEngine.power,
                csvEngine.transmission,
                csvEngine.description
        );
        engineService.saveEngine(engine);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CsvCar {
        @CsvBindByName
        private String mark;
        @CsvBindByName
        private String model;
        @CsvBindByName
        private String generation;
        @CsvBindByName
        private String yearOfProduction;
        @CsvBindByName
        private String engines;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CsvEngine {
        @CsvBindByName
        private String company;
        @CsvBindByName
        private String name;
        @CsvBindByName
        private String volume;
        @CsvBindByName
        private String fuel;
        @CsvBindByName
        private String power;
        @CsvBindByName
        private String transmission;
        @CsvBindByName
        private String description;
    }
}
