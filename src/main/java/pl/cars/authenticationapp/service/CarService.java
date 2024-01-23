package pl.cars.authenticationapp.service;

import pl.cars.authenticationapp.domain.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> getAllCars();
    Car getCar(long id);
    Car saveCarToEngine(Car car, long id);

    Car saveCar(Car car);
    Car updateCar(long id,Car updateCar);
    void deleteCar(long idCar, long idEngine);
}
