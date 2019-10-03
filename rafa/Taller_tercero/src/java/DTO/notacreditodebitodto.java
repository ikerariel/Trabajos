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
public class notacreditodebitodto {
    
    private Integer idcred_deb;
    private String nocred_tipo;
    private String nocred_fecha;
    private String nocred_motivo;
    private Integer idcompra;
    private String comp_fecha;
    private String comp_tipo;  
    private Integer idsucursal;
    private String suc_descripcion;
    private Integer idciudad;
    private Integer idusuario;
    private String usu_nombre;
    private Integer idestado;
    private String descri_estado;
    private Integer id_prov;
    private String prov_nombre;

    
    private Integer idcred_debD;
    private Integer idmercaderia;
    private Integer cred_deb_cantidad;
    private Integer cred_deb_precio;
    
    private Integer mer_costo;
    private Integer mer_precio;
    private String mer_descripcion;
    private Integer idcategoria;
    private Integer idmarca;
    private Integer idprocedencia;
    private Integer idimpuesto;
    private String codigogenerico;
    
    public notacreditodebitodto(){
        
    }
    public notacreditodebitodto(Integer idestado, String descripcion) {
        this.idestado = idestado;
        this.descri_estado = descripcion;
    }

    public notacreditodebitodto(Integer idmercaderia, Integer mer_costo, Integer mer_precio, String mer_descripcion,
            int idcategoria, Integer idmarca, Integer idprocedencia, Integer idimpuesto, String codigogenerico) {
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
    public notacreditodebitodto(Integer idcred_deb, String nocred_tipo, String nocred_fecha,
             String usu_nombre, String descri_estado, String prov_nombre){
        this.idcred_deb = idcred_deb;
        this.nocred_tipo = nocred_tipo;
        this.nocred_fecha = nocred_fecha;
        this.usu_nombre = usu_nombre;
        this.descri_estado = descri_estado;
        this.prov_nombre = prov_nombre;
    }

    public notacreditodebitodto(Integer idcred_deb, String nocred_tipo, String nocred_fecha, String nocred_motivo,
            Integer idcompra, String usu_nombre, String descri_estado, String prov_nombre, Integer idmercaderia,
            Integer cred_deb_cantidad, Integer cred_deb_precio, String codigogenerico, String mer_descripcion) {
        this.idcred_deb = idcred_deb;
        this.nocred_tipo = nocred_tipo;
        this.nocred_fecha = nocred_fecha;
        this.nocred_motivo = nocred_motivo;
        this.idcompra = idcompra;
        this.usu_nombre = usu_nombre;
        this.descri_estado = descri_estado;
        this.prov_nombre = prov_nombre;
        this.idmercaderia = idmercaderia;
        this.cred_deb_cantidad = cred_deb_cantidad;
        this.cred_deb_precio = cred_deb_precio;
        this.codigogenerico = codigogenerico;
        this.mer_descripcion = mer_descripcion;
    }

    public Integer getIdcred_deb() {
        return idcred_deb;
    }

    public void setIdcred_deb(Integer idcred_deb) {
        this.idcred_deb = idcred_deb;
    }

    public String getNocred_tipo() {
        return nocred_tipo;
    }

    public void setNocred_tipo(String nocred_tipo) {
        this.nocred_tipo = nocred_tipo;
    }

    public String getNocred_fecha() {
        return nocred_fecha;
    }

    public void setNocred_fecha(String nocred_fecha) {
        this.nocred_fecha = nocred_fecha;
    }

    public String getNocred_motivo() {
        return nocred_motivo;
    }

    public void setNocred_motivo(String nocred_motivo) {
        this.nocred_motivo = nocred_motivo;
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

    public Integer getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Integer idsucursal) {
        this.idsucursal = idsucursal;
    }

    public String getSuc_descripcion() {
        return suc_descripcion;
    }

    public void setSuc_descripcion(String suc_descripcion) {
        this.suc_descripcion = suc_descripcion;
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

    public Integer getIdcred_debD() {
        return idcred_debD;
    }

    public void setIdcred_debD(Integer idcred_debD) {
        this.idcred_debD = idcred_debD;
    }

    public Integer getIdmercaderia() {
        return idmercaderia;
    }

    public void setIdmercaderia(Integer idmercaderia) {
        this.idmercaderia = idmercaderia;
    }

    public Integer getCred_deb_cantidad() {
        return cred_deb_cantidad;
    }

    public void setCred_deb_cantidad(Integer cred_deb_cantidad) {
        this.cred_deb_cantidad = cred_deb_cantidad;
    }

    public Integer getCred_deb_precio() {
        return cred_deb_precio;
    }

    public void setCred_deb_precio(Integer cred_deb_precio) {
        this.cred_deb_precio = cred_deb_precio;
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
    
    public Integer getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(Integer idciudad) {
        this.idciudad = idciudad;
    }
    public String getComp_tipo() {
        return comp_tipo;
    }

    public void setComp_tipo(String comp_tipo) {
        this.comp_tipo = comp_tipo;
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
    
}
