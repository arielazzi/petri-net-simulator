package br.unisinos.edu.PetriNetSimulator.controller;

import br.unisinos.edu.PetriNetSimulator.domain.Lugar;
import br.unisinos.edu.PetriNetSimulator.service.PetriNetEngineService;
import br.unisinos.edu.PetriNetSimulator.service.PetriNetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/executa")
public class PetriNetSimulatorExecutionController {

    private final PetriNetEngineService petriNetEngineService;

    @GetMapping("/passo-a-passo")
    @ResponseStatus(HttpStatus.OK)
    public void passoAPasso() {
        petriNetEngineService.executarEnginePassoAPasso();
    }

    @GetMapping("/completo")
    public void completo() {
//        petriNetService.getLugar(lugarId);
    }

}
