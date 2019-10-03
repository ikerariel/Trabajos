/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.mercaderiadto;
import Genericos.mierror;

/**
 *
 * @author Rafel
 */
public interface mercaderiadao {
    
    boolean insertar(mercaderiadto funcion_Dto) throws mierror;
    boolean modificar(mercaderiadto funcion_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    //ArrayList<productosdto> getAllProductos () throws mierror;
    
    String getmercaderia() throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getmercaderiaFiltro(Integer idFiltro)  throws mierror;
    
    String listarcategorias() throws mierror;
    String listarmarcas() throws mierror;
    String listarprocedencia() throws mierror;
    String listarimpuestos() throws mierror;
    
}
