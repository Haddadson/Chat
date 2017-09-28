/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.DAO;

import br.cefetmg.inf.lpii.entities.Usuario;

/**
 *
 * @author Aluno
 */
public interface UsuarioDAO {
    public Long inserir(Usuario usuario);
    public boolean atualizar(Usuario usuario);
    public boolean remover(Long id);
    public Usuario get(Long id);
}
