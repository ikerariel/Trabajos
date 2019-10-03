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
public class funcionariodto {
    
    private Integer idfuncionario;
    private String fun_direccion;
    private String fun_correo;
    private String fun_ci;
    private String fun_nombres;
    private String fun_apellidos;
    private String fun_telefono;
    private Integer idcargo;
    private String car_descripcion;
    private Integer idciudad;
    private String ciu_descripcion;
    
    public funcionariodto(){}
    
    public funcionariodto(Integer idfuncionario, String fun_direccion, String fun_correo, String fun_ci, String fun_nombres,
            String fun_apellidos, String fun_telefono, String car_descripcion, String ciu_descripcion){
        this.idfuncionario=idfuncionario;
        this.fun_direccion=fun_direccion;
        this.fun_correo=fun_correo;
        this. fun_ci=fun_ci;
        this.fun_nombres=fun_nombres;
        this.fun_apellidos=fun_apellidos;
        this.fun_telefono=fun_telefono;
        this.car_descripcion=car_descripcion;
        this.ciu_descripcion=ciu_descripcion;
    }
    
    public funcionariodto(Integer idfuncionario, String fun_direccion, String fun_correo, String fun_ci, String fun_nombres,
            String fun_apellidos, String fun_telefono, Integer idcargo, Integer idciudad){
        this.idfuncionario=idfuncionario;
        this.fun_direccion=fun_direccion;
        this.fun_correo=fun_correo;
        this. fun_ci=fun_ci;
        this.fun_nombres=fun_nombres;
        this.fun_apellidos=fun_apellidos;
        this.fun_telefono=fun_telefono;
        this.idcargo=idcargo;
        this.idciudad=idciudad;
    }

    public Integer getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Integer idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public String getFun_direccion() {
        return fun_direccion;
    }

    public void setFun_direccion(String fun_direccion) {
        this.fun_direccion = fun_direccion;
    }

    public String getFun_correo() {
        return fun_correo;
    }

    public void setFun_correo(String fun_correo) {
        this.fun_correo = fun_correo;
    }

    public String getFun_ci() {
        return fun_ci;
    }

    public void setFun_ci(String fun_ci) {
        this.fun_ci = fun_ci;
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

    public String getFun_telefono() {
        return fun_telefono;
    }

    public void setFun_telefono(String fun_telefono) {
        this.fun_telefono = fun_telefono;
    }

    public Integer getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(Integer idcargo) {
        this.idcargo = idcargo;
    }

    public String getCar_descripcion() {
        return car_descripcion;
    }

    public void setCar_descripcion(String car_descripcion) {
        this.car_descripcion = car_descripcion;
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
