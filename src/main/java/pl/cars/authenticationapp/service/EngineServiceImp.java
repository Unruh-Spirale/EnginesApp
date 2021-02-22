package pl.cars.authenticationapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.cars.authenticationapp.domain.entity.Car;
import pl.cars.authenticationapp.domain.entity.Engine;
import pl.cars.authenticationapp.repository.EngineRepository;

import java.util.List;

@Service
public class EngineServiceImp implements EngineService {

    @Autowired
    EngineRepository engineRepository;

    @Override
    public List<Engine> getAllEngine() {
        List<Engine> engines = engineRepository.getAllSortedEngines();
        return engines;
    }

    @Override
    public Engine getEngine(long idEngine) {
        Engine engine = engineRepository.findById(idEngine).get();
        return engine;
    }

    @Override
    public void saveEngine(Engine engine) {
        engineRepository.save(engine);
    }

    @Override
    public void updateEngine(long id,Engine updateEngine) {
        Engine engine = engineRepository.findById(id).get();

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
    public void deleteEngine(long id) {
        Engine engine = engineRepository.findById(id).get();
        List<Car> cars = engine.getCars();
        for(Car car: cars){
            car.removeEngine(engine);
        }
        engineRepository.delete(engine);
    }


}
