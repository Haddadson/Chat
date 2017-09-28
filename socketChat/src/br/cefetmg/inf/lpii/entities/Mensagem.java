/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.entities;


/**
 *
 * @author Aluno
 */
public class Mensagem {

    private Usuario remetente;
    private Usuario destinatario;
    private Sala salasDestino;
    private String conteudo;

    public Mensagem(Usuario remetente, Usuario destinatario, String conteudo) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.conteudo = conteudo;
    }

    public Mensagem(Usuario remetente, Sala salasDestino, String conteudo) {
        this.remetente = remetente;
        this.salasDestino = salasDestino;
        this.conteudo = conteudo;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public Sala getSalasDestino() {
        return salasDestino;
    }

    public void setSalasDestino(Sala salasDestino) {
        this.salasDestino = salasDestino;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    
}
