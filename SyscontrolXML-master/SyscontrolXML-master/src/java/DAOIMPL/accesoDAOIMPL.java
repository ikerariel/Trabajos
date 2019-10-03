/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPL;

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
 * @author !mX
 */
public class accesoDAOIMPL implements accesoDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;

    @Override
    public boolean verificarUsuario(accesoDTO accesoDTO) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idusuario, usuario, pass, idestado FROM public.usuario WHERE usuario = ? AND pass =? AND idestado=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, accesoDTO.getUsuario());
            preparedStatement.setObject(2, accesoDTO.getPass());
            preparedStatement.setObject(3, (5));

            resultado = preparedStatement.executeQuery();
            if (resultado.next()) {

                return true;
            }
            conexion.desConectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(accesoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    @Override
    public String getUser(String login) {
          ResultSet rs;
            ArrayList<accesoDTO> alluser = new ArrayList<>();
            
        try {
         
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idusuario, usuario, pass, idestado FROM public.usuario WHERE usuario = ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setString(1, login);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alluser.add(new accesoDTO(rs.getInt("idusuario"),
                        rs.getString("usuario")));
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(accesoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
         return new Gson().toJson(alluser);
    }

}
