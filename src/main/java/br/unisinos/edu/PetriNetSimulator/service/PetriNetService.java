package br.unisinos.edu.PetriNetSimulator.service;

import br.unisinos.edu.PetriNetSimulator.domain.*;
import br.unisinos.edu.PetriNetSimulator.repository.PetriNetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetriNetService {
    public RedeDePetri fileParser(MultipartFile file) throws IOException {
        File xmlFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(xmlFile);
        fos.write(file.getBytes());
        fos.close();

        JAXBContext jaxbContext;
        Document document = new Document();
        try {
            jaxbContext = JAXBContext.newInstance(Document.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            document = (Document) jaxbUnmarshaller.unmarshal(xmlFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return document.getRedeDePetri();
    }

    public boolean criaLugar(int id, int tokens, String label) {
        return PetriNetRepository.objetos.add(new Lugar(id, tokens, label));
    }

    public Lugar getLugar(int id) {
        var lugar = PetriNetRepository.objetos.stream().filter(l -> l.getId() == id).findFirst();
        return (Lugar) lugar.get();
    }

    public boolean removeLugar(int id) {
        Lugar lugar = getLugar(id);
        return PetriNetRepository.objetos.remove(lugar);
    }

    public boolean criaTransicao(int id, String label) {
        return PetriNetRepository.objetos.add(new Transicao(id, label));
    }

    public Transicao getTransicao(int id) {
        var transicao = PetriNetRepository.objetos.stream().filter(t -> t.getId() == id).findFirst();
        return (Transicao) transicao.get();
    }

    public boolean removeTransicao(int id) {
        Transicao transicao = getTransicao(id);
        return PetriNetRepository.objetos.remove(transicao);
    }

    public boolean criaConexao(int sourceId, int destinationId, int multiplicity, String type) {
        return PetriNetRepository.conexoes.add(new Conexao(sourceId, destinationId, multiplicity, type));
    }

    public List<Conexao> getConexoesEntrada(int id) {
        return PetriNetRepository.conexoes.stream().filter(c -> c.getDestinationId() == id).collect(Collectors.toList());
    }

    public List<Conexao> getConexoesSaida(int id) {
        return PetriNetRepository.conexoes.stream().filter(c -> c.getSourceId() == id).collect(Collectors.toList());
    }

    public Transicao getRandomTransicao(List<Transicao> transicoes) {
        return transicoes.get(new Random().nextInt(transicoes.size()));
    }

    public void insereTokenEmLugar(Lugar lugar, int qtd) {
        int tokens = lugar.getTokens();
        lugar.setTokens(tokens + qtd);
    }

    public boolean removeTokenDeLugar(Lugar lugar, int qtd, String arcType) {
        int tokens = lugar.getTokens();

        if (tokens > 0 && arcType.equals("regular")) {
            lugar.setTokens(tokens - qtd);
            return true;
        }
        else if (tokens > 0 && arcType.equals("reset")) {
            lugar.setTokens(0);
            return true;
        }
        else { return false; }
    }

    public void clearLugar(Lugar lugar) {
        lugar.setTokens(0);
    }

    public int getToken(Lugar lugar) {
        return lugar.getTokens();
    }

    public boolean validaConexaoReset(Conexao conexao, Lugar lugar) {
        if (conexao.getType().equals("regular")) {
            return conexao.getMultiplicity() <= lugar.getTokens();
        }
        return true;
    }

    public boolean validaConexaoInhibitor(Conexao conexao, Lugar lugar) {
        if (conexao.getType().equals("inhibitor")) {
            return !(conexao.getMultiplicity() <= lugar.getTokens());
        }
        if (conexao.getType().equals("regular")) {
            return conexao.getMultiplicity() <= lugar.getTokens();
        }
        return false;
    }

    public void clearRede() {
        PetriNetRepository.objetos.clear();
        PetriNetRepository.conexoes.clear();
    }
}
