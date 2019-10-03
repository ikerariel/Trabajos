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
public class unimedidto {
    
    private Integer iduni_medi;
    private String unidad_descric;
    
    public unimedidto() {}
    
    public unimedidto(Integer iduni_medi, String unidad_descric){
        this.iduni_medi = iduni_medi;
        this.unidad_descric = unidad_descric;
    }

    public Integer getIduni_medi() {
        return iduni_medi;
    }

    public void setIduni_medi(Integer iduni_medi) {
        this.iduni_medi = iduni_medi;
    }

    public String getUnidad_descric() {
        return unidad_descric;
    }

    public void setUnidad_descric(String unidad_descric) {
        this.unidad_descric = unidad_descric;
    }
    
}
