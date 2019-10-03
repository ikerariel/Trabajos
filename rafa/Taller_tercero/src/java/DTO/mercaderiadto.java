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
public class mercaderiadto {
    
    private Integer idmercaderia;
    private Integer mer_costo;
    private Integer mer_precio;
    private String mer_descripcion;
    private Integer idcategoria;
    private String cate_descri;
    private Integer idmarca;
    private String marca_descri;
    private Integer idprocedencia;
    private String proce_descri;
    private Integer idimpuesto;
    private String descri_impuesto;
    private String codigogenerico;
    
    private String stock_merca;   
    private Integer stk_cantidad;

    public mercaderiadto(){}
   
    public mercaderiadto(Integer idmercaderia, Integer mer_costo,Integer mer_precio, String mer_descripcion,
                         Integer idcategoria, Integer idmarca, Integer idprocedencia, Integer idimpuesto,
                         String codigogenerico){
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
    public mercaderiadto(Integer idmercaderia, Integer mer_costo,Integer mer_precio, String mer_descripcion,
                         String cate_descri, String marca_descri, String proce_descri, String descri_impuesto,
                        String codigogenerico){
        this.idmercaderia=idmercaderia;
        this.mer_costo=mer_costo;
        this.mer_precio=mer_precio;
        this.mer_descripcion=mer_descripcion;
        this.cate_descri=cate_descri;
        this.marca_descri=marca_descri;
        this.proce_descri=proce_descri;
        this.descri_impuesto=descri_impuesto;
        this.codigogenerico=codigogenerico;
        
    }
     public String getCodigogenerico() {
        return codigogenerico;
    }

    public void setCodigogenerico(String codigogenerico) {
        this.codigogenerico = codigogenerico;
    }

    public Integer getIdmercaderia() {
        return idmercaderia;
    }

    public void setIdmercaderia(Integer idmercaderia) {
        this.idmercaderia = idmercaderia;
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

    public String getCate_descri() {
        return cate_descri;
    }

    public void setCate_descri(String cate_descri) {
        this.cate_descri = cate_descri;
    }

    public Integer getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(Integer idmarca) {
        this.idmarca = idmarca;
    }

    public String getMarca_descri() {
        return marca_descri;
    }

    public void setMarca_descri(String marca_descri) {
        this.marca_descri = marca_descri;
    }

    public Integer getIdprocedencia() {
        return idprocedencia;
    }

    public void setIdprocedencia(Integer idprocedencia) {
        this.idprocedencia = idprocedencia;
    }

    public String getProce_descri() {
        return proce_descri;
    }

    public void setProce_descri(String proce_descri) {
        this.proce_descri = proce_descri;
    }

    public Integer getIdimpuesto() {
        return idimpuesto;
    }

    public void setIdimpuesto(Integer idimpuesto) {
        this.idimpuesto = idimpuesto;
    }

    public String getDescri_impuesto() {
        return descri_impuesto;
    }

    public void setDescri_impuesto(String descri_impuesto) {
        this.descri_impuesto = descri_impuesto;
    }
    
    public String getStock_merca() {
        return stock_merca;
    }

    public void setStock_merca(String stock_merca) {
        this.stock_merca = stock_merca;
    }

    public Integer getStk_cantidad() {
        return stk_cantidad;
    }

    public void setStk_cantidad(Integer stk_cantidad) {
        this.stk_cantidad = stk_cantidad;
    }
    
}
