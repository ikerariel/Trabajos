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
public class sectoresdto {
    
    private Integer idsector;
    private String sect_descri;
    
    public sectoresdto() {}
    
    public sectoresdto(Integer idsector, String sect_descri){
        this.idsector = idsector;
        this.sect_descri = sect_descri;
    }

    public Integer getIdsector() {
        return idsector;
    }

    public void setIdsector(Integer idsector) {
        this.idsector = idsector;
    }

    public String getSect_descri() {
        return sect_descri;
    }

    public void setSect_descri(String sect_descri) {
        this.sect_descri = sect_descri;
    }
    
}
