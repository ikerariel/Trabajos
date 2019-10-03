/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.sucursaldto;
import Genericos.mierror;

/**
 *
 * @author Rafel
 */
public interface sucursaldao {
    
    boolean insertar(sucursaldto Sucur_Dto) throws mierror;
    boolean modificar(sucursaldto Sucur_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    
    //ArrayList<productosdto> getAllProductos () throws mierror;
    
    String getsucur() throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getsucurFiltro(Integer idFiltro)  throws mierror;
    
    String listarciudad() throws mierror;
    String listardeposito() throws mierror;
    
}
