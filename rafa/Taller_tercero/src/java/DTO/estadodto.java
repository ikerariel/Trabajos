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
public class estadodto {
    
    private Integer idestado ;
    private String descri_estado;
    
    
    
    public estadodto() {}
    
    public estadodto(Integer idestado, String descri_estado){
    this.idestado = idestado;
    this.descri_estado = descri_estado;
    }

    public String getDescri_estado() {
        return descri_estado;
    }

    public void setDescri_estado(String descri_estado) {
        this.descri_estado = descri_estado;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }
    
}
