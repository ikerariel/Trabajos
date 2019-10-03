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
       private Integer idtimbrado;
      private Integer numero;
     private String fecha_carga;
     private String fecha_vigencia_inicio;
     private String fecha_vigencia_final;
    private String factura_desde;
     private String factura_hasta;
     private String estado;
     private String tipodocumento;
     private String numerodocumento;
    private Integer idestado;
    private Integer secuencia;
    
     private String factura_establesimiento;
     private String factura_caja;
    
   
     private Integer idusuario;
     private Integer idtipodocumento;
     private Integer idsucursal;

    public ventasDTO(Integer idtimbrado, Integer numero, String fecha_carga, String fecha_vigencia_inicio, String fecha_vigencia_final, String factura_desde, String factura_hasta,String estado, String tipodocumento, Integer idestado) {
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
