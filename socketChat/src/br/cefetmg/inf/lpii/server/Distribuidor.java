/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.server;

import br.cefetmg.inf.lpii.DAO.MensagemDAOImpl;
import br.cefetmg.inf.lpii.DAO.SalaDAOImpl;
import br.cefetmg.inf.lpii.DAO.UsuarioDAOImpl;
import br.cefetmg.inf.lpii.DAO.interfaces.MensagemDAO;
import br.cefetmg.inf.lpii.DAO.interfaces.SalaDAO;
import br.cefetmg.inf.lpii.DAO.interfaces.UsuarioDAO;
import br.cefetmg.inf.lpii.entities.Mensagem;
import br.cefetmg.inf.lpii.entities.Payload;
import br.cefetmg.inf.lpii.entities.Sala;
import br.cefetmg.inf.lpii.entities.TipoOperacao;
import br.cefetmg.inf.lpii.entities.Usuario;
import br.cefetmg.inf.lpii.exception.BusinessException;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import br.cefetmg.inf.lpii.service.MensagemManagementImpl;
import br.cefetmg.inf.lpii.service.SalaManagementImpl;
import br.cefetmg.inf.lpii.service.UsuarioManagementImpl;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author João Pedro Renan
 */
public class Distribuidor implements Distribuivel {
    
    private SalaDAO salaDAO;
    private MensagemDAO mensagemDAO;
    private UsuarioDAO usuarioDAO;
    private final MensagemManagementImpl mensagemManagementImpl;
    private final SalaManagementImpl salaManagementImpl;
    private final UsuarioManagementImpl usuarioManagementImpl;
    private ObjectOutputStream out;
    private static final HashSet<Socket> SOCKETS = new HashSet();

    public Distribuidor(Socket socket) throws IOException {
        
        this.usuarioDAO = UsuarioDAOImpl.getInstance();
        this.mensagemDAO = MensagemDAOImpl.getInstance();
        this.salaDAO = SalaDAOImpl.getInstance();
        
        this.usuarioManagementImpl = new UsuarioManagementImpl(usuarioDAO);
        this.mensagemManagementImpl = new MensagemManagementImpl(mensagemDAO);
        this.salaManagementImpl = new SalaManagementImpl(salaDAO);
        
        this.out = new ObjectOutputStream(socket.getOutputStream());
        Distribuidor.SOCKETS.add(socket);
    }
    
    @Override
    public synchronized void enviarMensagem(Mensagem mensagem) throws IOException, BusinessException, PersistenceException {
        // registra mensagem no DB
        mensagemManagementImpl.inserir(mensagem);
        
        // se for msg p/ 1 unico usuario
        if (mensagem.getUsuarioDestino() != null) {
                this.out = mensagem.getUsuarioDestino().getOut();
                this.out.writeObject(mensagem);
                this.out.flush();
        } else {
            // se for p/ uma sala, envia p todos 
            for (Usuario usuario : mensagem.getSalaDestino().getUsuarios()) {
                this.out = usuario.getOut();
                this.out.writeObject(mensagem);
                this.out.flush();
            }
        }
    }
    
    @Override
    public synchronized void inserirUsuarioNaSala(Usuario usuario, Sala sala) {
        if (sala.getSenha() == null) {
            sala.getUsuarios().add(usuario);
        } /* else {
            if (sala.getSenha() != null && senha == null) {
                throw new IllegalArgumentException("Tentativa de entrar em sala com senha sem passa-la como parametro");
            } else if (sala.getSenha() != null && senha.equals(sala.getSenha())) {
                throw new IllegalArgumentException("Senha incorreta");
            }
        }*/
    }
    
    @Override
    public synchronized void removerUsuarioDaSala(Usuario usuario, Sala sala) throws IOException {
        if(!sala.getUsuarios().remove(usuario)) {
            throw new IllegalArgumentException("Esse usuario nao pertence a essa sala");
        }
        if (sala.getUsuarios().isEmpty()) {
            for (Socket socket : SOCKETS) {
                this.out = new ObjectOutputStream(socket.getOutputStream());
                // TODO: implementar payload falando pra remover sala
                //this.out.writeObject();
                //this.out.flush();
            }
        }
    }
    
    @Override
    public synchronized void criarSala(Sala sala) throws BusinessException, PersistenceException, IOException {
        sala.setId(salaManagementImpl.inserir(sala));
        this.out.writeObject(new Payload(TipoOperacao.CRIAR_SALA, sala));
        this.out.flush();
    }
    
    @Override
    public synchronized void criarConta(Usuario usuario) throws IOException, BusinessException, PersistenceException {
        usuario.setId(usuarioManagementImpl.inserir(usuario));
        this.out.writeObject(usuario);
        this.out.flush();
    }

    @Override
    public void removerSala(Sala sala) throws IOException, BusinessException, PersistenceException {
        this.out.writeBoolean(this.salaManagementImpl.remover(sala.getId()));
        this.out.flush();
    }

    @Override
    public void teste() throws IOException {
        Payload pl = new Payload(TipoOperacao.TESTE);
        this.out.writeObject(pl);
        this.out.flush();
    }
    @Override
    public void retornarMensagens(Long salaID) throws IOException, BusinessException, PersistenceException {
        Payload answerPl = new Payload(TipoOperacao.RETORNAR_MENSAGENS);
        answerPl.setMensagens(this.mensagemManagementImpl.getMensagens(salaID));
        this.out.writeObject(answerPl);
        this.out.flush();
    }

    @Override
    public void retornarUsuarios() throws IOException, BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void retornarSalas() throws IOException, BusinessException, PersistenceException {
        Payload answerPl = new Payload(TipoOperacao.RETORNAR_SALAS);
        answerPl.setSalas(this.salaManagementImpl.getTodas());
        this.out.writeObject(answerPl);
        this.out.flush();
    }

    
}
