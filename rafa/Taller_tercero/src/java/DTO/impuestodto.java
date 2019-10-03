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
public class impuestodto {
    
    private Integer idimpuesto;
    private String descri_impuesto;
    
    public impuestodto() {}
    
    public impuestodto(Integer idimpuesto, String descri_impuesto){
    this.idimpuesto = idimpuesto;
    this.descri_impuesto = descri_impuesto;
    
    }

    public String getDescri_impuesto() {
        return descri_impuesto;
    }

    public void setDescri_impuesto(String descri_impuesto) {
        this.descri_impuesto = descri_impuesto;
    }

    public Integer getIdimpuesto() {
        return idimpuesto;
    }

    public void setIdimpuesto(Integer idimpuesto) {
        this.idimpuesto = idimpuesto;
    }
    
}
