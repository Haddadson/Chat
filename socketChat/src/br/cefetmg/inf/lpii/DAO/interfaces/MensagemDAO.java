/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.DAO.interfaces;

import br.cefetmg.inf.lpii.entities.Mensagem;
import br.cefetmg.inf.lpii.entities.Sala;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public interface MensagemDAO {
    public Long inserir(Mensagem mensagem) throws PersistenceException;
    public boolean remover(Long id) throws PersistenceException;
    public boolean atualizar(Mensagem mensagem) throws PersistenceException;
    public Mensagem get(Long id)throws PersistenceException;
    public ArrayList<Mensagem> getMensagens(Long idSala) throws PersistenceException;
}
