package br.unisinos.edu.PetriNetSimulator.service;

import br.unisinos.edu.PetriNetSimulator.domain.Lugar;
import br.unisinos.edu.PetriNetSimulator.repository.PetriNetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetriNetService {
    public boolean criaLugar(int lugarId) {
        return PetriNetRepository.lugares.add(new Lugar(lugarId));
    }

    public Lugar getLugar(int lugarId) {
        var optionalLugar = PetriNetRepository.lugares.stream().findFirst();
        return optionalLugar.get();
    }

    public boolean removeLugar(int id) {
        Lugar lugar = getLugar(id);
        return PetriNetRepository.lugares.remove(lugar);
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
