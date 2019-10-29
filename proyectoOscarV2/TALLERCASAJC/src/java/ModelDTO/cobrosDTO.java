/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDTO;

/**
 *
 * @author Usuario
 */
public class cobrosDTO {
    Integer id_cuencob;
    String fecha_cuencob;
    String nrofactura;
    String cliente;
    Integer saldo_cuencob;
    Integer id_venta;
    Integer secuencia;
    String tipoventa;
    String nombrecliente;

    public cobrosDTO(Integer id_cuencob, String fecha_cuencob, String nrofactura, String cliente, String nombrecliente, Integer saldo_cuencob,Integer secuencia,Integer id_venta, String tipoventa) {
        this.id_cuencob = id_cuencob;
        this.fecha_cuencob = fecha_cuencob;
        this.nrofactura = nrofactura;
        this.cliente = cliente;
        this.nombrecliente = nombrecliente;
        this.saldo_cuencob = saldo_cuencob;
        this.secuencia = secuencia;
        this.id_venta = id_venta;
        this.tipoventa = tipoventa;
    }

    public cobrosDTO() {
    }

    public Integer getId_cuencob() {
        return id_cuencob;
    }

    public void setId_cuencob(Integer id_cuencob) {
        this.id_cuencob = id_cuencob;
    }

    public String getFecha_cuencob() {
        return fecha_cuencob;
    }

    public void setFecha_cuencob(String fecha_cuencob) {
        this.fecha_cuencob = fecha_cuencob;
    }

    public String getNrofactura() {
        return nrofactura;
    }

    public void setNrofactura(String nrofactura) {
        this.nrofactura = nrofactura;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Integer getSaldo_cuencob() {
        return saldo_cuencob;
    }

    public void setSaldo_cuencob(Integer saldo_cuencob) {
        this.saldo_cuencob = saldo_cuencob;
    }

    public String getTipoventa() {
        return tipoventa;
    }

    public void setTipoventa(String tipoventa) {
        this.tipoventa = tipoventa;
    }
    
}
