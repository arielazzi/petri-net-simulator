package br.unisinos.edu.PetriNetSimulator.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetriNetSimulatorController {

    @GetMapping("/home")
    @ResponseStatus(HttpStatus.OK)
    public void home() {
        System.out.println("trolololo");
    }

}
