/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.sEntregaEquiposDTO;

/**
 *
 * @author Usuario
 */
public interface sEntregaEquiposDAO {

    boolean insertarEntrgaEquipos(sEntregaEquiposDTO DTO, Integer cod);
    boolean insertarDetalleEntrgaEquipos(sEntregaEquiposDTO DTO, Integer cod);
    boolean updateEQ(sEntregaEquiposDTO DTO);
    boolean deleteEQ(sEntregaEquiposDTO DTO);
    String getOrdenTrabajoEQ();
    String getEQ();
    String getdetalleOTeq(Integer codOT);
    String getdetalleEQ(Integer codOT);

}
