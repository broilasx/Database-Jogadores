package com.exercise.registo_jogadores.exception;

public class GrupoCodinomeIndisponivelException extends IllegalArgumentException {
    public GrupoCodinomeIndisponivelException(String message) {
        super("Não existem codinomes disponíveis para o grupo disponivel");
    }
    
}
