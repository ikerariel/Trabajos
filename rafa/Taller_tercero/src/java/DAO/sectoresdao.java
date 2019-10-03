/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.sectoresdto;
import Genericos.mierror;

/**
 *
 * @author naty
 */
public interface sectoresdao {
    
    boolean insertar(sectoresdto sector_Dto) throws mierror;
    boolean modificar(sectoresdto sector_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    
    String getsector() throws mierror;
    //String getClientesHTML() throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getsectorFiltro(Integer idFiltro)  throws mierror;
    
}
