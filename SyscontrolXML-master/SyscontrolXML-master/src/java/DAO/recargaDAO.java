/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.recargaDTO;

/**
 *
 * @author Carlos
 */
public interface recargaDAO {
       boolean insertarrecarga(recargaDTO dto);
       boolean eliminarcarga(recargaDTO dto);
       boolean insertarpagorecarga(recargaDTO dto, Integer Vcodigo);
       String consultarecarga(Integer codoperadora);
       String getrecarga();
       String getconsulta(Integer codoperadora);
       String getconsultaci(String ci);
       String getoperadora();
       String getrecargaspendientes(Integer id);
}
