package pl.cars.authenticationapp.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.cars.authenticationapp.domain.entity.Car;
import pl.cars.authenticationapp.domain.entity.Engine;
import pl.cars.authenticationapp.repository.CarRepository;
import pl.cars.authenticationapp.repository.EngineRepository;
import pl.cars.authenticationapp.service.CarService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CarServiceImpTest {

    private CarService carService;
    @Mock
    private CarRepository carRepository;
    @Mock
    private EngineRepository engineRepository;

    @BeforeEach
    void setUp() {
        carService = new CarServiceImp(carRepository, engineRepository);
    }

    @Test
    void shouldReturnListOfAllCars() {
        Car car = new Car();
        List<Car> cars = List.of(car);
        Mockito.when(carRepository.getAllSortedCars()).thenReturn(cars);

        List<Car> result = carService.getAllCars();

        assertEquals(cars, result);
    }

    @Test
    void shouldReturnCar() {
        long id = 12;
        Car car = new Car();
        car.setIdCar(id);
        Mockito.when(carRepository.findById(id)).thenReturn(Optional.of(car));

        Car result = carService.getCar(id);
        assertEquals(car, result);
    }

    @Test
    void shouldSaveEngineToCar() {
        long engineId = 12;
        Car car = new Car();
        Engine engine = new Engine();
        Mockito.when(engineRepository.findById(engineId)).thenReturn(Optional.of(engine));
        Mockito.when(carRepository.findByMarkAndModelAndGenerationAndYearOfProduction(car.getMark(), car.getModel(), car.getGeneration(), car.getYearOfProduction()))
                .thenReturn(Optional.of(car));
        Mockito.when(carRepository.save(car)).thenReturn(car);
        Car result = carService.saveCarToEngine(car, engineId);
        assertEquals(car, result);
    }

    @Test
    void shouldSaveCar() {
        Car car = new Car();
        Mockito.when(carRepository.save(car)).thenReturn(car);
        Car result = carService.saveCar(car);
        assertEquals(car, result);
    }

    @Test
    void shouldUpdateCar() {
        long id = 12;
        Car car = new Car();
        Car updatedCar = new Car("VW", "Beetle", "2", "2010");
        Mockito.when(carRepository.findById(id)).thenReturn(Optional.of(car));
        Mockito.when(carRepository.save(car)).thenReturn(updatedCar);
        Car result = carService.updateCar(id, updatedCar);
        assertEquals(updatedCar, result);
    }

    @Test
    void shouldDeleteCar() {
        long idCar = 12;
        long idEngine = 12;
        Car car = new Car();
        car.setEngines(new HashSet<>());
        Engine engine = new Engine();
        engine.setCars(new HashSet<>());
        Mockito.when(carRepository.findById(idCar)).thenReturn(Optional.of(car));
        Mockito.when(engineRepository.findById(idEngine)).thenReturn(Optional.of(engine));

        carService.deleteCar(idCar, idEngine);
        Mockito.verify(engineRepository).save(engine);
        Mockito.verify(carRepository).delete(car);
    }
}