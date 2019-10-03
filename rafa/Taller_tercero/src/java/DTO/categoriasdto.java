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
public class categoriasdto {
    
    private Integer idcategoria;
    private String cate_descri;
    
    public categoriasdto() {}
    
    public categoriasdto(Integer idcategoria, String cate_descri){
    this.idcategoria = idcategoria;
    this.cate_descri = cate_descri;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getCate_descri() {
        return cate_descri;
    }

    public void setCate_descri(String cate_descri) {
        this.cate_descri = cate_descri;
    }
    
}
