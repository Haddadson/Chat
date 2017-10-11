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
import java.io.EOFException;
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
            while (true) {
                try {
                    payload = (Payload) in.readObject();
                    switch (payload.getOp()) {
                        case CRIAR_CONTA: 
                            this.criarConta(payload.getUsuario());
                            break;
                        case CRIAR_SALA:
                            this.criarSala(payload.getSala());
                            this.retornarSalas();
                            break;
                        case ENVIAR_MENSAGEM:
                            this.enviarMensagem(payload.getMensagem());
                            break;
                        case INSERIR_USUARIO_NA_SALA:
                            this.inserirUsuarioNaSala(payload.getUsuario(), payload.getSala());
                            break;
                        case REMOVER_SALA:
                            this.removerSala(payload.getSala());
                            break;
                        case REMOVER_USUARIO_DA_SALA:
                            this.removerUsuarioDaSala(payload.getUsuario(), payload.getSala());
                            break;
                        case TESTE:
                            System.out.println("recebeu");
                            this.teste();
                            break;
                        case RETORNAR_USUARIOS:
                            this.retornarUsuarios(payload.getSala().getId());
                            break;
                        case RETORNAR_SALAS:
                            this.retornarSalas();
                            break;
                        case RETORNAR_MENSAGENS:
                            this.retornarMensagens(payload.getSala().getId());
                            break;
                    }
                } catch (EOFException ex) {}

            }
        
        } catch (IOException | ClassNotFoundException | BusinessException | PersistenceException ex) {
            Logger.getLogger(ChatAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void enviarMensagem(Mensagem mensagem) throws PersistenceException, BusinessException, IOException {
        dist.enviarMensagem(mensagem);
    }

    @Override
    public void inserirUsuarioNaSala(Usuario usuario, Sala sala) {
        dist.inserirUsuarioNaSala(usuario, sala);
    }

    @Override
    public void removerUsuarioDaSala(Usuario usuario, Sala sala) throws IOException {
        dist.removerUsuarioDaSala(usuario, sala);
    }

    @Override
    public void criarSala(Sala sala) throws BusinessException, PersistenceException, IOException {
        dist.criarSala(sala);
    }

    @Override
    public void criarConta(Usuario usuario) throws IOException, BusinessException, PersistenceException {
        dist.criarConta(usuario);
    }

    @Override
    public void removerSala(Sala sala) throws IOException, BusinessException, PersistenceException {
        dist.removerSala(sala);
    }

    @Override
    public void teste() throws IOException {
        dist.teste();
    }
    @Override
    public void retornarMensagens(Long salaID) throws IOException, BusinessException, PersistenceException {
        dist.retornarMensagens(salaID);
    }

    @Override
    public void retornarUsuarios(Long salaID) throws IOException, BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void retornarSalas() throws IOException, BusinessException, PersistenceException {
        dist.retornarSalas();
    }

    
}
