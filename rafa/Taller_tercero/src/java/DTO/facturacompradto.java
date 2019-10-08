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
public class facturacompradto {
    
    private Integer idcompra;
    private Integer comp_cantcuota;
    private Integer comp_monto;
    private String comp_nrofact;
    private String comp_intervalo;
    private String comp_fecha;
    private Integer id_prov;
    private String prov_nombre;
    private Integer idsucursal;
    private String suc_descripcion;
    private Integer idusuario;
    private String usu_nombre;
    private Integer idestado;
    private String descri_estado;
    private Integer ordenc_nro;
    private String ordenc_fecha;
    private Integer tipo_codigo;
    private String tipo_descri;
    private Integer idciudad;
    
    private Integer idcompraD;
    private Integer idmercaderia;
    private Integer detfact_cantidad;
    private Integer detfact_precio;
    
    private Integer mer_costo;
    private Integer mer_precio;
    private String mer_descripcion;
    private Integer idcategoria;
    private Integer idmarca;
    private Integer idprocedencia;
    private Integer idimpuesto;
    private String codigogenerico;
    private Integer iddeposito;
    private Integer nrocompra;

    public Integer getIddeposito() {
        return iddeposito;
    }

    public void setIddeposito(Integer iddeposito) {
        this.iddeposito = iddeposito;
    }
    
   
    
    public facturacompradto(){
        
    }
    

    public facturacompradto(Integer ordenc_nro, String ordenc_fecha, String usu_nombre, String descri_estado) {
        this.ordenc_nro = ordenc_nro;
        this.ordenc_fecha = ordenc_fecha;
        this.usu_nombre = usu_nombre;
        this.descri_estado = descri_estado;
    }

    public facturacompradto(Integer idsucursal, String suc_descripcion, Integer idciudad) {
        this.idsucursal = idsucursal;
        this.suc_descripcion = suc_descripcion;
        this.idciudad = idciudad;
    }

