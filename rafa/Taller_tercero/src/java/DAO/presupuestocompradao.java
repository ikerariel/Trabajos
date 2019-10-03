/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.presupuestocompradto;

/**
 *
 * @author Rafel
 */
public interface presupuestocompradao {

    Integer getUltimoCodigoPresuCompra();

    String ListarEstadoPresuCompra2();

    String ListarUsuarioPresuCompra3();

    String ListarProveedorPresuCompras4();

    String ListarPedidoPresuCompra5();

    String ListarDetallePedidoPresuC6(Integer id);

    String ListarSucursalPresuCompra7();

    String ListarMercaderiaPresuCompra8();

    boolean insertarCabeceraPresuCompra9(presupuestocompradto dto, Integer Cod);

    boolean insertarDetallePresuCompra10(presupuestocompradto dto);

    boolean deletepresucompra(presupuestocompradto dto);

    String ListarPresuCompra11();

    boolean confirmarPresuCompra12(presupuestocompradto dto);

    String listarDetallePresuCompra13(Integer id);

    String ListarTipoPresuCompras14();

}
