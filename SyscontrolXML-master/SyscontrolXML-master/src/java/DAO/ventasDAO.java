/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ventasDTO;

/**
 *
 * @author Usuario
 */
public interface ventasDAO {
    boolean insertartimbrado(ventasDTO dto,Integer codOpcion);
    boolean insertarFacturas(ventasDTO dto);
    boolean insertarAperturaCierreVenta(ventasDTO dto);
    boolean cerrarCaja(ventasDTO dto);
    String getTimbrados();
    String getCajeros();
    String getCajas();
    String getTipoDoc();
    String getFacturasPorTimbrados(Integer TipDoc);
    String getAperCierreCajaVentas();
    String getCobros(Integer numero);
    
}
