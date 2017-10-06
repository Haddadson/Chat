/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.controllers;

import br.cefetmg.inf.lpii.client.Cliente;
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
    
    private Cliente cliente;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               
    }    
    
    @FXML
    private void inserirUsuario(){
        cliente = new Cliente(nomeUsuario.getText());
    }
   
}
