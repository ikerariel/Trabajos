/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.categoriasdto;
import Genericos.mierror;

/**
 *
 * @author naty
 */
public interface categoriasdao {
    
    boolean insertar(categoriasdto categoria_Dto) throws mierror;
    boolean modificar(categoriasdto categoria_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    
    String getcategoria() throws mierror;
    //String getClientesHTML() throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getcategoriaFiltro(Integer idFiltro)  throws mierror;
    
}
