/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Carlos
 */
public class accesoDTO {

    private Integer idusuario;
    private String usu_nombre;
    private String usu_clave;
    private Integer idfuncionario;
    private Integer idperfil;
    private Integer idsucursal;
    private Integer iddeposito;
    private String suc_descripcion;
    private String dep_descripcion;

    public Integer getIddeposito() {
        return iddeposito;
    }

    public void setIddeposito(Integer iddeposito) {
        this.iddeposito = iddeposito;
    }

    public String getSuc_descripcion() {
        return suc_descripcion;
    }

    public void setSuc_descripcion(String suc_descripcion) {
        this.suc_descripcion = suc_descripcion;
    }

    public String getDep_descripcion() {
        return dep_descripcion;
    }

    public void setDep_descripcion(String dep_descripcion) {
        this.dep_descripcion = dep_descripcion;
    }

    public accesoDTO() {
    }

    public accesoDTO(Integer idsucursal, String suc_descripcion, Integer iddeposito, String dep_descripcion,
            Integer idusuario, Integer idperfil, String usu_nombre) {
        this.idsucursal=idsucursal;
        this.suc_descripcion=suc_descripcion;
        this.iddeposito=iddeposito;
        this.dep_descripcion=dep_descripcion;
        this.idusuario=idusuario;
        this.idperfil=idperfil;
        this.usu_nombre=usu_nombre;
     
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

    public String getUsu_clave() {
        return usu_clave;
    }

    public void setUsu_clave(String usu_clave) {
        this.usu_clave = usu_clave;
    }

    public Integer getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Integer idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public Integer getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Integer idperfil) {
        this.idperfil = idperfil;
    }

    public Integer getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Integer idsucursal) {
        this.idsucursal = idsucursal;
    }
    
    
    
    
    
}
