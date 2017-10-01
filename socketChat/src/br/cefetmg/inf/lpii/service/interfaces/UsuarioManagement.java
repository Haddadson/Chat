/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.service.interfaces;

import br.cefetmg.inf.lpii.entities.Usuario;
import br.cefetmg.inf.lpii.exception.BusinessException;
import br.cefetmg.inf.lpii.exception.PersistenceException;

/**
 *
 * @author GABRIEL HADDAD
 */

public interface UsuarioManagement {
    public Long inserir(Usuario usuario) throws BusinessException, PersistenceException;
    public boolean atualizar(Usuario usuario) throws BusinessException, PersistenceException;
    public boolean remover(Long id) throws PersistenceException;
    public Usuario get(Long id) throws PersistenceException;
}
