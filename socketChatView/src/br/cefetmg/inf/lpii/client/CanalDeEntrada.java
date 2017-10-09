/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.client;

import br.cefetmg.inf.lpii.entities.Payload;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro Renan
 */
public class CanalDeEntrada implements Runnable {

    private ObjectInputStream in;
    private ChatProxy proxy;
    
    public CanalDeEntrada(Socket socket) {
        try {
            this.in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(CanalDeEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.proxy = ChatProxy.getInstance();
    }

    @Override
    public void run() {
        Payload payload;
        while (true) {
            try {
                payload = (Payload) in.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(CanalDeEntrada.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
