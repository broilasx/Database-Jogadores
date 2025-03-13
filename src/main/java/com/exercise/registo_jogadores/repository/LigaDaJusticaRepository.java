package com.exercise.registo_jogadores.repository;

import java.util.List;

public class LigaDaJusticaRepository implements CodinomeRepository {
    @Override
    public List<String> buscarCodinomes() {
        return List.of("Superman", "Batman", "Mulher Maravilha", "Flash", "Aquaman", "Ciborgue");
    }
    
}
