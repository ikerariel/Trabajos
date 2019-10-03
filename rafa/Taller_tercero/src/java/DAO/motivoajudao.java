/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.motivoajudto;
import Genericos.mierror;

/**
 *
 * @author naty
 */
public interface motivoajudao {
    
    boolean insertar(motivoajudto motiaju_Dto) throws mierror;
    boolean modificar(motivoajudto motiaju_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    
    //ArrayList<marcadto> getAllCliente() throws mierror;
    
    String getmotiaju() throws mierror;
    //String getClientesHTML() throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getmotiajuFiltro(Integer idFiltro)  throws mierror;
    
}
