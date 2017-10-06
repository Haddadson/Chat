/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.client;

import br.cefetmg.inf.lpii.entities.Payload;
import static br.cefetmg.inf.lpii.entities.TipoOperacao.CRIAR_CONTA;
import br.cefetmg.inf.lpii.entities.Usuario;
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
public class ChatProxy implements Runnable {

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
    
    public ChatProxy(String host, int porta, Usuario usuario) throws IOException {
        this.socket = new Socket(host, porta);
        this.out = new ObjectOutputStream(socket.getOutputStream());
        payload = new Payload(CRIAR_CONTA, usuario);
    }
    
    @Override
    public void run() {
        
        ObjectInputStream in;
        
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())){
            out.writeObject(payload);
        } catch (IOException ex){
            Logger.getLogger(ChatProxy.class.getName()).log(Level.SEVERE, null, ex); 
        }
        
    }
    
}
