/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public class Sala  implements Serializable {
    
    private ArrayList<Usuario> usuarios;
    private String nome;
    private String senha;
    private Long id; 
    
    public Sala(ArrayList<Usuario> usuarios, String nome, String senha) {
        this.usuarios = usuarios;
        this.nome = nome;
        this.senha = senha;
    }

    public Sala(ArrayList<Usuario> usuarios, String nome) {
        this.usuarios = usuarios;
        this.nome = nome;
    }
    
    public Sala(String nome){
        this.nome = nome;
    }
    
    public Sala() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
