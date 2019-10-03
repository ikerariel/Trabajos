/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.unimedidto;
import Genericos.mierror;

/**
 *
 * @author naty
 */
public interface unimedidao {
    
    boolean insertar(unimedidto unidadm_Dto) throws mierror;
    boolean modificar(unimedidto unidadm_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    
    String getunidadm() throws mierror;
    //String getClientesHTML() throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getunidadmFiltro(Integer idFiltro)  throws mierror;
    
}
