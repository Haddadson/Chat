/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.controllers;

import br.cefetmg.inf.lpii.entities.Payload;
import br.cefetmg.inf.lpii.entities.Usuario;

/**
 *
 * @author aluno
 */
public class Compartilhado {
    private static Usuario usuario;
    private static Payload payload;
    
    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        Compartilhado.usuario = usuario;
    }
    
    
    
}
