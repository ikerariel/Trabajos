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
public class ciudadesdto {
    
    private Integer idciudad;
    private String ciu_descripcion;
    
    public ciudadesdto() {}
    
    public ciudadesdto(Integer idciudad, String ciu_descripcion){
    this.idciudad = idciudad;
    this.ciu_descripcion = ciu_descripcion;
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
