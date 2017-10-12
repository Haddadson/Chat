/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.client;

import br.cefetmg.inf.lpii.controllers.TelaPrincipalController;
import br.cefetmg.inf.lpii.entities.Payload;
import br.cefetmg.inf.lpii.entities.Sala;
import javafx.collections.FXCollections;

/**
 *
 * @author João Pedro Renan
 */
public class Desencapsulador {
    
    private static Desencapsulador desencapsulador;
    private static ChatProxy proxy;
    private static TelaPrincipalController controller;
    
    public static Desencapsulador getInstance() {
        if (Desencapsulador.desencapsulador == null) {
            throw new IllegalArgumentException("Instancia nao existe. Exige construtor com controller");
        }
        return desencapsulador;
    }
    
    public static Desencapsulador getInstance(TelaPrincipalController controller) {
        if (Desencapsulador.desencapsulador != null) {
            throw new IllegalArgumentException("Instancia ja existe com controller");
        }
        Desencapsulador.desencapsulador = new Desencapsulador(controller);
        return desencapsulador;
    }

    private Desencapsulador(TelaPrincipalController controller) {
        Desencapsulador.controller = controller;
    }
    
  
    public void encaminhar(Payload pl) {
        if (pl == null || pl.getOp() == null) {
            throw new IllegalArgumentException("payload nula");
        }
        switch (pl.getOp()) {
            case CRIACAO_USUARIO_NEGADA: 
                break;
            case CRIAR_CONTA:
                Desencapsulador.controller.registrarUsuarioCompartilhado(pl.getUsuario());
                break;
            case CRIAR_SALA:
                // envia pro controller dados da sala recebida
                Sala novaSala = pl.getSala();
                
                break;
            case ENVIAR_MENSAGEM:
                Desencapsulador.controller.exibirMensagens(pl.getMensagem());
                break;
            case INSERIR_USUARIO_NA_SALA:
                Desencapsulador.controller.entrarSalaResponse(pl.getSala(), pl.getMensagens());
                break;
            case INSERIR_USUARIO_NA_SALA_SENHA:
                break;
            case REMOVER_USUARIO_DA_SALA:
                break;
            case RETORNAR_MENSAGENS:
                //envia pro controller lista com mensagens recentes
                Desencapsulador.controller.exibirMensagens(FXCollections.observableArrayList(pl.getMensagens()));
                break;
            case RETORNAR_SALAS:
                //envia pro controller lista com todas as salas
                Desencapsulador.controller.registrarSalas(pl.getSalas());
                break;
            //retorna os usuarios de uma sala
            case RETORNAR_USUARIOS:
                Desencapsulador.controller.registrarUsuarios(pl.getSala().getUsuarios());
                break;
            case TESTE:
                break;
        }
    }
    
}
