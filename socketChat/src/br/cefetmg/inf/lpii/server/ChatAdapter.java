/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.server;

import br.cefetmg.inf.lpii.DAO.MensagemDAOImpl;
import br.cefetmg.inf.lpii.DAO.interfaces.MensagemDAO;
import br.cefetmg.inf.lpii.entities.Mensagem;
import br.cefetmg.inf.lpii.entities.Payload;
import br.cefetmg.inf.lpii.entities.Sala;
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
public class ChatAdapter implements Runnable, Distribuivel {

    private final Socket socket;
    private final MensagemManagementImpl mensagemManagementImpl;
    private final MensagemDAO mensagemDAO;
    private final Distribuidor dist;
    
    public ChatAdapter(Socket socket) throws IOException {
        this.socket = socket;
        this.dist = new Distribuidor(this.socket);
        this.mensagemDAO = MensagemDAOImpl.getInstance();
        this.mensagemManagementImpl = new MensagemManagementImpl(mensagemDAO);
    }
    
    
    @Override
    public void run() {
        
        ObjectOutputStream out;
        Payload payload;
        
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            payload = (Payload) in.readObject();
            switch (payload.getOp()) {
                case CRIAR_CONTA: 
                    dist.criarConta(payload.getUsuario());
                    break;
                case CRIAR_SALA:
                    dist.criarSala(payload.getSala());
                    break;
                case ENVIAR_MENSAGEM:
                    dist.enviarMensagem(payload.getMensagem());
                    break;
                case INSERIR_USUARIO_NA_SALA:
                    dist.inserirUsuarioNaSala(payload.getUsuario(), payload.getSala(), payload.getSala().getSenha());
                    break;
                case REMOVER_SALA:
                    //todo
                    break;
                case REMOVER_USUARIO_DA_SALA:
                    dist.removerUsuarioDaSala(payload.getUsuario(), payload.getSala());
                    break;
            }
        } catch (IOException | ClassNotFoundException | BusinessException | PersistenceException ex) {
            Logger.getLogger(ChatAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void enviarMensagem(Mensagem mensagem) throws PersistenceException, BusinessException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inserirUsuarioNaSala(Usuario usuario, Sala sala, String senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removerUsuarioDaSala(Usuario usuario, Sala sala) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void criarSala(Sala sala) throws BusinessException, PersistenceException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void criarConta(Usuario usuario) throws IOException, BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
