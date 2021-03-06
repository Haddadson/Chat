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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;

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
    
    @FXML
    private TableColumn<Sala, String> colSalas;
    
    @FXML
    private TableColumn<Usuario, String> colUsuariosSala;
    
    @FXML
    private TableView<Sala> tabSalas;
   
    @FXML
    private TableView<Usuario> tabUsuarios;
    
    @FXML
    private ListView<String> tabMensagens;
    
    @FXML
    private Button botaoDefinirNomeUser;
    
    private Mensagem mensagem;
    private Usuario usuario;
    private Timestamp currentTime;
    private ChatProxy proxy;
    private Usuario usuarioCompartilhado;
    private Sala salaSendoExibida;
    private Usuario destino;
    private Cliente cliente;
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Usuario> listaUsuariosGeral;
    private ArrayList<Sala> salasRegistradas;
    private Desencapsulador des;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        des = Desencapsulador.getInstance(this);
        cliente = new Cliente();
        proxy = ChatProxy.getInstance();
        salasRegistradas = new ArrayList<>();
        requisitarSalas();

        //Define DoubleClick em sala para entrar e OneClick para exibir usuários
        tabSalas.setRowFactory(tv ->{
            TableRow<Sala> row = new TableRow<>();
            row.setOnMouseClicked(event -> {               
                Sala salaSelecionada = row.getItem();
                if(!row.isEmpty() && event.getButton()==MouseButton.PRIMARY && event.getClickCount() == 2){
                    entrarSalaRequest(salaSelecionada);
                    System.out.println("2click");
                }
                if(!row.isEmpty() && event.getButton()==MouseButton.PRIMARY && event.getClickCount() == 1){
                    requisitarUsuarios(salaSelecionada);
                    exibirUsuarios((ObservableList<Usuario>) salaSelecionada.getUsuarios());
                    System.out.println("1click");
                }
            });
            return row;
        });
        Sala teste5 = new Sala("oi");
        Usuario teste2 = new Usuario("Haddad");
        Mensagem msg = new Mensagem(teste2, teste5, "TESTE", currentTime);
        Mensagem msg2 = new Mensagem(teste2, teste5, "TESTE2", currentTime);
        exibirMensagens(msg);
        exibirMensagens(msg2);

    }
    
    public boolean checaInputConta() {
        
        //Checa se o nome do usuário é válido
        
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
            //Pega o nome do usuário inserido no campo e persiste na base de dados
            
            proxy.criarConta(new Usuario((nomeUsuario.getText())));
        }
    }
    
    public void registrarUsuarioCompartilhado(Usuario usuario) {
        
        //Defini o nome do usuário na conversa
        
        if (usuario.getId() != null) {
            this.usuarioCompartilhado = usuario;
            this.nomeUsuario.setDisable(true);
            this.botaoDefinirNomeUser.setVisible(false);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campo inválido");
            alert.setHeaderText("Por favor, insira um outro nome");
            alert.setContentText("Este nome já está sendo usado");
            alert.showAndWait();
        }
    }

    public void inserirSala(ActionEvent e) {
        if (checaInputSalas()) {
            //Adiciona o usuário da sessão atual na lista de pessoas da sala
            listaUsuarios = new ArrayList();
            listaUsuarios.add(Compartilhado.getUsuario());

            //Caso a sala tenha senha, um construtor específico é chamado, definindo-a
            
            Sala sala = new Sala(listaUsuarios, nomeSala.getText(), null);
            this.nomeSala.clear();
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
            mensagem = new Mensagem(usuarioCompartilhado, this.salaSendoExibida, insereMensagem.getText(), currentTime);
            try {
                proxy.enviarMensagem(mensagem);
            } catch (PersistenceException | BusinessException | IOException ex) {
                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
       }
    }

    public void definirDestinatario() {

    }
    
    public void entrarSalaResponse(Sala sala, ArrayList<Mensagem> mensagens) {
        // determina sala como sendo a exibida
        this.salaSendoExibida = sala;
        // exibe usuarios na sala
        System.out.println("Usuario entrou na sala " + sala.getId() );
        this.requisitarUsuarios(this.salaSendoExibida);
        this.exibirMensagens(FXCollections.observableArrayList(mensagens));
    }
    
    // resposta vem em entrarSalaResponse
    public void entrarSalaRequest(Sala sala) {
        try {
            System.out.println("teste");
            this.proxy.inserirUsuarioNaSala(this.usuarioCompartilhado, sala);
        } catch (IOException | BusinessException | PersistenceException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Método para exibição das mensagens recebidas pela sala ou usuário selecionados
    public void exibirMensagens(ObservableList<Mensagem> mensagens) {
        //TODO: Exibir as mensagens no painelMensagem
        ObservableList<String> lista = FXCollections.observableArrayList();
        if (mensagens.isEmpty()) {
            tabMensagens.setItems(lista);
        } else {
            for (int i = 0; i < mensagens.size(); i++) {
                lista.add(mensagens.get(i).getRemetente().getNome() + " diz: " + mensagens.get(i).getConteudo());
            }
            tabMensagens.setItems(lista);
        }
    }
    
    
    public void exibirMensagens(Mensagem mensagem) {
        //reenvia panew Sala().setId(salaID)new Sala().setId(salaID)new Sala().setId(salaID)new Sala().setId(salaID)new Sala().setId(salaID)new Sala().setId(salaID)new Sala().setId(salaID)ra exibirMensagens que recebe ArrayList.
        ArrayList<Mensagem> msg = new ArrayList();
        msg.add(mensagem);
        this.exibirMensagens(FXCollections.observableArrayList(msg));
    }
    
    //Método para exibição das salas existentes na tela
    

    // Após a chegada, será chamada a registrarSalas(List<Sala> salas)!
    public void requisitarSalas() {
        try {
            this.proxy.retornarSalas();
        } catch (IOException | BusinessException | PersistenceException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Chamado após requisitarSalas()
    public void registrarSalas(List<Sala> salas) {
        this.salasRegistradas = (ArrayList<Sala>) salas;
        this.exibirSalas(FXCollections.observableArrayList(salas));
    }
    
    //Exibe todas as salas presentes na base de dados no painel de Salas
    public void exibirSalas(ObservableList<Sala> listSala) {
        tabSalas.setItems(listSala);
        colSalas.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabSalas.setRowFactory(tv ->{
            TableRow<Sala> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Sala salaSelecionada = row.getItem();
                if(!row.isEmpty() && event.getButton()==MouseButton.PRIMARY && event.getClickCount() == 1){
                    entrarSalaRequest(salaSelecionada);
                }
            });
            return row;
        });

    }
    // apos a chegada da response, seá chamada registrarUsuariosResponse();
    public void requisitarUsuarios(Sala sala) {
        try {
            this.proxy.retornarUsuarios(sala.getId());
        } catch (IOException | BusinessException | PersistenceException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Chamado após requisitarUsuariosRequest
    public void registrarUsuarios(List<Usuario> usuarios) {
        this.listaUsuarios = (ArrayList<Usuario>) usuarios;
        this.exibirUsuarios(FXCollections.observableArrayList(usuarios));
    }
    
    //Método para exibição dos Usuários logados na tela
    public void exibirUsuarios(ObservableList<Usuario> usuarios) {
    //Exibe os usuários de cada sala no painel Usuarios
        
        tabUsuarios.setItems(usuarios);
        colUsuariosSala.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }
}
