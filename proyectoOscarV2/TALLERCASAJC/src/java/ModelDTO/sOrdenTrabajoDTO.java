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
public class sOrdenTrabajoDTO {

    private Integer id_ordenttabajo;
    private String fecha;
    private String fechaentrega;
    private Integer id_presuserv;
    private String usuario;
    private String estado;

    private String fechapresupuesto;
    private String cliente;

    private String diagnostico;
    private String orden;

    private String observacion;
    private Integer nropresupuesto;
    private Integer id_estado;
    private Integer id_usuario;

    public sOrdenTrabajoDTO(Integer id_ordenttabajo, String fecha, String fechaentrega, Integer id_presuserv, String usuario, String estado, Integer id_estado) {
        this.id_ordenttabajo = id_ordenttabajo;
        this.fecha = fecha;
        this.fechaentrega = fechaentrega;
        this.id_presuserv = id_presuserv;
        this.usuario = usuario;
        this.estado = estado;
        this.id_estado = id_estado;

    }

    public sOrdenTrabajoDTO(Integer id_ordenttabajo, String fecha, Integer id_presuserv, String fechapresupuesto, String cliente, String fechaentrega, String diagnostico, String orden) {
        this.id_ordenttabajo = id_ordenttabajo;
        this.fecha = fecha;
        this.id_presuserv = id_presuserv;
        this.fechapresupuesto = fechapresupuesto;
        this.cliente = cliente;
        this.fechaentrega = fechaentrega;
        this.diagnostico = diagnostico;
        this.orden = orden;
    }

    public sOrdenTrabajoDTO() {
    }

    public sOrdenTrabajoDTO(Integer id_presuserv, String fecha, String cliente, String observacion, Integer nropresupuesto, Integer id_estado) {
        this.id_presuserv = id_presuserv;
        this.fecha = fecha;
        this.cliente = cliente;
        this.observacion = observacion;
        this.nropresupuesto = nropresupuesto;
        this.id_estado = id_estado;
    }

    public Integer getId_ordenttabajo() {
        return id_ordenttabajo;
    }

    public void setId_ordenttabajo(Integer id_ordenttabajo) {
        this.id_ordenttabajo = id_ordenttabajo;
    }

    public Integer getId_presuserv() {
        return id_presuserv;
    }

    public void setId_presuserv(Integer id_presuserv) {
        this.id_presuserv = id_presuserv;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getNropresupuesto() {
        return nropresupuesto;
    }

    public void setNropresupuesto(Integer nropresupuesto) {
        this.nropresupuesto = nropresupuesto;
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

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getFechaentrega() {
        return fechaentrega;
    }

    public void setFechaentrega(String fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

}
