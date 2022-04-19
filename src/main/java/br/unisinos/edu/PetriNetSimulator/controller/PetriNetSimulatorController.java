package br.unisinos.edu.PetriNetSimulator.controller;

import br.unisinos.edu.PetriNetSimulator.domain.Lugar;
import br.unisinos.edu.PetriNetSimulator.service.PetriNetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PetriNetSimulatorController {

    private final PetriNetService petriNetService;

    @GetMapping("/get-lugar")
    @ResponseStatus(HttpStatus.OK)
    public Lugar getLugar(@RequestBody int lugarId) {
        return petriNetService.getLugar(lugarId);
    }

    @PostMapping("/cria-lugar")
    @ResponseStatus(HttpStatus.CREATED)
    public void criaLugar(@RequestBody int lugarId) {
        petriNetService.criaLugar(lugarId);
    }

    @DeleteMapping("/remove-lugar")
    @ResponseStatus(HttpStatus.OK)
    public boolean removeLugar(@RequestBody int id) {
        return petriNetService.removeLugar(id);
    }

}
