package pl.cars.authenticationapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmptyController2 {

    @GetMapping("/helloWorld2")
    public void emptyHelloWorld(){
        System.out.println("Hello World2!");
    }
}
