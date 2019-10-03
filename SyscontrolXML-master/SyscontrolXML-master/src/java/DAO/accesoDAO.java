/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.accesoDTO;

/**
 *
 * @author !mX
 */
public interface accesoDAO {

    boolean verificarUsuario(accesoDTO accesoDTO);
    String getUser(String login);

}
