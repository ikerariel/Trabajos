/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.marcasdao;
import DTO.marcasdto;
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
public class marcasdaoimple implements marcasdao{
    
    private String sintaxiSql;
    private String MsmError;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;
    
    /**
     *
     * @param marca_Dto
     * @return
     * @throws mierror
     */
    
    @Override
    public boolean insertar(marcasdto marca_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO marcas(idmarca, marca_descri)\n"
                    + " VALUES (?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, marca_Dto.getIdmarca());
            preparedStatement.setObject(2, marca_Dto.getMarca_descri());
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
    public boolean modificar(marcasdto marca_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE marcas SET marca_descri=? WHERE idmarca=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, marca_Dto.getMarca_descri());
            preparedStatement.setObject(2, marca_Dto.getIdmarca());
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
            sintaxiSql = "DELETE FROM marcas WHERE idmarca=?;";
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
    public String getmarca() throws mierror {
        ResultSet rs;
        ArrayList<marcasdto> allMarca = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idmarca, marca_descri FROM marcas ORDER BY idmarca;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allMarca.add(new marcasdto(rs.getInt("idmarca"),
                        rs.getString("marca_descri")));
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allMarca);
    }
    
    @Override
    public Integer getUltimoCodigo() throws mierror {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(idmarca),0 )+ 1 as codigo  FROM marcas;";
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
    public String getmarcaFiltro(Integer idFiltro) throws mierror {
        ResultSet rs;
        ArrayList<marcasdto> allmarca = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idmarca, marca_descri FROM marcas WHERE idmarca=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allmarca.add(new marcasdto(rs.getInt("idmarca"),
                        
                        rs.getString("marca_descri")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allmarca);

    }
    
}
