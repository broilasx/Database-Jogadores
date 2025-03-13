package com.exercise.registo_jogadores.repository;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

@Repository
public class VingadoresRepository implements CodinomeRepository {

    @Override
    public List<String> buscarCodinomes() {
        var codinomes = RestClient
            .builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN_VALUE)

}
