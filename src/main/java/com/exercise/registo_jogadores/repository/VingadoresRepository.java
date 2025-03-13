package com.exercise.registo_jogadores.repository;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import com.exercise.registo_jogadores.model.GrupoCodinome;
import com.exercise.registo_jogadores.web.VingadoresDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class VingadoresRepository implements CodinomeRepository {

    @Override
    public List<String> buscarCodinomes() throws Exception {
        var codinomes = RestClient
            .builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN_VALUE)
            .baseUrl(GrupoCodinome.VINGADORES.getUri())
            .build()
            .get()
            .retrieve()
            .body(String.class);
        
        var objectMapper = new ObjectMapper();
        var vingadores = objectMapper.readValue(codinomes, VingadoresDTO.class);

        return vingadores.getCodinomes();
    }
}
