/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.funcionariodto;
import Genericos.mierror;

/**
 *
 * @author Rafel
 */
public interface funcionariodao {
    
    boolean insertar(funcionariodto funcion_Dto) throws mierror;
    boolean modificar(funcionariodto funcion_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    //ArrayList<productosdto> getAllProductos () throws mierror;
    
    String getfuncionario() throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getfuncionarioFiltro(Integer idFiltro)  throws mierror;
    
    String listarcargos() throws mierror;
    String listarciudad() throws mierror;
    
}
