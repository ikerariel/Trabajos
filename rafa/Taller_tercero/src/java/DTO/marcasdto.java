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
public class marcasdto {
    
    private Integer idmarca;
    private  String marca_descri;
    
    public marcasdto() {}
    
    public marcasdto(Integer idmarca, String marca_descri) {
    this.idmarca = idmarca;
    this.marca_descri = marca_descri;
    }

    public Integer getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(Integer idmarca) {
        this.idmarca = idmarca;
    }

    public String getMarca_descri() {
        return marca_descri;
    }

    public void setMarca_descri(String marca_descri) {
        this.marca_descri = marca_descri;
    }
    
}
