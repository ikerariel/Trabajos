/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.timbradodao;
import DTO.estadodto;
import DTO.timbradodto;
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
 * @author Rafel
 */
public class timbradodaoimple implements timbradodao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public Integer getUltimoCodigoTimbrado1() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(idtimbrado),0 )+ 1 as codigotimb FROM timbrados;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigotimb");
            }
        } catch (SQLException ex) {
            Logger.getLogger(timbradodaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String ListarEstadoTimbrado2() {
        ResultSet rs;
        ArrayList<estadodto> allEstad = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idestado, descri_estado\n"
                    + "  FROM estado WHERE idestado=4;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allEstad.add(new estadodto(rs.getInt("idestado"),
                        rs.getString("descri_estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(timbradodaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEstad);
    }

    @Override
    public boolean insertarTimbrado3(timbradodto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO timbrados(tim_numero, tim_fechavence, idestado, tim_fechainicio, \n"
                    + "            idusuario, fac_establecimiento, fac_desde, fac_hasta, fac_caja)\n"
                    + "    VALUES (?, ?::date, ?, ?::date, ?, ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getTim_numero());
            preparedStatement.setObject(2, dto.getTim_fechavence());
            preparedStatement.setObject(3, dto.getIdestado());
            preparedStatement.setObject(4, dto.getTim_fechainicio());
            preparedStatement.setObject(5, dto.getIdusuario());
            preparedStatement.setObject(6, dto.getFac_establecimiento());
            preparedStatement.setObject(7, dto.getFac_desde());
            preparedStatement.setObject(8, dto.getFac_hasta());
            preparedStatement.setObject(9, dto.getFac_caja());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
            conexion.desConectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(timbradodaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertarDetalleNroFactura4(timbradodto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO numero_factura(idestado, idtimbrado, numerfactura)\n"
                    + "VALUES (9, (select idtimbrado from timbrados where tim_numero=?), ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getTim_numero());
            preparedStatement.setObject(2, dto.getNumerfactura());

            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
            conexion.desConectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(timbradodaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String listarTimbrado5() {
        ResultSet rs;
        ArrayList<timbradodto> allPedi = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT t.idtimbrado, t.tim_numero, t.tim_fechavence, e.idestado, e.descri_estado, t.tim_fechainicio, \n"
                    + "       u.idusuario, u.usu_nombre, t.fac_establecimiento, t.fac_desde, t.fac_hasta, t.fac_caja\n"
                    + "FROM timbrados t\n"
                    + "inner join estado e on t.idestado=e.idestado\n"
                    + "inner join usuarios u on t.idusuario=u.idusuario order by t.idtimbrado desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allPedi.add(new timbradodto(rs.getInt("idtimbrado"),
                        rs.getInt("tim_numero"),
                        rs.getString("tim_fechavence"),
                        rs.getInt("idestado"),
                        rs.getString("descri_estado"),
                        rs.getString("tim_fechainicio"),
                        rs.getInt("idusuario"),
                        rs.getString("usu_nombre"),
                        rs.getString("fac_establecimiento"),
                        rs.getString("fac_desde"),
                        rs.getString("fac_hasta"),
                        rs.getString("fac_caja")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(timbradodaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allPedi);
    }

}
