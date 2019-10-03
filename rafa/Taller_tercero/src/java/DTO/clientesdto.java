/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author naty
 */
public class clientesdto {
    
    private Integer idclientes;
    private Integer cv;
    private String cli_ruc;
    private String cli_razonsocial;
    private String cli_telefono;
    private String cli_direccion;
    private String cli_correo;
    private Integer idciudad;
    private String ciu_descripcion;

    public Integer getCv() {
        return cv;
    }

    public void setCv(Integer cv) {
        this.cv = cv;
    }
    
    public clientesdto() {}
    
    public clientesdto(Integer idclientes, String cli_ruc, String cli_razonsocial,
            String cli_telefono, String cli_direccion, String cli_correo, String ciu_descripcion){
        this.idclientes = idclientes;
        this.cli_ruc = cli_ruc;
        this.cli_razonsocial = cli_razonsocial;
        this.cli_telefono = cli_telefono;
        this.cli_direccion = cli_direccion;
        this.cli_correo = cli_correo;
        this.ciu_descripcion = ciu_descripcion;
    }
    
    public clientesdto(Integer idclientes, String cli_ruc, String cli_razonsocial, String cli_telefono, 
            String cli_direccion, String cli_correo, Integer idciudad, String ciu_descripcion){
        this.idclientes = idclientes;
        this.cli_ruc = cli_ruc;
        this.cli_razonsocial = cli_razonsocial;
        this.cli_telefono = cli_telefono;
        this.cli_direccion = cli_direccion;
        this.cli_correo = cli_correo;
        this.idciudad = idciudad;
        this.ciu_descripcion=ciu_descripcion;
    }

    public Integer getIdclientes() {
        return idclientes;
    }

    public void setIdclientes(Integer idclientes) {
        this.idclientes = idclientes;
    }

    public String getCli_ruc() {
        return cli_ruc;
    }

    public void setCli_ruc(String cli_ruc) {
        this.cli_ruc = cli_ruc;
    }

    public String getCli_razonsocial() {
        return cli_razonsocial;
    }

    public void setCli_razonsocial(String cli_razonsocial) {
        this.cli_razonsocial = cli_razonsocial;
    }

    public String getCli_telefono() {
        return cli_telefono;
    }

    public void setCli_telefono(String cli_telefono) {
        this.cli_telefono = cli_telefono;
    }

    public String getCli_direccion() {
        return cli_direccion;
    }

    public void setCli_direccion(String cli_direccion) {
        this.cli_direccion = cli_direccion;
    }

    public String getCli_correo() {
        return cli_correo;
    }

    public void setCli_correo(String cli_correo) {
        this.cli_correo = cli_correo;
    }

    public Integer getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(Integer idciudad) {
        this.idciudad = idciudad;
    }

    public String getCiu_descripcion() {
        return ciu_descripcion;
    }

    public void setCiu_descripcion(String ciu_descripcion) {
        this.ciu_descripcion = ciu_descripcion;
    }
    
}
