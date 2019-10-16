/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDTO;

/**
 *
 * @author Oscar
 */
public class NotaRemisionDTO {

    private Integer id_notaremi;
    private String fecha_notaremi;
    private Integer nro_notaremi;
    private String obser_notaremi;

    private Integer id_proveedor;
    private String ras_social;
    private Integer id_sucursal;
    private String suc_descripcion;

    private Integer id_usuario;
    private String usu_nombre;

    private Integer id_estado;
    private String est_descripcion;

    private Integer id_compra;
    private Integer co_nrofact;

    private Integer id_notaremiD;
    private Integer id_articulo;

    private Integer cantinotaremi;
    private Integer precionotaremi;

    private Integer preccompras;
    private Integer precventas;
    private String art_descripcion;
    private Integer id_marca;
    private Integer id_impuesto;
    private String codigenerico;

    public NotaRemisionDTO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getCantidad_detcomp() {
        return cantidad_detcomp;
    }

    public void setCantidad_detcomp(Integer cantidad_detcomp) {
        this.cantidad_detcomp = cantidad_detcomp;
    }

    public Integer getPrecio_detcomp() {
        return precio_detcomp;
    }

    public void setPrecio_detcomp(Integer precio_detcomp) {
        this.precio_detcomp = precio_detcomp;
    }
    
     private Integer cantidad_detcomp;
     private Integer precio_detcomp;

    public NotaRemisionDTO(Integer id_notaremi, String fecha_notaremi, Integer nro_notaremi, String obser_notaremi,
            String est_descripcion,  Integer id_usuario, String usu_nombre, Integer id_proveedor, String ras_social,
            Integer id_sucursal, String suc_descripcion, Integer id_compra, Integer id_articulo,  Integer cantinotaremi,
            Integer precionotaremi, String codigenerico, String art_descripcion) {
        this.id_notaremi=id_notaremi;
        this.fecha_notaremi=fecha_notaremi;
        this.nro_notaremi=nro_notaremi;
        this.obser_notaremi=obser_notaremi;
        this.est_descripcion=est_descripcion;
        this.usu_nombre=usu_nombre;
        this.id_proveedor=id_proveedor;
        this.ras_social=ras_social;
        this.id_sucursal=id_sucursal;
        this.suc_descripcion=suc_descripcion;
        this.id_compra=id_compra;
        this.id_articulo=id_articulo;
        this.cantinotaremi=cantinotaremi;
        this.precionotaremi=precionotaremi;
        this.codigenerico=codigenerico;
        this.art_descripcion=art_descripcion;
        }

    public NotaRemisionDTO(Integer id_notaremi, String fecha_notaremi, Integer nro_notaremi, String obser_notaremi, 
            String est_descripcion, String usu_nombre, String ras_social, String suc_descripcion, Integer co_nrofact) {
        this.id_notaremi=id_notaremi;
        this.fecha_notaremi=fecha_notaremi;
        this.nro_notaremi=nro_notaremi;
        this.obser_notaremi=obser_notaremi;
        this.est_descripcion=est_descripcion;
        this.ras_social=ras_social;
        this.est_descripcion=est_descripcion;
        this.suc_descripcion=suc_descripcion;
        this.co_nrofact=co_nrofact;
               
    }

    public NotaRemisionDTO(Integer id_compra, Integer co_nrofact,
            String ras_social, String suc_descripcion, String usu_nombre, String est_descripcion) {
       this.id_compra=id_compra;
       this.co_nrofact=co_nrofact;
       this.ras_social=ras_social;
       this.suc_descripcion=suc_descripcion;
       this.usu_nombre=usu_nombre;
       this.est_descripcion=est_descripcion;
    }

