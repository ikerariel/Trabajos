/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.sucursaldao;
import DTO.ciudadesdto;
import DTO.depositodto;
import DTO.sucursaldto;
import Genericos.Conexion;
import Genericos.mierror;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Rafel
 */
public class sucursaldaoimple implements sucursaldao {

    private String sintaxiSql;
    private String MsmError;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    /**
     *
     * @param Sucur_Dto
     * @return
     * @throws mierror
     */
    @Override
    public boolean insertar(sucursaldto Sucur_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO sucursales(idsucursal, suc_descripcion, idciudad, iddeposito)\n"
                    + "    VALUES (?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Sucur_Dto.getIdsucursal());
            preparedStatement.setObject(2, Sucur_Dto.getSuc_descripcion());
            preparedStatement.setObject(3, Sucur_Dto.getIdciudad());
            preparedStatement.setObject(4, Sucur_Dto.getIddeposito());
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
    public boolean modificar(sucursaldto Sucur_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE sucursales SET suc_descripcion=?, idciudad=?,"
                    + " iddeposito=? WHERE idsucursal=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Sucur_Dto.getSuc_descripcion());
            preparedStatement.setObject(2, Sucur_Dto.getIdciudad());
            preparedStatement.setObject(3, Sucur_Dto.getIddeposito());
            preparedStatement.setObject(4, Sucur_Dto.getIdsucursal());
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
            sintaxiSql = "DELETE FROM sucursales WHERE idsucursal=?;";
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
    public String getsucur() throws mierror {
        ResultSet rs;
        ArrayList<sucursaldto> allSucursal = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT s.idsucursal, s.suc_descripcion, c.ciu_descripcion, d.dep_descripcion\n"
                    + "  FROM sucursales s, ciudades c, depositos d\n"
                    + "  WHERE s.idciudad = c.idciudad and s.iddeposito = d.iddeposito\n"
                    + "  ORDER BY s.idsucursal;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allSucursal.add(new sucursaldto(rs.getInt("idsucursal"),
                        rs.getString("suc_descripcion"),
                        rs.getString("ciu_descripcion"),
                        rs.getString("dep_descripcion")));
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allSucursal);
    }

    @Override
    public Integer getUltimoCodigo() throws mierror {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(idsucursal),0 )+ 1 as codi_sucursal  FROM sucursales;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codi_sucursal");
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return 0;
    }

    @Override
    public String getsucurFiltro(Integer idFiltro) throws mierror {
        ResultSet rs;
        ArrayList<sucursaldto> allSucursal = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idsucursal, suc_descripcion, idciudad, "
                    + "iddeposito FROM sucursales WHERE idsucursal=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allSucursal.add(new sucursaldto(rs.getInt("idsucursal"),
                        rs.getString("suc_descripcion"),
                        rs.getInt("idciudad"),
                        rs.getInt("iddeposito")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allSucursal);
    }

    @Override
    public String listarciudad() throws mierror {
        ResultSet rs;
        ArrayList<ciudadesdto> allCiud = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idciudad, ciu_descripcion FROM ciudades ORDER BY idciudad;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allCiud.add(new ciudadesdto(rs.getInt("idciudad"),
                        rs.getString("ciu_descripcion")));
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allCiud);
    }

    @Override
    public String listardeposito() throws mierror {
        ResultSet rs;
        ArrayList<depositodto> allDepos = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT iddeposito, dep_descripcion FROM depositos ORDER BY iddeposito;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allDepos.add(new depositodto(rs.getInt("iddeposito"),
                        rs.getString("dep_descripcion")));
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allDepos);
    }
}
