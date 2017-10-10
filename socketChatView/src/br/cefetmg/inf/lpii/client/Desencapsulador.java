/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.client;

import br.cefetmg.inf.lpii.entities.Payload;

/**
 *
 * @author João Pedro Renan
 */
public class Desencapsulador {
    
    private ChatProxy proxy;
    
    public Desencapsulador(ChatProxy proxy) {
        this.proxy = proxy;
    }
    
    public void encaminhar(Payload pl) {
        if (pl == null || pl.getOp() == null) {
            throw new IllegalArgumentException("payload nula");
        }
        switch (pl.getOp()) {
            case CRIAR_CONTA:
                break;
            case CRIAR_SALA:
                break;
            case ENVIAR_MENSAGEM:
                break;
            case INSERIR_USUARIO_NA_SALA:
                break;
            case INSERIR_USUARIO_NA_SALA_SENHA:
                break;
            case REMOVER_SALA:
                break;
            case REMOVER_USUARIO_DA_SALA:
                break;
            case RETORNAR_MENSAGENS:
                break;
            case RETORNAR_SALAS:
                break;
            case RETORNAR_USUARIOS:
                break;
            case TESTE:
                break;
        }
    }
    
}
