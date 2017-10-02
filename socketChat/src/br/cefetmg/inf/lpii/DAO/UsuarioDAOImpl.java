
package br.cefetmg.inf.lpii.DAO;

import br.cefetmg.inf.lpii.DAO.interfaces.UsuarioDAO;
import br.cefetmg.inf.lpii.entities.Usuario;
import br.cefetmg.inf.lpii.exception.PersistenceException;
import br.cefetmg.inf.lpii.util.db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Breno Mariz
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    private static UsuarioDAOImpl userDAO = null;
    
    private UsuarioDAOImpl() {}
    
    private static UsuarioDAOImpl getInstance() {
        if (userDAO == null) {
            userDAO = new UsuarioDAOImpl();
        }
        return userDAO;
    }
    
    @Override
    public Long inserir(Usuario usuario) throws PersistenceException {
        if (usuario == null) {
            throw new PersistenceException("Domain cannot be null");
        }

        Long userId = null;

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO \"Usuario\" (COD_usuario, NOM_usuario) "
                    + "    VALUES (?, ?) returning COD_usuario;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, usuario.getId());
            pstmt.setString(2, usuario.getNome());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                userId = rs.getLong("COD_usuario");
                usuario.setId(userId);
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.getMessage();
        }

        return userId;
    }

    @Override
    public boolean atualizar(Usuario usuario) throws PersistenceException {
        try {

            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE \"Usuario\" "
                    + " SET NOM_usuario = ? "
                    + " WHERE COD_usuario = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, usuario.getNome());
            pstmt.setLong(5, usuario.getId());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
                throw new PersistenceException(e);
        }

    }

    @Override
    public boolean remover(Long id) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM \"usuario\" WHERE COD_usuario = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }

    @Override
    public Usuario get(Long id) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM \"Usuario\" WHERE COD_usuario = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            Usuario usuario = null;
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(id);
                usuario.setNome(rs.getString("NOM_usuario"));               
            }

            rs.close();
            pstmt.close();
            connection.close();

            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e);
        }
    }
    
}
