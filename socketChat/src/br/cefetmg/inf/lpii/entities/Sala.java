/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.entities;

import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public class Sala {
    
    private ArrayList<Usuario> usuarios;
    private String nome;
    private String senha;

    public Sala(ArrayList<Usuario> usuarios, String nome, String senha) {
        this.usuarios = usuarios;
        this.nome = nome;
        this.senha = senha;
    }

    public Sala(ArrayList<Usuario> usuarios, String nome) {
        this.usuarios = usuarios;
        this.nome = nome;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