    public facturacompradto(Integer idmercaderia, Integer mer_costo, Integer mer_precio, String mer_descripcion,
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

    public facturacompradto(Integer idcompra, String comp_nrofact, String comp_fecha, 
            String tipo_descri, String prov_nombre, String usu_nombre, String descri_estado) {
        this.idcompra = idcompra;
        this.comp_nrofact = comp_nrofact;
        this.comp_fecha = comp_fecha;
        this.tipo_descri = tipo_descri;
        this.prov_nombre = prov_nombre;
        this.usu_nombre = usu_nombre;
        this.descri_estado = descri_estado;
    }

    public facturacompradto(Integer idcompra, Integer comp_cantcuota, Integer comp_monto, String comp_nrofact, 
            String comp_intervalo, String comp_fecha, String tipo_descri, String prov_nombre, 
            String usu_nombre, String descri_estado, Integer ordenc_nro, Integer idmercaderia, Integer detfact_cantidad, 
            Integer detfact_precio, String codigogenerico, Integer id_prov, Integer tipo_codigo,Integer nrocompra, String mer_descripcion) {
        this.idcompra = idcompra;
        this.comp_cantcuota = comp_cantcuota;
        this.comp_monto = comp_monto;
        this.comp_nrofact = comp_nrofact;
        this.comp_intervalo = comp_intervalo;
        this.comp_fecha = comp_fecha;
        this.tipo_descri = tipo_descri;
        this.prov_nombre = prov_nombre;
        this.usu_nombre = usu_nombre;
        this.descri_estado = descri_estado;
        this.ordenc_nro = ordenc_nro;
        this.idmercaderia = idmercaderia;
        this.detfact_cantidad = detfact_cantidad;
        this.detfact_precio = detfact_precio;
        this.codigogenerico = codigogenerico;
        this.id_prov = id_prov;
        this.tipo_codigo = tipo_codigo;
        this.nrocompra = nrocompra;
        this.mer_descripcion = mer_descripcion;
    }
//    public facturacompradto(Integer idcompra, Integer comp_cantcuota, Integer comp_monto, String comp_nrofact, 
//            String comp_intervalo, String comp_fecha, String tipo_descri, String prov_nombre, 
//            String usu_nombre, String descri_estado, Integer ordenc_nro, Integer idmercaderia, Integer detfact_cantidad, 
//            Integer detfact_precio, String codigogenerico, Integer id_prov, Integer tipo_codigo,Integer idimpuesto, String mer_descripcion) {
//        this.idcompra = idcompra;
//        this.comp_cantcuota = comp_cantcuota;
//        this.comp_monto = comp_monto;
//        this.comp_nrofact = comp_nrofact;
//        this.comp_intervalo = comp_intervalo;
//        this.comp_fecha = comp_fecha;
//        this.tipo_descri = tipo_descri;
//        this.prov_nombre = prov_nombre;
//        this.usu_nombre = usu_nombre;
//        this.descri_estado = descri_estado;
//        this.ordenc_nro = ordenc_nro;
//        this.idmercaderia = idmercaderia;
//        this.detfact_cantidad = detfact_cantidad;
//        this.detfact_precio = detfact_precio;
//        this.codigogenerico = codigogenerico;
//        this.id_prov = id_prov;
//        this.tipo_codigo = tipo_codigo;
//        this.idimpuesto = idimpuesto;
//        this.mer_descripcion = mer_descripcion;
//    }

    public Integer getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(Integer idciudad) {
        this.idciudad = idciudad;
    }

    public String getDescri_estado() {
        return descri_estado;
    }

    public void setDescri_estado(String descri_estado) {
        this.descri_estado = descri_estado;
    }

    public facturacompradto(int tipo_codigo, String tipo_descri) {
        this.tipo_codigo = tipo_codigo;
        this.tipo_descri = tipo_descri;
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

    public String getOrdenc_fecha() {
        return ordenc_fecha;
    }

    public void setOrdenc_fecha(String ordenc_fecha) {
        this.ordenc_fecha = ordenc_fecha;
    }

    public Integer getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(Integer idcompra) {
        this.idcompra = idcompra;
    }

    public Integer getComp_cantcuota() {
        return comp_cantcuota;
    }

    public void setComp_cantcuota(Integer comp_cantcuota) {
        this.comp_cantcuota = comp_cantcuota;
    }

    public Integer getComp_monto() {
        return comp_monto;
    }

    public void setComp_monto(Integer comp_monto) {
        this.comp_monto = comp_monto;
    }

    public String getComp_nrofact() {
        return comp_nrofact;
    }

    public void setComp_nrofact(String comp_nrofact) {
        this.comp_nrofact = comp_nrofact;
    }

    public String getComp_intervalo() {
        return comp_intervalo;
    }

    public void setComp_intervalo(String comp_intervalo) {
        this.comp_intervalo = comp_intervalo;
    }

    public String getComp_fecha() {
        return comp_fecha;
    }

    public void setComp_fecha(String comp_fecha) {
        this.comp_fecha = comp_fecha;
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

    public Integer getOrdenc_nro() {
        return ordenc_nro;
    }

    public void setOrdenc_nro(Integer ordenc_nro) {
        this.ordenc_nro = ordenc_nro;
    }

    public Integer getIdcompraD() {
        return idcompraD;
    }

    public void setIdcompraD(Integer idcompraD) {
        this.idcompraD = idcompraD;
    }

    public Integer getIdmercaderia() {
        return idmercaderia;
    }

    public void setIdmercaderia(Integer idmercaderia) {
        this.idmercaderia = idmercaderia;
    }

    public Integer getDetfact_cantidad() {
        return detfact_cantidad;
    }

    public void setDetfact_cantidad(Integer detfact_cantidad) {
        this.detfact_cantidad = detfact_cantidad;
    }

    public Integer getDetfact_precio() {
        return detfact_precio;
    }

    public void setDetfact_precio(Integer detfact_precio) {
        this.detfact_precio = detfact_precio;
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