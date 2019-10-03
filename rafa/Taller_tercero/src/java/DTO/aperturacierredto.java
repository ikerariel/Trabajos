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
public class aperturacierredto {
    private Integer idapercierre;
    private Integer aper_monto;
    private String fecha_apertura;
    private Integer idcaja;
    private String caja_descripcion;
    private Integer idusuario;
    private String usu_nombre;
    private Integer idestado;
    private String descri_estado;
    private Integer cierre_monto;
    private String fecha_cierre;
    private String cajero;
    private Integer iddeposito;
    private String dep_descripcion;
    private Integer idtimbrado;
    private Integer tim_numero;

    public aperturacierredto(int idapercierre, int aper_monto, String caja_descripcion, 
            String usu_nombre, String descri_estado, int cierre_monto, String cajero, 
            int tim_numero, String fecha_apertura, String fecha_cierre) {
        this.idapercierre = idapercierre;
        this.aper_monto = aper_monto;
        this.caja_descripcion = caja_descripcion;
        this.usu_nombre = usu_nombre;
        this.descri_estado = descri_estado;
        this. cierre_monto = cierre_monto;
        this.cajero = cajero;
        this.tim_numero = tim_numero;
        this.fecha_apertura = fecha_apertura;
        this.fecha_cierre = fecha_cierre;
    }

    public aperturacierredto(int aper_monto, String caja_descripcion, String usu_nombre, 
            String descri_estado, int cierre_monto, String cajero, int tim_numero,
            String fecha_apertura, String fecha_cierre) {
        this.aper_monto = aper_monto;
        this.caja_descripcion = caja_descripcion;
        this.usu_nombre = usu_nombre;
        this.descri_estado = descri_estado;
        this. cierre_monto = cierre_monto;
        this.cajero = cajero;
        this.tim_numero = tim_numero;
        this.fecha_apertura = fecha_apertura;
        this.fecha_cierre = fecha_cierre;
    }
    
    public String getCaja_descripcion() {
        return caja_descripcion;
    }

    public void setCaja_descripcion(String caja_descripcion) {
        this.caja_descripcion = caja_descripcion;
    }

    public String getUsu_nombre() {
        return usu_nombre;
    }

    public void setUsu_nombre(String usu_nombre) {
        this.usu_nombre = usu_nombre;
    }

    public String getDescri_estado() {
        return descri_estado;
    }

    public void setDescri_estado(String descri_estado) {
        this.descri_estado = descri_estado;
    }

    public Integer getIddeposito() {
        return iddeposito;
    }

    public void setIddeposito(Integer iddeposito) {
        this.iddeposito = iddeposito;
    }

    public String getDep_descripcion() {
        return dep_descripcion;
    }

    public void setDep_descripcion(String dep_descripcion) {
        this.dep_descripcion = dep_descripcion;
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
       
    
    public aperturacierredto(){}

    public String getCajero() {
        return cajero;
    }

    public void setCajero(String cajero) {
        this.cajero = cajero;
    }

    public Integer getIdapercierre() {
        return idapercierre;
    }

    public void setIdapercierre(Integer idapercierre) {
        this.idapercierre = idapercierre;
    }

    public Integer getAper_monto() {
        return aper_monto;
    }

    public void setAper_monto(Integer aper_monto) {
        this.aper_monto = aper_monto;
    }

    public String getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(String fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }

    public Integer getIdcaja() {
        return idcaja;
    }

    public void setIdcaja(Integer idcaja) {
        this.idcaja = idcaja;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    public Integer getCierre_monto() {
        return cierre_monto;
    }

    public void setCierre_monto(Integer cierre_monto) {
        this.cierre_monto = cierre_monto;
    }

    public String getFecha_cierre() {
        return fecha_cierre;
    }

    public void setFecha_cierre(String fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }
}
