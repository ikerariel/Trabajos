/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.perfilesdto;
import Genericos.mierror;

/**
 *
 * @author naty
 */
public interface perfilesdao {
    
    boolean insertar(perfilesdto perfil_Dto) throws mierror;
    boolean modificar(perfilesdto perfil_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    
    String getperfil() throws mierror;
    //String getClientesHTML() throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getperfilFiltro(Integer idFiltro)  throws mierror;
    
}
