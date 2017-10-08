/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author aluno
 */
public class SocketChatView extends Application {
    
    //Define o Stage que receberá as telas
    private Stage stage; 

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    //Define o método para inicialização do JavaFX
    /*Ao carregar a tela de inicioChat, o controller dessa interface é chamado,
     *tratando o cadastro de usuários e chamando as outras telas conforme haja
     *necessidade
     */
    @Override
    public void start(Stage stage) throws Exception {
        //Carrega o arquivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("inicioChat.fxml"));
        this.stage = stage;
        
        //Define a tela em uma cena
        Scene scene = new Scene(root);
        
        //Define a cena em um Stage
        this.stage.setScene(scene);
        //Exibe o stage atual
        this.stage.show();
    }
    
    //Main do programa
    public static void main(String[] args) {
        //Inicia a aplicação JavaFX
        launch(args);
    }
    
}
