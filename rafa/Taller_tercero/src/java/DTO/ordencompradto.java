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
public class ordencompradto {

    private Integer ordenc_nro;
    private String ordenc_fecha;
    private Integer id_prov;
    private String prov_nombre;
    private Integer idusuario;
    private String usu_nombre;
    private Integer pcomp_nro;
    private String pcomp_fecha;
    private Integer idestado;
    private String descri_estado;
    private Integer idpresupuestocomp;
    private String presup_fecha;

    private Integer ordenc_nroD;
    private Integer idmercaderia;
    private Integer cant_orden;
    private Integer precio_orden;

    private Integer mer_costo;
    private Integer mer_precio;
    private String mer_descripcion;
    private Integer idcategoria;
    private Integer idmarca;
    private Integer idprocedencia;
    private Integer idimpuesto;
    private Integer nroorden;
    private String codigogenerico;

    public ordencompradto() {

    }

    public ordencompradto(Integer ordenc_nro, String ordenc_fecha, String prov_nombre,
            String usu_nombre, Integer pcomp_nro, String descri_estado) {
        this.ordenc_nro = ordenc_nro;
        this.ordenc_fecha = ordenc_fecha;
        this.prov_nombre = prov_nombre;
        this.usu_nombre = usu_nombre;
        this.pcomp_nro = pcomp_nro;
        this.descri_estado = descri_estado;
    }   

    public ordencompradto(Integer ordenc_nroD, Integer idmercaderia, Integer cant_orden,
            Integer precio_orden) {
        this.ordenc_nroD = ordenc_nroD;
        this.idmercaderia = idmercaderia;
        this.cant_orden = cant_orden;
        this.precio_orden = precio_orden;
    }

    public ordencompradto(Integer idmercaderia, Integer mer_costo, Integer mer_precio,
            String mer_descripcion, Integer idcategoria, Integer idmarca, Integer idprocedencia,
            Integer idimpuesto, String codigogenerico) {
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

    public ordencompradto(String ordenc_fecha, String prov_nombre, String usu_nombre,
            Integer pcomp_nro, String descri_estado, Integer idmercaderia, Integer cant_orden,
            Integer precio_orden, String codigogenerico,Integer id_prov,Integer nroorden, String mer_descripcion) {
        this.ordenc_fecha = ordenc_fecha;
        this.prov_nombre = prov_nombre;
        this.usu_nombre = usu_nombre;
        this.pcomp_nro = pcomp_nro;
        this.descri_estado = descri_estado;
        this.idmercaderia = idmercaderia;
        this.cant_orden = cant_orden;
        this.precio_orden = precio_orden;
        this.codigogenerico = codigogenerico;
        this.id_prov = id_prov;
        this.nroorden = nroorden;
        this.mer_descripcion = mer_descripcion;
        
    }

    public ordencompradto(int pcomp_nro, String pcomp_fecha, String usu_nombre, String descri_estado) {
        this.pcomp_nro = pcomp_nro;
        this.pcomp_fecha = pcomp_fecha;
        this.usu_nombre = usu_nombre;
        this.descri_estado = descri_estado;
    }
    
    
    public Integer getIdpresupuestocomp() {
        return idpresupuestocomp;
    }

    public void setIdpresupuestocomp(Integer idpresupuestocomp) {
        this.idpresupuestocomp = idpresupuestocomp;
    }

    public String getPresup_fecha() {
        return presup_fecha;
    }

    public void setPresup_fecha(String presup_fecha) {
        this.presup_fecha = presup_fecha;
    }
    
    public String getDescri_estado() {
        return descri_estado;
    }

    public void setDescri_estado(String descri_estado) {
        this.descri_estado = descri_estado;
    }

    public String getPcomp_fecha() {
        return pcomp_fecha;
    }

    public void setPcomp_fecha(String pcomp_fecha) {
        this.pcomp_fecha = pcomp_fecha;
    }
    public Integer getOrdenc_nro() {
        return ordenc_nro;
    }

    public void setOrdenc_nro(Integer ordenc_nro) {
        this.ordenc_nro = ordenc_nro;
    }

    public String getOrdenc_fecha() {
        return ordenc_fecha;
    }

    public void setOrdenc_fecha(String ordenc_fecha) {
        this.ordenc_fecha = ordenc_fecha;
    }

    public Integer getId_prov() {
        return id_prov;
    }

    public void setId_prov(Integer id_prov) {
        this.id_prov = id_prov;
    }

    public String getProv_nombre() {
        return prov_nombre;
    }

    public void setProv_nombre(String prov_nombre) {
        this.prov_nombre = prov_nombre;
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

    public Integer getPcomp_nro() {
        return pcomp_nro;
    }

    public void setPcomp_nro(Integer pcomp_nro) {
        this.pcomp_nro = pcomp_nro;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    public Integer getOrdenc_nroD() {
        return ordenc_nroD;
    }

    public void setOrdenc_nroD(Integer ordenc_nroD) {
        this.ordenc_nroD = ordenc_nroD;
    }

    public Integer getIdmercaderia() {
        return idmercaderia;
    }

    public void setIdmercaderia(Integer idmercaderia) {
        this.idmercaderia = idmercaderia;
    }

    public Integer getCant_orden() {
        return cant_orden;
    }

    public void setCant_orden(Integer cant_orden) {
        this.cant_orden = cant_orden;
    }

    public Integer getPrecio_orden() {
        return precio_orden;
    }

    public void setPrecio_orden(Integer precio_orden) {
        this.precio_orden = precio_orden;
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

}
