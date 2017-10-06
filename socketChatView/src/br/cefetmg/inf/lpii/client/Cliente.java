/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.client;

import br.cefetmg.inf.lpii.entities.Usuario;
import java.io.IOException;


/**
 *
 * @author João Pedro Renan
 */
public class Cliente {
    private Usuario usuario;
    public Cliente(String nome){
        try {
            usuario = new Usuario(nome);
            String host = "localhost";
            int porta = 2223;

            ChatProxy proxy = new ChatProxy(host, porta, usuario);
            new Thread(proxy).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
