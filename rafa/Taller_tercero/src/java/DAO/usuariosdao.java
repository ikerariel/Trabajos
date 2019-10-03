/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.usuariosdto;
import Genericos.mierror;

/**
 *
 * @author Rafel
 */
public interface usuariosdao {
    
    boolean insertar(usuariosdto usu_Dto) throws mierror;
    boolean modificar(usuariosdto usu_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    //ArrayList<productosdto> getAllProductos () throws mierror;
    
    String getusuario() throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getusuarioFiltro(Integer idFiltro)  throws mierror;
    
    String listarfuncionario() throws mierror;
    String listarperfil() throws mierror;
    String listarsucursal() throws mierror;
    
}
