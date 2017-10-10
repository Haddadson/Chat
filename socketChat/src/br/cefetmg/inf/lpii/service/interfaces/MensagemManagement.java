/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.service.interfaces;

import br.cefetmg.inf.lpii.entities.Mensagem;
import br.cefetmg.inf.lpii.exception.BusinessException;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import java.util.ArrayList;

/**
 *
 * @author GABRIEL HADDAD
 */
public interface MensagemManagement {
    public Long inserir(Mensagem mensagem) throws BusinessException, PersistenceException;
    public boolean remover(Long id) throws BusinessException, PersistenceException;
    public boolean atualizar(Mensagem mensagem) throws BusinessException, PersistenceException;
    public Mensagem get(Long id) throws BusinessException, PersistenceException;
    public ArrayList<Mensagem> getMensagens(Long idSala) throws BusinessException, PersistenceException;
}
