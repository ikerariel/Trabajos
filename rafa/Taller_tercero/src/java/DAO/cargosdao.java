/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.cargosdto;
import Genericos.mierror;

/**
 *
 * @author naty
 */
public interface cargosdao {
    boolean insertar(cargosdto cargos_Dto) throws mierror;
    boolean modificar(cargosdto cargos_Dto) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    //ArrayList<marcadto> getAllCliente() throws mierror;
    
    String getcargos() throws mierror;
    //String getClientesHTML() throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getcargosFiltro(Integer idFiltro)  throws mierror;
}
