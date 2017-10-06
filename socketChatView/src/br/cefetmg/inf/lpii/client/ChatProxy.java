/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.client;

import br.cefetmg.inf.lpii.entities.Usuario;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author João Pedro Renan
 */
public class ChatProxy implements Runnable {

    private final Socket socket;
    private final ObjectOutputStream out;  
    private Usuario usuario;
    
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
        this.usuario = usuario;
    }
    
    @Override
    public void run() {
        
    }
    
}
