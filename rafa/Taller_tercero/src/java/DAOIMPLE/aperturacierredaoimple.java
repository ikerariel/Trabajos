/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.aperturacierredao;
import DTO.aperturacierredto;
import DTO.cajadto;
import DTO.estadodto;
import DTO.timbradodto;
import DTO.usuariosdto;
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
public class aperturacierredaoimple implements aperturacierredao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public Integer getUltimoCodigoAper1() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(idapercierre),0 )+ 1 as codigoAper FROM apertura_caja;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigoAper");
            }
        } catch (SQLException ex) {
            Logger.getLogger(aperturacierredaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String ListarEstadoAper2() {
        ResultSet rs;
        ArrayList<estadodto> allEstad = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idestado, descri_estado\n"
                    + "  FROM estado WHERE idestado=7;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allEstad.add(new estadodto(rs.getInt("idestado"),
                        rs.getString("descri_estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(aperturacierredaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEstad);
    }

    @Override
    public String ListarUsuarioAper3() {
        ResultSet rs;
        ArrayList<usuariosdto> allUsuario = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT u.idusuario, u.usu_nombre, u.usu_clave, f.fun_nombres, f.fun_apellidos,\n"
                    + "                    p.per_descripcion, s.suc_descripcion\n"
                    + "                    FROM usuarios u\n"
                    + "                    inner join funcionarios f on u.idfuncionario=f.idfuncionario\n"
                    + "                    inner join perfiles p on u.idperfil=p.idperfil\n"
                    + "                    inner join sucursales s on u.idsucursal=s.idsucursal\n"
                    + "                    WHERE p.per_descripcion='cajero';";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allUsuario.add(new usuariosdto(rs.getInt("idusuario"),
                        rs.getString("usu_nombre"),
                        rs.getString("usu_clave"),
                        rs.getString("fun_nombres"),
                        rs.getString("fun_apellidos"),
                        rs.getString("per_descripcion"),
                        rs.getString("suc_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(aperturacierredaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allUsuario);
    }

    @Override
    public String ListarCajasAper4() {
        ResultSet rs;
        ArrayList<cajadto> allCaja = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idcaja, caja_descripcion FROM cajas ORDER BY idcaja;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allCaja.add(new cajadto(rs.getInt("idcaja"),
                        rs.getString("caja_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(aperturacierredaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allCaja);
    }

    @Override
    public boolean insertarAperturacierre5(aperturacierredto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO apertura_caja( aper_monto, idcaja, idusuario, idestado,\n"
                    + "cierre_monto, cajero, iddeposito, idtimbrado, fecha_apertura, fecha_cierre)\n"
                    + "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?::date, ?::date);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getAper_monto());
            preparedStatement.setObject(2, dto.getIdcaja());
            preparedStatement.setObject(3, dto.getIdusuario());
            preparedStatement.setObject(4, dto.getIdestado());
            preparedStatement.setObject(5, dto.getCierre_monto());
            preparedStatement.setObject(6, dto.getCajero());
            preparedStatement.setObject(7, dto.getIddeposito());
            preparedStatement.setObject(8, dto.getIdtimbrado());
            preparedStatement.setObject(9, dto.getFecha_apertura());
            preparedStatement.setObject(10, dto.getFecha_cierre());
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
            Logger.getLogger(aperturacierredaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String ListarTimbradoAper6() {
        ResultSet rs;
        ArrayList<timbradodto> allPedi = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT t.idtimbrado, t.tim_numero, t.tim_fechavence, e.idestado, e.descri_estado, t.tim_fechainicio,\n"
                    + "   u.idusuario, u.usu_nombre, t.fac_establecimiento, t.fac_desde, t.fac_hasta, t.fac_caja\n"
                    + "   FROM timbrados t\n"
                    + "   inner join estado e on t.idestado=e.idestado\n"
                    + "   inner join usuarios u on t.idusuario=u.idusuario where e.idestado = 4;";
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
            Logger.getLogger(aperturacierredaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allPedi);
    }

    @Override
    public String ListarAperturCierre7() {
        ResultSet rs;
        ArrayList<aperturacierredto> allPedi = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT a.idapercierre, a.aper_monto, c.caja_descripcion, u.usu_nombre, e.descri_estado, a.cierre_monto, \n"
                    + "       a.cajero, t.tim_numero, a.fecha_apertura, a.fecha_cierre\n"
                    + "FROM apertura_caja a\n"
                    + "inner join cajas c on a.idcaja=c.idcaja\n"
                    + "inner join usuarios u on a.idusuario=u.idusuario\n"
                    + "inner join estado e on a.idestado=e.idestado\n"
                    + "inner join timbrados t on a.idtimbrado=t.idtimbrado order by a.idapercierre desc;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allPedi.add(new aperturacierredto(rs.getInt("idapercierre"),
                        rs.getInt("aper_monto"),
                        rs.getString("caja_descripcion"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado"),
                        rs.getInt("cierre_monto"),
                        rs.getString("cajero"),
                        rs.getInt("tim_numero"),
                        rs.getString("fecha_apertura"),
                        rs.getString("fecha_cierre")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(aperturacierredaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allPedi);
    }

    @Override
    public String RecuperarApertura8(Integer id) {
        ResultSet rs;
        ArrayList<aperturacierredto> allPedi = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT a.aper_monto, c.caja_descripcion, u.usu_nombre, e.descri_estado, a.cierre_monto, \n"
                    + "  a.cajero, t.tim_numero, a.fecha_apertura, a.fecha_cierre\n"
                    + "FROM apertura_caja a\n"
                    + "inner join cajas c on a.idcaja=c.idcaja\n"
                    + "inner join usuarios u on a.idusuario=u.idusuario\n"
                    + "inner join estado e on a.idestado=e.idestado\n"
                    + "inner join timbrados t on a.idtimbrado=t.idtimbrado where a.idapercierre=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allPedi.add(new aperturacierredto(
                        rs.getInt("aper_monto"),
                        rs.getString("caja_descripcion"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado"),
                        rs.getInt("cierre_monto"),
                        rs.getString("cajero"),
                        rs.getInt("tim_numero"),
                        rs.getString("fecha_apertura"),
                        rs.getString("fecha_cierre")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(aperturacierredaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allPedi);
    }
}
