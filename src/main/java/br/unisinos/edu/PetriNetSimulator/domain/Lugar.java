package br.unisinos.edu.PetriNetSimulator.domain;

import lombok.Data;

@Data
public class Lugar extends Objeto{
    private int tokens;
    
    public Lugar() {
        super();
    }

    public Lugar(int id, int tokens) {
        super(id);
        this.tokens = tokens;
    }


    public int getTokens() {
        return tokens;
    }
    
    public void seToken(int tokens) {
        this.tokens = tokens;
    }
}
