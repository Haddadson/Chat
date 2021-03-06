/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author João Pedro Renan
 */
public class Payload implements Serializable {
    
    private TipoOperacao op;
    private Mensagem mensagem;
    private Usuario usuario;
    private Sala sala;
    private ArrayList<Mensagem> mensagens;
    private ArrayList<Sala> salas;
    private ArrayList<Usuario> usuarios;
    
    public Payload() {}
    
    public Payload(TipoOperacao op) {
        this.op = op;
    }

    public Payload(TipoOperacao op, Mensagem mensagem) {
        this.op = op;
        this.mensagem = mensagem;
    }

    public Payload(TipoOperacao op, Usuario usuario) {
        this.op = op;
        this.usuario = usuario;
    }

    public Payload(TipoOperacao op, Sala sala) {
        this.op = op;
        this.sala = sala;
    }
    
    public TipoOperacao getOp() {
        return op;
    }

    public void setOp(TipoOperacao op) {
        this.op = op;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public ArrayList<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(ArrayList<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public void setSalas(ArrayList<Sala> salas) {
        this.salas = salas;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}