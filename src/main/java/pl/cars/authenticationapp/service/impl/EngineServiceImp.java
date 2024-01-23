package pl.cars.authenticationapp.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.cars.authenticationapp.domain.entity.Car;
import pl.cars.authenticationapp.domain.entity.Engine;
import pl.cars.authenticationapp.repository.CarRepository;
import pl.cars.authenticationapp.repository.EngineRepository;
import pl.cars.authenticationapp.service.EngineService;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class EngineServiceImp implements EngineService {

    private final EngineRepository engineRepository;
    private final CarRepository carRepository;

    @Override
    public List<Engine> getAllEngine() {
        return engineRepository.getAllSortedEngines();
    }

    @Override
    public Engine getEngine(long idEngine) {
        return engineRepository.findById(idEngine).orElseThrow(() -> new IllegalArgumentException("Engine does not found"));
    }

    @Override
    public void saveEngine(Engine engine) {
        Engine engineToSave = engineRepository.findByCompanyAndNameAndVolumeAndFuelAndPowerAndTransmissionAndDescription(
                engine.getCompany(), engine.getName(), engine.getVolume(), engine.getFuel(), engine.getPower(), engine.getTransmission(), engine.getDescription()
        ).orElse(engine);
        engineRepository.save(engineToSave);
    }

    @Override
    public void updateEngine(long id,Engine updateEngine) {
        Engine engine = engineRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Engine does not exist in database"));

        engine.setCompany(updateEngine.getCompany());
        engine.setName(updateEngine.getName());
        engine.setVolume(updateEngine.getVolume());
        engine.setFuel(updateEngine.getFuel());
        engine.setPower(updateEngine.getPower());
        engine.setTransmission(updateEngine.getTransmission());
        engine.setDescription(updateEngine.getDescription());

        engineRepository.save(engine);
    }

    @Override
    @Transactional
    public void deleteEngine(long id) {
        Engine engine = engineRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Engine does not found"));
        Set<Car> cars = engine.getCars();
        for(Car car: cars){
            car.removeEngine(engine);
            if (car.getEngines().isEmpty()) {
                carRepository.delete(car);
            }
        }

        engineRepository.delete(engine);
    }


}
