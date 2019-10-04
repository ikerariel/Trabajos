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
public class sEntregaEquiposDTO {

    private Integer id_entregaequipos;
    private String fecha;
    private Integer id_ordenttabajo;
    private String fechaordentrabajo;
    private String cliente;
    private Integer id_cliente;
    private String observacion;
    private Integer id_articulo;
    private String articulo;
    private Integer cantidad;
    

    private String orden;

    private Integer nrOOT;

    private Integer id_estado;
    private Integer id_usuario;

    private Integer catidad;

    private String estado;
    private String usuario;

    public sEntregaEquiposDTO(Integer id_entregaequipos, String fecha, Integer id_ordenttabajo, String fechaordentrabajo, String cliente, Integer id_cliente, String observacion, Integer id_articulo, String articulo, Integer cantidad) {
        this.id_entregaequipos = id_entregaequipos;
        this.fecha = fecha;
        this.id_ordenttabajo = id_ordenttabajo;
        this.fechaordentrabajo = fechaordentrabajo;
        this.cliente = cliente;
        this.id_cliente = id_cliente;
        this.observacion = observacion;
        this.id_articulo = id_articulo;
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public sEntregaEquiposDTO(Integer id_entregaequipos, String fecha, String cliente, String estado, String usuario, Integer id_estado) {
        this.id_entregaequipos = id_entregaequipos;
        this.fecha = fecha;
        this.cliente = cliente;
        this.estado = estado;
        this.usuario = usuario;
        this.id_estado = id_estado;
    }

    public sEntregaEquiposDTO(Integer id_ordenttabajo, String fecha, String cliente, Integer id_cliente, String orden, Integer id_articulo, String articulo, Integer cantidad, Integer nrOOT) {
        this.id_ordenttabajo = id_ordenttabajo;
        this.fecha = fecha;
        this.cliente = cliente;
        this.id_cliente = id_cliente;
        this.orden = orden;
        this.id_articulo = id_articulo;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.nrOOT = nrOOT;
    }

    public sEntregaEquiposDTO(Integer id_ordenttabajo, String fecha, String cliente) {
        this.id_ordenttabajo = id_ordenttabajo;
        this.fecha = fecha;
        this.cliente = cliente;
    }

    public sEntregaEquiposDTO() {
    }

    public Integer getId_ordenttabajo() {
        return id_ordenttabajo;
    }

    public void setId_ordenttabajo(Integer id_ordenttabajo) {
        this.id_ordenttabajo = id_ordenttabajo;
    }

    public Integer getId_entregaequipos() {
        return id_entregaequipos;
    }

    public void setId_entregaequipos(Integer id_entregaequipos) {
        this.id_entregaequipos = id_entregaequipos;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getCatidad() {
        return catidad;
    }

    public void setCatidad(Integer catidad) {
        this.catidad = catidad;
    }

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
    }

}
