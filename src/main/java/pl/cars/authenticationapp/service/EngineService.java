package pl.cars.authenticationapp.service;

import pl.cars.authenticationapp.domain.entity.Engine;

import java.util.List;

public interface EngineService {

    List<Engine> getAllEngine();
    Engine getEngine(long idEngine);
    Engine saveEngine(Engine engine);
    Engine updateEngine(long id,Engine updateEngine);
    void deleteEngine(long id);

}
