package br.unisinos.edu.PetriNetSimulator.controller;

import br.unisinos.edu.PetriNetSimulator.domain.Conexao;
import br.unisinos.edu.PetriNetSimulator.domain.Lugar;
import br.unisinos.edu.PetriNetSimulator.domain.Transicao;
import br.unisinos.edu.PetriNetSimulator.service.PetriNetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/cria-transicao")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean criaTransicao(@RequestBody int id) {
        return petriNetService.criaTransicao(id);
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
    public boolean criaConexao(@RequestBody int sourceId, int destinationId, int multiplicity) {
        return petriNetService.criaConexao(sourceId, destinationId, multiplicity);
    }

    public boolean removeConexao() {
        return true;
    }

    public Lugar getLugarDeConexao() {
        return new Lugar();
    }

    public Transicao getTransicaoDeConexao() {
        return new Transicao();
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
    public void insereTokenEmLugar(@RequestBody Lugar lugar) {
        petriNetService.insereTokenEmLugar(lugar, 1);
    }

    @DeleteMapping("/remove-token-de-lugar")
    @ResponseStatus(HttpStatus.OK)
    public boolean removeTokenDeLugar(@RequestBody Lugar lugar) {
        return petriNetService.removeTokenDeLugar(lugar, 1);
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

}
