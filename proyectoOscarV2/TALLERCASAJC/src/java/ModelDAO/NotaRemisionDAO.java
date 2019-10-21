/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO;

import ModelDTO.NotaRemisionDTO;

/**
 *
 * @author Oscar
 */
public interface NotaRemisionDAO {
    
    Integer getUltimoCodigoNotaRemision1();

    String ListarEstadosNotaRemision2();

    String ListarUsuariosNotaRemision3();

    String ListarProveedoresNotaRemision4();

    String ListarFacturasComprasNotaRemision5();

    String ListarDetFacturasComprasRemision6(Integer id);

    String ListarSucursalesNotaRemision7();

    String ListarArticulosNotaRemision8();

    boolean insertarCabeceraNotaRemision9(NotaRemisionDTO dto);
    boolean deleteNR(NotaRemisionDTO dto);

    boolean modificarCabeceraNotaRemision10(NotaRemisionDTO dto);

    boolean insertarDetNotaRemision11(NotaRemisionDTO dto);

    String ListarNotaRemision12();

    boolean confirmarNotaRemision13(NotaRemisionDTO dto);

    String listarDetNotaRemision14(Integer id);
    
}
