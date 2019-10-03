/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.cajadto;
import Genericos.mierror;

/**
 *
 * @author naty
 */
public interface cajadao {
    
    boolean insertar(cajadto caja_Dto) throws mierror;
    boolean modificar(cajadto caja_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    
    //ArrayList<marcadto> getAllCliente() throws mierror;
    
    String getcaja() throws mierror;
    //String getClientesHTML() throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getcajaFiltro(Integer idFiltro)  throws mierror;
    
}
