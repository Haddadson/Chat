/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.entities;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Usuario() {}
    
    public Usuario(String nome) {
        this.nome = nome;
    }

    public Usuario(String nome, Socket socket) {
        this.nome = nome;
        this.socket = socket;
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

    public Usuario(Long id, String nome, ArrayList<Sala> salas, Socket socket) {
        this.id = id;
        this.nome = nome;
        this.salas = salas;
        this.socket = socket;
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

    public Socket getSocket() {
        return socket;
    }

    public void setPorta(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }
    
    

}
