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
public class proveedoresdto {
    
    private Integer id_prov;
    private String prov_nombre;
    private String prov_direc;
    private String prov_imail;
    private String prov_telf;
    private String prov_ruc;
    
    public proveedoresdto(){}
    
    public proveedoresdto(Integer id_prov, String prov_nombre, String prov_direc, String prov_imail,
            String prov_telf, String prov_ruc){
        this.id_prov=id_prov;
        this.prov_nombre=prov_nombre;
        this.prov_direc=prov_direc;
        this.prov_imail=prov_imail;
        this.prov_telf=prov_telf;
        this.prov_ruc=prov_ruc;
    }

    public Integer getId_prov() {
        return id_prov;
    }

    public void setId_prov(Integer id_prov) {
        this.id_prov = id_prov;
    }

    public String getProv_nombre() {
        return prov_nombre;
    }

    public void setProv_nombre(String prov_nombre) {
        this.prov_nombre = prov_nombre;
    }

    public String getProv_direc() {
        return prov_direc;
    }

    public void setProv_direc(String prov_direc) {
        this.prov_direc = prov_direc;
    }

    public String getProv_imail() {
        return prov_imail;
    }

    public void setProv_imail(String prov_imail) {
        this.prov_imail = prov_imail;
    }

    public String getProv_telf() {
        return prov_telf;
    }

    public void setProv_telf(String prov_telf) {
        this.prov_telf = prov_telf;
    }

    public String getProv_ruc() {
        return prov_ruc;
    }

    public void setProv_ruc(String prov_ruc) {
        this.prov_ruc = prov_ruc;
    }
    
}