    public NotaRemisionDTO(Integer id_compra, Integer co_nrofact, Integer id_proveedor,
            String ras_social, Integer id_sucursal, String suc_descripcion, Integer id_usuario,
            String usu_nombre, String est_descripcion,  Integer id_articulo,
            Integer cantidad_detcomp, Integer precio_detcomp, String codigenerico, String art_descripcion) {
        this.id_compra=id_compra;
        this.co_nrofact=co_nrofact;
        this.id_proveedor=id_proveedor;
        this.ras_social=ras_social;
        this.id_sucursal=id_sucursal;
        this.suc_descripcion=suc_descripcion;
        this.id_usuario=id_usuario;
        this.usu_nombre=usu_nombre;
        this.id_articulo=id_articulo;
        this.cantidad_detcomp=cantidad_detcomp;
        this.precio_detcomp=precio_detcomp;
        this.codigenerico=codigenerico;
        this.art_descripcion=art_descripcion;
         }

    public Integer getId_notaremi() {
        return id_notaremi;
    }

    public void setId_notaremi(Integer id_notaremi) {
        this.id_notaremi = id_notaremi;
    }

    public String getFecha_notaremi() {
        return fecha_notaremi;
    }

    public void setFecha_notaremi(String fecha_notaremi) {
        this.fecha_notaremi = fecha_notaremi;
    }

    public Integer getNro_notaremi() {
        return nro_notaremi;
    }

    public void setNro_notaremi(Integer nro_notaremi) {
        this.nro_notaremi = nro_notaremi;
    }

    public String getObser_notaremi() {
        return obser_notaremi;
    }

    public void setObser_notaremi(String obser_notaremi) {
        this.obser_notaremi = obser_notaremi;
    }

    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getRas_social() {
        return ras_social;
    }

    public void setRas_social(String ras_social) {
        this.ras_social = ras_social;
    }

    public Integer getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(Integer id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getSuc_descripcion() {
        return suc_descripcion;
    }

    public void setSuc_descripcion(String suc_descripcion) {
        this.suc_descripcion = suc_descripcion;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsu_nombre() {
        return usu_nombre;
    }

    public void setUsu_nombre(String usu_nombre) {
        this.usu_nombre = usu_nombre;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public String getEst_descripcion() {
        return est_descripcion;
    }

    public void setEst_descripcion(String est_descripcion) {
        this.est_descripcion = est_descripcion;
    }

    public Integer getId_compra() {
        return id_compra;
    }

    public void setId_compra(Integer id_compra) {
        this.id_compra = id_compra;
    }

    public Integer getCo_nrofact() {
        return co_nrofact;
    }

    public void setCo_nrofact(Integer co_nrofact) {
        this.co_nrofact = co_nrofact;
    }

    public Integer getId_notaremiD() {
        return id_notaremiD;
    }

    public void setId_notaremiD(Integer id_notaremiD) {
        this.id_notaremiD = id_notaremiD;
    }

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
    }

    public Integer getCantinotaremi() {
        return cantinotaremi;
    }

    public void setCantinotaremi(Integer cantinotaremi) {
        this.cantinotaremi = cantinotaremi;
    }

    public Integer getPrecionotaremi() {
        return precionotaremi;
    }

    public void setPrecionotaremi(Integer precionotaremi) {
        this.precionotaremi = precionotaremi;
    }

    public Integer getPreccompras() {
        return preccompras;
    }

    public void setPreccompras(Integer preccompras) {
        this.preccompras = preccompras;
    }

    public Integer getPrecventas() {
        return precventas;
    }

    public void setPrecventas(Integer precventas) {
        this.precventas = precventas;
    }

    public String getArt_descripcion() {
        return art_descripcion;
    }

    public void setArt_descripcion(String art_descripcion) {
        this.art_descripcion = art_descripcion;
    }

    public Integer getId_marca() {
        return id_marca;
    }

    public void setId_marca(Integer id_marca) {
        this.id_marca = id_marca;
    }

    public Integer getId_impuesto() {
        return id_impuesto;
    }

    public void setId_impuesto(Integer id_impuesto) {
        this.id_impuesto = id_impuesto;
    }

    public String getCodigenerico() {
        return codigenerico;
    }

    public void setCodigenerico(String codigenerico) {
        this.codigenerico = codigenerico;
    }
    }


