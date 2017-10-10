/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.DAO;

import br.cefetmg.inf.lpii.DAO.interfaces.MensagemDAO;
import br.cefetmg.inf.lpii.entities.Mensagem;
import br.cefetmg.inf.lpii.entities.Sala;
import br.cefetmg.inf.lpii.entities.Usuario;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import br.cefetmg.inf.lpii.util.db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MensagemDAOImpl implements MensagemDAO {

    private static MensagemDAOImpl messageDAO = null;

    public MensagemDAOImpl() {
    }

    public static MensagemDAOImpl getInstance() {
        if (messageDAO == null) {
            messageDAO = new MensagemDAOImpl();
        }
        return messageDAO;
    }
    
    @Override
    public Long inserir(Mensagem mensagem) throws PersistenceException {
        /*Um objeto pré-instanciado é recebido nessa classe e seus dados 
         *são inseridos na base de dados.
         */
        
        if (mensagem == null) {
            throw new PersistenceException("Domain cannot be null");
        }

        Long messageId = null;

        try {
            try (Connection connection = ConnectionManager.getInstance().getConnection()) {
                String sql = "INSERT INTO \"Mensagem\" (COD_remetente, COD_mensagem, COD_destinatario, COD_salaDestino, DAT_msg, TXT_conteudo) "
                        + "    VALUES (?, ?, ?, ?, ?, ?) returning COD_mensagem;";
                
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setObject(1, mensagem.getRemetente());
                    pstmt.setLong(2, mensagem.getIdMensagem());
                    pstmt.setObject(3, mensagem.getUsuarioDestino());
                    pstmt.setObject(4, mensagem.getSalaDestino());
                    pstmt.setTimestamp(5, mensagem.getHoraEnvio());
                    pstmt.setString(6, mensagem.getConteudo());
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            messageId = rs.getLong("COD_mensagem");
                            mensagem.setIdMensagem(messageId);
                        }
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.getMessage();
        }

        return messageId;
    }

    @Override
    public boolean remover(Long id) throws PersistenceException {
        //Recebe uma id de Mensagem e a remove da base de dados
        
        try {
            try (Connection connection = ConnectionManager.getInstance().getConnection()) {
                String sql = "DELETE FROM \"Mensagem\" WHERE COD_mensagem = ?";
                
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setLong(1, id);
                    pstmt.executeUpdate();
                }
            }

            return true;

        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public boolean atualizar(Mensagem mensagem) throws PersistenceException {
        /*Um objeto pré-instanciado é recebido nesse método e seus dados 
         *são atualizados na base de dados.
         */
        
        try {
            try (Connection connection = ConnectionManager.getInstance().getConnection()) {
                String sql = "UPDATE \"Mensagem\" " +
                        " SET COD_remetente = ?, " +
                        "     COD_destinatario = ?, " +
                        "     COD_salaDestino = ?, " +
                        "     DAT_msg = ?, " +
                        "     TXT_conteudo = ?, " +
                        " WHERE COD_mensagem = ?";
                
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setObject(1, mensagem.getRemetente());
                    pstmt.setObject(2, mensagem.getUsuarioDestino());
                    pstmt.setObject(3, mensagem.getSalaDestino());
                    pstmt.setTimestamp(4, mensagem.getHoraEnvio());
                    pstmt.setString(5, mensagem.getConteudo());
                    pstmt.executeUpdate();
                }
            }

            return true;

        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Mensagem get(Long id) throws PersistenceException {
        // Recebe a id de uma mensgem e retorna todo os dados relacionados a ela
        
        try {
            Mensagem mensagem;
            Usuario usuario;
            try (Connection connection = ConnectionManager.getInstance().getConnection()) {
                String sql = "SELECT * FROM \"Mensagem\" WHERE COD_mensagem = ? ";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setLong(1, id);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        mensagem = null;
                        if (rs.next()) {
                            mensagem = new Mensagem();
                            usuario = new Usuario();
                            usuario.setId(rs.getLong("COD_remetente"));
                            mensagem.setUsuarioDestino(usuario);
                            mensagem.setIdMensagem(id);
                            usuario.setId(rs.getLong("COD_destinatatio"));
                            mensagem.setRemetente(usuario);
                            mensagem.setSalaDestino((Sala) rs.getObject("COD_salaDestino"));
                            mensagem.setHoraEnvio(rs.getTimestamp("DAT_msg"));
                            mensagem.setConteudo(rs.getString("TXT_conteudo"));
                        }
                    }
                }
            }

            return mensagem;
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e);
        }
    }
    
}
