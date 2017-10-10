/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.client;

import br.cefetmg.inf.lpii.entities.Payload;
import br.cefetmg.inf.lpii.entities.TipoOperacao;
import java.io.IOException;
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
    private static Socket socket;
    private static CanalDeEntrada canalDeEntrada;
    //private ObjectOutputStream out;  
    private Payload payload;
    private ObjectOutputStream out;
    
    private ChatProxy(Socket socket) throws IOException {
        ChatProxy.socket = socket;
        this.out  = new ObjectOutputStream(ChatProxy.socket.getOutputStream());
    }
  
    public static ChatProxy getInstance(Socket socket) throws IOException {
        if (chatProxy != null) {
            throw new IllegalArgumentException("Instancia ja existe com outro socket");
        }
        ChatProxy.chatProxy = new ChatProxy(socket);
        criarCanalDeEntrada(socket);
        return chatProxy;
    }
    
    public static ChatProxy getInstance() {
        if (chatProxy == null) {
            throw new IllegalArgumentException("Instancia nao existe. Exige construtor com socket");
        }
        
        return chatProxy;
    }
    
    
    private static void criarCanalDeEntrada(Socket socket) {
        ChatProxy.canalDeEntrada = new CanalDeEntrada(socket);
        new Thread(canalDeEntrada).start();
    }
    
    public void teste() throws IOException {
        out.writeObject(new Payload(TipoOperacao.TESTE));
        out.flush();
    }
    
}
