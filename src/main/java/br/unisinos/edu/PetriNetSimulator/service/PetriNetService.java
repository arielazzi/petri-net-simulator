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

    public boolean criaLugar(int id, int tokens) {
        return PetriNetRepository.objetos.add(new Lugar(id, tokens));
    }

    public Lugar getLugar(int id) {
        var lugar = PetriNetRepository.objetos.stream().filter(l -> l.getId() == id).findFirst();
        return (Lugar) lugar.get();
    }

    public boolean removeLugar(int id) {
        Lugar lugar = getLugar(id);
        return PetriNetRepository.objetos.remove(lugar);
    }

    public boolean criaTransicao(int id) {
        return PetriNetRepository.objetos.add(new Transicao(id));
    }

    public Transicao getTransicao(int id) {
        var transicao = PetriNetRepository.objetos.stream().filter(t -> t.getId() == id).findFirst();
        return (Transicao) transicao.get();
    }

    public boolean removeTransicao(int id) {
        Transicao transicao = getTransicao(id);
        return PetriNetRepository.objetos.remove(transicao);
    }


    public boolean criaConexao(int sourceId, int destinationId, int multiplicity) {
        return PetriNetRepository.conexoes.add(new Conexao(sourceId, destinationId, multiplicity));
    }

    public Conexao getConexao(int sourceId, int destinationId) {
        var conexao = PetriNetRepository.conexoes.stream().filter(c -> c.getSourceId() == sourceId &&
                c.getDestinationId() == destinationId).findFirst();
        return conexao.get();
    }

    public Conexao[] getConexoesEntrada(int id) {
        return PetriNetRepository.conexoes.stream().filter(c -> c.getDestinationId() == id).toArray(Conexao[]::new);
    }

    public Conexao[] getConexoesSaida(int id) {
        return PetriNetRepository.conexoes.stream().filter(c -> c.getSourceId() == id).toArray(Conexao[]::new);
    }

    public void insereTokenEmLugar(Lugar lugar) {
        int tokens = lugar.getTokens();
        lugar.setTokens(tokens + 1);
    }

    public boolean removeTokenDeLugar(Lugar lugar) {
        int tokens = lugar.getTokens();

        if (tokens > 1) {
            lugar.setTokens(tokens);
            return true;
        } else {
            return false;
        }
    }

    public void clearLugar(Lugar lugar) {
        lugar.setTokens(0);
    }

    public int getToken(Lugar lugar) {
        return lugar.getTokens();
    }

}
