/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.service;

import br.cefetmg.inf.lpii.DAO.interfaces.UsuarioDAO;
import br.cefetmg.inf.lpii.service.interfaces.UsuarioManagement;
import br.cefetmg.inf.lpii.entities.Usuario;
import br.cefetmg.inf.lpii.exception.BusinessException;
import br.cefetmg.inf.lpii.exception.PersistenceException;

/**
 *
 * @author GABRIEL HADDAD
 */
public class UsuarioManagementImpl implements UsuarioManagement{

    private final UsuarioDAO usuarioDAO;

    public UsuarioManagementImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    
    @Override
    public Long inserir(Usuario usuario) throws BusinessException, PersistenceException {
        if (usuario == null) {
            throw new BusinessException("Usuario a ser inserido nao pode ser nulo");
        }
        if (usuario.getNome() == null) {
            throw new BusinessException("Nome do usuario a ser inserido nao pode ser nulo");
        }
        if (usuario.getNome().trim().isEmpty()) {
            throw new BusinessException("Nome do usuario a ser inserido nao pode ser vazio");
        }
        return usuarioDAO.inserir(usuario);
    }

    @Override
    public boolean atualizar(Usuario usuario) throws BusinessException, PersistenceException {
        if (usuario == null) {
            throw new BusinessException("Usuario a ser inserido nao pode ser nulo");
        }
        if (usuario.getNome() == null) {
            throw new BusinessException("Nome do usuario a ser inserido nao pode ser nulo");
        }
        if (usuario.getNome().trim().isEmpty()) {
            throw new BusinessException("Nome do usuario a ser inserido nao pode ser vazio");
        }
        if (usuario.getId() == null) {
            throw new BusinessException("Id do usuario a ser atualizado nao pode ser nulo");
        }
        return usuarioDAO.atualizar(usuario);
    }

    @Override
    public boolean remover(Long id) throws BusinessException, PersistenceException {
        if (id == null) {
            throw new BusinessException("Id do usuario a ser removido nao pode ser nulo");
        }
        return usuarioDAO.remover(id);
    }

    @Override
    public Usuario get(Long id) throws BusinessException, PersistenceException {
        if (id == null) {
            throw new BusinessException("Id do usuario nao pode ser nulo");
        }
        return usuarioDAO.get(id);
    }
    
}
