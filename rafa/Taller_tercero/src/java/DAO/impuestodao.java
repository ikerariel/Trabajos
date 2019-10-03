/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.impuestodto;
import Genericos.mierror;

/**
 *
 * @author naty
 */
public interface impuestodao {
    
    boolean insertar(impuestodto impuest_Dto) throws mierror;
    boolean modificar(impuestodto impuest_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    
    //ArrayList<marcadto> getAllCliente() throws mierror;
    
    String getimpuesto() throws mierror;
    //String getClientesHTML() throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getimpuestoFiltro(Integer idFiltro)  throws mierror;
    
}
