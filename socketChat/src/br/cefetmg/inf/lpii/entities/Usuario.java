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
    
    private Long id;
    private String nome;
    private ArrayList<Sala> salas;

    public Usuario(String nome) {
        this.nome = nome;
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
    
}
