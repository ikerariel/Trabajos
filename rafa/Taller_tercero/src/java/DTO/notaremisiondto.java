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
public class notaremisiondto {
    
    private Integer idnotaremi;
    private String observacionremi;
    private String fecharemi;
    private Integer id_prov;
    private String prov_nombre;
    private Integer idestado;
    private String descri_estado;
    private Integer idusuario;
    private String usu_nombre;
    private Integer idcompra;
    private String comp_fecha;
    private String comp_nrofact;
    
    private Integer idnotaremiRE;
    private Integer idmercaderia;
    private String cantidadremi;
    
    private Integer mer_costo;
    private Integer mer_precio;
    private String mer_descripcion;
    private Integer idcategoria;
    private Integer idmarca;
    private Integer idprocedencia;
    private Integer idimpuesto;
    private String codigogenerico;
    
    public notaremisiondto(){
        
    }

    public notaremisiondto(int idestado, String descri_estado) {
        this.idestado=idestado;
        this.descri_estado=descri_estado;
    }

    public notaremisiondto(int idmercaderia, int mer_costo, int mer_precio, String mer_descripcion,
            int idcategoria, int idmarca, int idprocedencia, int idimpuesto, String codigogenerico) {
        this.idmercaderia=idmercaderia;
        this.mer_costo=mer_costo;
        this.mer_precio=mer_precio;
        this.mer_descripcion=mer_descripcion;
        this.idcategoria=idcategoria;
        this.idmarca=idmarca;
        this.idprocedencia=idprocedencia;
        this.idimpuesto=idimpuesto;
        this.codigogenerico=codigogenerico;
    }

    public notaremisiondto(int idnotaremi, String observacionremi, String fecharemi, String prov_nombre, 
            String descri_estado, String usu_nombre, String comp_nrofact) {
        this.idnotaremi=idnotaremi;
        this.observacionremi=observacionremi;
        this.fecharemi=fecharemi;
        this.prov_nombre=prov_nombre;
        this.descri_estado=descri_estado;
        this.usu_nombre=usu_nombre;
        this.comp_nrofact=comp_nrofact;
    }

    public notaremisiondto(int idnotaremi, String observacionremi, String fecharemi, 
            String prov_nombre, String descri_estado, String usu_nombre, String comp_nrofact, 
            int idmercaderia, String cantidadremi, String codigogenerico, Integer idcompra, String mer_descripcion) {
        this.idnotaremi=idnotaremi;
        this.observacionremi=observacionremi;
        this.fecharemi=fecharemi;
        this.prov_nombre=prov_nombre;
        this.descri_estado=descri_estado;
        this.usu_nombre=usu_nombre;
        this.comp_nrofact=comp_nrofact;
        this.idmercaderia=idmercaderia;
        this.cantidadremi=cantidadremi;
        this.codigogenerico=codigogenerico;
        this.idcompra=idcompra;
        this.mer_descripcion=mer_descripcion;
    }

    public Integer getIdnotaremi() {
        return idnotaremi;
    }

    public void setIdnotaremi(Integer idnotaremi) {
        this.idnotaremi = idnotaremi;
    }

    public String getObservacionremi() {
        return observacionremi;
    }

    public void setObservacionremi(String observacionremi) {
        this.observacionremi = observacionremi;
    }

    public String getFecharemi() {
        return fecharemi;
    }

    public void setFecharemi(String fecharemi) {
        this.fecharemi = fecharemi;
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

    public Integer getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Integer idcompra) {
        this.idcompra = idcompra;
    }

    public String getComp_fecha() {
        return comp_fecha;
    }

    public void setComp_fecha(String comp_fecha) {
        this.comp_fecha = comp_fecha;
    }

    public String getComp_nrofact() {
        return comp_nrofact;
    }

    public void setComp_nrofact(String comp_nrofact) {
        this.comp_nrofact = comp_nrofact;
    }

    public Integer getIdnotaremiRE() {
        return idnotaremiRE;
    }

    public void setIdnotaremiRE(Integer idnotaremiRE) {
        this.idnotaremiRE = idnotaremiRE;
    }

    public Integer getIdmercaderia() {
        return idmercaderia;
    }

    public void setIdmercaderia(Integer idmercaderia) {
        this.idmercaderia = idmercaderia;
    }
    
    public String getCantidadremi() {
        return cantidadremi;
    }

    public void setCantidadremi(String cantidadremi) {
        this.cantidadremi = cantidadremi;
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
