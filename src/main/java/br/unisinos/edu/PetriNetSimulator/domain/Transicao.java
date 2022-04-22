package br.unisinos.edu.PetriNetSimulator.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transicao extends Objeto {

    private int id;
    
    public Transicao(int id) {
        super(id);
    }
}
