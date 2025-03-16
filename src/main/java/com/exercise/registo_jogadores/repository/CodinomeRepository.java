package com.exercise.registo_jogadores.repository;

import java.util.List;

import com.exercise.registo_jogadores.web.CodinomeDTO;

public interface CodinomeRepository {
    CodinomeDTO buscarCodinomes() throws Exception;
}
