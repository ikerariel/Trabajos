/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.presupuestoventadto;

/**
 *
 * @author Rafel
 */
public interface presupuestoventadao {
    Integer getUltimoCodigoPresuVenta1();
    String ListarEstadoPresuVenta2();
    String ListarClientePresuVenta3();
    String ListarPedidoPresuVenta4();
    String ListarDetallePedidoPresuV5(Integer id);
    String ListarTipoPresuVenta6();
    String ListarMercaderiaPresuVenta7();
    boolean insertarCabeceraPresuVenta8(presupuestoventadto dto);
    boolean insertarDetallePresuVenta9(presupuestoventadto dto);
    String listarDetallePresuVenta10(Integer id);
    String ListarPresuVenta11();
    boolean eliminarPresuVenta12(Integer id);
    boolean modificarPresuVenta13(presupuestoventadto dto);
    boolean confirmarPresuVenta14(presupuestoventadto dto);
}
