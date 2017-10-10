/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.client;

import br.cefetmg.inf.lpii.entities.Usuario;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author João Pedro Renan
 */
public class Cliente {
    //Atributo que recebe o usuário da sessão atual
    private Usuario usuario;
    
    public Cliente(){
        try {
            //Define o host e a porta para conexão
            String host = "localhost";
            int porta = 2223;
            
            //Chama o Proxy para fazer a transmissão de dados
            ChatProxy proxy = ChatProxy.getInstance(new Socket(host, porta));
            proxy.teste();
            proxy.teste();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
