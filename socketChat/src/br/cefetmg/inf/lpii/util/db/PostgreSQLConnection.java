/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author João Pedro Renan
 */
    
    /*Essa classe tem como responsabilidade realizar a conexão entre o sistema e o banco
     *de dados, que é onde todos os dados presente no sistema são persistidos.
     */


public class PostgreSQLConnection implements ConnectionFactory {

    private final static String DB_DRIVER = "org.postgresql.Driver";
    private final static String DB_URL = "jdbc:postgresql://localhost:5432/chat";
    private final static String USER = "postgres";
    private final static String PASS = "123456";

    public PostgreSQLConnection() {
    }

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
    
    public static void main(String[] args) {
        try {
            ConnectionFactory cf = new PostgreSQLConnection();            
            cf.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostgreSQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
