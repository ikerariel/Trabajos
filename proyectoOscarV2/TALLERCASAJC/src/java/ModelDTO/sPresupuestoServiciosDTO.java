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
public class sPresupuestoServiciosDTO {

    private Integer id_presuserv;
    private String fecha;
    private Integer id_diagnostico;
    private String fechaDiagnostico;
    private String cliente;
    private String observacion;
    private Integer id_articulo;
    private Integer precio;
    private Integer cantidad;
    private String articulo;
    private String diganostico;
    private Integer nrodiagnostico;
    private Integer id_estado;
    private String estado;
    private String usuario;
    private Integer id_usuario;
    private Integer id_condicionpago;

    public String getDiganostico() {
        return diganostico;
    }

    public void setDiganostico(String diganostico) {
        this.diganostico = diganostico;
    }

    public Integer getId_condicionpago() {
        return id_condicionpago;
    }

    public void setId_condicionpago(Integer id_condicionpago) {
        this.id_condicionpago = id_condicionpago;
    }

    public sPresupuestoServiciosDTO(Integer id_presuserv, String fecha, Integer id_diagnostico, String fechaDiagnostico, String cliente, String observacion, Integer id_articulo, Integer precio, Integer cantidad, String articulo) {
        this.id_presuserv = id_presuserv;
        this.fecha = fecha;
        this.id_diagnostico = id_diagnostico;
        this.fechaDiagnostico = fechaDiagnostico;
        this.cliente = cliente;
        this.observacion = observacion;
        this.id_articulo = id_articulo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.articulo = articulo;
    }

    public sPresupuestoServiciosDTO(Integer id_diagnostico, String fecha, String cliente, String diganostico, Integer id_articulo, Integer cantidad, String articulo, Integer nrodiagnostico, Integer id_estado) {
        this.id_diagnostico = id_diagnostico;
        this.fecha = fecha;
        this.cliente = cliente;
        this.diganostico = diganostico;
        this.id_articulo = id_articulo;
        this.cantidad = cantidad;
        this.articulo = articulo;
        this.nrodiagnostico = nrodiagnostico;
        this.id_estado = id_estado;
    }

    public sPresupuestoServiciosDTO(Integer id_presuserv, String fecha, String cliente, String estado, String usuario, Integer id_estado) {
        this.id_presuserv = id_presuserv;
        this.fecha = fecha;
        this.cliente = cliente;
        this.estado = estado;
        this.usuario = usuario;
        this.id_estado = id_estado;
    }

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getId_presuserv() {
        return id_presuserv;
    }

    public void setId_presuserv(Integer id_presuserv) {
        this.id_presuserv = id_presuserv;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getId_diagnostico() {
        return id_diagnostico;
    }

    public void setId_diagnostico(Integer id_diagnostico) {
        this.id_diagnostico = id_diagnostico;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public sPresupuestoServiciosDTO() {
    }

}
