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
public class cajadto {
    
    private Integer idcaja;
    private String caja_descripcion;
    
    public cajadto () {}
    
    public cajadto(Integer idcaja, String caja_descripcion) {
        this.idcaja = idcaja;
        this.caja_descripcion = caja_descripcion;
    }

    public Integer getIdcaja() {
        return idcaja;
    }

    public void setIdcaja(Integer idcaja) {
        this.idcaja = idcaja;
    }

    public String getCaja_descripcion() {
        return caja_descripcion;
    }

    public void setCaja_descripcion(String caja_descripcion) {
        this.caja_descripcion = caja_descripcion;
    }
    
}
