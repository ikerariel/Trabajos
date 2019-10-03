/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Ajustesdto;

/**
 *
 * @author Rafel
 */
public interface ajustesdao {
    Integer getUltimoCodigoAjustes1();
    String ListarEstadoAjuste2();
    String ListarUsuarioAjustes3();
    String ListarMotivoAjuste4();
    String ListarMercaderiaAjuste5();
    boolean insertarCabeceraAjuste6(Ajustesdto dto);
    boolean insertarDetalleAjuste7(Ajustesdto dto);
    String ListarAjuste8();
    boolean confirmarAjuste9(Ajustesdto dto);
    String listarDetalleAjuste10(Integer id);
}
