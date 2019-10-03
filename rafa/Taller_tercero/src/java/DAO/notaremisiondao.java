/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.notaremisiondto;

/**
 *
 * @author Rafel
 */
public interface notaremisiondao {

    Integer getUltimoCodigoNotaRemi1();

    String listarEstadoNotaRemi2();

    String listarUsuarioNotaRemi3();

    String listarProveedorNotaRemi4();

    String listarfacturaRemi5();

    String listarDetalleFacturaRemi6(Integer id);

    String listarMercaderiaNotaRemi7();

    boolean insertarCabeceraNotaRemi8(notaremisiondto dto, Integer cod);

    boolean deleteNotaremision(notaremisiondto dto);

    boolean insertarDetalleNotaRemi9(notaremisiondto dto);

    String listarNotaRemi10();

    boolean confirmarNotaRemi11(notaremisiondto dto);

    String listarDetalleNotaRemi12(Integer id);

}
