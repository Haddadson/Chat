/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.client;

import br.cefetmg.inf.lpii.entities.Payload;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author João Pedro Renan
 */
public class ChatProxy {

    private static ChatProxy chatProxy = null;
    private static ArrayList<Integer> portsBeingUsed = new ArrayList();
    private final Socket socket;
    private CanalDeEntrada canalDeEntrada;
    private ObjectOutputStream out;  
    private Payload payload;
    
    private ChatProxy(Socket socket) {
        this.socket = socket;
        this.criarCanalDeEntrada();
    }
  
    public static ChatProxy getInstance(Socket socket) {
        if (chatProxy != null) {
            throw new IllegalArgumentException("Instancia ja existe com outro socket");
        }
        return new ChatProxy(socket);
    }
    
    public static ChatProxy getInstance() {
        if (chatProxy == null) {
            throw new IllegalArgumentException("Instancia nao existe. Exige construtor com socket");
        }
        return chatProxy;
    }
    
    
    public void criarCanalDeEntrada() {
        this.canalDeEntrada = new CanalDeEntrada(this.socket);
        new Thread(canalDeEntrada).start();
    }
    
}
