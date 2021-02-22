package pl.cars.authenticationapp.service;

import pl.cars.authenticationapp.domain.entity.Engine;

import java.util.List;

public interface EngineService {

    List<Engine> getAllEngine();
    Engine getEngine(long idEngine);
    void saveEngine(Engine engine);
    void updateEngine(long id,Engine updateEngine);
    void deleteEngine(long id);

}
