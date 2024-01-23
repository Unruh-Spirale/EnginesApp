package pl.cars.authenticationapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.cars.authenticationapp.domain.entity.Car;
import pl.cars.authenticationapp.service.CarService;
import pl.cars.authenticationapp.service.EngineService;

@AllArgsConstructor
@Controller
public class CarController {

    private final CarService carService;
    private final EngineService engineService;

    @GetMapping("/carform/{idEngine}")
    public String addCar(@PathVariable("idEngine") long id, Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("engine",engineService.getEngine(id));
        return "add_car";
    }

    @PostMapping("/savecar/{idEngine}")
    public String saveCar(@PathVariable("idEngine") long id, Car car){
        carService.saveCarToEngine(car, id);
        return "redirect:/engine/{idEngine}";
    }

    @GetMapping("/deletecar/{idCar}/{idEngine}")
    public String deleteCar(@PathVariable("idCar") Long idCar, @PathVariable("idEngine") Long idEngine){
        carService.deleteCar(idCar, idEngine);
        return "redirect:/engine/{idEngine}";
    }

    @GetMapping("/{idEngine}/updatecar/{idCar}")
    public String updateCar(@PathVariable("idCar") long id, Model model, @PathVariable("idEngine") long idEngine){
        Car car = carService.getCar(id);
        model.addAttribute("car",car);
        model.addAttribute("engine",engineService.getEngine(idEngine));
        return "update_car";
    }

    @PostMapping("/{idEngine}/updatecar/{idCar}")
    public String update(@PathVariable("idCar") long id, Car car){
        carService.updateCar(id,car);
        return "redirect:/engine/{idEngine}";
    }

}
