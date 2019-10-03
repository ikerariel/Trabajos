/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.usuariosdao;
import DTO.funcionariodto;
import DTO.perfilesdto;
import DTO.sucursaldto;
import DTO.usuariosdto;
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
public class usuariosdaoimple implements usuariosdao {

    private String sintaxiSql;
    private String MsmError;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    /**
     *
     * @param usu_Dto
     * @return
     * @throws mierror
     */
    @Override
    public boolean insertar(usuariosdto usu_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO usuarios( idusuario, usu_nombre, usu_clave, idfuncionario, idperfil, idsucursal)\n"
                    + "    VALUES (?, ?, md5(?), ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, usu_Dto.getIdusuario());
            preparedStatement.setObject(2, usu_Dto.getUsu_nombre());
            preparedStatement.setObject(3, usu_Dto.getUsu_clave());
            preparedStatement.setObject(4, usu_Dto.getIdfuncionario());
            preparedStatement.setObject(5, usu_Dto.getIdperfil());
            preparedStatement.setObject(6, usu_Dto.getIdsucursal());
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
    public boolean modificar(usuariosdto usu_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE usuarios SET usu_nombre=?, usu_clave=md5(?), idfuncionario=?,\n"
                    + "    idperfil=?, idsucursal=? WHERE idusuario=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, usu_Dto.getUsu_nombre());
            preparedStatement.setObject(2, usu_Dto.getUsu_clave());
            preparedStatement.setObject(3, usu_Dto.getIdfuncionario());
            preparedStatement.setObject(4, usu_Dto.getIdperfil());
            preparedStatement.setObject(5, usu_Dto.getIdsucursal());
            preparedStatement.setObject(6, usu_Dto.getIdusuario());
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
            sintaxiSql = "DELETE FROM usuarios WHERE idusuario=?;";
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
    public String getusuario() throws mierror {
        ResultSet rs;
        ArrayList<usuariosdto> allUsuario = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT u.idusuario, u.usu_nombre, u.usu_clave, f.fun_nombres, f.fun_apellidos,\n"
                    + "	p.per_descripcion, s.suc_descripcion\n"
                    + "  FROM usuarios u, funcionarios f, perfiles p, sucursales s\n"
                    + "  WHERE u.idfuncionario=f.idfuncionario AND u.idperfil=p.idperfil AND u.idsucursal=s.idsucursal\n"
                    + "  ORDER BY u.idusuario;";
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
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allUsuario);
    }

    @Override
    public Integer getUltimoCodigo() throws mierror {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(idusuario),0 )+ 1 as cod_usua  FROM usuarios;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("cod_usua");
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return 0;
    }

    @Override
    public String getusuarioFiltro(Integer idFiltro) throws mierror {
        ResultSet rs;
        ArrayList<usuariosdto> allUsuario = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idusuario, usu_nombre, usu_clave, idfuncionario, idperfil, idsucursal\n"
                    + "  FROM usuarios WHERE idusuario=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allUsuario.add(new usuariosdto(rs.getInt("idusuario"),
                        rs.getString("usu_nombre"),
                        rs.getString("usu_clave"),
                        rs.getInt("idfuncionario"),
                        rs.getInt("idperfil"),
                        rs.getInt("idsucursal")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allUsuario);
    }

    @Override
    public String listarfuncionario() throws mierror {
        ResultSet rs;
        ArrayList<funcionariodto> allFuncionario = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idfuncionario, fun_direccion, fun_correo, fun_ci, fun_nombres, \n"
                    + "       fun_apellidos, fun_telefono, idcargo, idciudad\n"
                    + "  FROM funcionarios ORDER BY idfuncionario;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allFuncionario.add(new funcionariodto(rs.getInt("idfuncionario"),
                        rs.getString("fun_direccion"),
                        rs.getString("fun_correo"),
                        rs.getString("fun_ci"),
                        rs.getString("fun_nombres"),
                        rs.getString("fun_apellidos"),
                        rs.getString("fun_telefono"),
                        rs.getInt("idcargo"),
                        rs.getInt("idciudad")));
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allFuncionario);
    }

    @Override
    public String listarperfil() throws mierror {
        ResultSet rs;
        ArrayList<perfilesdto> allPerf = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idperfil, per_descripcion FROM perfiles ORDER BY idperfil;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allPerf.add(new perfilesdto(rs.getInt("idperfil"),
                        rs.getString("per_descripcion")));
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allPerf); 
    }

    @Override
    public String listarsucursal() throws mierror {
        ResultSet rs;
        ArrayList<sucursaldto> allSucur = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idsucursal, suc_descripcion, idciudad, iddeposito FROM sucursales ORDER BY idsucursal;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allSucur.add(new sucursaldto(rs.getInt("idsucursal"),
                        rs.getString("suc_descripcion"),
                        rs.getInt("idciudad"),
                        rs.getInt("iddeposito")));
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allSucur);
    }

}
