/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.controllers;

import br.cefetmg.inf.lpii.DAO.UsuarioDAOImpl;
import br.cefetmg.inf.lpii.DAO.interfaces.UsuarioDAO;
import br.cefetmg.inf.lpii.entities.Usuario;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Pós Graduação
 */
public class InicioChatController implements Initializable {

    @FXML
    private TextField nomeUsuario;
    @FXML
    private Button defineUsuario;
    
    private Usuario usuario;
    private UsuarioDAOImpl usuarioDAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuario = new Usuario();
        try {
            inserirUsuario();
        } catch (PersistenceException ex) {
            Logger.getLogger(InicioChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    public void definirUsuario() throws PersistenceException{
        try {
            if (nomeUsuario != null) {
                usuario.setNome(nomeUsuario.getText());
                usuarioDAO.inserir(usuario);
            }
            
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            throw new PersistenceException(ex);
        }

    }
    
    public void inserirUsuario() throws PersistenceException {
        definirUsuario();
    }
}
