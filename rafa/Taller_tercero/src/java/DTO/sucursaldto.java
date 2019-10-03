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
public class sucursaldto {
    
    private Integer idsucursal;
    private String suc_descripcion;
    private Integer idciudad;
    private String ciu_descripcion;
    private Integer iddeposito;
    private String dep_descripcion;
    
    public sucursaldto(){}
    
    public sucursaldto(Integer idsucursal, String suc_descripcion, String ciu_descripcion, String dep_descripcion){
        this.idsucursal=idsucursal;
        this.suc_descripcion=suc_descripcion;
        this.ciu_descripcion=ciu_descripcion;
        this.dep_descripcion=dep_descripcion;
    }
    
    public sucursaldto(Integer idsucursal, String suc_descripcion, Integer idciudad, Integer iddeposito){
        this.idsucursal=idsucursal;
        this.suc_descripcion=suc_descripcion;
        this.idciudad=idciudad;
        this.iddeposito=iddeposito;
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

    public Integer getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Integer idsucursal) {
        this.idsucursal = idsucursal;
    }

    public String getSuc_descripcion() {
        return suc_descripcion;
    }

    public void setSuc_descripcion(String suc_descripcion) {
        this.suc_descripcion = suc_descripcion;
    }

    public Integer getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(Integer idciudad) {
        this.idciudad = idciudad;
    }

    public String getCiu_descripcion() {
        return ciu_descripcion;
    }

    public void setCiu_descripcion(String ciu_descripcion) {
        this.ciu_descripcion = ciu_descripcion;
    }
    
}
