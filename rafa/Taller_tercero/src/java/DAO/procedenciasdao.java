/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.procedenciasdto;
import Genericos.mierror;

/**
 *
 * @author naty
 */
public interface procedenciasdao {
    
    boolean insertar(procedenciasdto proceden_Dto) throws mierror;
    boolean modificar(procedenciasdto proceden_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    
    String getprocedencia() throws mierror;
    //String getClientesHTML() throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getprocedenciaFiltro(Integer idFiltro)  throws mierror;
    
}
