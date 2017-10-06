/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.controllers;

import br.cefetmg.inf.lpii.DAO.SalaDAOImpl;
import br.cefetmg.inf.lpii.entities.Payload;
import br.cefetmg.inf.lpii.entities.Sala;
import br.cefetmg.inf.lpii.entities.TipoOperacao;
import br.cefetmg.inf.lpii.entities.Usuario;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pós Graduação
 */
public class CriarSalaController implements Initializable {
    
    @FXML
    private TextField nomeSala;
    @FXML
    private PasswordField senhaSala;
    
    private Sala sala;
    private SalaDAOImpl salaDAO;
    private Stage salaStage;
    private ArrayList<Usuario> listaUsuarios;  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void defineStage(Stage stage){
        salaStage = stage;
    }
    
    public boolean checaInput(){
        String mensagemErro = "";
        
        if (nomeSala.getText() == null || nomeSala.getText().length() == 0){
            mensagemErro += "Nome de Sala inválido!\n";
        } 
        
        if (mensagemErro.length() != 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Campo inválido");
            alert.setHeaderText("Por favor, insira um nome para a sala");
            alert.setContentText(mensagemErro);
            alert.showAndWait();
            
            return false;
        } else {
            return true;
        }
    }
    
    public void insereSala() {
        if (checaInput()){
            listaUsuarios = new ArrayList();
            listaUsuarios.add(Compartilhado.getUsuario());
            if(senhaSala.getText()!= null || senhaSala.getText().length() != 0){
                sala = new Sala(listaUsuarios, nomeSala.getText(), senhaSala.getText());
            }
            else {
                sala = new Sala(listaUsuarios, nomeSala.getText(), null);
            }
            Payload payload = new Payload(TipoOperacao.CRIAR_SALA, sala);
            
        }
    }
    
    @FXML
    private void clickConfirmar() {
        insereSala();
        salaStage.close();
    }
    
    @FXML
    private void clickCancelar() {
        salaStage.close();
    }
}

