package br.unisinos.edu.PetriNetSimulator.service;

import br.unisinos.edu.PetriNetSimulator.domain.Lugar;
import br.unisinos.edu.PetriNetSimulator.domain.Transicao;
import br.unisinos.edu.PetriNetSimulator.repository.PetriNetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetriNetEngineService {

    private final PetriNetService petriNetService;

    public void executarEnginePassoAPasso() {


        List<String> lug = new ArrayList<>();
        lug.add("Lugar");
        List<String> tok = new ArrayList<>();
        tok.add("Marcacão");
        PetriNetRepository.objetos.forEach(objeto -> {
            if (objeto instanceof Lugar) {
                var lugar = (Lugar) objeto;
                lug.add("  " + lugar.getLabel() + "  ");
                tok.add("  " + lugar.getTokens() + "  ");
            }
        });

        String[][] table = new String[][]{lug.toArray(new String[0]), tok.toArray(new String[0])};
        Table.tableWithLinesAndMaxWidth(table);

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

        transicoesAtivas.forEach(transicao -> {
            var conexoesEntrada = petriNetService.getConexoesEntrada(transicao.getId());
            var conexoesSaida = petriNetService.getConexoesSaida(transicao.getId());

            // Remove tokens da origem
            conexoesEntrada.forEach(c -> {
                var lugarOrigem = petriNetService.getLugar(c.getSourceId());
                var qtd = c.getType().equals("reset") ? lugarOrigem.getTokens() : c.getMultiplicity();
                if (!c.getType().equals("inhibitor")) {
                    petriNetService.removeTokenDeLugar(lugarOrigem, qtd);
                }
            });

            // insere tokens no destino
            conexoesSaida.forEach(c -> {
                var lugarDestino = petriNetService.getLugar(c.getDestinationId());
                petriNetService.insereTokenEmLugar(lugarDestino, c.getMultiplicity());
            });
        });


        List<String> aaaa = new ArrayList<>();
        aaaa.add("Lugar");
        List<String> bbb = new ArrayList<>();
        bbb.add("Marcacão");
        PetriNetRepository.objetos.forEach(objeto -> {
            if (objeto instanceof Lugar) {
                var lugar = (Lugar) objeto;
                aaaa.add("  " + lugar.getLabel() + "  ");
                bbb.add("  " + lugar.getTokens() + "  ");
            }
        });

        String[][] table2 = new String[][]{aaaa.toArray(new String[0]), bbb.toArray(new String[0])};
        Table.tableWithLinesAndMaxWidth(table2);

        if (!transicoesAtivas.isEmpty()) {
            System.out.println("Press enter to continue");
            try {
                System.in.read();
            } catch (Exception e) {
            }
            executarEnginePassoAPasso();
        }


    }

}
