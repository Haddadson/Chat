/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.System.in;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro Renan
 */
public class ChatAdapter implements Runnable {

    private final Socket socket;

    public ChatAdapter(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        ObjectOutputStream out;
        
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            
        } catch (IOException ex) {
            Logger.getLogger(ChatAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
