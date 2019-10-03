/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.pedidocompradto;

/**
 *
 * @author Rafel
 */
public interface pedidocompradao {

    Integer getUltimoCodigo();

    boolean insertar(pedidocompradto dto, Integer cod);
    boolean insertarDetalle(pedidocompradto dto);
    boolean eliminar(Integer id);
    
    
    String getcabeseraFiltro(Integer idFiltro);
    String getdetalleFiltro(Integer idFiltro);
    
    boolean confirmar(pedidocompradto dto);
    
    String listarusuario();
    String listarMercaderia();
    String listarestado();
    String listarpedido();
    String listarDetalle(Integer id);

}
