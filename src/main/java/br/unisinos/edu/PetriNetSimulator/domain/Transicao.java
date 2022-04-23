package br.unisinos.edu.PetriNetSimulator.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Transicao extends Objeto {

    private String label;
    public Transicao(int id, String label) {
        super(id);
        this.label = label;
    }
}
