package br.unisinos.edu.PetriNetSimulator.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "document")
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

    public void setRedeDePetri(RedeDePetri redeDePetri) {
        this.redeDePetri = redeDePetri;
    }
}
