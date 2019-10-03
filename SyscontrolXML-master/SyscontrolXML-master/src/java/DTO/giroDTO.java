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
public class giroDTO {

    Integer iddetallerecargas;

    Integer idtipooperadora;
    Integer nrotransaccion;
    Integer nrooperacion;
    Integer idtipooperacion;
    Integer idcliente;
    Integer idusuario;
    String descripcion;
    String nombrecliente;
    String cedula;
    Integer idapertura;
    Integer iddetallegiro;
    Integer nroorigen;
    Integer nrodestino;
    String operadora;
    String operacion;
    Integer montogirobilletera;
    String porcentaje;
    Integer montoenvio;
    Integer idestado;
    Integer montopagado;
    Integer idpago;
    Integer vnrooperacion;
    Integer montorecarga;
    Integer idpagorecarga;
    String fecha;
    String cliente;
    String fechapago;
    String fecharecarga;
    String fechapagorecarga;

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getVnrooperacion() {
        return vnrooperacion;
    }

    public void setVnrooperacion(Integer vnrooperacion) {
        this.vnrooperacion = vnrooperacion;
    }

    public Integer getMontopagado() {
        return montopagado;
    }

    public void setMontopagado(Integer montopagado) {
        this.montopagado = montopagado;
    }

    public giroDTO(Integer iddetallegiro,String cliente, String operacion, Integer montoenvio,Integer idestado, String fechapago,Integer idpago,String fecha) {
        this.iddetallegiro = iddetallegiro;
        this.operacion = operacion;
        this.montoenvio = montoenvio;
        this.idestado = idestado;
        this.fechapago = fechapago;
        this.fecha = fecha;
        this.idpago = idpago;
        this.cliente = cliente;
    }
    public giroDTO(Integer iddetallerecargas,String cliente, String operacion, Integer montorecarga,Integer idestado, String fechapagorecarga,Integer idpagorecarga,Integer nrodestino,String fecharecarga) {
        this.iddetallerecargas = iddetallerecargas;
        this.operacion = operacion;
        this.montorecarga = montorecarga;
        this.idestado = idestado;
        this.fechapagorecarga = fechapagorecarga;
        this.fecharecarga = fecharecarga;
        this.idpagorecarga = idpagorecarga;
        this.nrodestino = nrodestino;
        this.cliente = cliente;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    public Integer getIdapertura() {
        return idapertura;
    }

    public giroDTO(Integer iddetallegiro) {
        this.iddetallegiro = iddetallegiro;
    }

    public void setIdapertura(Integer idapertura) {
        this.idapertura = idapertura;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Integer getMontoenvio() {
        return montoenvio;
    }

    public void setMontoenvio(Integer montoenvio) {
        this.montoenvio = montoenvio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getIddetallegiro() {
        return iddetallegiro;
    }

    public void setIddetallegiro(Integer iddetallegiro) {
        this.iddetallegiro = iddetallegiro;
    }

    public giroDTO(Integer iddetallegiro, Integer nroorigen, Integer nrodestino, String operadora, String operacion, Integer montogirobilletera, String porcentaje, Integer montoenvio, Integer idapertura, String fecha) {
        this.iddetallegiro = iddetallegiro;
        this.nroorigen = nroorigen;
        this.nrodestino = nrodestino;
        this.operadora = operadora;
        this.operacion = operacion;
        this.montogirobilletera = montogirobilletera;
        this.porcentaje = porcentaje;
        this.montoenvio = montoenvio;
        this.idapertura = idapertura;
        this.fecha = fecha;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public giroDTO(Integer idcliente, String nombrecliente, String cedula) {
        this.idcliente = idcliente;
        this.nombrecliente = nombrecliente;
        this.cedula = cedula;
    }

    public giroDTO() {
    }

    public giroDTO(Integer idtipooperacion, String descripcion) {
        this.idtipooperacion = idtipooperacion;
        this.descripcion = descripcion;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIddetallerecargas() {
        return iddetallerecargas;
    }

    public void setIddetallerecargas(Integer iddetallerecargas) {
        this.iddetallerecargas = iddetallerecargas;
    }

    public Integer getNrodestino() {
        return nrodestino;
    }

    public void setNrodestino(Integer nrodestino) {
        this.nrodestino = nrodestino;
    }

    public Integer getNroorigen() {
        return nroorigen;
    }

    public void setNroorigen(Integer nroorigen) {
        this.nroorigen = nroorigen;
    }

    public Integer getMontogirobilletera() {
        return montogirobilletera;
    }

    public void setMontogirobilletera(Integer montogirobilletera) {
        this.montogirobilletera = montogirobilletera;
    }

    public Integer getIdtipooperadora() {
        return idtipooperadora;
    }

    public void setIdtipooperadora(Integer idtipooperadora) {
        this.idtipooperadora = idtipooperadora;
    }

    public Integer getNrotransaccion() {
        return nrotransaccion;
    }

    public void setNrotransaccion(Integer nrotransaccion) {
        this.nrotransaccion = nrotransaccion;
    }

    public Integer getNrooperacion() {
        return nrooperacion;
    }

    public void setNrooperacion(Integer nrooperacion) {
        this.nrooperacion = nrooperacion;
    }

    public Integer getIdtipooperacion() {
        return idtipooperacion;
    }

    public void setIdtipooperacion(Integer idtipooperacion) {
        this.idtipooperacion = idtipooperacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
