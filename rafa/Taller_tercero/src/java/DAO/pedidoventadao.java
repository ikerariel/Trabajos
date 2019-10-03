/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.pedidoventadto;

/**
 *
 * @author Rafel
 */
public interface pedidoventadao {
    Integer getUltimoCodigoPedidoV1();
    String ListarEstadoPedidoV2();
    String ListarClientePedidoV3();
    String ListarMercaderiaPediV4();
    boolean insertarPedidoVentas5(pedidoventadto dto);
    boolean insertarDetallePedidoVentas6(pedidoventadto dto);
    String listarpedidoV7();
    boolean confirmarPedidoV8(pedidoventadto dto);
    String listarDetallePedidoV9(Integer id);
    boolean eliminarPedidoV10(Integer id);
    boolean modificarPedidoV11(pedidoventadto dto);
}