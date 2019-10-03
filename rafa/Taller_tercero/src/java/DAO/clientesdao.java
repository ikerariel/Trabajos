/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.clientesdto;
import Genericos.mierror;

/**
 *
 * @author naty
 */
public interface clientesdao {
    
    boolean insertar(clientesdto cliente_Dto) throws mierror;
    boolean modificar(clientesdto cliente_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    
    //ArrayList<productosdto> getAllProductos () throws mierror;
    
    String getcliente() throws mierror;
    String codigoVerificado(String p_numero, Integer p_basemax );
    Integer getUltimoCodigo() throws mierror;
    String getclienteFiltro(Integer idFiltro)  throws mierror;
    
    String listarciudad() throws mierror;
    
    
}
