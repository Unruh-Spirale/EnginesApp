package pl.cars.authenticationapp.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cars.authenticationapp.domain.entity.Car;
import pl.cars.authenticationapp.domain.entity.Engine;
import pl.cars.authenticationapp.repository.CarRepository;
import pl.cars.authenticationapp.repository.EngineRepository;
import pl.cars.authenticationapp.service.CarService;

import java.util.List;

@AllArgsConstructor
@Service
public class CarServiceImp implements CarService {

    private final CarRepository carRepository;
    private final EngineRepository engineRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.getAllSortedCars();
    }
    @Override
    public Car getCar(long id) {
        return carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Car does not found"));
    }
    @Override
    public void saveCarToEngine(Car car, long id) {
        Engine engine = engineRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Engine does not found"));
        Car carToSave = carRepository.findByMarkAndModelAndGenerationAndYearOfProduction(car.getMark(), car.getModel(), car.getGeneration(), car.getYearOfProduction())
                .orElse(car);
        carToSave.addEngine(engine);
        carRepository.save(carToSave);
    }
    @Override
    public void saveCar(Car car) {
        carRepository.save(car);
    }
    @Override
    public void updateCar(long id, Car updateCar) {
        Car car = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Car does not found"));

        car.setMark(updateCar.getMark());
        car.setModel(updateCar.getModel());
        car.setGeneration(updateCar.getGeneration());
        car.setYearOfProduction(updateCar.getYearOfProduction());

        carRepository.save(car);
    }

    @Override
    public void deleteCar(long idCar, long idEngine) {
        Car car = carRepository.findById(idCar).orElseThrow(() -> new IllegalArgumentException("Car is not in database"));
        Engine engine = engineRepository.findById(idEngine).orElseThrow(() -> new IllegalArgumentException("Engine is not in database"));
        engine.removeCar(car);
        engineRepository.save(engine);
        car.removeEngine(engine);
        if (car.getEngines().isEmpty()){
            carRepository.delete(car);
        } else {
            carRepository.save(car);
        }
    }
}
