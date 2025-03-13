package com.exercise.registo_jogadores.repository;

import org.springframework.stereotype.Repository;

import com.exercise.registo_jogadores.model.Jogador;

import org.springframework.jdbc.core.simple.JdbcClient;

@Repository
public class JogadorRepository {
    private final JdbcClient jdbcClient;

    public JogadorRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Jogador salvar(Jogador jogador) {
        jdbcClient.sql("""
            INSERT INTO jogadores (nome, email, telefone, codinome, grupo_codinome)
            VALUES (:nome, :email, :telefone, :codinome, :grupoCodinome)
        """)
        .param("nome", jogador.nome())
        .param("email", jogador.email())
        .param("telefone", jogador.telefone())
        .param("codinome", jogador.codinome())
        .param("grupoCodinome", jogador.grupoCodinome())
        .update();
    return jogador;     
    }
}

