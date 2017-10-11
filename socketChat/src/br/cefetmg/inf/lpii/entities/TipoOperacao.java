/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.entities;

/**
 *
 * @author João Pedro Renan
 */
public enum TipoOperacao {
    
    CRIAR_CONTA(1),
    ENVIAR_MENSAGEM(2),
    INSERIR_USUARIO_NA_SALA(3),
    REMOVER_USUARIO_DA_SALA(4),
    CRIAR_SALA(5),
    REMOVER_SALA(6),
    INSERIR_USUARIO_NA_SALA_SENHA(7),
    TESTE(8),
    RETORNAR_USUARIOS(9),
    RETORNAR_SALAS(10),
    RETORNAR_MENSAGENS(11),
    CRIACAO_USUARIO_NEGADA(12);
    
    private final int valor;

    private TipoOperacao(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
}
