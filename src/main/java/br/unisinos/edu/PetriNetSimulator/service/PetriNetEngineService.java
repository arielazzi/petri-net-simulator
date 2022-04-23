package br.unisinos.edu.PetriNetSimulator.service;

import br.unisinos.edu.PetriNetSimulator.domain.Lugar;
import br.unisinos.edu.PetriNetSimulator.repository.PetriNetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetriNetEngineService {

    private final PetriNetService petriNetService;

    public void executarEnginePassoAPasso() {
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

        String[][] table = new String[][]{rowLugares.toArray(new String[0]), rowTokens.toArray(new String[0])};
        Table.tableWithLinesAndMaxWidth(table);

        var lugaresAtivos = PetriNetRepository.objetos.stream()
                .filter(o -> o instanceof Lugar)
                .map(l -> {
                    var lugar = (Lugar) l;
                    var conexoesL = petriNetService.getConexoesSaida(lugar.getId());
                    conexoesL.removeIf(c -> c.getType().equals("regular") && c.getMultiplicity() > lugar.getTokens());
                    return conexoesL;
                })
                .filter(c -> !c.isEmpty())
                .collect(Collectors.toList());

        lugaresAtivos.forEach(la -> {
            var conexao = petriNetService.getRandomConexao(la);
            petriNetService.removeTokenDeLugar(petriNetService.getLugar(conexao.getSourceId()), conexao.getMultiplicity());
            System.out.println("Source Id: " + conexao.getSourceId() + " - Destination Id:" + conexao.getDestinationId());

            var transicao = petriNetService.getTransicao(conexao.getDestinationId());
            System.out.println("Transição Id: " + transicao.getId());

            var conexoesDestinos = petriNetService.getConexoesSaida(transicao.getId());
            conexoesDestinos.forEach(c -> {
                var lugarDestino = petriNetService.getLugar(c.getDestinationId());
                System.out.println("Lugar Destino Label: " + lugarDestino.getLabel() +  "Id: " + lugarDestino.getId());
                petriNetService.insereTokenEmLugar(lugarDestino, c.getMultiplicity());
            });
            System.out.println("------------------------");
        });

        List<String> rowLugares2 = new ArrayList<>();
        rowLugares2.add("Lugar");
        List<String> rowTokens2 = new ArrayList<>();
        rowTokens2.add("Marcacão");
        PetriNetRepository.objetos.forEach(objeto -> {
            if (objeto instanceof Lugar) {
                var lugar = (Lugar) objeto;
                rowLugares2.add("  " + lugar.getId() + " (" + lugar.getLabel() + ") ");
                rowTokens2.add("  " + lugar.getTokens() + "  ");
            }
        });

        String[][] table2 = new String[][]{rowLugares2.toArray(new String[0]), rowTokens2.toArray(new String[0])};
        Table.tableWithLinesAndMaxWidth(table2);

        if (!lugaresAtivos.isEmpty()) {
            System.out.println("Press enter to continue");
            try {
                System.in.read();
            } catch (Exception e) {
            }
            executarEnginePassoAPasso();
        }
    }
}
