package com.exercise.registo_jogadores.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exercise.registo_jogadores.exception.GrupoCodinomeIndisponivelException;
import com.exercise.registo_jogadores.model.GrupoCodinome;

@Service
public class CodinomeService {
    private final CodinomeRepositoryFactory codinomeRepositoryFactory;

    public CodinomeService(CodinomeRepositoryFactory codinomeRepositoryFactory) {
        this.codinomeRepositoryFactory = codinomeRepositoryFactory;
    }
    
    public String gerarCodinome(GrupoCodinome grupoCodinome, List<String> codinomesEmUso) throws Exception {
        var codinomesDisponiveis = listarCodinomesDisponiveis(grupoCodinome, codinomesEmUso);
        if (codinomesDisponiveis.isEmpty()) {
            throw new GrupoCodinomeIndisponivelException(null);
        }   
        
        var codinomeSorteado = sortearCodinome(codinomesDisponiveis);
        return codinomeSorteado;
    }

    private List<String> listarCodinomesDisponiveis(GrupoCodinome grupoCodinome, List<String> codinomesEmUso) throws Exception {
        var codinomes = buscarCodinomes(grupoCodinome);

        var codinomesDisponiveis = codinomes
            .stream()
            .filter(codinome -> !codinomesEmUso.contains(codinome))
            .toList();
        
        return codinomesDisponiveis;
    }

    private List<String> buscarCodinomes(GrupoCodinome grupoCodinome) throws Exception {
        var codinomeRepository = codinomeRepositoryFactory.create(grupoCodinome);
        return codinomeRepository.buscarCodinomes().getCodinomes();
    }

    private String sortearCodinome(List<String> codinomesDisponiveis) {
        return codinomesDisponiveis
            .get((int) (Math.random() * codinomesDisponiveis.size()));
    }
}
