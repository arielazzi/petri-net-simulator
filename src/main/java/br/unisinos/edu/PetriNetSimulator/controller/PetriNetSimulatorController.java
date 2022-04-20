package br.unisinos.edu.PetriNetSimulator.controller;

import br.unisinos.edu.PetriNetSimulator.domain.Lugar;
import br.unisinos.edu.PetriNetSimulator.service.PetriNetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PetriNetSimulatorController {

    private final PetriNetService petriNetService;

    @PostMapping("/criar-lugar")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean criaLugar(@RequestBody int id, int tokens) {
        return petriNetService.criaLugar(id, tokens);
    }

    @GetMapping("/get-lugar")
    @ResponseStatus(HttpStatus.OK)
    public Lugar getLugar(@RequestBody int id) {
        return petriNetService.getLugar(id);
    }

    @DeleteMapping("/remove-lugar")
    @ResponseStatus(HttpStatus.OK)
    public boolean removeLugar(@RequestBody int id) {
        return petriNetService.removeLugar(id);
    }

}
