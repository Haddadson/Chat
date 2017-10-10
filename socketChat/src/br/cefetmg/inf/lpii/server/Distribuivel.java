/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.server;

import br.cefetmg.inf.lpii.entities.Mensagem;
import br.cefetmg.inf.lpii.entities.Sala;
import br.cefetmg.inf.lpii.entities.Usuario;
import br.cefetmg.inf.lpii.exception.BusinessException;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author João Pedro Renan
 */
public interface Distribuivel {
    public void enviarMensagem(Mensagem mensagem) throws PersistenceException, BusinessException, IOException;
    public void inserirUsuarioNaSala(Usuario usuario, Sala sala) throws IOException, BusinessException, PersistenceException;
    public void removerUsuarioDaSala(Usuario usuario, Sala sala) throws IOException;
    public void criarSala(Sala sala) throws BusinessException, PersistenceException, IOException;
    public void criarConta(Usuario usuario) throws IOException, BusinessException, PersistenceException;
    public void removerSala(Sala sala) throws IOException, BusinessException, PersistenceException;
    public void teste() throws IOException;
    public void retornarMensagens() throws IOException, BusinessException, PersistenceException;
    public void retornarUsuarios() throws IOException, BusinessException, PersistenceException;
    public void retornarSalas() throws IOException, BusinessException, PersistenceException;
}
