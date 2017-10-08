/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.controllers;

import br.cefetmg.inf.lpii.entities.Usuario;

/**
 *
 * @author aluno
 */

//Classe para entidades compartilhadas pela camada de visão durante a sessão atual
public class Compartilhado {
    //Atributo do tipo Usuario para representar o usuário atual da sessão
    private static Usuario usuario;
    
    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        Compartilhado.usuario = usuario;
    }
    
    
    
}
