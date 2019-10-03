/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.proveedoresdto;
import Genericos.mierror;

/**
 *
 * @author Rafel
 */
public interface proveedoresdao {
    
    boolean insertar(proveedoresdto provee_Dto) throws mierror;
    boolean modificar(proveedoresdto provee_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    
    //ArrayList<productosdto> getAllProductos () throws mierror;
    
    String getproveedor() throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getproveedorFiltro(Integer idFiltro)  throws mierror;
    
    String listarciudad() throws mierror;
    
}
