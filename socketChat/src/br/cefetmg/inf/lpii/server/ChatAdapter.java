/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.server;

import br.cefetmg.inf.lpii.DAO.MensagemDAOImpl;
import br.cefetmg.inf.lpii.DAO.interfaces.MensagemDAO;
import br.cefetmg.inf.lpii.entities.Mensagem;
import br.cefetmg.inf.lpii.entities.Usuario;
import br.cefetmg.inf.lpii.exception.BusinessException;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import br.cefetmg.inf.lpii.service.MensagemManagementImpl;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro Renan
 */
public class ChatAdapter implements Runnable {

    private final Socket socket;
    private final MensagemManagementImpl mensagemManagementImpl;
    private final MensagemDAO mensagemDAO;
    
    public ChatAdapter(Socket socket) {
        this.socket = socket;
        this.mensagemDAO = MensagemDAOImpl.getInstance();
        this.mensagemManagementImpl = new MensagemManagementImpl(mensagemDAO);
    }
    
    
    @Override
    public void run() {
        
        ObjectOutputStream out;
        Mensagem mensagem;
        
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            mensagem = (Mensagem) in.readObject();
            mensagemManagementImpl.inserir(mensagem);
            if (mensagem.getUsuarioDestino() != null) {
                out = mensagem.getUsuarioDestino().getOut();
                out.writeObject(mensagem);
                out.flush();
            } else {
                for (Usuario usuario : mensagem.getSalaDestino().getUsuarios()) {
                    out = usuario.getOut();
                    out.writeObject(mensagem);
                    out.flush();
                }
            }
            
        } catch (IOException | ClassNotFoundException | BusinessException | PersistenceException ex) {
            Logger.getLogger(ChatAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
