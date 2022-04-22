package br.unisinos.edu.PetriNetSimulator.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Transicao extends Objeto {

    public Transicao(int id) {
        super(id);
    }
}
