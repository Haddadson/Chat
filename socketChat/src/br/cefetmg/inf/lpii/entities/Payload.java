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
public class Payload {
    
    private TipoOperacao op;
    private Mensagem mensagem;
    private Usuario usuario;
    private Sala sala;
    
    public Payload(TipoOperacao op) {
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
    
}