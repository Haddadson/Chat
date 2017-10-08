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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
    
    @FXML
    private Button confirmaSala;
    
    @FXML
    private Button cancelar;
    
    private Sala sala;
    private Stage salaStage;
    private ArrayList<Usuario> listaUsuarios;  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    //Método para verificar a inserção de dados válidos
    public boolean checaInput(){
        String mensagemErro = "";
        
         //Checa se o nome é nulo ou possui tamanho igual à 0
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
        }
        
        return true;
       
    }
    
    //Método para inserir a sala ao confirmar
    public void inserirSala() {
        if (checaInput()){
            //Adiciona o usuário da sessão atual na lista de pessoas da sala
            listaUsuarios = new ArrayList();
            listaUsuarios.add(Compartilhado.getUsuario());
            
            //Caso a sala tenha senha, um construtor específico é chamado, definindo-a
            if(senhaSala.getText()!= null || senhaSala.getText().length() != 0){
                sala = new Sala(listaUsuarios, nomeSala.getText(), senhaSala.getText());
            }
            //Caso não tenha senha, outro construtor é chamado, definindo-a sem senha
            else {
                sala = new Sala(listaUsuarios, nomeSala.getText(), null);
            }
            
            //A sala é empacotada em uma Payload e enviada ao proxy
            Payload payload = new Payload(TipoOperacao.CRIAR_SALA, sala);
            
        }
    }
    
    //Método que é chamado ao clicar no botão de confirmar criação de sala
    @FXML
    private void clickConfirmar(ActionEvent e) {
        
        //Chama o método de inserção
        inserirSala();
        
        //Fecha o Stage atual, retornando para a tela de Chat
        salaStage = (Stage) confirmaSala.getScene().getWindow();
        salaStage.close();
        
        //TODO: Chamar a tela de Chat (ChatGUI.fxml)
        
    }
    
    //Método que é chamado ao clicar no botão de cancelar criação de sala
    @FXML
    private void clickCancelar() {
        //Fecha o Stage atual, retornando para a tela de Chat
        salaStage = (Stage) cancelar.getScene().getWindow();
        salaStage.close();
        
        //TODO: Chamar a tela de Chat (ChatGUI.fxml)
    }
}

