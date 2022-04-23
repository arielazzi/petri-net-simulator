package br.unisinos.edu.PetriNetSimulator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RedeDePetri {
    private List<Lugar> lugares;
    private List<Conexao> conexoes;
    private List<Transicao> transicoes;

    @XmlElement(name = "place")
    public List<Lugar> getLugares() {
        return lugares;
    }

    @XmlElement(name = "arc")
    public List<Conexao> getConexoes() {
        return conexoes;
    }

    @XmlElement(name = "transition")
    public List<Transicao> getTransicoes() {
        return transicoes;
    }
}
