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
public class ventasDTO {

    private Integer idcobro;
    private String fechasaldo;
    private Integer saldo;
    private Integer importe;
    private String estado;
    private Integer idestado;
    private String numerodocumento;

    private Integer idaperturacierre;
    private String fecha_apertura;
    private Integer monto_apertura;
    private String cajero;
    private String fecha_cierre;

    private Integer idtimbrado;
    private Integer numero;
    private String fecha_carga;
    private String fecha_vigencia_inicio;
    private String fecha_vigencia_final;
    private String factura_desde;
    private String factura_hasta;
    private String tipodocumento;
 

    private Integer secuencia;
    private Integer idcajero;
    private String usuario;
    private String factura_establesimiento;
    private String factura_caja;

    private Integer idusuario;
    private Integer idtipodocumento;
    private Integer idsucursal;
    private Integer idGenerico;
    private String decripGenerico;
    private String caja;
    private String nombrecliente;
    private String cliente;
    private Integer idcaja;

    public ventasDTO(Integer idcobro, String fechasaldo, Integer saldo, Integer importe, String estado,String cliente, String nombrecliente, String numerodocumento,Integer idestado) {
        this.idcobro = idcobro;
        this.fechasaldo = fechasaldo;
        this.saldo = saldo;
        this.importe = importe;
        this.estado = estado;
        this.cliente = cliente;
        this.nombrecliente = nombrecliente;
        this.numerodocumento = numerodocumento;
        this.idestado = idestado;
    }

    public Integer getIdaperturacierre() {
        return idaperturacierre;
    }

    public void setIdaperturacierre(Integer idaperturacierre) {
        this.idaperturacierre = idaperturacierre;
    }

    public Integer getMonto_apertura() {
        return monto_apertura;
    }

    public void setMonto_apertura(Integer monto_apertura) {
        this.monto_apertura = monto_apertura;
    }

    public Integer getIdcajero() {
        return idcajero;
    }

    public void setIdcajero(Integer idcajero) {
        this.idcajero = idcajero;
    }

    public Integer getIdcaja() {
        return idcaja;
    }

    public void setIdcaja(Integer idcaja) {
        this.idcaja = idcaja;
    }

    public ventasDTO(Integer idaperturacierre, String fecha_apertura, Integer monto_apertura, String cajero, String fecha_cierre, String estado, String caja, Integer idcaja, Integer idcajero, Integer idestado) {
        this.idaperturacierre = idaperturacierre;
        this.fecha_apertura = fecha_apertura;
        this.monto_apertura = monto_apertura;
        this.cajero = cajero;
        this.fecha_cierre = fecha_cierre;
        this.estado = estado;
        this.caja = caja;
        this.idcaja = idcaja;
        this.idcajero = idcajero;
        this.idestado = idestado;
    }

    public ventasDTO(Integer idtimbrado, Integer numero) {
        this.idtimbrado = idtimbrado;
        this.numero = numero;
    }

    public ventasDTO(Integer idtimbrado, Integer numero, String fecha_carga, String fecha_vigencia_inicio, String fecha_vigencia_final, String factura_desde, String factura_hasta, String estado, String tipodocumento, Integer idestado) {
        this.idtimbrado = idtimbrado;
        this.numero = numero;
        this.fecha_carga = fecha_carga;
        this.fecha_vigencia_inicio = fecha_vigencia_inicio;
        this.fecha_vigencia_final = fecha_vigencia_final;
        this.factura_desde = factura_desde;
        this.factura_hasta = factura_hasta;
        this.estado = estado;
        this.tipodocumento = tipodocumento;
        this.idestado = idestado;
    }

    public ventasDTO(Integer idGenerico, String decripGenerico) {
        this.idGenerico = idGenerico;
        this.decripGenerico = decripGenerico;
    }

    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public ventasDTO() {
    }

    public Integer getIdtimbrado() {
        return idtimbrado;
    }

    public void setIdtimbrado(Integer idtimbrado) {
        this.idtimbrado = idtimbrado;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getFecha_carga() {
        return fecha_carga;
    }

    public void setFecha_carga(String fecha_carga) {
        this.fecha_carga = fecha_carga;
    }

    public String getFecha_vigencia_inicio() {
        return fecha_vigencia_inicio;
    }

    public void setFecha_vigencia_inicio(String fecha_vigencia_inicio) {
        this.fecha_vigencia_inicio = fecha_vigencia_inicio;
    }

    public String getFecha_vigencia_final() {
        return fecha_vigencia_final;
    }

    public void setFecha_vigencia_final(String fecha_vigencia_final) {
        this.fecha_vigencia_final = fecha_vigencia_final;
    }

    public String getFactura_establesimiento() {
        return factura_establesimiento;
    }

    public void setFactura_establesimiento(String factura_establesimiento) {
        this.factura_establesimiento = factura_establesimiento;
    }

    public String getFactura_caja() {
        return factura_caja;
    }

    public void setFactura_caja(String factura_caja) {
        this.factura_caja = factura_caja;
    }

    public String getFactura_desde() {
        return factura_desde;
    }

    public void setFactura_desde(String factrua_desde) {
        this.factura_desde = factrua_desde;
    }

    public String getFactura_hasta() {
        return factura_hasta;
    }

    public void setFactura_hasta(String factura_hasta) {
        this.factura_hasta = factura_hasta;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getIdtipodocumento() {
        return idtipodocumento;
    }

    public void setIdtipodocumento(Integer idtipodocumento) {
        this.idtipodocumento = idtipodocumento;
    }

    public Integer getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Integer idsucursal) {
        this.idsucursal = idsucursal;
    }

}
