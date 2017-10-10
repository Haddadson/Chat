/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.service;

import br.cefetmg.inf.lpii.DAO.interfaces.MensagemDAO;
import br.cefetmg.inf.lpii.service.interfaces.MensagemManagement;
import br.cefetmg.inf.lpii.entities.Mensagem;
import br.cefetmg.inf.lpii.exception.BusinessException;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import java.util.ArrayList;

/**
 *
 * @author GABRIEL HADDAD
 */
public class MensagemManagementImpl implements MensagemManagement {

    private final MensagemDAO mensagemDAO;

    public MensagemManagementImpl(MensagemDAO mensagemDAO) {
        this.mensagemDAO = mensagemDAO;
    }
    
    
    
    @Override
    public Long inserir(Mensagem mensagem) throws BusinessException, PersistenceException {
        /*Checa se os dados necessários para que a mensagem enviada por um usuário
         *seja persistida no banco de dados não estão nulos. Caso estejam, uma exceção 
        ´*é lançada, o processo é interrompido e o motivo da interrupção é mostrado ao usuário.
         */
        
        if (mensagem == null) {
            throw new BusinessException("Mensagem nao instanciada");
        }
        if (mensagem.getConteudo() == null){
            throw new BusinessException("Conteudo da mensagem nao pode ser nulo ");
        }
        if (mensagem.getConteudo().trim().isEmpty()) {
            throw new BusinessException("Conteudo da mensagem nao pode ser vazio");
        }
        if (mensagem.getUsuarioDestino() == null && mensagem.getSalaDestino() == null ) {
            throw new BusinessException("Destinatario da mensagem nao pode ser nulo");
        }
        if (mensagem.getHoraEnvio() == null) {
            throw new BusinessException("Horario de envio da mensagem nao pode ser nulo ");
        }
        if (mensagem.getRemetente() == null) {
            throw new BusinessException("Remetente da mensagem nao pode ser nulo");
        }
        
        return mensagemDAO.inserir(mensagem);
        
    }

    @Override
    public boolean remover(Long id) throws BusinessException, PersistenceException {
        //Checa se a id da mensagem é valida, para que ela seja excluida da base de dados
        
        if (id == null) {
            throw new BusinessException("Id da mensagem nao pode ser nulo");
        }
        
        return mensagemDAO.remover(id);
    }

    @Override
    public boolean atualizar(Mensagem mensagem) throws BusinessException, PersistenceException {
        /*Checa se os dados necessários para que uma mensagem seja atualizada na 
         * base de dados não estão nulos. Caso estejam, uma exceção é lançada, o 
        ´* processo é interrompido e o motivo da interrupção é mostrado ao usuário.
         */
        
        if (mensagem == null) {
            throw new BusinessException("Mensagem nao instanciada");
        }
        if (mensagem.getConteudo() == null){
            throw new BusinessException("Conteudo da mensagem nao pode ser nulo ");
        }
        if (mensagem.getConteudo().trim().isEmpty()) {
            throw new BusinessException("Conteudo da mensagem nao pode ser vazio");
        }
        if (mensagem.getUsuarioDestino() == null && mensagem.getSalaDestino() == null ) {
            throw new BusinessException("Destinatario da mensagem nao pode ser nulo");
        }
        if (mensagem.getHoraEnvio() == null) {
            throw new BusinessException("Horario de envio da mensagem nao pode ser nulo ");
        }
        if (mensagem.getRemetente() == null) {
            throw new BusinessException("Remetente da mensagem nao pode ser nulo");
        }
        if (mensagem.getIdMensagem() == null) {
            throw new BusinessException("ID da mensagem a ser atualizada nao pode ser nulo");
        }
        return mensagemDAO.atualizar(mensagem);
    }

    @Override
    public Mensagem get(Long id) throws BusinessException, PersistenceException {
        //Checa se a id da mensagem é valida, para que ela seja retornada da base de dados
        
        if (id == null) {
            throw new BusinessException("Id da mensagem nao pode ser nulo");
        }
        return mensagemDAO.get(id);
    }

    @Override
    public ArrayList<Mensagem> getMensagens(Long idSala) throws BusinessException, PersistenceException {
        if (idSala == null) {
            throw new BusinessException("Id da sala nao pode ser nulo");
        }
        return mensagemDAO.getMensagens(idSala);
    }
    
}
