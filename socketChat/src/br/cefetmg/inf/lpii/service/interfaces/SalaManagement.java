/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.service.interfaces;

import br.cefetmg.inf.lpii.entities.Sala;
import br.cefetmg.inf.lpii.exception.BusinessException;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import java.util.ArrayList;

/**
 *
 * @author GABRIEL HADDAD
 */
public interface SalaManagement {
    public Long inserir(Sala sala) throws BusinessException, PersistenceException;
    public boolean remover(Long id) throws BusinessException, PersistenceException;
    public boolean atualizar(Sala sala) throws BusinessException, PersistenceException;
    public Sala get(Long id) throws BusinessException, PersistenceException;
    public ArrayList<Sala> getTodas() throws PersistenceException;
    public void inserirUsuario(Long usuarioID, Long salaID) throws BusinessException, PersistenceException;
}
