/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.giroDTO;

/**
 *
 * @author Carlos
 */
public interface giroDAO {

    String getoperacion();

    String getopago();

    String getcliente(String ci);

    String getgiros();

    String getgirospendientes(Integer id);

    boolean insertarcliente(giroDTO dto);

    boolean insertarpago(giroDTO dto, Integer cod);

    boolean eliminargiro(giroDTO dto);

    String consultagiro(Integer codapertura);

    String getconsultagiro(Integer nrolineadestino, Integer nroliaorigen);

    String getconsultagiroci(String ci);

    boolean insertargiro(giroDTO dto);
}
