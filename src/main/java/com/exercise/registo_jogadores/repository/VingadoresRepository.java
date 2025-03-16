package com.exercise.registo_jogadores.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import com.exercise.registo_jogadores.model.GrupoCodinome;
import com.exercise.registo_jogadores.web.CodinomeDTO;
import com.exercise.registo_jogadores.web.VingadoresDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class VingadoresRepository implements CodinomeRepository {

    @Override
    public CodinomeDTO buscarCodinomes() throws Exception {
        var codinomes = RestClient
            .builder()
            .baseUrl(GrupoCodinome.VINGADORES.getUri())
            .build()
            .get()
            .retrieve()
            .body(String.class);
        
        var objectMapper = new ObjectMapper();
        var vingadores = objectMapper.readValue(codinomes, VingadoresDTO.class);

        return vingadores;
    }
}
