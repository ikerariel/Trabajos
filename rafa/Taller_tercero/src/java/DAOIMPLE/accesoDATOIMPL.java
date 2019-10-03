/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.accesoDAO;
import DTO.accesoDTO;
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
 * @author Carlos
 */
public class accesoDATOIMPL implements accesoDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;

    @Override
    public boolean verificarUser(accesoDTO accDTO) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT u.idusuario, u.usu_nombre, u.usu_clave, u.idfuncionario,\n"
                    + "p.per_descripcion, s.suc_descripcion  from usuarios u\n"
                    + "inner join perfiles p on u.idperfil=p.idperfil\n"
                    + "inner join sucursales s on u.idsucursal=s.idsucursal\n"
                    + "where u.usu_nombre=? and usu_clave=md5(?)";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, accDTO.getUsu_nombre());
            preparedStatement.setObject(2, accDTO.getUsu_clave());

            resultado = preparedStatement.executeQuery();
            if (resultado.next()) {

                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(accesoDATOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getUser(String nombre) {
        ResultSet rs;
        ArrayList<accesoDTO> alluser = new ArrayList<>();

        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = " SELECT u.idusuario, u.usu_nombre, u.usu_clave, u.idperfil, u.idfuncionario, p.per_descripcion,\n" +
"                    s.suc_descripcion, s.idsucursal, d.iddeposito, d.dep_descripcion\n" +
"                    from usuarios u\n" +
"                    inner join perfiles p on u.idperfil=p.idperfil\n" +
"                   inner join sucursales s on u.idsucursal=s.idsucursal\n" +
"                    inner join depositos d on s.iddeposito=d.iddeposito\n" +
"                    where u.usu_nombre=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setString(1, nombre);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alluser.add(new accesoDTO(rs.getInt("idsucursal"),
                        rs.getString("suc_descripcion"),
                        rs.getInt("iddeposito"),
                        rs.getString("dep_descripcion"),
                        rs.getInt("idusuario"),
                        rs.getInt("idperfil"),
                        rs.getString("usu_nombre")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(accesoDATOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alluser);
    }

}
