/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.controllers;

import br.cefetmg.inf.lpii.client.ChatProxy;
import br.cefetmg.inf.lpii.client.Cliente;
import br.cefetmg.inf.lpii.entities.Usuario;
import br.cefetmg.inf.lpii.view.SocketChatView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    
    private Stage contaStage;
    private Cliente cliente;
    private SocketChatView run;
    private Usuario usuarioCompartilhado;
    private ChatProxy proxy;
    
    
    /**
     * Initializes the controller class.
     */
    
    public void setRun(SocketChatView run) {
        this.run = run;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cliente = new Cliente();
        proxy = ChatProxy.getInstance();
    }    
    
    public void defineStage(Stage stage) {
        contaStage = stage;
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
    
   
    public void inserirUsuario(ActionEvent e) throws Exception{
        if(checaInput()){
            usuarioCompartilhado = new Usuario(nomeUsuario.getText());
            Compartilhado.setUsuario(usuarioCompartilhado);
            proxy.criarConta(usuarioCompartilhado);
        }

        Stage stage = (Stage) defineUsuario.getScene().getWindow();
        stage.close();  
        abrirTelaPrincipal();
        
    }
    
    private void abrirTelaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../controllers/chatGUI.fxml"));
            AnchorPane telaChat = (AnchorPane) loader.load();
            run.getRootLayout().setCenter(telaChat);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
