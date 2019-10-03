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
public class usuariosdto {
    
    private Integer idusuario;
    private String usu_nombre;
    private String usu_clave;
    private Integer idfuncionario;
    private String fun_nombres;
    private String fun_apellidos;
    private Integer idperfil;
    private String per_descripcion;
    private Integer idsucursal;
    private String suc_descripcion;
    
    public usuariosdto(){}
    
    public usuariosdto(Integer idusuario, String usu_nombre, String usu_clave, String fun_nombres,
            String fun_apellidos, String per_descripcion, String suc_descripcion){
        this.idusuario=idusuario;
        this.usu_nombre=usu_nombre;
        this.usu_clave=usu_clave;
        this.fun_nombres=fun_nombres;
        this.fun_apellidos=fun_apellidos;
        this.per_descripcion=per_descripcion;
        this.suc_descripcion=suc_descripcion;
    }
    
    public usuariosdto(Integer idusuario, String usu_nombre, String usu_clave, Integer idfuncionario, 
            Integer idperfil, Integer idsucursal){
        this.idusuario=idusuario;
        this.usu_nombre=usu_nombre;
        this.usu_clave=usu_clave;
        this.idfuncionario=idfuncionario;
        this.idperfil=idperfil;
        this.idsucursal=idsucursal;
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

    public String getFun_nombres() {
        return fun_nombres;
    }

    public void setFun_nombres(String fun_nombres) {
        this.fun_nombres = fun_nombres;
    }

    public String getFun_apellidos() {
        return fun_apellidos;
    }

    public void setFun_apellidos(String fun_apellidos) {
        this.fun_apellidos = fun_apellidos;
    }

    public Integer getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Integer idperfil) {
        this.idperfil = idperfil;
    }

    public String getPer_descripcion() {
        return per_descripcion;
    }

    public void setPer_descripcion(String per_descripcion) {
        this.per_descripcion = per_descripcion;
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
    
}
