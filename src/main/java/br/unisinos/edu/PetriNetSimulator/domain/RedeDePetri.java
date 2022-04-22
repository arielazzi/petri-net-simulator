package br.unisinos.edu.PetriNetSimulator.domain;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class RedeDePetri {
    private List<Lugar> lugares;
    private List<Conexao> conexoes;
    private List<Transicao> transicoes;

    public RedeDePetri(){}
    public RedeDePetri(List<Lugar> lugares, List<Conexao> conexoes, List<Transicao> transicoes) {
        this.lugares = lugares;
        this.conexoes = conexoes;
        this.transicoes = transicoes;
    }

    @XmlElement(name = "place")
    public List<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(List<Lugar> answers) {
        this.lugares = answers;
    }

    @XmlElement(name = "arc")
    public List<Conexao> getConexoes() {
        return conexoes;
    }

    public void setConexoes(List<Conexao> conexoes) {
        this.conexoes = conexoes;
    }

    @XmlElement(name = "transition")
    public List<Transicao> getTransicoes() {
        return transicoes;
    }

    public void setTransicoes(List<Transicao> transicoes) {
        this.transicoes = transicoes;
    }
}
