/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.depositodto;
import Genericos.mierror;

/**
 *
 * @author Rafel
 */
public interface depositodao {
    
    boolean insertar(depositodto Depo_DTO) throws mierror;
    boolean modificar(depositodto Depo_DTO) throws mierror;
    boolean eliminar(Integer id) throws mierror;
    
    String getDeposito()throws mierror;
    
    Integer getUltimoCodigo() throws mierror;
    String getDepositoFiltro(Integer idFiltro) throws mierror;
    
}
