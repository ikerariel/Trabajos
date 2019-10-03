/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Carlos
 */
public class recargaDTO {
    Integer idprefijo;
    Integer idusuario;
    String descripcion;
    Integer nrodestino;
    Integer montopagadorecarga;

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getMontopagadorecarga() {
        return montopagadorecarga;
    }

    public void setMontopagadorecarga(Integer montopagadorecarga) {
        this.montopagadorecarga = montopagadorecarga;
    }
    Integer nrooperarecarga;
    String operadora;
    String fecharecarga;
    String nrooperacion;
    String observacion;

    public Integer getNrooperarecarga() {
        return nrooperarecarga;
    }

    public void setNrooperarecarga(Integer nrooperarecarga) {
        this.nrooperarecarga = nrooperarecarga;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
  Integer montorecarga;
  Integer nrotransaccion;
  Integer idtipooperadora;
  Integer iddetallerecargas;
  Integer idapertura;
  Integer idcliente;
  Integer idestado;

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    public recargaDTO(Integer idapertura) {
        this.idapertura = idapertura;
    }

    public String getNrooperacion() {
        return nrooperacion;
    }

    public void setNrooperacion(String nrooperacion) {
        this.nrooperacion = nrooperacion;
    }

    public recargaDTO() {
    }

    public recargaDTO(Integer idprefijo, String descripcion) {
       this.idprefijo=idprefijo;
       this.descripcion=descripcion;
    }

    public Integer getIddetallerecargas() {
        return iddetallerecargas;
    }

    public void setIddetallerecargas(Integer iddetallerecargas) {
        this.iddetallerecargas = iddetallerecargas;
    }

    public recargaDTO(Integer iddetallerecargas,Integer idapertura, String operadora, Integer nrodestino, Integer montorecarga,Integer nrotransaccion, String fecharecarga) {
        this.iddetallerecargas=iddetallerecargas;
        this.idapertura=idapertura;
        this.operadora=operadora;
        this.nrodestino=nrodestino;
        this.montorecarga=montorecarga;
        this.nrotransaccion=nrotransaccion;
        this.fecharecarga=fecharecarga;
    }
    public recargaDTO(Integer iddetallerecargas, String operadora, Integer nrodestino, Integer montorecarga,Integer nrotransaccion, String fecharecarga) {
        this.iddetallerecargas=iddetallerecargas;
        this.operadora=operadora;
        this.nrodestino=nrodestino;
        this.montorecarga=montorecarga;
        this.nrotransaccion=nrotransaccion;
        this.fecharecarga=fecharecarga;
    }

    public Integer getNrodestino() {
        return nrodestino;
    }

    public void setNrodestino(Integer nrodestino) {
        this.nrodestino = nrodestino;
    }

    public Integer getMontorecarga() {
        return montorecarga;
    }

    public void setMontorecarga(Integer montorecarga) {
        this.montorecarga = montorecarga;
    }

    public Integer getNrotransaccion() {
        return nrotransaccion;
    }

    public void setNrotransaccion(Integer nrotransaccion) {
        this.nrotransaccion = nrotransaccion;
    }

    public Integer getIdtipooperadora() {
        return idtipooperadora;
    }

    public void setIdtipooperadora(Integer idtipooperadora) {
        this.idtipooperadora = idtipooperadora;
    }
    
    
}
