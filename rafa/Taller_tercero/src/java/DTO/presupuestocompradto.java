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
public class presupuestocompradto {
    
    private Integer idpresupuestocomp;
    private String presup_fecha;
    private Integer presup_cantcuota;
    private Integer presup_monto;
    private String presup_intervalo;
    private Integer pcomp_nro;
    private String pcomp_fecha;
    private Integer id_prov;
    private String prov_nombre;
    private Integer idsucursal;
    private String suc_descripcion;
    private Integer idusuario;
    private String usu_nombre;
    private Integer idestado;
    private String descri_estado;
     private Integer tipo_codigo;
    private String tipo_descri;
    private Integer idciudad;
    
    private Integer idpresupuestocompD;
    private Integer idmercaderia;
    private Integer detprescomp_cantidad;
    private Integer detprescomp_precio;
    
    private Integer mer_costo;
    private Integer mer_precio;
    private String mer_descripcion;
    private Integer idcategoria;
    private Integer idmarca;
    private Integer idprocedencia;
    private Integer idimpuesto;
    private Integer iddeposito;
    private String codigogenerico;
    
    public presupuestocompradto(){
        
    }

    public Integer getIddeposito() {
        return iddeposito;
    }

    public void setIddeposito(Integer iddeposito) {
        this.iddeposito = iddeposito;
    }
    
    public presupuestocompradto(Integer idpresupuestocomp,Integer tipo_codigo, Integer id_prov,String presup_fecha, Integer presup_cantcuota, Integer presup_monto,
            String presup_intervalo, Integer pcomp_nro, String prov_nombre, String usu_nombre,
            String descri_estado, String tipo_descri, Integer idmercaderia, Integer detprescomp_cantidad, Integer detprescomp_precio,
            String codigogenerico, String mer_descripcion){
        this.idpresupuestocomp=idpresupuestocomp;
        this.tipo_codigo=tipo_codigo;
        this.id_prov=id_prov;
        this.presup_fecha=presup_fecha;
        this.presup_cantcuota=presup_cantcuota;
        this.presup_monto=presup_monto;
        this.presup_intervalo=presup_intervalo;
        this.pcomp_nro=pcomp_nro;
        this.prov_nombre=prov_nombre;
        this.usu_nombre=usu_nombre;
        this.descri_estado=descri_estado;
        this.tipo_descri=tipo_descri;
        this.idmercaderia=idmercaderia;
        this.detprescomp_cantidad=detprescomp_cantidad;
        this.detprescomp_precio=detprescomp_precio;
        this.codigogenerico=codigogenerico;
        this.mer_descripcion=mer_descripcion;
    }
    
    public presupuestocompradto(int pcomp_nro, String pcomp_fecha, String usu_nombre, String descri_estado) {
        this.pcomp_nro = pcomp_nro;
        this.pcomp_fecha = pcomp_fecha;
        this.usu_nombre = usu_nombre;
        this.descri_estado = descri_estado;
    }
     
    public presupuestocompradto(Integer idsucursal, String suc_descripcion, Integer idciudad) {
        this.idsucursal = idsucursal;
        this.suc_descripcion = suc_descripcion;
        this.idciudad = idciudad;
    }
    
    public presupuestocompradto(Integer idmercaderia, Integer mer_costo, Integer mer_precio, String mer_descripcion,
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
    
       
    public presupuestocompradto(int tipo_codigo, String tipo_descri) {
        this.tipo_codigo = tipo_codigo;
        this.tipo_descri = tipo_descri;
    }

    public presupuestocompradto(int idpresupuestocomp, String presup_fecha, String prov_nombre,
            String usu_nombre, String descri_estado, String tipo_descri) {
        this.idpresupuestocomp=idpresupuestocomp;
        this.presup_fecha=presup_fecha;
        this.prov_nombre=prov_nombre;
        this.usu_nombre=usu_nombre;
        this.descri_estado=descri_estado;
        this.tipo_descri=tipo_descri;
    }

    public presupuestocompradto(int idmercaderia, int detprescomp_cantidad,
            int detprescomp_precio, String mer_descripcion, String codigogenerico) {
        this.idmercaderia = idmercaderia;
        this.detprescomp_cantidad = detprescomp_cantidad;
        this.detprescomp_precio = detprescomp_precio;
        this.mer_descripcion = mer_descripcion;
        this.codigogenerico = codigogenerico;
    }

    public Integer getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(Integer idciudad) {
        this.idciudad = idciudad;
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

    public Integer getPresup_cantcuota() {
        return presup_cantcuota;
    }

    public void setPresup_cantcuota(Integer presup_cantcuota) {
        this.presup_cantcuota = presup_cantcuota;
    }

    public Integer getPresup_monto() {
        return presup_monto;
    }

    public void setPresup_monto(Integer presup_monto) {
        this.presup_monto = presup_monto;
    }

    public String getPresup_intervalo() {
        return presup_intervalo;
    }

    public void setPresup_intervalo(String presup_intervalo) {
        this.presup_intervalo = presup_intervalo;
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

    public Integer getIdpresupuestocompD() {
        return idpresupuestocompD;
    }

    public void setIdpresupuestocompD(Integer idpresupuestocompD) {
        this.idpresupuestocompD = idpresupuestocompD;
    }

    public Integer getIdmercaderia() {
        return idmercaderia;
    }

    public void setIdmercaderia(Integer idmercaderia) {
        this.idmercaderia = idmercaderia;
    }

    public Integer getDetprescomp_cantidad() {
        return detprescomp_cantidad;
    }

    public void setDetprescomp_cantidad(Integer detprescomp_cantidad) {
        this.detprescomp_cantidad = detprescomp_cantidad;
    }

    public Integer getDetprescomp_precio() {
        return detprescomp_precio;
    }

    public void setDetprescomp_precio(Integer detprescomp_precio) {
        this.detprescomp_precio = detprescomp_precio;
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
    
     public Integer getTipo_codigo() {
        return tipo_codigo;
    }

    public void setTipo_codigo(Integer tipo_codigo) {
        this.tipo_codigo = tipo_codigo;
    }

    public String getTipo_descri() {
        return tipo_descri;
    }

    public void setTipo_descri(String tipo_descri) {
        this.tipo_descri = tipo_descri;
    }
    
    
}
