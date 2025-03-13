package com.exercise.registo_jogadores.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exercise.registo_jogadores.model.GrupoCodinome;

@Service
public class CodinomeService {
    private final CodinomeRepositoryFactory codinomeRepositoryFactory;

    public CodinomeService(CodinomeRepositoryFactory codinomeRepositoryFactory) {
        this.codinomeRepositoryFactory = codinomeRepositoryFactory;
    }
    
    public String gerarCodinome(GrupoCodinome grupoCodinome, List<String> codinomesEmUso) {
        var codinomesDisponiveis = listarCodinomesDisponiveis(grupoCodinome, codinomesEmUso);
        if (codinomesDisponiveis.isEmpty()) {
            throw new Exception("Não há codinomes disponíveis para o grupo " + grupoCodinome.getNome());
        }   
        
        var codinomeSorteado = sortearCodinome(codinomesDisponiveis);
        return codinomeSorteado;
    }

    private List<String> listarCodinomesDisponiveis(GrupoCodinome grupoCodinome, List<String> codinomesEmUso) {
        var codinomes = buscarCodinomes(grupoCodinome);

        var codinomesDisponiveis = codinomes
            .stream()
            .filter(codinome -> !codinomesEmUso.contains(codinome))
            .toList();
        
        return codinomesDisponiveis;
    }

    private List<String> buscarCodinomes(GrupoCodinome grupoCodinome) {
        var codinomeRepository = codinomeRepositoryFactory.create(grupoCodinome);
    }
}
