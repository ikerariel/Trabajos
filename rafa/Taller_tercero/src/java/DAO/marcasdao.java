/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.marcasdto;
import Genericos.mierror;

/**
 *
 * @author naty
 */
public interface marcasdao {
    
    boolean insertar(marcasdto marca_Dto) throws mierror;
    boolean modificar(marcasdto marca_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    
    String getmarca() throws mierror;
    //String getClientesHTML() throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getmarcaFiltro(Integer idFiltro)  throws mierror;
    
}
