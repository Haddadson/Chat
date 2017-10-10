/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.client;

import br.cefetmg.inf.lpii.entities.Mensagem;
import br.cefetmg.inf.lpii.entities.Payload;
import br.cefetmg.inf.lpii.entities.Sala;
import br.cefetmg.inf.lpii.entities.Usuario;
import br.cefetmg.inf.lpii.exception.BusinessException;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import br.cefetmg.inf.lpii.server.Distribuivel;
import br.cefetmg.inf.lpii.entities.TipoOperacao;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author João Pedro Renan
 */
public class ChatProxy implements Distribuivel {

    private static ChatProxy chatProxy = null;
    private static ArrayList<Integer> portsBeingUsed = new ArrayList();
    private static Socket socket;
    private static CanalDeEntrada canalDeEntrada;
    private ObjectOutputStream out;  
    private Payload payload;
    
    private ChatProxy(Socket socket) throws IOException {
        ChatProxy.socket = socket;
        this.out  = new ObjectOutputStream(ChatProxy.socket.getOutputStream());
    }
  
    public static ChatProxy getInstance(Socket socket) throws IOException {
        if (chatProxy != null) {
            throw new IllegalArgumentException("Instancia ja existe com outro socket");
        }
        ChatProxy.chatProxy = new ChatProxy(socket);
        criarCanalDeEntrada(socket);
        return chatProxy;
    }
    
    public static ChatProxy getInstance() {
        if (chatProxy == null) {
            throw new IllegalArgumentException("Instancia nao existe. Exige construtor com socket");
        }
        
        return chatProxy;
    }
    
    public void criarCanalDeEntrada() {
        this.canalDeEntrada = new CanalDeEntrada(this.socket);
        new Thread(canalDeEntrada).start();
    }

    @Override
    public void enviarMensagem(Mensagem mensagem) throws PersistenceException, BusinessException, IOException {
        Payload pl = new Payload(TipoOperacao.ENVIAR_MENSAGEM, mensagem);
        out.writeObject(pl);
    }

    @Override
    public void inserirUsuarioNaSala(Usuario usuario, Sala sala) throws IOException, BusinessException, PersistenceException{
        Payload pl = new Payload(TipoOperacao.INSERIR_USUARIO_NA_SALA);
        out.writeObject(pl);
    }

    @Override
    public void removerUsuarioDaSala(Usuario usuario, Sala sala) throws IOException {
        Payload pl = new Payload(TipoOperacao.REMOVER_USUARIO_DA_SALA);
        out.writeObject(pl);
    }

    @Override
    public void criarSala(Sala sala) throws BusinessException, PersistenceException, IOException {
        Payload pl = new Payload(TipoOperacao.CRIAR_CONTA, sala);
        out.writeObject(pl);
    }

    @Override
    public void criarConta(Usuario usuario) throws IOException, BusinessException, PersistenceException {
        Payload pl = new Payload(TipoOperacao.CRIAR_CONTA, usuario);
        out.writeObject(pl);
    }

    @Override
    public void removerSala(Sala sala) throws IOException, BusinessException, PersistenceException {
        Payload pl = new Payload(TipoOperacao.REMOVER_SALA);
        out.writeObject(pl);
    }
    
    private static void criarCanalDeEntrada(Socket socket) {
        ChatProxy.canalDeEntrada = new CanalDeEntrada(socket);
        new Thread(canalDeEntrada).start();
    }
    
    @Override
    public void teste() throws IOException {
        out.writeObject(new Payload(TipoOperacao.TESTE));
        out.flush();
    }

    @Override
    public void retornarMensagens(Long salaID) throws IOException, BusinessException, PersistenceException {
        Payload pl = new Payload(TipoOperacao.RETORNAR_MENSAGENS);
        out.writeObject(pl);
    }

    @Override
    public void retornarUsuarios() throws IOException, BusinessException, PersistenceException {
        Payload pl = new Payload(TipoOperacao.RETORNAR_USUARIOS);
        out.writeObject(pl);
    }

    @Override
    public void retornarSalas() throws IOException, BusinessException, PersistenceException {
        Payload pl = new Payload(TipoOperacao.RETORNAR_SALAS);
        out.writeObject(pl);
    }
    
}
