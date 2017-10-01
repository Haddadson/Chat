/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.service.interfaces;

import br.cefetmg.inf.lpii.entities.Mensagem;

/**
 *
 * @author GABRIEL HADDAD
 */
public interface MensagemManagement {
    public Long inserir(Mensagem mensagem);
    public boolean remover(Long id);
    public boolean atualizar(Mensagem mensagem);
    public Mensagem get(Long id);
}
