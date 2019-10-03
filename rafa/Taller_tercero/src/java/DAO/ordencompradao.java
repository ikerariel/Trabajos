/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ordencompradto;

/**
 *
 * @author Rafel
 */
public interface ordencompradao {

    Integer getUltimoCodigo();

    boolean insertarOrdenC(ordencompradto dto, Integer cod);

    boolean insertarDetalleOrdenC(ordencompradto dto);

    String getcabeseraFiltroOrdenC(Integer idFiltro);

    String getdetalleFiltroOrdenC(Integer idFiltro);

    boolean modificarOrdenC(ordencompradto dto);
    boolean deleteOrdencompra(ordencompradto dto);

    boolean confirmarOrdenC(ordencompradto dto);

    boolean modificardetallesOrdenC(ordencompradto dto);

    String listarordenComp();

    String listarusuario();

    String listarMercaderia();

    String listarestado();

    String listarpedido();

    String listarproveedor();

    String listarDetallePedido(Integer id);

    String listarDetalleOrdenC(Integer id);

    Integer getUltimoCodigoMercaderia();

    boolean insertarMercaderia(ordencompradto dto);

}
