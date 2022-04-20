package br.unisinos.edu.PetriNetSimulator.controller;

import br.unisinos.edu.PetriNetSimulator.domain.Lugar;
import br.unisinos.edu.PetriNetSimulator.domain.Transicao;
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

    @GetMapping("/cria-transicao")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean criaTransicao(@RequestBody int id) {
        return petriNetService.criaTransicao(id);
    }

    @PostMapping("/get-transicao")
    @ResponseStatus(HttpStatus.OK)
    public Transicao getTransicao(@RequestBody int id) {
        return petriNetService.getTransicao(id);
    }

    @DeleteMapping("/remove-transicao")
    @ResponseStatus(HttpStatus.OK)
    public boolean removeTransicao(@RequestBody int id) {
        return petriNetService.removeTransicao(id);
    }

}
