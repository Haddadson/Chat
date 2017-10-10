/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.controllers;

import br.cefetmg.inf.lpii.client.ChatProxy;
import br.cefetmg.inf.lpii.entities.Mensagem;
import br.cefetmg.inf.lpii.entities.Usuario;
import br.cefetmg.inf.lpii.exception.BusinessException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pós Graduação
 */
public class ChatGUIController extends Application implements Initializable {

    /*Controller responsável por gerenciar a tela principal do Chat. Possui métodos
     *para exibir os usuários logados, salas criadas, mensagens da conversa e tratar
     *o envio de mensagens. Para ações que envolvam o banco de dados (inserção e remoção
     *de dados), uma payload será criada com os dados a serem tratados. Essa será enviada
     *ao cliente, que por sua vez o enviará ao proxy e transmitirá ao servidor.
    */
    
    @FXML
    private ScrollPane painelMensagem;
    
    @FXML
    private ScrollPane painelSalas;
    
    @FXML
    private ScrollPane painelUsuarios;
    
    
    @FXML
    private Button enviarMensagem;
    
    @FXML
    private Button criarSala;
    
    @FXML
    private TextArea insereMensagem;
    
    
    private Mensagem mensagem;
    
    private Usuario usuario;
    
    private Timestamp currentTime;
    
    private Stage stage;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
    }
   
    //Método para selecionar o texto no campo insereMensagem
    public void captarMensagem() {
        if (insereMensagem.getText() != null) {
            //Seleciona a data e hora atuais
            currentTime = new java.sql.Timestamp(System.currentTimeMillis());
            //Define uma mensagem
            mensagem = new Mensagem(usuario, usuario, insereMensagem.getText(), currentTime);

       }
   }
    
    //Método para envio da mensagem ao clicar no botão 
    @FXML
    public void enviarMensagem(ActionEvent e) throws PersistenceException {
        captarMensagem();
        //TODO: enviar mensagem selecionada através de Payload para o Proxy
    }
    
    //Método para exibir a tela criarSala
    @FXML
    public void exibeCriarSala(ActionEvent e) throws Exception {
        start(stage);
        
    }
    
    //Método para exibição das mensagens recebidas pela sala ou usuário selecionados
    public void exibirMensagens() {
        
        //TODO: Exibir as mensagens no painelMensagem
    }
    
    //Método para exibição das salas existentes na tela
    public void eixibirSalas() {
        //TODO: Exibir as salas no painelSalas
    }
    
    //Método para exibição dos Usuários logados na tela
    public void eixibirUsuarios() {
        //TODO: Exibir os usuarios no painelUsuarios
    }

    //Método para definir o Stage com a tela criarSala
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("criarSala.fxml"));
        
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    
}
