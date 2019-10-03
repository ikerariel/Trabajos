/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Carlos
 */
public class aperturaDTO {

    Integer idapertura;
    String date;
    String comentario;
    Integer idusuario;
    Integer idestado;
    Integer idsucursal;
    Integer idaperturagiro;
    Integer idtipooperadora;
    Integer idaperturarecarga;
    Integer montogiro;
    Integer montocarga;
    Integer giromontotigo;
    Integer giromontoclaro;
    Integer giromontopersonal;
    Integer giromontovox;
    Integer montotigo;
    Integer montoclaro;
    Integer montopersonal;
    Integer montovox;

    String fecha_apertura;
    String estado;
    String usuario;
    Integer totalrecarga;
    Integer totalgiro;
    Integer totalgral;
    Integer total;
    Integer Tigo;
    Integer Claro;
    Integer Personal;
    Integer Vox;
    
    public aperturaDTO() {
    }

    public aperturaDTO(Integer idapertura, String fecha_apertura, Integer totalrecarga, Integer totalgiro, Integer totalgral, String estado, String usuario) {
        this.idapertura=idapertura;
        this.fecha_apertura=fecha_apertura;
        this.totalrecarga=totalrecarga;
        this.totalgiro=totalgiro;
        this.totalgral=totalgral;
        this.estado=estado;
        this.usuario=usuario;
    }
    public aperturaDTO(Integer idapertura,Integer idestado, String fecha_apertura, Integer Tigo, Integer Claro, Integer Personal, Integer Vox, Integer total) {
        this.idapertura=idapertura;
        this.idestado=idestado;
        this.fecha_apertura=fecha_apertura;
        this.Tigo=Tigo;
        this.Claro=Claro;
        this.Personal=Personal;
        this.Vox=Vox;
        this.total=total;
    }

    public aperturaDTO(Integer montotigo, Integer montoclaro, Integer montopersonal, Integer montovox, Integer giromontotigo, Integer giromontoclaro, Integer giromontopersonal, Integer giromontovox, String comentario) {
        this.montotigo=montotigo;
        this.montoclaro=montoclaro;
        this.montopersonal=montopersonal;
        this.montovox=montovox;
        this.giromontotigo=giromontotigo;
        this.giromontoclaro=giromontoclaro;
        this.giromontopersonal=giromontopersonal;
        this.giromontovox=giromontovox;
        this.comentario=comentario;
    }

    public Integer getGiromontotigo() {
        return giromontotigo;
    }

    public void setGiromontotigo(Integer giromontotigo) {
        this.giromontotigo = giromontotigo;
    }

    public Integer getGiromontoclaro() {
        return giromontoclaro;
    }

    public void setGiromontoclaro(Integer giromontoclaro) {
        this.giromontoclaro = giromontoclaro;
    }

    public Integer getGiromontopersonal() {
        return giromontopersonal;
    }

    public void setGiromontopersonal(Integer giromontopersonal) {
        this.giromontopersonal = giromontopersonal;
    }

    public Integer getGiromontovox() {
        return giromontovox;
    }

    public void setGiromontovox(Integer giromontovox) {
        this.giromontovox = giromontovox;
    }

    public Integer getMontotigo() {
        return montotigo;
    }

    public void setMontotigo(Integer montotigo) {
        this.montotigo = montotigo;
    }

    public Integer getMontoclaro() {
        return montoclaro;
    }

    public void setMontoclaro(Integer montoclaro) {
        this.montoclaro = montoclaro;
    }

    public Integer getMontopersonal() {
        return montopersonal;
    }

    public void setMontopersonal(Integer montopersonal) {
        this.montopersonal = montopersonal;
    }

    public Integer getMontovox() {
        return montovox;
    }

    public void setMontovox(Integer montovox) {
        this.montovox = montovox;
    }

    
    public Integer getIdapertura() {
        return idapertura;
    }

    public void setIdapertura(Integer idapertura) {
        this.idapertura = idapertura;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    public Integer getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Integer idsucursal) {
        this.idsucursal = idsucursal;
    }

    public Integer getIdaperturagiro() {
        return idaperturagiro;
    }

    public void setIdaperturagiro(Integer idaperturagiro) {
        this.idaperturagiro = idaperturagiro;
    }

    public Integer getIdtipooperadora() {
        return idtipooperadora;
    }

    public void setIdtipooperadora(Integer idtipooperadora) {
        this.idtipooperadora = idtipooperadora;
    }

    public Integer getIdaperturarecarga() {
        return idaperturarecarga;
    }

    public void setIdaperturarecarga(Integer idaperturarecarga) {
        this.idaperturarecarga = idaperturarecarga;
    }

    public Integer getMontogiro() {
        return montogiro;
    }

    public void setMontogiro(Integer montogiro) {
        this.montogiro = montogiro;
    }

    public Integer getMontocarga() {
        return montocarga;
    }

    public void setMontocarga(Integer montocarga) {
        this.montocarga = montocarga;
    }

}
