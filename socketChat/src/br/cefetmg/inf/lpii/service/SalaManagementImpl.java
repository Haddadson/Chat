/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.service;

import br.cefetmg.inf.lpii.DAO.interfaces.SalaDAO;
import br.cefetmg.inf.lpii.service.interfaces.SalaManagement;
import br.cefetmg.inf.lpii.entities.Sala;
import br.cefetmg.inf.lpii.exception.BusinessException;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import java.util.ArrayList;

/**
 *
 * @author GABRIEL HADDAD
 */
public class SalaManagementImpl implements SalaManagement{

    private final SalaDAO salaDAO;

    public SalaManagementImpl(SalaDAO salaDAO) {
        this.salaDAO = salaDAO;
    }
    
    @Override
    public Long inserir(Sala sala) throws BusinessException, PersistenceException {
        if (sala == null) {
            throw new BusinessException("Sala a ser inserida nao pode ser nula");
        }
        if (sala.getNome() == null) {
            throw new BusinessException("Nome da sala nao pode ser nulo");
        }
        if (sala.getNome().trim().isEmpty()) {
            throw new BusinessException("Nome da sala a ser inserida nao pode ser vazio");
        }
        if (sala.getUsuarios() == null) {
            throw new BusinessException("Sala a ser criada nao pode ser vazia");
        }
        return salaDAO.inserir(sala);
    }

    @Override
    public boolean remover(Long id) throws BusinessException, PersistenceException {
        if (id == null) {
            throw new BusinessException("ID da sala a ser removida nao pode ser nulo");
        }
        return salaDAO.remover(id);
    }

    @Override
    public boolean atualizar(Sala sala) throws BusinessException, PersistenceException {
        if (sala == null) {
            throw new BusinessException("Sala a ser inserida nao pode ser nula");
        }
        if (sala.getNome() == null) {
            throw new BusinessException("Nome da sala nao pode ser nulo");
        }
        if (sala.getNome().trim().isEmpty()) {
            throw new BusinessException("Nome da sala a ser inserida nao pode ser vazio");
        }
        if (sala.getUsuarios() == null) {
            throw new BusinessException("Sala a ser criada nao pode ser vazia");
        }
        if (sala.getId() == null) {
            throw new BusinessException("Id da sala a ser atualizada nao pode ser nulo");
        }
         return salaDAO.atualizar(sala);
    }

    @Override
    public Sala get(Long id) throws BusinessException, PersistenceException {
        if (id == null) {
            throw new BusinessException("Id da sala nao pode ser nulo");
        }
         return salaDAO.get(id);
    }

    @Override
    public ArrayList<Sala> getTodas() throws PersistenceException {
         return salaDAO.getTodas();
    }
    
}
