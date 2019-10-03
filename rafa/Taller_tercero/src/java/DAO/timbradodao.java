/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.timbradodto;

/**
 *
 * @author Rafel
 */
public interface timbradodao {
    Integer getUltimoCodigoTimbrado1();
    String ListarEstadoTimbrado2();
    boolean insertarTimbrado3(timbradodto dto);
    boolean insertarDetalleNroFactura4(timbradodto dto);
    String listarTimbrado5();
}
