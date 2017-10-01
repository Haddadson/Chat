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
public interface ConnectionFactory {
    public Connection getConnection() throws ClassNotFoundException, SQLException;
}
