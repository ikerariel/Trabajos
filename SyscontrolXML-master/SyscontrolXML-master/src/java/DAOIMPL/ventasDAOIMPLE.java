/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPL;

import DAO.ventasDAO;
import DTO.ventasDTO;
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
public class ventasDAOIMPLE implements ventasDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertartimbrado(ventasDTO dto, Integer codOpcion) {
        switch (codOpcion) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO ventas.timbrado(\n"
                            + "             numero,  fecha_vigencia_inicio, fecha_vigencia_final, \n"
                            + "            factura_establesimiento, factura_caja, factura_desde, factura_hasta, \n"
                            + "            idestado, idusuario, idtipodocumento, idsucursal)\n"
                            + "    VALUES ( ?, ?::date, ?::date, \n"
                            + "            ?, ?, ?, ?, \n"
                            + "            5, ?, ?, ?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getNumero());
                    preparedStatement.setObject(2, dto.getFecha_vigencia_inicio());
                    preparedStatement.setObject(3, dto.getFecha_vigencia_final());
                    preparedStatement.setObject(4, dto.getFactura_establesimiento());
                    preparedStatement.setObject(5, dto.getFactura_caja());
                    preparedStatement.setObject(6, dto.getFactura_desde());
                    preparedStatement.setObject(7, dto.getFactura_hasta());
                    preparedStatement.setObject(8, dto.getIdusuario());
                    preparedStatement.setObject(9, dto.getIdtipodocumento());
                    preparedStatement.setObject(10, dto.getIdsucursal());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ventasDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }

        return false;

    }

    @Override
    public String getTimbrados() {

        ResultSet rs;
        ArrayList<ventasDTO> alltimbrados = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = " SELECT t.idtimbrado, t.numero, t.fecha_carga::date, t.fecha_vigencia_inicio::date, t.fecha_vigencia_final::date, \n"
                    + "                          (t.factura_establesimiento||'-'||t.factura_caja||'-'||t.factura_desde) as facturaDesde, \n"
                    + "                  (t.factura_establesimiento||'-'||t.factura_caja||'-'||t.factura_hasta)as facturaHasta, (e.descripcion) as estado, \n"
                    + "                          t.idestado, idusuario, t.idtipodocumento, t.idsucursal,(d.descripcion) as tipodocumento\n"
                    + "                     FROM ventas.timbrado t\n"
                    + "left join ventas.tipo_documento d on t.idtipodocumento = d.idtipodocumento\n"
                    + "		    left join estado e on t.idestado = e.idestado\n"
                    + "		 where t.idestado in(5,10)";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alltimbrados.add(new ventasDTO(rs.getInt("idtimbrado"),
                        rs.getInt("numero"),
                        rs.getString("fecha_carga"),
                        rs.getString("fecha_vigencia_inicio"),
                        rs.getString("fecha_vigencia_final"),
                        rs.getString("facturaDesde"),
                        rs.getString("facturaHasta"),
                        rs.getString("estado"),
                        rs.getString("tipodocumento"),
                        rs.getInt("idestado")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ventasDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alltimbrados);
    }

    @Override
    public boolean insertarFacturas(ventasDTO dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO ventas.documentos_facturas(\n"
                    + "            idestado, idtimbrado, numerodocumento, secuencia)\n"
                    + "    VALUES (5, (select idtimbrado from ventas.timbrado order by idtimbrado desc limit 1), ?, ?)";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getNumerodocumento());
            preparedStatement.setObject(2, dto.getSecuencia());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ventasDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
