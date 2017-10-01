/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.inf.lpii.service.interfaces;

import br.cefetmg.inf.lpii.entities.Sala;
import java.util.ArrayList;

/**
 *
 * @author GABRIEL HADDAD
 */
public interface SalaManagement {
    public Long inserir(Sala sala);
    public boolean remover(Long id);
    public boolean atualizar(Sala sala);
    public Sala get(Long id);
    public ArrayList<Sala> getTodas();
}
