/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.aperturaDTO;

/**
 *
 * @author Carlos
 */
public interface aperturaDAO {
    
    
    boolean insertarapertura(aperturaDTO dto);
    boolean cerrrarapertura(aperturaDTO dto);
    boolean actualizarapertura(aperturaDTO dto, Integer v_valor);
    String getapertura();
    String getcierre();
    String obtenerapertura(Integer idapertura);
    
}
