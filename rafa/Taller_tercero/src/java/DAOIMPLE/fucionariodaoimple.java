/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.funcionariodao;
import DTO.cargosdto;
import DTO.ciudadesdto;
import DTO.funcionariodto;
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
public class fucionariodaoimple implements funcionariodao {

    private String sintaxiSql;
    private String MsmError;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    /**
     *
     * @param funcion_Dto
     * @return
     * @throws mierror
     */
    @Override
    public boolean insertar(funcionariodto funcion_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO funcionarios( idfuncionario, fun_direccion, fun_correo, fun_ci, fun_nombres, \n"
                    + " fun_apellidos, fun_telefono, idcargo, idciudad)\n"
                    + " VALUES (?, ?, ?, ?, ?,  ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, funcion_Dto.getIdfuncionario());
            preparedStatement.setObject(2, funcion_Dto.getFun_direccion());
            preparedStatement.setObject(3, funcion_Dto.getFun_correo());
            preparedStatement.setObject(4, funcion_Dto.getFun_ci());
            preparedStatement.setObject(5, funcion_Dto.getFun_nombres());
            preparedStatement.setObject(6, funcion_Dto.getFun_apellidos());
            preparedStatement.setObject(7, funcion_Dto.getFun_telefono());
            preparedStatement.setObject(8, funcion_Dto.getIdcargo());
            preparedStatement.setObject(9, funcion_Dto.getIdciudad());
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
    public boolean modificar(funcionariodto funcion_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE funcionarios SET fun_direccion=?, fun_correo=?, fun_ci=?, fun_nombres=?, \n"
                    + "  fun_apellidos=?, fun_telefono=?, idcargo=?, idciudad=? WHERE idfuncionario=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, funcion_Dto.getFun_direccion());
            preparedStatement.setObject(2, funcion_Dto.getFun_correo());
            preparedStatement.setObject(3, funcion_Dto.getFun_ci());
            preparedStatement.setObject(4, funcion_Dto.getFun_nombres());
            preparedStatement.setObject(5, funcion_Dto.getFun_apellidos());
            preparedStatement.setObject(6, funcion_Dto.getFun_telefono());
            preparedStatement.setObject(7, funcion_Dto.getIdcargo());
            preparedStatement.setObject(8, funcion_Dto.getIdciudad());
            preparedStatement.setObject(9, funcion_Dto.getIdfuncionario());
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
            sintaxiSql = "DELETE FROM funcionarios WHERE idfuncionario=?;";
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
    public String getfuncionario() throws mierror {
        ResultSet rs;
        ArrayList<funcionariodto> allFuncionar = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT f.idfuncionario, f.fun_direccion, f.fun_correo, f.fun_ci, f.fun_nombres,\n"
                    + "       f.fun_apellidos, f.fun_telefono, c.car_descripcion, ci.ciu_descripcion\n"
                    + "       FROM funcionarios f, cargos c, ciudades ci\n"
                    + "       WHERE f.idcargo = c.idcargo AND f.idciudad = ci.idciudad\n"
                    + "       ORDER BY f.idfuncionario;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allFuncionar.add(new funcionariodto(rs.getInt("idfuncionario"),
                        rs.getString("fun_direccion"),
                        rs.getString("fun_correo"),
                        rs.getString("fun_ci"),
                        rs.getString("fun_nombres"),
                        rs.getString("fun_apellidos"),
                        rs.getString("fun_telefono"),
                        rs.getString("car_descripcion"),
                        rs.getString("ciu_descripcion")));
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allFuncionar);
    }

    @Override
    public Integer getUltimoCodigo() throws mierror {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(idfuncionario),0 )+ 1 as cod_funcionar  FROM funcionarios;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("cod_funcionar");
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return 0;
    }

    @Override
    public String getfuncionarioFiltro(Integer idFiltro) throws mierror {
        ResultSet rs;
        ArrayList<funcionariodto> allFuncionar = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idfuncionario, fun_direccion, fun_correo, fun_ci, fun_nombres, \n"
                    + "  fun_apellidos, fun_telefono, idcargo, idciudad\n"
                    + "  FROM funcionarios WHERE idfuncionario=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allFuncionar.add(new funcionariodto(rs.getInt("idfuncionario"),
                        rs.getString("fun_direccion"),
                        rs.getString("fun_correo"),
                        rs.getString("fun_ci"),
                        rs.getString("fun_nombres"),
                        rs.getString("fun_apellidos"),
                        rs.getString("fun_telefono"),
                        rs.getInt("idcargo"),
                        rs.getInt("idciudad")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allFuncionar);
    }

    @Override
    public String listarcargos() throws mierror {
        ResultSet rs;
        ArrayList<cargosdto> allCargos = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idcargo, car_descripcion FROM cargos ORDER BY idcargo;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allCargos.add(new cargosdto(rs.getInt("idcargo"),
                        rs.getString("car_descripcion")));
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allCargos); 
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
