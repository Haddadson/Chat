/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.DAO;

import br.cefetmg.inf.lpii.entities.Mensagem;

/**
 *
 * @author Aluno
 */
public interface MensagemDAO {
    public Long inserir(Mensagem mensagem);
    public boolean remover(Long id);
    public boolean atualizar(Mensagem mensagem);
    public Mensagem get(Long id);
}
