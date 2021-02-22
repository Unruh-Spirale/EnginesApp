package pl.cars.authenticationapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.cars.authenticationapp.domain.entity.Engine;
import pl.cars.authenticationapp.service.EngineService;


import java.util.List;

@Controller
public class EngineController {

    @Autowired
    EngineService engineService;

    @GetMapping("/")
    public String getAllEngines(Model model){
        List<Engine> engines = engineService.getAllEngine();
        model.addAttribute("engines",engines);
        return "home";
    }

    @GetMapping("/engine/{id}")
    public String getEngine(@PathVariable("id") Long id, Model model){
        Engine engine = engineService.getEngine(id);
        model.addAttribute("engine",engine);
        return "engine";
    }

    @GetMapping("/engineform")
    public String addEngine(Model model){
        model.addAttribute("engine",new Engine());
        return "add_engine";
    }

    @PostMapping("/saveengine")
    public String saveEngine(Engine engine){
        engineService.saveEngine(engine);
        return "redirect:/";
    }

    @GetMapping("/deleteengine/{id}")
    public String deleteEngine(@PathVariable("id") Long id){
        engineService.deleteEngine(id);
        return "redirect:/";
    }

    @GetMapping("/updateengine/{idEngine}")
    public String updateEngine(@PathVariable("idEngine") long id, Model model){
        Engine engine = engineService.getEngine(id);
        model.addAttribute("engine",engine);
        return "update_engine";
    }

    @PostMapping("/updateengine/{idEngine}")
    public String update(@PathVariable("idEngine") long id, Engine engine){
        engineService.updateEngine(id,engine);
        return "redirect:/engine/{idEngine}";
    }

}
