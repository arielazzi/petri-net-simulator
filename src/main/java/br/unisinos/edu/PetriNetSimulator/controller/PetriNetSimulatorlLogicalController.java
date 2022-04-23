package br.unisinos.edu.PetriNetSimulator.controller;

import br.unisinos.edu.PetriNetSimulator.domain.RedeDePetri;
import br.unisinos.edu.PetriNetSimulator.service.PetriNetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PetriNetSimulatorlLogicalController {

    private final PetriNetService petriNetService;

    @PostMapping(value = "/executa", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public void executaCiclo(@RequestPart("file") MultipartFile file) throws IOException {
        RedeDePetri redeDePetri = petriNetService.fileParser(file);
        redeDePetri.getLugares()
                .forEach(lugar -> petriNetService.criaLugar(lugar.getId(), lugar.getTokens(), lugar.getLabel()));
        redeDePetri.getTransicoes()
                .forEach(transicao -> petriNetService.criaTransicao(transicao.getId(), transicao.getLabel()));
        redeDePetri.getConexoes()
                .forEach(conexao -> petriNetService.criaConexao(conexao.getSourceId(), conexao.getDestinationId(),
                        conexao.getMultiplicity(), conexao.getType()));
    }
}
