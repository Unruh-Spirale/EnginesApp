package pl.cars.authenticationapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cars.authenticationapp.domain.entity.Car;
import pl.cars.authenticationapp.repository.CarRepository;
import pl.cars.authenticationapp.repository.EngineRepository;

import java.util.List;

@Service
public class CarServiceImp implements CarService{

    @Autowired
    CarRepository carRepository;

    @Autowired
    EngineRepository engineRepository;

    @Override
    public List<Car> getAllCars() {
        List<Car>cars = carRepository.getAllSortedCars();
        return cars;
    }

    @Override
    public Car getCar(long id) {
        return carRepository.findById(id).get();
    }

    @Override
    public void saveCar(Car car) {
        carRepository.save(car);
    }

    public void saveCar(long id,Car car){
        carRepository.save(car);
        car.addEngine(engineRepository.findById(id).get());
        carRepository.save(car);
    }

    @Override
    public void updateCar(long id, Car updateCar) {
        Car car = carRepository.findById(id).get();

        car.setMark(updateCar.getMark());
        car.setModel(updateCar.getModel());
        car.setGeneration(updateCar.getGeneration());
        car.setYearOfProduction(updateCar.getYearOfProduction());

        carRepository.save(car);
    }

    @Override
    public void deleteCar(long id) {
        Car car = carRepository.findById(id).get();
        car.getEngines().clear();
        carRepository.delete(car);
    }
}
