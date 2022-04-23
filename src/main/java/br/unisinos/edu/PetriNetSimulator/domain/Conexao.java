package br.unisinos.edu.PetriNetSimulator.domain;

import lombok.Data;

@Data
public class Conexao {
    private int sourceId;
    private int destinationId;
    private int multiplicity;

    private String type;

    public Conexao() {}

    public Conexao(int sourceId, int destinationId, int multiplicity, String type) {
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.multiplicity = multiplicity;
        this.type = type;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public int getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(int multiplicity) {
        this.multiplicity = multiplicity;
    }
}
