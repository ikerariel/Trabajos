/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DTO.facturacionDTO;
/**
 *
 * @author Usuario
 */
public interface facturacionDAO {
    
    String getFacturas(Integer Cajero);
    String getArticulos();
    String getAnular(String nrofac);
    public boolean insertarVenta(facturacionDTO dto);
    public boolean insertarVentaDetalle(facturacionDTO dto);
    public boolean anularfactura(facturacionDTO dto);
    
}
