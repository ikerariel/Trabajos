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
public class NotaCreComprasDTO {
      private Integer id_compra;
      private Integer id_estado;
      private Integer nrofact;
      private Integer id_articulo;
      private String art_descripcion;
      private Integer precio_detcomp;
      private Integer cantidad_detcomp;
      private Integer id_deposito;
                   
    Integer id_notacrecompra;
    String fecha_nocred;
    Integer nro_nocred;
    Integer nro_timbrado;
    String obs_nocred;
    Integer id_usuario;

    public Integer getId_deposito() {
        return id_deposito;
    }

    public void setId_deposito(Integer id_deposito) {
        this.id_deposito = id_deposito;
    }

    public NotaCreComprasDTO(Integer id_compra, Integer id_estado, Integer nrofact,Integer id_deposito, Integer id_articulo, String art_descripcion, Integer precio_detcomp, Integer cantidad_detcomp) {
        this.id_compra = id_compra;
        this.id_estado = id_estado;
        this.nrofact = nrofact;
        this.id_deposito = id_deposito;
        this.id_articulo = id_articulo;
        this.art_descripcion = art_descripcion;
        this.precio_detcomp = precio_detcomp;
        this.cantidad_detcomp = cantidad_detcomp;
    }
    
    Integer cantidad_detnocre;
    Integer preciouni_detnocre;
    Integer cod;
    
    Integer iddetnotacrecompras;
    Integer co_nrofact;
    String descrip;
    String estado;
    String usuario;
    String articulo;
    String factura;

    public NotaCreComprasDTO(Integer id_notacrecompra,Integer co_nrofact,Integer id_compra, String fecha_nocred, Integer nro_nocred,  Integer nro_timbrado, String obs_nocred, Integer id_articulo, String articulo, Integer preciouni_detnocre, Integer iddetnotacrecompras,Integer id_deposito, Integer cantidad_detnocre) {
        this.id_notacrecompra=id_notacrecompra;
        this.co_nrofact=co_nrofact;
        this.id_compra=id_compra;
        this.fecha_nocred=fecha_nocred;
        this.nro_nocred=nro_nocred;
        this.nro_timbrado=nro_timbrado;
        this.obs_nocred=obs_nocred;
        this.id_articulo=id_articulo;
        this.articulo=articulo;
        this.preciouni_detnocre=preciouni_detnocre;
        this.iddetnotacrecompras=iddetnotacrecompras;
        this.id_deposito=id_deposito;
        this.cantidad_detnocre=cantidad_detnocre;
  }

    public NotaCreComprasDTO(Integer id_notacrecompra, String fecha_nocred, Integer nro_nocred,  Integer nro_timbrado, String obs_nocred, String estado, String usuario) {
    this.id_notacrecompra=id_notacrecompra;    
    this.fecha_nocred=fecha_nocred;    
    this.nro_nocred=nro_nocred;    
    this.nro_timbrado=nro_timbrado;    
    this.obs_nocred=obs_nocred;    
    this.estado=estado;    
    this.usuario=usuario;    
    }

    public NotaCreComprasDTO() {
            }

    public Integer getId_notacrecompra() {
        return id_notacrecompra;
    }

    public void setId_notacrecompra(Integer id_notacrecompra) {
        this.id_notacrecompra = id_notacrecompra;
    }

    public String getFecha_nocred() {
        return fecha_nocred;
    }

    public void setFecha_nocred(String fecha_nocred) {
        this.fecha_nocred = fecha_nocred;
    }

    public Integer getNro_nocred() {
        return nro_nocred;
    }

    public void setNro_nocred(Integer nro_nocred) {
        this.nro_nocred = nro_nocred;
    }

    public Integer getNro_timbrado() {
        return nro_timbrado;
    }

    public void setNro_timbrado(Integer nro_timbrado) {
        this.nro_timbrado = nro_timbrado;
    }

    public String getObs_nocred() {
        return obs_nocred;
    }

    public void setObs_nocred(String obs_nocred) {
        this.obs_nocred = obs_nocred;
    }

    public Integer getId_compra() {
        return id_compra;
    }

    public void setId_compra(Integer id_compra) {
        this.id_compra = id_compra;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
    }

    public Integer getCantidad_detnocre() {
        return cantidad_detnocre;
    }

    public void setCantidad_detnocre(Integer cantidad_detnocre) {
        this.cantidad_detnocre = cantidad_detnocre;
    }

    public Integer getPreciouni_detnocre() {
        return preciouni_detnocre;
    }

    public void setPreciouni_detnocre(Integer preciouni_detnocre) {
        this.preciouni_detnocre = preciouni_detnocre;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Integer getIddetnotacrecompras() {
        return iddetnotacrecompras;
    }

    public void setIddetnotacrecompras(Integer iddetnotacrecompras) {
        this.iddetnotacrecompras = iddetnotacrecompras;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }
    
}
