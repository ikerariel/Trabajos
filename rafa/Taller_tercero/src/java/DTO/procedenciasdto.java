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
public class procedenciasdto {
    
    private Integer idprocedencia;
    private String proce_descri;
    
    public procedenciasdto() {}
    
    public procedenciasdto(Integer idprocedencia, String proce_descri){
    this.idprocedencia = idprocedencia;
    this.proce_descri = proce_descri;
    }

    public Integer getIdprocedencia() {
        return idprocedencia;
    }

    public void setIdprocedencia(Integer idprocedencia) {
        this.idprocedencia = idprocedencia;
    }

    public String getProce_descri() {
        return proce_descri;
    }

    public void setProce_descri(String proce_descri) {
        this.proce_descri = proce_descri;
    }
}
