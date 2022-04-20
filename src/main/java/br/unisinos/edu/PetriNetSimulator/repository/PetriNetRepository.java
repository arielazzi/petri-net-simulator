package br.unisinos.edu.PetriNetSimulator.repository;

import br.unisinos.edu.PetriNetSimulator.domain.Conexao;
import br.unisinos.edu.PetriNetSimulator.domain.Lugar;
import br.unisinos.edu.PetriNetSimulator.domain.Transicao;

import java.util.ArrayList;
import java.util.List;

public class PetriNetRepository {
    public static List<Lugar> lugares = new ArrayList<>();
    public static List<Conexao> conexoes = new ArrayList<>();

    public static List<Transicao> transicoes = new ArrayList<>();
}
