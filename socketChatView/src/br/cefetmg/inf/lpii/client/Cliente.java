/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.client;

import br.cefetmg.inf.lpii.view.VisaoChat;

/**
 *
 * @author João Pedro Renan
 */
public class Cliente {
    public static void main(String[] args) {
        try {
            String host = "localhost";
            int porta = 2223;
            
            VisaoChat.launch(args);
            ChatProxy proxy = new ChatProxy(host, porta);
            new Thread(proxy).start();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
