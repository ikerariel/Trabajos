/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Rafel
 */
public class depositodto {
    
    private Integer iddeposito;
    private String dep_descripcion;
    
    public depositodto(){}
    
    public depositodto(Integer iddeposito, String dep_descripcion){
        this.iddeposito = iddeposito;
        this.dep_descripcion = dep_descripcion;
    }

    public Integer getIddeposito() {
        return iddeposito;
    }

    public void setIddeposito(Integer iddeposito) {
        this.iddeposito = iddeposito;
    }

    public String getDep_descripcion() {
        return dep_descripcion;
    }

    public void setDep_descripcion(String dep_descripcion) {
        this.dep_descripcion = dep_descripcion;
    }
    
}
