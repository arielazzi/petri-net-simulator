package br.unisinos.edu.PetriNetSimulator.controller;

import br.unisinos.edu.PetriNetSimulator.domain.Conexao;
import br.unisinos.edu.PetriNetSimulator.domain.Lugar;
import br.unisinos.edu.PetriNetSimulator.domain.Transicao;
import br.unisinos.edu.PetriNetSimulator.service.PetriNetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PetriNetSimulatorController {

    private final PetriNetService petriNetService;

    @PostMapping("/criar-lugar")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean criaLugar(@RequestBody int id, int tokens, String label) {
        return petriNetService.criaLugar(id, tokens, label);
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

    @PostMapping("/cria-transicao")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean criaTransicao(@RequestBody int id, String label) {
        return petriNetService.criaTransicao(id, label);
    }

    @GetMapping("/get-transicao")
    @ResponseStatus(HttpStatus.OK)
    public Transicao getTransicao(@RequestBody int id) {
        return petriNetService.getTransicao(id);
    }

    @DeleteMapping("/remove-transicao")
    @ResponseStatus(HttpStatus.OK)
    public boolean removeTransicao(@RequestBody int id) {
        return petriNetService.removeTransicao(id);
    }

    @PostMapping("/cria-conexao")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean criaConexao(@RequestBody int sourceId, int destinationId, int multiplicity, String type) {
        return petriNetService.criaConexao(sourceId, destinationId, multiplicity, type);
    }

    @GetMapping("/get-conexoes-entrada")
    @ResponseStatus(HttpStatus.OK)
    public List<Conexao> getConexoesEntrada(@RequestBody int id) {
        return petriNetService.getConexoesEntrada(id);
    }

    @GetMapping("/get-conexoes-saida")
    @ResponseStatus(HttpStatus.OK)
    public List<Conexao> getConexoesSaida(@RequestBody int id) {
        return petriNetService.getConexoesSaida(id);
    }

    @PostMapping("/insere-token-em-lugar")
    @ResponseStatus(HttpStatus.CREATED)
    public void insereTokenEmLugar(@RequestBody int id) {
        petriNetService.insereTokenEmLugar(petriNetService.getLugar(id), 1);
    }

    @DeleteMapping("/remove-token-de-lugar")
    @ResponseStatus(HttpStatus.OK)
    public boolean removeTokenDeLugar(@RequestBody int id, String arcType) {
        return petriNetService.removeTokenDeLugar(petriNetService.getLugar(id), 1, arcType);
    }

    @PutMapping("/clear-lugar")
    @ResponseStatus(HttpStatus.OK)
    public void clearLugar(@RequestBody Lugar lugar) {
        petriNetService.clearLugar(lugar);
    }

    @GetMapping("/get-token")
    @ResponseStatus(HttpStatus.OK)
    public int getToken(Lugar lugar) {
        return petriNetService.getToken(lugar);
    }

    @GetMapping("/get-token-quantity")
    @ResponseStatus(HttpStatus.OK)
    public int getTokenQuantity(int id) {
        return petriNetService.getLugar(id).getTokens();
    }

}
