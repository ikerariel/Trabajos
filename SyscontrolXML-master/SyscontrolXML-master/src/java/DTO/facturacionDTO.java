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
    private Integer montoventa;
    private String descripcion;
    private Integer precio_venta;
    private Integer preciou;
    private String impuesto;
    private Integer cantidad_stock;
    private Integer idimpuesto;
    private Integer idaperturacierre;
    private Integer iddocfactura;
    private Integer cant;
    private String cajero;
    private String caja;
    private String numerodocumento;
    
    
  private Integer idcliente;
  private Integer idvendedor;
  private Integer idcondicionventa;
  private Integer idestado;

    public Integer getMontoventa() {
        return montoventa;
    }

    public void setMontoventa(Integer montoventa) {
        this.montoventa = montoventa;
    }

    public Integer getPreciou() {
        return preciou;
    }

    public void setPreciou(Integer preciou) {
        this.preciou = preciou;
    }

    public Integer getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(Integer idarticulo) {
        this.idarticulo = idarticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Integer precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public Integer getCantidad_stock() {
        return cantidad_stock;
    }

    public void setCantidad_stock(Integer cantidad_stock) {
        this.cantidad_stock = cantidad_stock;
    }

    public Integer getIdimpuesto() {
        return idimpuesto;
    }

    public void setIdimpuesto(Integer idimpuesto) {
        this.idimpuesto = idimpuesto;
    }

    public Integer getIdaperturacierre() {
        return idaperturacierre;
    }

    public void setIdaperturacierre(Integer idaperturacierre) {
        this.idaperturacierre = idaperturacierre;
    }

    public Integer getIddocfactura() {
        return iddocfactura;
    }

    public void setIddocfactura(Integer iddocfactura) {
        this.iddocfactura = iddocfactura;
    }

    public Integer getCant() {
        return cant;
    }

    public void setCant(Integer cant) {
        this.cant = cant;
    }

    public String getCajero() {
        return cajero;
    }

    public void setCajero(String cajero) {
        this.cajero = cajero;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(Integer idvendedor) {
        this.idvendedor = idvendedor;
    }

    public Integer getIdcondicionventa() {
        return idcondicionventa;
    }

    public void setIdcondicionventa(Integer idcondicionventa) {
        this.idcondicionventa = idcondicionventa;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

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
