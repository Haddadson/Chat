/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.controllers;

import br.cefetmg.inf.lpii.client.Cliente;
import br.cefetmg.inf.lpii.entities.Payload;
import br.cefetmg.inf.lpii.entities.TipoOperacao;
import br.cefetmg.inf.lpii.entities.Usuario;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    private Usuario usuarioCompartilhado;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cliente = new Cliente();
    }    
    
    public boolean checaInput() {
        String mensagemErro = "";

        if (nomeUsuario.getText() == null || nomeUsuario.getText().length() == 0) {
            mensagemErro += "Nome de usuário inválido!\n";
        }

        if (mensagemErro.length() != 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campo inválido");
            alert.setHeaderText("Por favor, insira um nome para a sala");
            alert.setContentText(mensagemErro);
            alert.showAndWait();

            return false;
        } 
        return true;
    }
    
    @FXML
    private void inserirUsuario(){
        if(checaInput()){
            usuarioCompartilhado = new Usuario(nomeUsuario.getText());
            Compartilhado.setUsuario(usuarioCompartilhado);
            cliente.setUsuario(usuarioCompartilhado);
        }
        
    }
}
