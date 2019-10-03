/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.aperturacierredto;

/**
 *
 * @author Rafel
 */
public interface aperturacierredao {
    Integer getUltimoCodigoAper1();
    String ListarEstadoAper2();
    String ListarUsuarioAper3();
    String ListarCajasAper4();
    boolean insertarAperturacierre5(aperturacierredto dto);
    String ListarTimbradoAper6();
    String ListarAperturCierre7();
    String RecuperarApertura8(Integer id);
}
