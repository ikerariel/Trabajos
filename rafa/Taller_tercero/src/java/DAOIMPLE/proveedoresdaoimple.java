/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.proveedoresdao;
import DTO.ciudadesdto;
import DTO.proveedoresdto;
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
public class proveedoresdaoimple implements proveedoresdao {

    private String sintaxiSql;
    private String MsmError;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    /**
     *
     * @param provee_Dto
     * @return
     * @throws mierror
     */
    @Override
    public boolean insertar(proveedoresdto provee_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO proveedores( id_prov, prov_nombre, prov_direc, prov_imail, prov_telf,\n"
                    + " prov_ruc)  VALUES (?, ?, ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, provee_Dto.getId_prov());
            preparedStatement.setObject(2, provee_Dto.getProv_nombre());
            preparedStatement.setObject(3, provee_Dto.getProv_direc());
            preparedStatement.setObject(4, provee_Dto.getProv_imail());
            preparedStatement.setObject(5, provee_Dto.getProv_telf());
            preparedStatement.setObject(6, provee_Dto.getProv_ruc());
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
    public boolean modificar(proveedoresdto provee_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE proveedores SET prov_nombre=?, prov_direc=?, prov_imail=?, prov_telf=?, \n"
                    + "       prov_ruc=? WHERE id_prov=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, provee_Dto.getProv_nombre());
            preparedStatement.setObject(2, provee_Dto.getProv_direc());
            preparedStatement.setObject(3, provee_Dto.getProv_imail());
            preparedStatement.setObject(4, provee_Dto.getProv_telf());
            preparedStatement.setObject(5, provee_Dto.getProv_ruc());
            preparedStatement.setObject(6, provee_Dto.getId_prov());
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
            sintaxiSql = "DELETE FROM proveedores WHERE id_prov=?;";
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
    public String getproveedor() throws mierror {
        ResultSet rs;
        ArrayList<proveedoresdto> allProveedor = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_prov, prov_nombre, prov_direc, prov_imail, prov_telf, prov_ruc\n"
                    + "  FROM proveedores ORDER BY id_prov;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allProveedor.add(new proveedoresdto(rs.getInt("id_prov"),
                        rs.getString("prov_nombre"),
                        rs.getString("prov_direc"),
                        rs.getString("prov_imail"),
                        rs.getString("prov_telf"),
                        rs.getString("prov_ruc")));
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allProveedor);
    }

    @Override
    public Integer getUltimoCodigo() throws mierror {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_prov),0 )+ 1 as cod_proveedor FROM proveedores;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("cod_proveedor");
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return 0;
    }

    @Override
    public String getproveedorFiltro(Integer idFiltro) throws mierror {
        ResultSet rs;
        ArrayList<proveedoresdto> allProveedor = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_prov, prov_nombre, prov_direc, prov_imail, \n"
                    + "      prov_telf, prov_ruc\n"
                    + "      FROM proveedores WHERE id_prov=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allProveedor.add(new proveedoresdto(rs.getInt("id_prov"),
                        rs.getString("prov_nombre"),
                        rs.getString("prov_direc"),
                        rs.getString("prov_imail"),
                        rs.getString("prov_telf"),
                        rs.getString("prov_ruc")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allProveedor);
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

}
