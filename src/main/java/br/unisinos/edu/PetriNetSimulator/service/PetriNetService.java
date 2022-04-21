package br.unisinos.edu.PetriNetSimulator.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.unisinos.edu.PetriNetSimulator.domain.Conexao;
import br.unisinos.edu.PetriNetSimulator.domain.Document;
import br.unisinos.edu.PetriNetSimulator.domain.Lugar;
import br.unisinos.edu.PetriNetSimulator.domain.RedeDePetri;
import br.unisinos.edu.PetriNetSimulator.repository.PetriNetRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetriNetService {
    public boolean criaLugar(int lugarId, int tokens) {
        return PetriNetRepository.objetos.add(new Lugar(lugarId, tokens));
    }

    public Lugar getLugar(int lugarId) {
        var lugar = PetriNetRepository.objetos.stream().filter(l -> l.getId() == lugarId).findFirst();
        return (Lugar)lugar.get();
    }

    public boolean criaConexao(int sourceId, int destinationId, int multiplicity) {
        return PetriNetRepository.conexoes.add(new Conexao(sourceId, destinationId, multiplicity));
    }

    public Conexao getConexao(int sourceId, int destinationId) {
        var conexao = PetriNetRepository.conexoes.stream().filter(c -> c.getSourceId() == sourceId &&
                c.getDestinationId() == destinationId).findFirst();
        return conexao.get();
    }

    public RedeDePetri fileParser(MultipartFile file) throws IOException {
        File xmlFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream( xmlFile );
        fos.write( file.getBytes() );
        fos.close();

        JAXBContext jaxbContext;
        Document document = new Document();
        try
        {
            jaxbContext = JAXBContext.newInstance(Document.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            document = (Document)jaxbUnmarshaller.unmarshal(xmlFile);
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
        return document.getRedeDePetri();
    }

    public boolean removeLugar(int id) {
        Lugar lugar = getLugar(id);
        return PetriNetRepository.objetos.remove(lugar);
    }
//    boolean criaTransicao(int id)
//    Transicao getTransicao(int id)
//    boolean removeTransicao(int id)
//    boolean criaConexao(Lugar lugar, Transicao transicao, int peso, boolean ehEntrada, boolean ehArcoInibidor,
//                        boolean removeConexao(Lugar lugar, Transicao transicao)
//    Lugar getLugarDeConexao(Conexao conexao)
//    Transicao getTransicaoDeConexao(Conexao conexao)
//    Conexao[] getConexoesEntrada(int id) // retorna array de conexões de entrada de uma transição
//    Conexao[] getConexoesSaida(int id) // retorna array de conexões de saida de uma transição
//    void insereTokenEmLugar(Token token, Lugar lugar)
//    boolean removeTokenDeLugar(Token token, Lugar lugar)
//    void clearLugar(Lugar lugar) // remove todos tokens do lugar
//    Token getToken(Lugar lugar) //retorna token
//    Token[] getToken(Lugar lugar) //retorna um array de tokens
//    int quantosTokens(int id) //retorna a quantidade de tokens de um lugar com este id
//    boolean getStatusTransicao (int id) // retona True se Transição habilitada e False caso contrário;
//    void setTransicaoInativa(int id) // seta transicao como inativa
//    void setTransicaoAtiva(int id) // seta transicao como ativa (default)
//    boolean isTransicaoAtiva(int id)
//    boolean salvaRede (String nomeArquivo)
//    Rede carregaRede (String nomeArquivo)
}
