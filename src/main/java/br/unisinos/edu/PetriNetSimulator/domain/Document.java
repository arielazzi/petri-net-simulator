package br.unisinos.edu.PetriNetSimulator.domain;

import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "document")
@Setter
public class Document {
    private RedeDePetri redeDePetri;

    public Document(){}
    public Document(RedeDePetri redeDePetri) {
        this.redeDePetri = redeDePetri;
    }

    @XmlElement(name = "subnet")
    public RedeDePetri getRedeDePetri() {
        return redeDePetri;
    }
}
