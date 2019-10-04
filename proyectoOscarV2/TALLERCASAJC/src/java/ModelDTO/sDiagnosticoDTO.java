/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDTO;

/**
 *
 * @author Usuario
 */
public class sDiagnosticoDTO {

    Integer id_diagnostico;
    String fecha;
    Integer id_recepcion;
    String fecharecepcion;
    String cliente;
    String diganostico;
    Integer id_articulo;
    Integer cantidad;
    String articulo;
    String fecentreg;
    String razonsocial;
    String usu_nombre;
    String ruc;
    String est_descripcion;
    String observacion;
    Integer id_cliente;

    public sDiagnosticoDTO(Integer id_diagnostico, String fecha, Integer id_recepcion, String fecharecepcion, String cliente, String diganostico, Integer id_articulo, Integer cantidad, String articulo) {
        this.id_diagnostico = id_diagnostico;
        this.fecha = fecha;
        this.id_recepcion = id_recepcion;
        this.fecharecepcion = fecharecepcion;
        this.cliente = cliente;
        this.diganostico = diganostico;
        this.id_articulo = id_articulo;
        this.cantidad = cantidad;
        this.articulo = articulo;
    }
    Integer id_estado;
    Integer id_usuario;
    Integer nrorecepcion;

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
    }

    public sDiagnosticoDTO(Integer id_diagnostico, String fecha, String razonsocial, String est_descripcion, String usu_nombre, Integer id_estado) {
        this.id_diagnostico = id_diagnostico;
        this.fecha = fecha;
        this.razonsocial = razonsocial;
        this.est_descripcion = est_descripcion;
        this.usu_nombre = usu_nombre;
        this.id_estado = id_estado;

    }

    public sDiagnosticoDTO(Integer id_recepcion, String fecha, String ruc, String cliente, String observacion, Integer id_articulo, String articulo, Integer id_cliente, Integer id_estado, Integer nrorecepcion, Integer cantidad) {
        this.id_recepcion = id_recepcion;
        this.fecha = fecha;
        this.ruc = ruc;
        this.cliente = cliente;
        this.observacion = observacion;
        this.id_articulo = id_articulo;
        this.articulo = articulo;
        this.id_cliente = id_cliente;
        this.id_estado = id_estado;
        this.nrorecepcion = nrorecepcion;
        this.cantidad = cantidad;
    }

    public sDiagnosticoDTO() {
    }

    public String getDiganostico() {
        return diganostico;
    }

    public void setDiganostico(String diganostico) {
        this.diganostico = diganostico;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getUsu_nombre() {
        return usu_nombre;
    }

    public void setUsu_nombre(String usu_nombre) {
        this.usu_nombre = usu_nombre;
    }

    public String getEst_descripcion() {
        return est_descripcion;
    }

    public void setEst_descripcion(String est_descripcion) {
        this.est_descripcion = est_descripcion;
    }

    public Integer getId_diagnostico() {
        return id_diagnostico;
    }

    public void setId_diagnostico(Integer id_diagnostico) {
        this.id_diagnostico = id_diagnostico;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecentreg() {
        return fecentreg;
    }

    public void setFecentreg(String fecentreg) {
        this.fecentreg = fecentreg;
    }

    public Integer getId_recepcion() {
        return id_recepcion;
    }

    public void setId_recepcion(Integer id_recepcion) {
        this.id_recepcion = id_recepcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

}
