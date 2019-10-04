/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.facturacompradto;

/**
 *
 * @author Rafel
 */
public interface facturacompradao {

    Integer getUltimoCodigoCompra();

    String ListarEstadoCompra2();

    String ListarUsuarioCompra3();

    String ListarProveedorCompras4();

    String ListarOrdenCompra5();

    String ListarDetalleOrdenC6(Integer id);

    String ListarSucursalCompra7();

    String ListarMercaderiaCompra8();

    boolean insertarCabeceraCompra9(facturacompradto dto, Integer cod);

    boolean insertarDetalleCompra10(facturacompradto dto);
    boolean generarCtasapagar(facturacompradto dto);

    boolean deletfacturaCompra(facturacompradto dto);

    String ListarFacturaCompra11();

    boolean confirmarFacturaCompra12(facturacompradto dto);

    String listarDetalleCompra13(Integer id);

    String ListarTipoCompras14();
}
