/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.util.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author João Pedro Renan
 */
public class ConnectionManager {
  
    private static ConnectionManager connection;
    private static ConnectionFactory cf;

    private ConnectionManager() {
         ConnectionManager.cf = new PostgreSQLConnection();
    }

    public static ConnectionManager getInstance() {
        if( connection == null)
            connection = new ConnectionManager();
        return connection;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return ConnectionManager.cf.getConnection();
    }
}
