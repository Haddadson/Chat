/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.controllers;

import br.cefetmg.inf.lpii.client.ChatProxy;
import br.cefetmg.inf.lpii.client.Cliente;
import br.cefetmg.inf.lpii.client.Desencapsulador;
import br.cefetmg.inf.lpii.entities.Mensagem;
import br.cefetmg.inf.lpii.entities.Sala;
import br.cefetmg.inf.lpii.entities.Usuario;
import br.cefetmg.inf.lpii.exception.BusinessException;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Pós Graduação
 */
public class TelaPrincipalController implements Initializable{

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
    private TextArea insereMensagem;
    
    @FXML
    private TextField nomeUsuario;
    
    @FXML
    private TextField nomeSala;
    
    @FXML
    private Label teste;
  
    
    private Mensagem mensagem;
    private Usuario usuario;
    private Timestamp currentTime;
    private ChatProxy proxy;
    private Usuario usuarioCompartilhado;
    private Sala sala;
    private Usuario destino;
    private Cliente cliente;
    private ListView<?> listaSalas;
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Sala> salasRegistradas;
    private Desencapsulador des;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        des = Desencapsulador.getInstance(this);
        cliente = new Cliente();
        proxy = ChatProxy.getInstance();
        
        /*listaSalas.setOnMouseClicked((MouseEvent mouseEvent) -> {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        entrarSala();
                    }
                }
        });*/
    }
    
    public boolean checaInputConta() {
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
    
    public boolean checaInputSalas() {
        String mensagemErro = "";

        //Checa se o nome é nulo ou possui tamanho igual à 0
        if (nomeSala.getText() == null || nomeSala.getText().length() == 0) {
            mensagemErro += "Nome de Sala inválido!\n";
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
    public void inserirUsuario(ActionEvent e) throws Exception{
        if(checaInputConta()){
            usuarioCompartilhado = new Usuario(nomeUsuario.getText());
            Compartilhado.setUsuario(usuarioCompartilhado);
            proxy.criarConta(usuarioCompartilhado);
        }
    }
    
    public void teste() {
        System.out.println("testando controller");
    }
    
    public void inserirSala(ActionEvent e) {
        if (checaInputSalas()) {
            //Adiciona o usuário da sessão atual na lista de pessoas da sala
            listaUsuarios = new ArrayList();
            listaUsuarios.add(Compartilhado.getUsuario());

            //Caso a sala tenha senha, um construtor específico é chamado, definindo-a
            
            sala = new Sala(listaUsuarios, nomeSala.getText(), null);
            try {
                proxy.criarSala(sala);
            } catch (BusinessException | PersistenceException | IOException ex) {
                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Método para envio da mensagem ao clicar no botão 
    public void enviarMensagem(ActionEvent e) {
        if (insereMensagem.getText() != null) {
            //Seleciona a data e hora atuais
            currentTime = new java.sql.Timestamp(System.currentTimeMillis());
            //Define uma mensagem
            
            //TODO: definir o destinatário (usuario)
            mensagem = new Mensagem(usuarioCompartilhado, usuario, insereMensagem.getText(), currentTime);
            try {
                proxy.enviarMensagem(mensagem);
            } catch (PersistenceException | BusinessException | IOException ex) {
                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
       }
    }

    public void definirDestinatario() {

    }
    
    public void entrarSala() {
        
        ArrayList<Usuario> users= sala.getUsuarios();
        users.add(Compartilhado.getUsuario());
        try {
            proxy.inserirUsuarioNaSala(Compartilhado.getUsuario(), sala);
        } catch (IOException | BusinessException | PersistenceException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    //Método para exibição das mensagens recebidas pela sala ou usuário selecionados
    public void exibirMensagens() {

        //TODO: Exibir as mensagens no painelMensagem
    }
    
    //Método para exibição das salas existentes na tela
    public void exibirSalas() throws IOException, BusinessException, PersistenceException {
        proxy.retornarSalas();
        //TODO: Exibir as salas no painelSalas
    }
    
    //Método para exibição dos Usuários logados na tela
    public void exibirUsuarios() {
        //TODO: Exibir os usuarios no painelUsuarios
        
    }

}
