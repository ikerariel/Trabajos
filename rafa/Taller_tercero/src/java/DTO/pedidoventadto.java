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
public class pedidoventadto {
    private Integer pedi_ven_nro;
    private String pedi_fecha;
    private Integer pedi_total;
    private Integer idusuario;
    private String usu_nombre;
    private Integer idclientes;
    private String cli_razonsocial;
    private String obsevacion_pven;
    private Integer idestado;
    private String descri_estado;
    private Integer iddeposito;
    private String dep_descripcion;

    public pedidoventadto(String pedi_fecha, int pedi_total, String usu_nombre, String cli_razonsocial,
            String obsevacion_pven, String descri_estado, String dep_descripcion, int idmercaderia, 
            int detpedi_cant, int detpedi_precio, String codigogenerico, String mer_descripcion) {
        this.pedi_fecha = pedi_fecha;
        this.pedi_total = pedi_total;
        this.usu_nombre = usu_nombre;
        this.cli_razonsocial = cli_razonsocial;
        this.obsevacion_pven = obsevacion_pven;
        this.descri_estado = descri_estado;
        this.dep_descripcion = dep_descripcion;
        this.idmercaderia = idmercaderia;
        this.detpedi_cant = detpedi_cant;
        this.detpedi_precio = detpedi_precio;
        this.codigogenerico = codigogenerico;
        this.mer_descripcion = mer_descripcion;
    }

    public String getDep_descripcion() {
        return dep_descripcion;
    }

    public void setDep_descripcion(String dep_descripcion) {
        this.dep_descripcion = dep_descripcion;
    }

    public pedidoventadto(int pedi_ven_nro, String pedi_fecha, int pedi_total,  String usu_nombre, 
            String cli_razonsocial, String obsevacion_pven, String descri_estado, String dep_descripcion) {
        this.pedi_ven_nro = pedi_ven_nro;
        this.pedi_fecha = pedi_fecha;
        this.pedi_total = pedi_total;
        this.usu_nombre = usu_nombre;
        this.cli_razonsocial = cli_razonsocial;
        this.obsevacion_pven = obsevacion_pven;
        this.descri_estado = descri_estado;
        this.dep_descripcion = dep_descripcion;
    }

    public Integer getIddeposito() {
        return iddeposito;
    }

    public void setIddeposito(Integer iddeposito) {
        this.iddeposito = iddeposito;
    }
    
    private Integer pedi_ven_nroD;
    private Integer idmercaderia;
    private Integer detpedi_cant;
    private Integer detpedi_precio;
    
    private Integer mer_costo;
    private Integer mer_precio;
    private String mer_descripcion;
    private Integer idcategoria;
    private Integer idmarca;
    private Integer idprocedencia;
    private Integer idimpuesto;
    private String codigogenerico;
    
    public pedidoventadto(){
    }

    public pedidoventadto(Integer idmercaderia, Integer mer_costo, Integer mer_precio,
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

    public Integer getPedi_ven_nro() {
        return pedi_ven_nro;
    }

    public void setPedi_ven_nro(Integer pedi_ven_nro) {
        this.pedi_ven_nro = pedi_ven_nro;
    }

    public String getPedi_fecha() {
        return pedi_fecha;
    }

    public void setPedi_fecha(String pedi_fecha) {
        this.pedi_fecha = pedi_fecha;
    }

    public Integer getPedi_total() {
        return pedi_total;
    }

    public void setPedi_total(Integer pedi_total) {
        this.pedi_total = pedi_total;
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

    public Integer getIdclientes() {
        return idclientes;
    }

    public void setIdclientes(Integer idclientes) {
        this.idclientes = idclientes;
    }

    public String getCli_razonsocial() {
        return cli_razonsocial;
    }

    public void setCli_razonsocial(String cli_razonsocial) {
        this.cli_razonsocial = cli_razonsocial;
    }

    public String getObsevacion_pven() {
        return obsevacion_pven;
    }

    public void setObsevacion_pven(String obsevacion_pven) {
        this.obsevacion_pven = obsevacion_pven;
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

    public Integer getPedi_ven_nroD() {
        return pedi_ven_nroD;
    }

    public void setPedi_ven_nroD(Integer pedi_ven_nroD) {
        this.pedi_ven_nroD = pedi_ven_nroD;
    }

    public Integer getIdmercaderia() {
        return idmercaderia;
    }

    public void setIdmercaderia(Integer idmercaderia) {
        this.idmercaderia = idmercaderia;
    }

    public Integer getDetpedi_cant() {
        return detpedi_cant;
    }

    public void setDetpedi_cant(Integer detpedi_cant) {
        this.detpedi_cant = detpedi_cant;
    }

    public Integer getDetpedi_precio() {
        return detpedi_precio;
    }

    public void setDetpedi_precio(Integer detpedi_precio) {
        this.detpedi_precio = detpedi_precio;
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
