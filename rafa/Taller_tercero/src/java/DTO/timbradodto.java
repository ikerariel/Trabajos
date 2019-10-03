/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Rafel
 */
public class timbradodto {
    private Integer idtimbrado;
    private Integer tim_numero;
    private String tim_fechavence;
    private Integer idestado;
    private String descri_estado;
    private String tim_fechainicio;
    private Integer idusuario;
    private String usu_nombre;
    private String fac_establecimiento;
    private String fac_desde;
    private String fac_hasta;
    private String fac_caja;
    
    private Integer idnrofactura;
    private String numerfactura;

    public timbradodto(int idtimbrado, int tim_numero, String tim_fechavence, int idestado, 
            String descri_estado, String tim_fechainicio, int idusuario, String usu_nombre, 
            String fac_establecimiento, String fac_desde, String fac_hasta, String fac_caja) {
        this.idtimbrado = idtimbrado;
        this.tim_numero = tim_numero;
        this.tim_fechavence = tim_fechavence;
        this.idestado = idestado;
        this.descri_estado = descri_estado;
        this.tim_fechainicio = tim_fechainicio;
        this.idusuario = idusuario;
        this.usu_nombre = usu_nombre;
        this.fac_establecimiento = fac_establecimiento;
        this.fac_desde = fac_desde;
        this.fac_hasta = fac_hasta;
        this.fac_caja = fac_caja;
    }

    public timbradodto() {
        
    }


    public Integer getIdnrofactura() {
        return idnrofactura;
    }

    public void setIdnrofactura(Integer idnrofactura) {
        this.idnrofactura = idnrofactura;
    }

    public String getNumerfactura() {
        return numerfactura;
    }

    public void setNumerfactura(String numerfactura) {
        this.numerfactura = numerfactura;
    }

    public Integer getIdtimbrado() {
        return idtimbrado;
    }

    public void setIdtimbrado(Integer idtimbrado) {
        this.idtimbrado = idtimbrado;
    }

    public Integer getTim_numero() {
        return tim_numero;
    }

    public void setTim_numero(Integer tim_numero) {
        this.tim_numero = tim_numero;
    }

    public String getTim_fechavence() {
        return tim_fechavence;
    }

    public void setTim_fechavence(String tim_fechavence) {
        this.tim_fechavence = tim_fechavence;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    public String getDescri_estado() {
        return descri_estado;
    }

    public void setDescri_estado(String descri_estado) {
        this.descri_estado = descri_estado;
    }

    public String getTim_fechainicio() {
        return tim_fechainicio;
    }

    public void setTim_fechainicio(String tim_fechainicio) {
        this.tim_fechainicio = tim_fechainicio;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsu_nombre() {
        return usu_nombre;
    }

    public void setUsu_nombre(String usu_nombre) {
        this.usu_nombre = usu_nombre;
    }

    public String getFac_establecimiento() {
        return fac_establecimiento;
    }

    public void setFac_establecimiento(String fac_establecimiento) {
        this.fac_establecimiento = fac_establecimiento;
    }

    public String getFac_desde() {
        return fac_desde;
    }

    public void setFac_desde(String fac_desde) {
        this.fac_desde = fac_desde;
    }

    public String getFac_hasta() {
        return fac_hasta;
    }

    public void setFac_hasta(String fac_hasta) {
        this.fac_hasta = fac_hasta;
    }

    public String getFac_caja() {
        return fac_caja;
    }

    public void setFac_caja(String fac_caja) {
        this.fac_caja = fac_caja;
    }
}
