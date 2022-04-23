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


        List<String> lug = new ArrayList<>();
        lug.add("Lugar");
        List<String> tok = new ArrayList<>();
        tok.add("Marcacão");
        PetriNetRepository.objetos.forEach(objeto -> {
            if (objeto instanceof Lugar) {
                var lugar = (Lugar) objeto;
                lug.add("  " + lugar.getId() + " (" + lugar.getLabel() + ") ");
                tok.add("  " + lugar.getTokens() + "  ");
            }
        });

        String[][] table = new String[][]{lug.toArray(new String[0]), tok.toArray(new String[0])};
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

            var al = petriNetService.getRandomConexao(la);
            petriNetService.removeTokenDeLugar(petriNetService.getLugar(al.getSourceId()), al.getMultiplicity());
            System.out.println("source id: " + al.getSourceId() + " - destination id:" + al.getDestinationId());

            var transicao = petriNetService.getTransicao(al.getDestinationId());
            System.out.println("transicao id: " + transicao.getId());

            var conexoesT = petriNetService.getConexoesSaida(transicao.getId());
            conexoesT.forEach(c -> {
                var lugarDestino = petriNetService.getLugar(c.getDestinationId());
                System.out.println("lugar Destino id: " + lugarDestino.getId());
                petriNetService.insereTokenEmLugar(lugarDestino, c.getMultiplicity());
            });
            System.out.println("------------------------");
        });


        List<String> aaaa = new ArrayList<>();
        aaaa.add("Lugar");
        List<String> bbb = new ArrayList<>();
        bbb.add("Marcacão");
        PetriNetRepository.objetos.forEach(objeto -> {
            if (objeto instanceof Lugar) {
                var lugar = (Lugar) objeto;
                aaaa.add("  " + lugar.getId() + " (" + lugar.getLabel() + ") ");
                bbb.add("  " + lugar.getTokens() + "  ");
            }
        });

        String[][] table2 = new String[][]{aaaa.toArray(new String[0]), bbb.toArray(new String[0])};
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
