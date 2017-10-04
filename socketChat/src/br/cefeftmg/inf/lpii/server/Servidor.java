/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefeftmg.inf.lpii.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro Renan
 */
public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket socket;
            while (true) {
                // define socket e porta do server
                socket = new ServerSocket(2223);
                System.out.println("Aguardando...");
                // recebe conexao
                Socket packet = socket.accept();
                System.out.println("Conexao estabelecida...");
                // apos receber, inicia thread do adapter
                ChatAdapter chatAdapter = new ChatAdapter(packet);
                new Thread(chatAdapter).start();
                // fecha conexao
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
