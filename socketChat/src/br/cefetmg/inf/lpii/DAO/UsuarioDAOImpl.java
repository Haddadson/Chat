
package br.cefetmg.inf.lpii.DAO;

import br.cefetmg.inf.lpii.DAO.interfaces.UsuarioDAO;
import br.cefetmg.inf.lpii.entities.Usuario;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import br.cefetmg.inf.lpii.util.db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Breno Mariz
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    private static UsuarioDAOImpl userDAO = null;
    
    public UsuarioDAOImpl() {}
    
    public static UsuarioDAOImpl getInstance() {
        //Instancia a variável userDAO
        
        if (userDAO == null) {
            userDAO = new UsuarioDAOImpl();
        }
        return userDAO;
    }
    
    @Override
    public Long inserir(Usuario usuario) throws PersistenceException {
        /*Um objeto pré-instanciado é recebido nessa classe e seus dados 
         *são inseridos na base de dados.
         */
        
        if (usuario == null) {
            throw new PersistenceException("Domain cannot be null");
        }

        Long userId = null;

        try (Connection connection = ConnectionManager.getInstance().getConnection();) {

            String sql = "INSERT INTO \"Usuario\" (COD_usuario, NOM_usuario) "
                    + "    VALUES (?, ?) returning COD_usuario;";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setLong(1, usuario.getId());
                pstmt.setString(2, usuario.getNome());
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        userId = rs.getLong("COD_usuario");
                        usuario.setId(userId);
                    }
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.getMessage();
        }

        return userId;
    }

    @Override
    public boolean atualizar(Usuario usuario) throws PersistenceException {
        /*Um objeto pré-instanciado é recebido nessa classe e seus dados 
         *são inseridos na base de dados.
         */
        
        try {

            try (Connection connection = ConnectionManager.getInstance().getConnection()) {
                String sql = "UPDATE \"Usuario\" "
                        + " SET NOM_usuario = ? "
                        + " WHERE COD_usuario = ?";
                
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setString(1, usuario.getNome());
                    pstmt.setLong(5, usuario.getId());
                    pstmt.executeUpdate();
                }
            }

            return true;
            
        } catch (ClassNotFoundException | SQLException e) {
                throw new PersistenceException(e);
        }

    }

    @Override
    public boolean remover(Long id) throws PersistenceException {
        //A id de um usuário é recebido e os dados relacionados a ele são removidos da base de dados
        
        try {
            try (Connection connection = ConnectionManager.getInstance().getConnection()) {
                String sql = "DELETE FROM \"usuario\" WHERE COD_usuario = ?";
                
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setLong(1, id);
                    pstmt.executeUpdate();
                }
            }

            return true;

        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Usuario get(Long id) throws PersistenceException {
        //A id de um usuário é recebido e os dados relacionados a ele são retornados da base de dados
        try {
            Usuario usuario;
            try (Connection connection = ConnectionManager.getInstance().getConnection()) {
                String sql = "SELECT * FROM \"Usuario\" WHERE COD_usuario = ? ";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setLong(1, id);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        usuario = null;
                        if (rs.next()) {
                            usuario = new Usuario();
                            usuario.setId(id);
                            usuario.setNome(rs.getString("NOM_usuario"));
                        }
                    }
                }               
            }

            return usuario;
            
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenceException(e);
        }
    }
    
}
