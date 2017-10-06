/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.client;

import br.cefetmg.inf.lpii.entities.Mensagem;
import br.cefetmg.inf.lpii.entities.Payload;
import br.cefetmg.inf.lpii.entities.Sala;
import br.cefetmg.inf.lpii.entities.TipoOperacao;
import static br.cefetmg.inf.lpii.entities.TipoOperacao.CRIAR_CONTA;
import br.cefetmg.inf.lpii.entities.Usuario;
import br.cefetmg.inf.lpii.exception.BusinessException;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import br.cefetmg.inf.lpii.server.Distribuivel;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro Renan
 */
public class ChatProxy implements Runnable, Distribuivel {

    private final Socket socket;
    private final ObjectOutputStream out;  
    private Payload payload;
    private static ArrayList<Integer> portsBeingUsed = new ArrayList();
    
    public ChatProxy(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
    }

    public ChatProxy(String host, int porta) throws IOException {
        this.socket = new Socket(host, porta);
        this.out = new ObjectOutputStream(socket.getOutputStream());
    }
    
    public ChatProxy(String host, int porta, Payload payload) throws IOException {
        this.socket = new Socket(host, porta);
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.payload = payload;
    }
    
    @Override
    public void run() {
        
        ObjectInputStream in;
        /*while (true) {
         Socket packet = this.socket.ac
        }*/
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())){
            out.writeObject(payload);
        } catch (IOException ex){
            Logger.getLogger(ChatProxy.class.getName()).log(Level.SEVERE, null, ex); 
        }
        
    }

    @Override
    public void enviarMensagem(Mensagem mensagem) throws PersistenceException, BusinessException, IOException {
        Payload pl = new Payload(TipoOperacao.ENVIAR_MENSAGEM, mensagem);
        this.out.writeObject(pl);
        this.out.flush();
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
        Payload pl = new Payload(TipoOperacao.CRIAR_CONTA, usuario);
        this.out.writeObject(pl);
        this.out.flush();
    }

    @Override
    public void removerSala(Sala sala) throws IOException, BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
