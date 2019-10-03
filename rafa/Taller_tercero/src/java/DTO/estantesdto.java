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
public class estantesdto {
    
    private Integer idestante;
    private String estan_descri;
    
    public estantesdto() {}
    
    public estantesdto(Integer idestante, String estan_descri){
    this.idestante = idestante;
    this.estan_descri = estan_descri;
    }

    public Integer getIdestante() {
        return idestante;
    }

    public void setIdestante(Integer idestante) {
        this.idestante = idestante;
    }

    public String getEstan_descri() {
        return estan_descri;
    }

    public void setEstan_descri(String estan_descri) {
        this.estan_descri = estan_descri;
    }
    
}
