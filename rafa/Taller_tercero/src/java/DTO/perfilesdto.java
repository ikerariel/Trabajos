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
public class perfilesdto {
    
    private Integer idperfil;
    private String per_descripcion;
    
    public perfilesdto() {}
    
    public perfilesdto(Integer idperfil, String per_descripcion){
    this.idperfil = idperfil;
    this.per_descripcion = per_descripcion;
    }

    public Integer getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Integer idperfil) {
        this.idperfil = idperfil;
    }

    public String getPer_descripcion() {
        return per_descripcion;
    }

    public void setPer_descripcion(String per_descripcion) {
        this.per_descripcion = per_descripcion;
    }
}
