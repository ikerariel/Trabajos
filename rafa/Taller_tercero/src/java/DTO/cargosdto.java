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
public class cargosdto {
    
    private Integer idcargo;
    private String car_descripcion;
    
    public cargosdto() {}
    
    public cargosdto(Integer idcargo, String car_descripcion) {
        this.idcargo = idcargo;
        this.car_descripcion = car_descripcion;
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
    
}
