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
public class Usuario {
    // atributos persistidos
    private Long id;
    private String nome;
    private ArrayList<Sala> salas;
    
    // atributos nao persistidos
    private int porta;

    public Usuario() {}
    
    public Usuario(String nome) {
        this.nome = nome;
    }

    public Usuario(String nome, int porta) {
        this.nome = nome;
        this.porta = porta;
    }
    
    public Usuario(String nome, ArrayList<Sala> salas) {
        this.nome = nome;
        this.salas = salas;
    }

    public Usuario(Long id, String nome, ArrayList<Sala> salas) {
        this.id = id;
        this.nome = nome;
        this.salas = salas;
    }

    public Usuario(Long id, String nome, ArrayList<Sala> salas, int porta) {
        this.id = id;
        this.nome = nome;
        this.salas = salas;
        this.porta = porta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public void setSalas(ArrayList<Sala> salas) {
        this.salas = salas;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

}
