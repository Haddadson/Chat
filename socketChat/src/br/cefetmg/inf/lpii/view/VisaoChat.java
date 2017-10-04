/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.view;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author GABRIEL HADDAD
 */
public class VisaoChat extends Application {
    
    private Stage stagePrimario;
    private BorderPane layoutInicial;
    
    @Override
    public void start(Stage stagePrimario) throws Exception {
        this.stagePrimario = stagePrimario;
        this.stagePrimario.setTitle("Chat");
        
        inicializar();
            
    }
    
    public void inicializar() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VisaoChat.class.getResource("chatGUI.fxml"));
            layoutInicial = (BorderPane) loader.load();
            
            Scene scene = new Scene(layoutInicial);
            stagePrimario.setScene(scene);
            stagePrimario.show();
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    //retornar ao stage inicial
    public Stage getStagePrimario() {
        return stagePrimario;
    }
}
