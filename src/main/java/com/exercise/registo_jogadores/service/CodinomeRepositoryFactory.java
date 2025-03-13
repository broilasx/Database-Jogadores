package com.exercise.registo_jogadores.service;

import org.springframework.stereotype.Component;

import com.exercise.registo_jogadores.model.GrupoCodinome;
import com.exercise.registo_jogadores.repository.CodinomeRepository;
import com.exercise.registo_jogadores.repository.LigaDaJusticaRepository;
import com.exercise.registo_jogadores.repository.VingadoresRepository;

@Component
public class CodinomeRepositoryFactory {
    private final LigaDaJusticaRepository ligaDaJusticaRepository;
    private final VingadoresRepository vingadoresRepository;

    public CodinomeRepositoryFactory(LigaDaJusticaRepository ligaDaJusticaRepository, VingadoresRepository vingadoresRepository) {
        this.ligaDaJusticaRepository = ligaDaJusticaRepository;
        this.vingadoresRepository = vingadoresRepository;
    }

    public CodinomeRepository create(GrupoCodinome grupoCodinome) {
        return switch (grupoCodinome) {
            case LIGA_DA_JUSTICA -> ligaDaJusticaRepository;
            case VINGADORES -> vingadoresRepository;
        };
    }
}
