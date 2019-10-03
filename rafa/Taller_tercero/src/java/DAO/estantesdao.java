/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.estantesdto;
import Genericos.mierror;

/**
 *
 * @author naty
 */
public interface estantesdao {
    
    boolean insertar(estantesdto estante_Dto) throws mierror;
    boolean modificar(estantesdto estante_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    
    String getEstante() throws mierror;
    //String getClientesHTML() throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getEstanteFiltro(Integer idFiltro)  throws mierror;
    
}
