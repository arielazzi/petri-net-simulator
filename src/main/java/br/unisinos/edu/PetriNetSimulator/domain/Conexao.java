package br.unisinos.edu.PetriNetSimulator.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class Conexao {
    private int sourceId;
    private int destinationId;
    private int multiplicity;

    private String type;

    public Conexao(int sourceId, int destinationId, int multiplicity, String type) {
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.multiplicity = multiplicity;
        this.type = type;
    }
}
