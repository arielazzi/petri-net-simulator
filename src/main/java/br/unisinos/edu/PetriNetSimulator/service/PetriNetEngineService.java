package br.unisinos.edu.PetriNetSimulator.service;

import br.unisinos.edu.PetriNetSimulator.domain.Lugar;
import br.unisinos.edu.PetriNetSimulator.domain.Transicao;
import br.unisinos.edu.PetriNetSimulator.repository.PetriNetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetriNetEngineService {

    private final PetriNetService petriNetService;

    public void executarEnginePassoAPasso() {
        montaTabelaLugares();

        var transicoesAtivas = PetriNetRepository.objetos.stream()
                .filter(o -> o instanceof Transicao)
                .map(t -> {
                    var transicao = (Transicao) t;
                    var conexoesL = petriNetService.getConexoesEntrada(transicao.getId());

                    // Valida se tem arco de reset
                    var temInhibitor = conexoesL.stream().anyMatch(c -> c.getType().equals("inhibitor"));
                    if (temInhibitor) {
                        var manterTransicao = conexoesL.stream().allMatch(c -> petriNetService.validaConexaoInhibitor(c, petriNetService.getLugar(c.getSourceId())));
                        if (manterTransicao) {
                            return transicao;
                        } else {
                            return null;
                        }
                    }
                    var manterTransicao = conexoesL.stream().allMatch(c -> petriNetService.validaConexaoReset(c, petriNetService.getLugar(c.getSourceId())));
                    if (manterTransicao) {
                        return transicao;
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        montaTabelaTransicoes(transicoesAtivas);

        Map<Lugar, List<Transicao>> algo = new HashMap<>();
        transicoesAtivas.forEach(transicao -> {
            var conexoesEntrada = petriNetService.getConexoesEntrada(transicao.getId());
            conexoesEntrada.forEach(c -> {
                algo.computeIfAbsent(petriNetService.getLugar(c.getSourceId()), k -> new ArrayList<>()).add(transicao);
            });
        });

        for (Map.Entry<Lugar, List<Transicao>> set : algo.entrySet()) {
            if (set.getValue().size() > 1) {
                transicoesAtivas.remove(petriNetService.getRandomTransicao(set.getValue()));
            }
        }

        transicoesAtivas.forEach(transicao -> {
            var conexoesEntrada = petriNetService.getConexoesEntrada(transicao.getId());
            var conexoesSaida = petriNetService.getConexoesSaida(transicao.getId());

            // Remove tokens da origem
            conexoesEntrada.forEach(c -> {
                var lugarOrigem = petriNetService.getLugar(c.getSourceId());
                if (!c.getType().equals("inhibitor")) {
                    petriNetService.removeTokenDeLugar(lugarOrigem, c.getMultiplicity(), c.getType());
                }
            });

            // insere tokens no destino
            conexoesSaida.forEach(c -> {
                var lugarDestino = petriNetService.getLugar(c.getDestinationId());
                petriNetService.insereTokenEmLugar(lugarDestino, c.getMultiplicity());
            });
        });

        if (!transicoesAtivas.isEmpty()) {
            System.out.println("Press enter to continue");
            try {
                System.in.read();
            } catch (Exception e) {
            }
            executarEnginePassoAPasso();
        }
    }

    private void montaTabelaLugares() {
        List<String> rowLugares = new ArrayList<>();
        rowLugares.add("Lugar");
        List<String> rowTokens = new ArrayList<>();
        rowTokens.add("Marcacão");

        PetriNetRepository.objetos.forEach(objeto -> {
            if (objeto instanceof Lugar) {
                var lugar = (Lugar) objeto;
                rowLugares.add("  " + lugar.getId() + " (" + lugar.getLabel() + ") ");
                rowTokens.add("  " + lugar.getTokens() + "  ");
            }
        });

        String[][] tableLugares = new String[][]{rowLugares.toArray(new String[0]), rowTokens.toArray(new String[0])};
        Table.tableWithLinesAndMaxWidth(tableLugares);
    }

    private void montaTabelaTransicoes(List<Transicao> transicoesAtivas) {
        List<String> rowTransicoes = new ArrayList<>();
        rowTransicoes.add("Transição");
        List<String> rowHabilitada = new ArrayList<>();
        rowHabilitada.add("Habilitada");

        PetriNetRepository.objetos.forEach(objeto -> {
            if (objeto instanceof Transicao) {
                var transicao = (Transicao) objeto;
                rowTransicoes.add("  " + transicao.getId() + " (" + transicao.getLabel() + ") ");
                rowHabilitada.add("  " + (transicoesAtivas.contains(transicao) ? "S" : "N") + "  ");
            }
        });

        String[][] tableTransicoes = new String[][]{rowTransicoes.toArray(new String[0]), rowHabilitada.toArray(new String[0])};
        Table.tableWithLinesAndMaxWidth(tableTransicoes);
    }
}
