package br.unisinos.edu.PetriNetSimulator.domain;

import lombok.Data;

@Data
public class Lugar {
    private int id;
    private int tokens;
    
    public Lugar() {
    }

    public Lugar(int id, int tokens) {
        this.id = id;
        this.tokens = tokens;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getTokens() {
        return tokens;
    }
    
    public void seToken(int tokens) {
        this.tokens = tokens;
    }
}
