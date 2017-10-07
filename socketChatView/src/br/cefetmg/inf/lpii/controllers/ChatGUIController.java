/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.controllers;

import br.cefetmg.inf.lpii.entities.Mensagem;
import br.cefetmg.inf.lpii.entities.Usuario;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import java.sql.Timestamp;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pós Graduação
 */
public class ChatGUIController extends Application implements Initializable {

    @FXML
    private ScrollPane painelMensagem;
    @FXML
    private ScrollPane painelSalas;
    @FXML
    private CheckBox destinatarioAll;
    @FXML
    private CheckBox destinatarioPrivate;
    @FXML
    private Button enviarMensagem;
    @FXML
    private TextArea insereMensagem;
    @FXML
    private ScrollPane painelUsuarios;
    @FXML
    private Button criarSala;
    
    private Mensagem mensagem;
    
    private  Usuario usuario;
    
    private Timestamp currentTime;
    
    private Stage stage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
   
    public void captarMensagem() throws PersistenceException {
        if (insereMensagem.getText() != null) {
            currentTime = new java.sql.Timestamp(System.currentTimeMillis());
            
            mensagem = new Mensagem(usuario, usuario, insereMensagem.getText(), currentTime);

       }
   }
    
    public void enviarMensagem() throws PersistenceException {
        //captarMensagem();
    }
    @FXML
    public void exibeCriarSala(ActionEvent e) throws Exception {
        start(stage);
//TODO: Chamar FXML criarSala
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("criarSala.fxml"));
        
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    
}
