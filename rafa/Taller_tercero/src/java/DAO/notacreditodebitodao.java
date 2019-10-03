/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.notacreditodebitodto;

/**
 *
 * @author Rafel
 */
public interface notacreditodebitodao {
    
    Integer getUltimoCodigoNota1();
    String listarEstadoNota2();
    String listarUsuarioNota3();
    String listarProveedorNota4();
    String listarfacturaNota5();
    String listarDetalleFactura6(Integer id);
    
    String listarMercaderiaNota7();
    boolean insertarCabeceraNota8(notacreditodebitodto dto);
    boolean insertarDetalleNota9(notacreditodebitodto dto);
    String listarNota10();
    boolean confirmarNota11(notacreditodebitodto dto);
    String listarDetalleNota12(Integer id);
    String listarfacturaNota13();
}
