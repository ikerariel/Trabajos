/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author !mX
 */
public class accesoDTO {

    private Integer idusuario;
    private String usuario;
    private String pass;
    private Integer estdo;

    public accesoDTO(Integer idusuario, String usuario) {
        this.idusuario = idusuario;
        this.usuario = usuario;
    }

    public accesoDTO() {
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getEstdo() {
        return estdo;
    }

    public void setEstdo(Integer estdo) {
        this.estdo = estdo;
    }

}
