package pl.cars.authenticationapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmptyController {

    @GetMapping("/hello")
    public void helloController(){
        System.out.println("Hello World!");
    }
}
