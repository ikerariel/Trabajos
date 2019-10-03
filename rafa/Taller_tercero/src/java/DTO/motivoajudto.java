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
public class motivoajudto {
    
    private Integer idmot_ajus;
    private String ajustmotiv_descri;
    
    public motivoajudto() {}
    
    public motivoajudto(Integer idmot_ajus, String ajustmotiv_descri){
    this.idmot_ajus = idmot_ajus;
    this.ajustmotiv_descri = ajustmotiv_descri;
    }

    public Integer getIdmot_ajus() {
        return idmot_ajus;
    }

    public void setIdmot_ajus(Integer idmot_ajus) {
        this.idmot_ajus = idmot_ajus;
    }

    public String getAjustmotiv_descri() {
        return ajustmotiv_descri;
    }

    public void setAjustmotiv_descri(String ajustmotiv_descri) {
        this.ajustmotiv_descri = ajustmotiv_descri;
    }
    
}
