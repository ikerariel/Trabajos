/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.ajustesdao;
import DTO.Ajustesdto;
import DTO.estadodto;
import DTO.motivoajudto;
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
public class ajustesdaoimple implements ajustesdao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public Integer getUltimoCodigoAjustes1() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(idajuste),0 )+ 1 as codigoAjus FROM ajuste;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigoAjus");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ajustesdaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String ListarEstadoAjuste2() {
        ResultSet rs;
        ArrayList<estadodto> allEstad = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idestado, descri_estado FROM estado WHERE idestado=1;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allEstad.add(new estadodto(rs.getInt("idestado"),
                        rs.getString("descri_estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ajustesdaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEstad);
    }

    @Override
    public String ListarUsuarioAjustes3() {
        ResultSet rs;
        ArrayList<usuariosdto> allUsua = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idusuario, usu_nombre, usu_clave, idfuncionario,\n"
                    + " idperfil, idsucursal FROM usuarios where idusuario=1";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allUsua.add(new usuariosdto(rs.getInt("idusuario"),
                        rs.getString("usu_nombre"),
                        rs.getString("usu_clave"),
                        rs.getInt("idfuncionario"),
                        rs.getInt("idperfil"),
                        rs.getInt("idsucursal")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ajustesdaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allUsua);
    }

    @Override
    public String ListarMotivoAjuste4() {
        ResultSet rs;
        ArrayList<motivoajudto> allmotiaju = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idmot_ajus, ajustmotiv_descri FROM motivo_ajuste ORDER BY idmot_ajus;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allmotiaju.add(new motivoajudto(rs.getInt("idmot_ajus"),
                        rs.getString("ajustmotiv_descri")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ajustesdaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allmotiaju);
    }

    @Override
    public String ListarMercaderiaAjuste5() {
        ResultSet rs;
        ArrayList<Ajustesdto> allMercaderia = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "Select *from mercaderias";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allMercaderia.add(new Ajustesdto(rs.getInt("idmercaderia"),
                        rs.getInt("mer_costo"),
                        rs.getInt("mer_precio"),
                        rs.getString("mer_descripcion"),
                        rs.getInt("idcategoria"),
                        rs.getInt("idmarca"),
                        rs.getInt("idprocedencia"),
                        rs.getInt("idimpuesto"),
                        rs.getString("codigogenerico")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ajustesdaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allMercaderia); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertarCabeceraAjuste6(Ajustesdto dto, Integer cod) {
        switch (cod) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO ajuste(ajuste_fecha, idmot_ajus, idusuario, idestado,idtipo_ajuste)\n"
                            + "VALUES (?::date, ?, ?, ?,?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getAjuste_fecha());
                    preparedStatement.setObject(2, dto.getIdmot_ajus());
                    preparedStatement.setObject(3, dto.getIdusuario());
                    preparedStatement.setObject(4, dto.getIdestado());
                    preparedStatement.setObject(5, dto.getIdtipo_ajuste());
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
                    Logger.getLogger(ajustesdaoimple.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.ajuste\n"
                            + "   SET  ajuste_fecha=?::date, idmot_ajus=?, idusuario=?, idestado=1,idtipo_ajuste=?\n"
                            + " WHERE idajuste=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getAjuste_fecha());
                    preparedStatement.setObject(2, dto.getIdmot_ajus());
                    preparedStatement.setObject(3, dto.getIdusuario());
                    preparedStatement.setObject(4, dto.getIdtipo_ajuste());
                    preparedStatement.setObject(5, dto.getIdajuste());

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
                    Logger.getLogger(ajustesdaoimple.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }

        return false;
    }

    @Override
    public boolean insertarDetalleAjuste7(Ajustesdto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detalle_ajuste(idajuste, idmercaderia, ajuste_cantidad)\n"
                    + "VALUES (?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdajusteA());
            preparedStatement.setObject(2, dto.getIdmercaderia());
            preparedStatement.setObject(3, dto.getAjuste_cantidad());
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
            Logger.getLogger(ajustesdaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String ListarAjuste8() {
        ResultSet rs;
        ArrayList<Ajustesdto> allFacturaC = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT a.idajuste, a.ajuste_fecha, m.ajustmotiv_descri, u.usu_nombre, e.descri_estado\n"
                    + "FROM ajuste a\n"
                    + "inner join motivo_ajuste m on a.idmot_ajus = m.idmot_ajus\n"
                    + "inner join usuarios u on a.idusuario = u.idusuario\n"
                    + "inner join estado e on a.idestado = e.idestado where a.idestado in(1,2)\n"
                    + "order by idajuste desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allFacturaC.add(new Ajustesdto(rs.getInt("idajuste"),
                        rs.getString("ajuste_fecha"),
                        rs.getString("ajustmotiv_descri"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ajustesdaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allFacturaC);
    }

    @Override
    public boolean confirmarAjuste9(Ajustesdto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE ajuste SET idestado=? WHERE idajuste=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdestado());
            preparedStatement.setObject(2, dto.getIdajuste());
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
            Logger.getLogger(ajustesdaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String listarDetalleAjuste10(Integer id) {
        ResultSet rs;
        ArrayList<Ajustesdto> alldetalleAjuste = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT a.idajuste,a.idmot_ajus, a.ajuste_fecha, m.ajustmotiv_descri, u.usu_nombre, e.descri_estado,\n"
                    + "d.idmercaderia, d.ajuste_cantidad, me.codigogenerico, me.mer_descripcion\n"
                    + "FROM ajuste a\n"
                    + "inner join motivo_ajuste m on a.idmot_ajus = m.idmot_ajus\n"
                    + "inner join usuarios u on a.idusuario = u.idusuario\n"
                    + "inner join estado e on a.idestado = e.idestado\n"
                    + "inner join detalle_ajuste d on a.idajuste = d.idajuste\n"
                    + "inner join mercaderias me on d.idmercaderia = me.idmercaderia\n"
                    + "where a.idajuste=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetalleAjuste.add(new Ajustesdto(
                        rs.getInt("idajuste"),
                        rs.getString("ajuste_fecha"),
                        rs.getString("ajustmotiv_descri"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado"),
                        rs.getInt("idmercaderia"),
                        rs.getInt("ajuste_cantidad"),
                        rs.getString("codigogenerico"),
                        rs.getInt("idmot_ajus"),
                        rs.getString("mer_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ajustesdaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldetalleAjuste);
    }

    @Override
    public boolean deleteDetalleAjustes(Ajustesdto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM public.detalle_ajuste\n"
                    + " WHERE idajuste=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdajuste());
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
            Logger.getLogger(ajustesdaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
