/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPL;

import DAO.facturacionDAO;
import DTO.facturacionDTO;
import Genericos.Conexion;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class facturacionDAOIMPL implements facturacionDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public String getFacturas(Integer Cajero) {
        ResultSet rs;
        ArrayList<facturacionDTO> getFacturas = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = " SELECT a.idaperturacierre,  a.idcaja, a.idcajero, (uj.usuario) as cajero, (c.descripcion) as caja,\n"
                    + "                    a.idtimbrado, tp.idtipodocumento, f.secuencia, f.numerodocumento, f.iddocfactura,\n"
                    + "                   coalesce(fc.cant,0) as cant\n"
                    + "                     FROM ventas.aperturacierrecajas a\n"
                    + "                      left join ventas.cajero cj on a.idcajero=cj.idcajero\n"
                    + "                      left join usuario uj on cj.idusuario=uj.idusuario\n"
                    + "                      left join ventas.caja c on a.idcaja=c.idcaja\n"
                    + "                      left join ventas.timbrado t on a.idtimbrado=t.idtimbrado\n"
                    + "                     left join ventas.tipo_documento tp on t.idtipodocumento=tp.idtipodocumento\n"
                    + "                     left join ventas.documentos_facturas f on t.idtimbrado=f.idtimbrado\n"
                    + "		     left join  (select t.idtimbrado, count(f.numerodocumento) as cant\n"
                    + "		     from ventas.timbrado t\n"
                    + "                     left join ventas.tipo_documento tp on t.idtipodocumento=tp.idtipodocumento\n"
                    + "                     left join ventas.documentos_facturas f on t.idtimbrado=f.idtimbrado\n"
                    + "                     where  t.idtipodocumento=1 and t.idestado=5\n"
                    + "                    and f.idestado = 5 \n"
                    + "                     group by t.idtimbrado) fc on a.idtimbrado = a.idtimbrado 	\n"
                    + "                     where a.idcajero=? and a.idestado=1 and t.idtipodocumento=1 and t.idestado=5\n"
                    + "                    and f.idestado = 5 \n"
                    + "                    group by idaperturacierre,  a.idcaja, a.idcajero, uj.usuario, c.descripcion,\n"
                    + "                    a.idtimbrado, tp.idtipodocumento, f.secuencia, f.numerodocumento, f.iddocfactura,coalesce(fc.cant,0)\n"
                    + "		    order by f.secuencia asc ";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Cajero);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                getFacturas.add(new facturacionDTO(rs.getInt("idaperturacierre"),
                        rs.getInt("cant"),
                        rs.getInt("iddocfactura"),
                        rs.getString("cajero"),
                        rs.getString("caja"),
                        rs.getString("numerodocumento")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(facturacionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(getFacturas);
    }

    @Override
    public String getArticulos() {
        ResultSet rs;
        ArrayList<facturacionDTO> getarticulos = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT a.idarticulo, a.descripcion, a.precio_unitario, a.idimpuesto, a.precio_promedio, \n"
                    + "       a.precio_venta, s.cantidad_stock, (i.descripcion) as impuesto\n"
                    + "  FROM ventas.articulo a\n"
                    + "  left join ventas.impuesto i on a.idimpuesto = i.idimpuesto\n"
                    + "  left join ventas.stock s on 	a.idarticulo=s.idarticulo\n"
                    + "  left join deposito d on s.iddeposito = d.iddeposito \n"
                    + "  left join sucursal sc on d.idsucursal=sc.idsucursal\n"
                    + "  where  sc.idsucursal =1 ";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                getarticulos.add(new facturacionDTO(rs.getInt("idarticulo"),
                        rs.getString("descripcion"),
                        rs.getInt("precio_venta"),
                        rs.getString("impuesto"),
                        rs.getInt("cantidad_stock"),
                        rs.getInt("idimpuesto")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(facturacionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(getarticulos);
    }

}
