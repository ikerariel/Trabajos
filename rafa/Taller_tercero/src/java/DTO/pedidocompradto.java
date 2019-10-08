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
public class pedidocompradto {

    private Integer pcomp_nro;
    private String pcomp_fecha;
    private String observacion;
    private Integer idusuario;
    private String usu_nombre;
    private String descri_estado;
    private Integer iddeposito;



    public Integer getIddeposito() {
        return iddeposito;
    }

    public void setIddeposito(Integer iddeposito) {
        this.iddeposito = iddeposito;
    }

   
    private Integer idestado;
    
    private Integer pcomp_nroD;
    private Integer idmercaderia;
    private Integer cantidad;
    private Integer precio;

    private Integer mer_costo;
    private Integer mer_precio;
    private Integer nropedido;
    private String mer_descripcion;
    private Integer idcategoria;
    private Integer idmarca;
    private Integer idprocedencia;
    private Integer idimpuesto;
    private String codigogenerico;

    public pedidocompradto() {
    }

    public pedidocompradto(Integer pcomp_nro, String pcomp_fecha, String pcomp_estado,
            String observacion, Integer idusuario, Integer idestado) {
        this.pcomp_nro = pcomp_nro;
        this.pcomp_fecha = pcomp_fecha;
        this.observacion = observacion;
        this.idusuario = idusuario;
        this.idestado = idestado;
    }

    public pedidocompradto(Integer pcomp_nro, String pcomp_fecha,
            String observacion, String usu_nombre, String descri_estado) {
        this.pcomp_nro = pcomp_nro;
        this.pcomp_fecha = pcomp_fecha;
        this.usu_nombre = usu_nombre;
        this.descri_estado = descri_estado;
        this.observacion = observacion;

    }

    public pedidocompradto(Integer pcomp_nroD, Integer idmercaderia, Integer cantidad, Integer precio) {
        this.pcomp_nroD = pcomp_nroD;
        this.idmercaderia = idmercaderia;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public pedidocompradto(Integer idmercaderia, Integer mer_costo, Integer mer_precio,
            String mer_descripcion, Integer idcategoria, Integer idmarca,  Integer idprocedencia, Integer idimpuesto, String codigogenerico) {
        this.idmercaderia = idmercaderia;
        this.mer_costo = mer_costo;
        this.mer_precio = mer_precio;
        this.mer_descripcion = mer_descripcion;
        this.idcategoria = idcategoria;
        this.idmarca = idmarca;
        this.idprocedencia = idprocedencia;
        this.idimpuesto = idimpuesto;
        this.codigogenerico = codigogenerico;
    }
        public pedidocompradto(String pcomp_fecha, 
                String usu_nombre, String descri_estado, 
                String observacion, Integer idmercaderia, Integer cantidad,
                Integer precio, String codigogenerico, String mer_descripcion) {
            this.pcomp_fecha = pcomp_fecha;
            this.usu_nombre = usu_nombre;
            this.descri_estado = descri_estado;
            this.observacion = observacion;
            this.idmercaderia = idmercaderia;
            this.cantidad = cantidad;
            this.precio = precio;
            this.codigogenerico = codigogenerico;
            this.mer_descripcion = mer_descripcion;

        
        }

    public pedidocompradto(String pcomp_fecha, String usu_nombre, String descri_estado, 
            String observacion, Integer idmercaderia, Integer cantidad,Integer precio, 
            String codigogenerico, Integer nropedido, String mer_descripcion) {
        this.pcomp_fecha = pcomp_fecha;
        this.usu_nombre = usu_nombre;
        this.descri_estado = descri_estado;
        this.observacion = observacion;
        this.idmercaderia = idmercaderia;
        this.cantidad = cantidad;
        this.precio = precio;
        this.codigogenerico = codigogenerico;
        this.nropedido = nropedido;
        this.mer_descripcion = mer_descripcion;
        
    }
    
    public String getDescri_estado() {
        return descri_estado;
    }
     public void setDescri_estado(String descri_estado) {
        this.descri_estado = descri_estado;
    }

    public Integer getMer_costo() {
        return mer_costo;
    }

    public void setMer_costo(Integer mer_costo) {
        this.mer_costo = mer_costo;
    }

    public Integer getMer_precio() {
        return mer_precio;
    }

    public void setMer_precio(Integer mer_precio) {
        this.mer_precio = mer_precio;
    }

    public String getMer_descripcion() {
        return mer_descripcion;
    }

    public void setMer_descripcion(String mer_descripcion) {
        this.mer_descripcion = mer_descripcion;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public Integer getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(Integer idmarca) {
        this.idmarca = idmarca;
    }

    public Integer getIdprocedencia() {
        return idprocedencia;
    }

    public void setIdprocedencia(Integer idprocedencia) {
        this.idprocedencia = idprocedencia;
    }

    public Integer getIdimpuesto() {
        return idimpuesto;
    }

    public void setIdimpuesto(Integer idimpuesto) {
        this.idimpuesto = idimpuesto;
    }

    public String getCodigogenerico() {
        return codigogenerico;
    }

    public void setCodigogenerico(String codigogenerico) {
        this.codigogenerico = codigogenerico;
    }

    public Integer getPcomp_nro() {
        return pcomp_nro;
    }

    public void setPcomp_nro(Integer pcomp_nro) {
        this.pcomp_nro = pcomp_nro;
    }

    public String getPcomp_fecha() {
        return pcomp_fecha;
    }

    public void setPcomp_fecha(String pcomp_fecha) {
        this.pcomp_fecha = pcomp_fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    public Integer getPcomp_nroD() {
        return pcomp_nroD;
    }

    public void setPcomp_nroD(Integer pcomp_nroD) {
        this.pcomp_nroD = pcomp_nroD;
    }

    public Integer getIdmercaderia() {
        return idmercaderia;
    }

    public void setIdmercaderia(Integer idmercaderia) {
        this.idmercaderia = idmercaderia;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

}
