package com.exercise.registo_jogadores.service;

import java.util.List;

import com.exercise.registo_jogadores.model.GrupoCodinome;
import com.exercise.registo_jogadores.model.Jogador;
import com.exercise.registo_jogadores.repository.JogadorRepository;

public class JogadorService {
    private final JogadorRepository jogadorRepository;
    private final CodinomeService codinomeService;

    public JogadorService(JogadorRepository jogadorRepository, CodinomeService codinomeService) {
        this.jogadorRepository = jogadorRepository;
        this.codinomeService = codinomeService;
    }
    
    public Jogador registarJogador(Jogador jogador) throws Exception {
        var codinomesEmUso = listarCodinomesEmUso(jogador.grupoCodinome());
        var novoCodinome = codinomeService.gerarCodinome(jogador.grupoCodinome(), codinomesEmUso);

        var novoJogador = new Jogador(
            jogador.nome(),
            jogador.email(),
            jogador.telefone(),
            novoCodinome,
            jogador.grupoCodinome()
        );
        return jogadorRepository.salvar(novoJogador);
    }

    private List<String> listarCodinomesEmUso(GrupoCodinome grupoCodinome) {
        return jogadorRepository.listarCodinomesPorGrupo(grupoCodinome);
    }
}
