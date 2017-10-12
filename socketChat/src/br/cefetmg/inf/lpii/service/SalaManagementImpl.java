/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.service;

import br.cefetmg.inf.lpii.DAO.interfaces.SalaDAO;
import br.cefetmg.inf.lpii.service.interfaces.SalaManagement;
import br.cefetmg.inf.lpii.entities.Sala;
import br.cefetmg.inf.lpii.entities.Usuario;
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
        /*Checa se os dados necessários para que a sala criada por um usuário
         *seja persistida no banco de dados não estão nulos. Caso estejam, uma exceção 
        ´*é lançada, o processo é interrompido e o motivo da interrupção é mostrado ao usuário.
         */
        
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
        // Checa se a id da sala é valida, para que ela seja removida da base da dados
        
        if (id == null) {
            throw new BusinessException("ID da sala a ser removida nao pode ser nulo");
        }
        return salaDAO.remover(id);
    }

    @Override
    public boolean atualizar(Sala sala) throws BusinessException, PersistenceException {
        /*Checa se os dados necessários para que uma sala já existente seja atualizada
         *no banco de dados não estão nulos. Caso estejam, uma exceção é lançada, o
        ´*processo é interrompido e o motivo da interrupção é mostrado ao usuário.
         */
        
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
        // Checa se o id da Sala é valido, para que os dados relacionados àquela id sejam retornados
        
        if (id == null) {
            throw new BusinessException("Id da sala nao pode ser nulo");
        }
         return salaDAO.get(id);
    }

    @Override
    public ArrayList<Sala> getTodas() throws PersistenceException {
        // Todas as salas presentes na base de dados são retornadas
        
         return salaDAO.getTodas();
    }

    public ArrayList<Usuario> getUsuarios(Long salaID) throws BusinessException, PersistenceException {
        if (salaID == null) {
            throw new BusinessException("Id da sala nao pode ser nulo");
        }
        return salaDAO.getUsuarios(salaID);
    }

    @Override
    public void inserirUsuario(Long usuarioID, Long salaID) throws BusinessException, PersistenceException {
        if (usuarioID == null || salaID == null) {
            throw new BusinessException("Id da sala ou usuario nao pode ser nulo");
        }
        salaDAO.inserirUsuario(usuarioID, salaID);
    }
    
}
