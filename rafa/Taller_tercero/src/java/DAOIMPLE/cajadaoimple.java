/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.cajadao;
import DTO.cajadto;
import Genericos.Conexion;
import Genericos.mierror;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author naty
 */
public class cajadaoimple implements cajadao{
    
    private String sintaxiSql;
    private String MsmError;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;
    
    /**
     *
     * @param caja_Dto
     * @return
     * @throws mierror
     */
    @Override
    public boolean insertar(cajadto caja_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO cajas(idcaja, caja_descripcion)\n"
                    + " VALUES (?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, caja_Dto.getIdcaja());
            preparedStatement.setObject(2, caja_Dto.getCaja_descripcion());
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
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);

        }
        return false;

    }
    @Override
    public boolean modificar(cajadto caja_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE cajas SET caja_descripcion=? WHERE idcaja=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, caja_Dto.getCaja_descripcion());
            preparedStatement.setObject(2, caja_Dto.getIdcaja());
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
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);

        }
        return false;
    }
    @Override
    public boolean eliminar(Integer id) throws mierror {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM cajas WHERE idcaja=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, id);
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
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return false;
    }
    @Override
    public String getcaja() throws mierror {
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
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allCaja);
    }
    @Override
    public Integer getUltimoCodigo() throws mierror {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(idcaja),0 )+ 1 as codigo  FROM cajas;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return 0;
    }
    @Override
    public String getcajaFiltro(Integer idFiltro) throws mierror {
        ResultSet rs;
        ArrayList<cajadto> allCaja = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idcaja, caja_descripcion FROM cajas WHERE idcaja=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allCaja.add(new cajadto(rs.getInt("idcaja"),
                        
                        rs.getString("caja_descripcion")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allCaja);

    }
}
