/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.DAO;

import br.cefetmg.inf.lpii.DAO.interfaces.SalaDAO;
import br.cefetmg.inf.lpii.entities.Sala;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import br.cefetmg.inf.lpii.util.db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Breno Mariz
 */
public class SalaDAOImpl implements SalaDAO {
    private static SalaDAOImpl salaDAO = null;
    
    public static SalaDAOImpl getInstance(){
        if(salaDAO == null){
            salaDAO = new SalaDAOImpl();
        }
        return salaDAO;
    }
    
    
    @Override
    public Long inserir(Sala sala) throws PersistenceException{
        /*Um objeto pré-instanciado é recebido nessa classe e seus dados 
         *são inseridos na base de dados.
         */
        
        if (sala == null){
            throw new PersistenceException("Sala não pode ser nula");
        }
        Long salaId = null;
        try{
            try (Connection connection = ConnectionManager.getInstance().getConnection()) {
                String sql = "INSERT INTO Sala (NOM_sala)" +
                        " VALUES(?) returning COD_sala";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setString(1, sala.getNome());
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if(rs.next()) {
                            salaId = rs.getLong("COD_sala");
                            sala.setId(salaId);
                        }
                    }
                }
            }
            
        }catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(SalaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return salaId;
    }

    @Override
    public boolean remover(Long id) throws PersistenceException {
        //A id de uma sala é recebida e os dados relacionados a ela são removidos da base de dados
        
        try {
            try (Connection connection = ConnectionManager.getInstance().getConnection()) {
                String sql = "DELETE FROM Sala WHERE COD_sala = ?";
                
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setLong(1, id);
                    pstmt.executeUpdate();
                }
            }
            
            return true;
            
        }catch (ClassNotFoundException | SQLException e){
            throw new PersistenceException(e);
       }
    }

    @Override
    public boolean atualizar(Sala sala) throws PersistenceException {
        /*Um objeto pré-instanciado é recebido nessa classe e seus dados 
         *são atualizados na base de dados.
         */
        
         try {
             try (Connection connection = ConnectionManager.getInstance().getConnection()) {
                 String sql = "UPDATE Sala" +
                         "SET NOM_sala = ?,"+
                         "   TXT_senha = ? "+
                         "WHERE COD_sala = ?";
                 
                 try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                     pstmt.setString(1, sala.getNome());
                     pstmt.setString(2, sala.getSenha());
                     pstmt.executeUpdate();
                 }
             }
            
            return true;
        } catch (ClassNotFoundException | SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    //A id de uma sala é retornada e suas características são retornadas
    
    public Sala get(Long id) throws PersistenceException {
        try {
            Sala sala;
            try (Connection connection = ConnectionManager.getInstance().getConnection()) {
                String sql = "SELECT * FROM Sala WHERE COD_sala = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setLong(1, id);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        sala = new Sala();
                        if(rs.next()){
                            sala.setId(id);
                            sala.setNome(rs.getString("NOM_sala"));
                            sala.setSenha(rs.getString("TXT_senha"));
                        }
                    }
                }
            }
            
            return sala;
        } catch(ClassNotFoundException | SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public ArrayList<Sala> getTodas() throws PersistenceException {
        //Retorna todas as salas presentes na base de dados
        
        try{
            ArrayList<Sala> listAll;
            try (Connection connection = ConnectionManager.getInstance().getConnection()) {
                String sql = "SELECT * FROM Sala ORDER BY NOM_sala";
                try (PreparedStatement pstmt = connection.prepareCall(sql); 
                    ResultSet rs = pstmt.executeQuery()) {
                    listAll = null;
                    Sala sala;
                    if(rs.next()){
                        listAll = new ArrayList<>();
                        do{
                            sala = new Sala();
                            sala.setId(rs.getLong("COD_sala"));
                            sala.setNome(rs.getString("NOM_sala"));
                            sala.setSenha(rs.getString("TXT_senha"));
                        }while(rs.next());
                    }                  }
            }
            
            return listAll;
            
        }catch (ClassNotFoundException | SQLException e){
            throw new PersistenceException(e);
        }
    }
}

