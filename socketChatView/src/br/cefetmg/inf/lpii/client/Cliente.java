/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.client;

import br.cefetmg.inf.lpii.entities.Usuario;
import br.cefetmg.inf.lpii.exception.BusinessException;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import java.io.IOException;


/**
 *
 * @author João Pedro Renan
 */
public class Cliente {
    private Usuario usuario;
    
    public Cliente(){
        try {
            
            String host = "localhost";
            int porta = 2223;

            ChatProxy proxy = new ChatProxy(host, porta);
            
            
            proxy.criarConta(usuario);
            
            new Thread(proxy).start();

        } catch (IOException | PersistenceException | BusinessException e) {
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
