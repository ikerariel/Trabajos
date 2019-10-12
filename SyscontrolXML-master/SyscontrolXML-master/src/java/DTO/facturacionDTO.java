/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Usuario
 */
public class facturacionDTO {

    private Integer idarticulo;
    private String descripcion;
    private Integer precio_venta;
    private String impuesto;
    private Integer cantidad_stock;
    private Integer idimpuesto;
    private Integer idaperturacierre;
    private Integer iddocfactura;
    private Integer cant;
    private String cajero;
    private String caja;
    private String numerodocumento;

    public facturacionDTO() {
    }

    public facturacionDTO(Integer idaperturacierre, Integer cant, Integer iddocfactura, String cajero, String caja, String numerodocumento) {
        this.idaperturacierre = idaperturacierre;
        this.cant = cant;
        this.iddocfactura = iddocfactura;
        this.cajero = cajero;
        this.caja = caja;
        this.numerodocumento = numerodocumento;
    }

    public facturacionDTO(Integer idarticulo, String descripcion, Integer precio_venta, String impuesto, Integer cantidad_stock, Integer idimpuesto) {
        this.idarticulo = idarticulo;
        this.descripcion = descripcion;
        this.precio_venta = precio_venta;
        this.impuesto = impuesto;
        this.cantidad_stock = cantidad_stock;
        this.idimpuesto = idimpuesto;
    }

}
