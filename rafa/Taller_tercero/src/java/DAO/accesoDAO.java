/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.accesoDTO;
import Genericos.mierror;

/**
 *
 * @author Carlos
 */
public interface accesoDAO {

    boolean verificarUser(accesoDTO accDTO);

    String getUser(String nombre);

}
