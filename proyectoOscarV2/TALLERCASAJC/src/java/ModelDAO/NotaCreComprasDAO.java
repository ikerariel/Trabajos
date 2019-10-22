/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.NotaCreComprasDTO;

/**
 *
 * @author Oscar
 */
public interface NotaCreComprasDAO {
    
     boolean insertarNotaCreCompras(NotaCreComprasDTO Dto, Integer v_caso);
    boolean insertardetalleNotaCreCompras(NotaCreComprasDTO Dto);
    boolean deletedealleNotaCreCompras(NotaCreComprasDTO Dto);
    boolean actualizarestado(NotaCreComprasDTO Dto);
    String getdetalleNotaCreCompras(Integer codNotaCreCompras);
    String getdetalleComprasNC(Integer nrofactura);
    String getNotaCreCompras();
    
}
