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
public class presupuestoventadto {
    private Integer idpresupuestoventa;
    private String pres_fecha;
    private Integer pres_cantcuota;
    private Integer pres_monto;
    private String pres_intervalo;
    private Integer pedi_ven_nro;
    private String pedi_fecha;
    private Integer idclientes;
    private String cli_razonsocial;
    private Integer idusuario;
    private String usu_nombre;
    private Integer idestado;
    private String descri_estado;
    private Integer tipo_codigo;
    private String tipo_descri;
    private Integer iddeposito;
    private String dep_descripcion;
    
    private Integer idpresupuestoventaD;
    private Integer idmercaderia;
    private Integer detpresvent_cantidad;
    private Integer detpresvent_precio;
    
    private Integer mer_costo;
    private Integer mer_precio;
    private String mer_descripcion;
    private Integer idcategoria;
    private Integer idmarca;
    private Integer idprocedencia;
    private Integer idimpuesto;
    private String codigogenerico;
    
    public presupuestoventadto(){}
    
    public presupuestoventadto(Integer idpresupuestoventa, String pres_fecha, String cli_razonsocial,
            String usu_nombre, String descri_estado, String tipo_descri){
        this.idpresupuestoventa = idpresupuestoventa;
        this.pres_fecha = pres_fecha;
        this.cli_razonsocial = cli_razonsocial;
        this.usu_nombre = usu_nombre;
        this.descri_estado = descri_estado;
        this.tipo_descri = tipo_descri;
    }

    public presupuestoventadto(int pedi_ven_nro, String pedi_fecha, String usu_nombre, 
            int idclientes, String cli_razonsocial, String descri_estado) {
        this.pedi_ven_nro = pedi_ven_nro;
        this.pedi_fecha = pedi_fecha;
        this.usu_nombre = usu_nombre;
        this.idclientes = idclientes;
        this.cli_razonsocial = cli_razonsocial;
        this.descri_estado = descri_estado;
    }

    public presupuestoventadto(int tipo_codigo, String tipo_descri) {
        this.tipo_codigo = tipo_codigo;
        this.tipo_descri = tipo_descri; 
    }

    public presupuestoventadto(Integer idmercaderia, Integer mer_costo, Integer mer_precio, String mer_descripcion,
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

    public presupuestoventadto(int idpresupuestoventa, String pres_fecha, int pres_cantcuota, int pres_monto,
            String pres_intervalo, int pedi_ven_nro, String cli_razonsocial, String usu_nombre, String descri_estado,
            int tipo_codigo, String tipo_descri, String dep_descripcion, int idmercaderia, int detpresvent_cantidad, int detpresvent_precio,
            String codigogenerico, String mer_descripcion) {
        this.idpresupuestoventa = idpresupuestoventa;
        this.pres_fecha = pres_fecha;
        this.pres_cantcuota = pres_cantcuota;
        this.pres_monto = pres_monto;
        this.pres_intervalo = pres_intervalo;
        this.pedi_ven_nro = pedi_ven_nro;
        this.cli_razonsocial = cli_razonsocial;
        this.usu_nombre = usu_nombre;
        this.descri_estado = descri_estado;
        this.tipo_codigo = tipo_codigo;
        this.tipo_descri = tipo_descri;
        this.dep_descripcion = dep_descripcion;
        this.idmercaderia = idmercaderia;
        this.detpresvent_cantidad = detpresvent_cantidad;
        this.detpresvent_precio = detpresvent_precio;
        this.codigogenerico = codigogenerico;
        this.mer_descripcion = mer_descripcion;
    }
    
    
    public String getPedi_fecha() {
        return pedi_fecha;
    }

    public void setPedi_fecha(String pedi_fecha) {
        this.pedi_fecha = pedi_fecha;
    }

    public Integer getIdpresupuestoventa() {
        return idpresupuestoventa;
    }

    public void setIdpresupuestoventa(Integer idpresupuestoventa) {
        this.idpresupuestoventa = idpresupuestoventa;
    }

    public String getPres_fecha() {
        return pres_fecha;
    }

    public void setPres_fecha(String pres_fecha) {
        this.pres_fecha = pres_fecha;
    }

    public Integer getPres_cantcuota() {
        return pres_cantcuota;
    }

    public void setPres_cantcuota(Integer pres_cantcuota) {
        this.pres_cantcuota = pres_cantcuota;
    }

    public Integer getPres_monto() {
        return pres_monto;
    }

    public void setPres_monto(Integer pres_monto) {
        this.pres_monto = pres_monto;
    }

    public String getPres_intervalo() {
        return pres_intervalo;
    }

    public void setPres_intervalo(String pres_intervalo) {
        this.pres_intervalo = pres_intervalo;
    }

    public Integer getPedi_ven_nro() {
        return pedi_ven_nro;
    }

    public void setPedi_ven_nro(Integer pedi_ven_nro) {
        this.pedi_ven_nro = pedi_ven_nro;
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

    public Integer getIddeposito() {
        return iddeposito;
    }

    public void setIddeposito(Integer iddeposito) {
        this.iddeposito = iddeposito;
    }

    public String getDep_descripcion() {
        return dep_descripcion;
    }

    public void setDep_descripcion(String dep_descripcion) {
        this.dep_descripcion = dep_descripcion;
    }

    public Integer getIdpresupuestoventaD() {
        return idpresupuestoventaD;
    }

    public void setIdpresupuestoventaD(Integer idpresupuestoventaD) {
        this.idpresupuestoventaD = idpresupuestoventaD;
    }

    public Integer getIdmercaderia() {
        return idmercaderia;
    }

    public void setIdmercaderia(Integer idmercaderia) {
        this.idmercaderia = idmercaderia;
    }

    public Integer getDetpresvent_cantidad() {
        return detpresvent_cantidad;
    }

    public void setDetpresvent_cantidad(Integer detpresvent_cantidad) {
        this.detpresvent_cantidad = detpresvent_cantidad;
    }

    public Integer getDetpresvent_precio() {
        return detpresvent_precio;
    }

    public void setDetpresvent_precio(Integer detpresvent_precio) {
        this.detpresvent_precio = detpresvent_precio;
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
