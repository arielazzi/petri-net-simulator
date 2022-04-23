package br.unisinos.edu.PetriNetSimulator.domain;

import lombok.Data;

@Data
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


    public int getTokens() {
        return tokens;
    }
    
    public void seToken(int tokens) {
        this.tokens = tokens;
    }
}
