/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.sOrdenTrabajoDTO;



public interface sOrdenTrabajoDAO {
      String getdetallePresupuesto(Integer codPresupuesto);
      String getdetalleOT(Integer codOT);
      String getOT();
      boolean insetarOrdentrabajo(sOrdenTrabajoDTO dto, Integer cod);
      boolean updateEstadoOT(sOrdenTrabajoDTO dto);
}
