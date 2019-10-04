/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.sPresupuestoServiciosDTO;

/**
 *
 * @author Usuario
 */
public interface sPresupuestoServiciosDAO {

    boolean insertarpresupuestoservicios(sPresupuestoServiciosDTO psDTO, Integer cod);

    boolean insertarDetallePServicios(sPresupuestoServiciosDTO psDTO, Integer vcod);

    boolean deleteDetallePServicio(sPresupuestoServiciosDTO psDTO);

    boolean actualizarEstadoPServicio(sPresupuestoServiciosDTO psDTO);

    String getPServicio();

    String getdetallePServicio(Integer codDiagnostico);

    String getdetallesPServicios(Integer codPServicio);

}
