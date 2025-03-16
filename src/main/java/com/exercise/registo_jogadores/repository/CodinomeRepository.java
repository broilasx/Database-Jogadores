package com.exercise.registo_jogadores.repository;

import com.exercise.registo_jogadores.web.CodinomeDTO;

public interface CodinomeRepository {
    CodinomeDTO buscarCodinomes() throws Exception;
}
