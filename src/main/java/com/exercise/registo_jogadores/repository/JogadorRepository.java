package com.exercise.registo_jogadores.repository;

import org.springframework.stereotype.Repository;

import com.exercise.registo_jogadores.model.GrupoCodinome;
import com.exercise.registo_jogadores.model.Jogador;

import java.util.List;

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

    public List<String> listarCodinomesPorGrupo(GrupoCodinome grupoCodinome) {
        return jdbcClient.sql("SELECT distinct(codinomes) FROM JOGADORES WHERE grupo_codinome = :grupoCodinome")
            .param("grupoCodinome", grupoCodinome.name())
            .query(String.class)
            .list();
    }
}

