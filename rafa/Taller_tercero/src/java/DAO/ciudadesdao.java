/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ciudadesdto;
import Genericos.mierror;

/**
 *
 * @author naty
 */
public interface ciudadesdao {
    
    boolean insertar(ciudadesdto ciudad_Dto) throws mierror;
    boolean modificar(ciudadesdto ciudad_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    
    String getciudad() throws mierror;
    //String getClientesHTML() throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getciudadFiltro(Integer idFiltro)  throws mierror;
    
}
