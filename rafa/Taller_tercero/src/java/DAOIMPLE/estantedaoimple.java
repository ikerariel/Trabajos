/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.estantesdao;
import DTO.estantesdto;
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
public class estantedaoimple implements estantesdao{
    
    private String sintaxiSql;
    private String MsmError;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;
    
    /**
     *
     * @param estante_Dto
     * @return
     * @throws mierror
     */

    @Override
    public boolean insertar(estantesdto estante_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO estantes(idestante, estan_descri) VALUES (?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, estante_Dto.getIdestante());
            preparedStatement.setObject(2, estante_Dto.getEstan_descri());
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
    public boolean modificar(estantesdto estante_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE estantes SET estan_descri=? WHERE idestante=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, estante_Dto.getEstan_descri());
            preparedStatement.setObject(2, estante_Dto.getIdestante());
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
            sintaxiSql = "DELETE FROM estantes WHERE idestante=?;";
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
    public String getEstante() throws mierror {
        ResultSet rs;
        ArrayList<estantesdto> allEstan = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idestante, estan_descri FROM estantes ORDER BY idestante;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allEstan.add(new estantesdto(rs.getInt("idestante"),
                        rs.getString("estan_descri")));
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allEstan);
    }

    @Override
    public Integer getUltimoCodigo() throws mierror {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(idestante),0 )+ 1 as codigo FROM estantes;";
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
    public String getEstanteFiltro(Integer idFiltro) throws mierror {
        ResultSet rs;
        ArrayList<estantesdto> allEstan = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idestante, estan_descri FROM estantes WHERE idestante=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allEstan.add(new estantesdto(rs.getInt("idestante"),                        
                        rs.getString("estan_descri")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allEstan);
    }
    
}
