package com.exercise.registo_jogadores.repository;


import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import com.exercise.registo_jogadores.model.GrupoCodinome;
import com.exercise.registo_jogadores.web.CodinomeDTO;
import com.exercise.registo_jogadores.web.LigaDaJusticaDTO;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Repository
public class LigaDaJusticaRepository implements CodinomeRepository {
    @Override
    public CodinomeDTO buscarCodinomes() throws Exception {
        var codinomes = RestClient
            .builder()
            .baseUrl(GrupoCodinome.LIGA_DA_JUSTICA.getUri())
            .build()
            .get()
            .retrieve()
            .body(String.class);
        
        var xmlMapper = new XmlMapper();
        var ligaDaJustica = xmlMapper.readValue(codinomes, LigaDaJusticaDTO.class);
        return ligaDaJustica;
    }
    
}
