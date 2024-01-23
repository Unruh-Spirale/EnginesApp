package pl.cars.authenticationapp.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.cars.authenticationapp.domain.entity.Engine;
import pl.cars.authenticationapp.repository.CarRepository;
import pl.cars.authenticationapp.repository.EngineRepository;
import pl.cars.authenticationapp.service.EngineService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EngineServiceImpTest {

    EngineService engineService;
    @Mock
    EngineRepository engineRepository;
    @Mock
    CarRepository carRepository;

    @BeforeEach
    void setUp(){
        engineService = new EngineServiceImp(engineRepository, carRepository);
    }

    @Test
    void shouldReturnAllEngines() {
        List<Engine> engines = List.of(new Engine());
        when(engineRepository.getAllSortedEngines()).thenReturn(engines);
        List<Engine> result = engineService.getAllEngine();
        assertEquals(engines, result);
    }
    @Test
    void shouldReturnEngine() {
        long idEngine = 12;
        Engine engine = new Engine();
        when(engineRepository.findById(idEngine)).thenReturn(Optional.of(engine));
        Engine result = engineService.getEngine(idEngine);
        assertEquals(engine, result);
    }
    @Test
    void shouldSaveEngine() {
        Engine engine = new Engine();
        when(engineRepository.findByCompanyAndNameAndVolumeAndFuelAndPowerAndTransmissionAndDescription(null,null,0,null, null,null,null))
                .thenReturn(Optional.of(engine));
        when(engineRepository.save(engine)).thenReturn(engine);
        Engine result = engineService.saveEngine(engine);
        assertEquals(engine, result);
    }

    @Test
    void shouldUpdateEngine() {
        long id = 12;
        Engine engine = new Engine();
        Engine updatedEngine = new Engine("VW", "Beetle", 2.0, "DIESEL", "120", "M", "description");
        when(engineRepository.findById(id)).thenReturn(Optional.of(engine));
        when(engineRepository.save(engine)).thenReturn(updatedEngine);
        Engine result = engineService.updateEngine(id, updatedEngine);
        assertEquals(updatedEngine, result);
    }
    @Test
    void shouldDeleteEngine() {
        long engineId = 12;
        Engine engine = new Engine();
        when(engineRepository.findById(engineId)).thenReturn(Optional.of(engine));
        engineService.deleteEngine(engineId);
        verify(engineRepository).delete(engine);
    }

}