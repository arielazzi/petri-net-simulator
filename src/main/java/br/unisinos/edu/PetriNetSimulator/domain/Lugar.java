package br.unisinos.edu.PetriNetSimulator.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Lugar extends Objeto{
    private int tokens;
    private String label;

    public Lugar() {
        super();
    }

    public Lugar(int id, int tokens, String label) {
        super(id);
        this.tokens = tokens;
        this.label = label;
    }
}
