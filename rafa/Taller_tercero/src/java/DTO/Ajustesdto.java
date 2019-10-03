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
public class Ajustesdto {
    private Integer idajuste;
    private String ajuste_fecha;
    private Integer idmot_ajus;
    private String ajustmotiv_descri;
    private Integer idusuario;
    private String usu_nombre;
    private Integer idestado;
    private String descri_estado;
    
    private Integer idajusteA;
    private Integer idmercaderia;
    private Integer ajuste_cantidad;
    
    private Integer mer_costo;
    private Integer mer_precio;
    private String mer_descripcion;
    private Integer idcategoria;
    private Integer idmarca;
    private Integer idprocedencia;
    private Integer idimpuesto;
    private String codigogenerico;
    
    public Ajustesdto(){
    }
    
    public Ajustesdto(Integer idajuste, String ajuste_fecha, String ajustmotiv_descri, String usu_nombre,
           String descri_estado, Integer idmercaderia, Integer ajuste_cantidad, String codigogenerico, String mer_descripcion){
        this.idajuste=idajuste;
        this.ajuste_fecha=ajuste_fecha;
        this.ajustmotiv_descri=ajustmotiv_descri;
        this.usu_nombre=usu_nombre;
        this.descri_estado=descri_estado;
        this.idmercaderia=idmercaderia;
        this.ajuste_cantidad=ajuste_cantidad;
        this.codigogenerico=codigogenerico;
        this.mer_descripcion=mer_descripcion;
    }

    public Ajustesdto(Integer idmercaderia, Integer mer_costo, Integer mer_precio,
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

    public Ajustesdto(int idajuste, String ajuste_fecha, String ajustmotiv_descri, 
            String usu_nombre, String descri_estado) {
        this.idajuste=idajuste;
        this.ajuste_fecha=ajuste_fecha;
        this.ajustmotiv_descri=ajustmotiv_descri;
        this.usu_nombre=usu_nombre;
        this.descri_estado=descri_estado;
    }

    public Integer getIdajuste() {
        return idajuste;
    }

    public void setIdajuste(Integer idajuste) {
        this.idajuste = idajuste;
    }

    public String getAjuste_fecha() {
        return ajuste_fecha;
    }

    public void setAjuste_fecha(String ajuste_fecha) {
        this.ajuste_fecha = ajuste_fecha;
    }

    public Integer getIdmot_ajus() {
        return idmot_ajus;
    }

    public void setIdmot_ajus(Integer idmot_ajus) {
        this.idmot_ajus = idmot_ajus;
    }

    public String getAjustmotiv_descri() {
        return ajustmotiv_descri;
    }

    public void setAjustmotiv_descri(String ajustmotiv_descri) {
        this.ajustmotiv_descri = ajustmotiv_descri;
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

    public String getDescri_estado() {
        return descri_estado;
    }

    public void setDescri_estado(String descri_estado) {
        this.descri_estado = descri_estado;
    }

    public Integer getIdajusteA() {
        return idajusteA;
    }

    public void setIdajusteA(Integer idajusteA) {
        this.idajusteA = idajusteA;
    }

    public Integer getIdmercaderia() {
        return idmercaderia;
    }

    public void setIdmercaderia(Integer idmercaderia) {
        this.idmercaderia = idmercaderia;
    }

    public Integer getAjuste_cantidad() {
        return ajuste_cantidad;
    }

    public void setAjuste_cantidad(Integer ajuste_cantidad) {
        this.ajuste_cantidad = ajuste_cantidad;
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
