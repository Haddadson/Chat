/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Aluno
 */
public class Mensagem implements Serializable {

    private Usuario remetente;
    private Usuario usuarioDestino;
    private Sala salaDestino;
    private String conteudo;
    private Timestamp horaEnvio;
    private Long idMensagem;

    public Mensagem () {}
    
    public Mensagem(Usuario remetente, Usuario usuarioDestino, String conteudo, Timestamp horaEnvio) {
        this.remetente = remetente;
        this.usuarioDestino = usuarioDestino;
        this.conteudo = conteudo;
        this.horaEnvio = horaEnvio;
    }

    public Mensagem(Usuario remetente, Sala salaDestino, String conteudo, Timestamp horaEnvio) {
        this.remetente = remetente;
        this.salaDestino = salaDestino;
        this.conteudo = conteudo;
        this.horaEnvio = horaEnvio;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(Usuario usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

    public Sala getSalaDestino() {
        return salaDestino;
    }

    public void setSalaDestino(Sala salaDestino) {
        this.salaDestino = salaDestino;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Timestamp getHoraEnvio() {
        return horaEnvio;
    }

    public void setHoraEnvio(Timestamp horaEnvio) {
        this.horaEnvio = horaEnvio;
    }

    public Long getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Long idMensagem) {
        this.idMensagem = idMensagem;
    }
  
}
