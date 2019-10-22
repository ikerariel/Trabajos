/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.NotaCreComprasDAO;
import ModelDTO.NotaCreComprasDTO;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oscar
 */
public class NotaCreComprasDAOIMPL implements NotaCreComprasDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarNotaCreCompras(NotaCreComprasDTO Dto, Integer v_caso) {
        switch (v_caso) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO notacrecompras( nro_nocred, \n"
                            + " nro_timbrado, obs_nocred, id_compra, id_usuario, id_estado, id_deposito)\n"
                            + " VALUES (?, ?, ?, ?, ?, 3,?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, Dto.getNro_nocred());
                    preparedStatement.setObject(2, Dto.getNro_timbrado());
                    preparedStatement.setObject(3, Dto.getObs_nocred());
                    preparedStatement.setObject(4, Dto.getId_compra());
                    preparedStatement.setObject(5, Dto.getId_usuario());
                    preparedStatement.setObject(6, Dto.getId_deposito());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                        return true;
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                        return false;
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(NotaCreComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);

                }

                break;
            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE notacrecompras SET fecha_nocred=now(), nro_nocred=?, \n"
                            + "nro_timbrado=?,  obs_nocred=?, id_compra=?, id_usuario=?, id_estado=3, id_deposito=?\n"
                            + "WHERE id_notacrecompra=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, Dto.getNro_nocred());
                    preparedStatement.setObject(2, Dto.getNro_timbrado());
                    preparedStatement.setObject(3, Dto.getObs_nocred());
                    preparedStatement.setObject(4, Dto.getId_compra());
                    preparedStatement.setObject(5, Dto.getId_usuario());
                    preparedStatement.setObject(6, Dto.getId_deposito());
                    preparedStatement.setObject(7, Dto.getId_notacrecompra());

                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                        return true;
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                        return false;
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(NotaCreComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);

                }

                break;
        }
        return false;
    }

    @Override
    public boolean insertardetalleNotaCreCompras(NotaCreComprasDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detnotacrecompras(id_notacrecompra, id_articulo,\n"
                    + "cantidad_detnocre, montouni_detnocre)\n"
                    + "VALUES (?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                   preparedStatement.setObject(1, Dto.getId_notacrecompra());
            preparedStatement.setObject(2, Dto.getId_articulo());
            preparedStatement.setObject(3, Dto.getCantidad_detnocre());
            preparedStatement.setObject(4, Dto.getPreciouni_detnocre());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {

                conexion.comit();
                System.out.println("DETALLE GAURDARO");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaCreComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deletedealleNotaCreCompras(NotaCreComprasDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM detnotacrecompras\n"
                    + " WHERE id_notacrecompra=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_notacrecompra());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("DETALLE GAURDARO");
                return true;

            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaCreComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean actualizarestado(NotaCreComprasDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE public.notacrecompras\n"
                    + "   SET  id_estado=?\n"
                    + " WHERE id_notacrecompra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_estado());
            preparedStatement.setObject(2, Dto.getId_notacrecompra());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("DETALLE GAURDARO");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaCreComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getdetalleNotaCreCompras(Integer codNotaCreCompras) {
        ResultSet rs;
        ArrayList<NotaCreComprasDTO> alldetalleNotaCreCompras = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT nc.id_notacrecompra,fc.co_nrofact,nc.id_deposito, dnc.id_detallenc, fc.id_compra, nc.fecha_nocred::date, nc.nro_nocred, nc.nro_timbrado, nc.obs_nocred,\n" +
"                     nc.id_usuario, nc.id_estado ,\n" +
"                    dnc.id_articulo, (a.art_descripcion) as articulo, \n" +
"                    dnc.cantidad_detnocre, dnc.montouni_detnocre,dnc.id_notacrecompra   \n" +
"                     FROM notacrecompras nc\n" +
"                    inner join facturascompras fc on nc.id_compra=fc.id_compra\n" +
"                    inner join detnotacrecompras dnc on nc.id_notacrecompra= dnc.id_notacrecompra\n" +
"                     inner join articulos a on dnc.id_articulo= a.id_articulo\n" +
"                    where nc.id_notacrecompra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, codNotaCreCompras);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetalleNotaCreCompras.add(new NotaCreComprasDTO(
                        rs.getInt("id_notacrecompra"),
                        rs.getInt("co_nrofact"),
                        rs.getInt("id_compra"),
                        rs.getString("fecha_nocred"),
                        rs.getInt("nro_nocred"),
                        rs.getInt("nro_timbrado"),
                        rs.getString("obs_nocred"),
                        rs.getInt("id_articulo"),
                        rs.getString("articulo"),
                        rs.getInt("montouni_detnocre"),
                        rs.getInt("id_detallenc"),
                        rs.getInt("id_deposito"),
                        rs.getInt("cantidad_detnocre")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(NotaCreComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldetalleNotaCreCompras);
    }

    @Override
    public String getNotaCreCompras() {
        ResultSet rs;
        ArrayList<NotaCreComprasDTO> allNotaCreCompras = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT nc.id_notacrecompra, nc.fecha_nocred::date, nc.nro_nocred, nc.nro_timbrado, nc.obs_nocred,(e.est_descripcion)as estado,\n"
                    + " (u.usu_nombre)as usuario\n"
                    + " FROM notacrecompras nc\n"
                    + " INNER JOIN estados e on nc.id_estado=e.id_estado\n"
                    + " INNER JOIN usuarios u on nc.id_usuario = u.id_usuario"
                    + " where nc.id_estado in(1,3)\n"
                    + " order by nc.id_notacrecompra desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allNotaCreCompras.add(new NotaCreComprasDTO(
                        rs.getInt("id_notacrecompra"),
                        rs.getString("fecha_nocred"),
                        rs.getInt("nro_nocred"),
                        rs.getInt("nro_timbrado"),
                        rs.getString("obs_nocred"),
                        rs.getString("estado"),
                        rs.getString("usuario")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaCreComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allNotaCreCompras);
    }

    @Override
    public String getdetalleComprasNC(Integer nrofactura) {
        ResultSet rs;
        ArrayList<NotaCreComprasDTO> alldetallecnc = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = " SELECT f.id_compra,f.id_deposito, f.id_estado, f.co_nrofact, f.co_intervalo, \n"
                    + " d.id_articulo, (select n.id_compra from notacrecompras n left join facturascompras f on \n"
                    + "n.id_compra=f.id_compra where f.co_nrofact=? and n.id_estado in(1,3)) as nrofact,\n"
                    + " d.cantidad_detcomp, d.precio_detcomp, a.codigenerico, a.art_descripcion\n"
                    + "FROM facturascompras f\n"
                    + "inner join proveedores p on f.id_proveedor = p.id_proveedor\n"
                    + "inner join usuarios u on f.id_usuario = u.id_usuario\n"
                    + "inner join estados e on f.id_estado = e.id_estado\n"
                    + "inner join detfacturascompras d on f.id_compra = d.id_compra\n"
                    + "inner join articulos a on d.id_articulo = a.id_articulo\n"
                    + "where f.co_nrofact=? and f.id_estado in(1,3)";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, nrofactura);
            preparedStatement.setInt(2, nrofactura);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetallecnc.add(new NotaCreComprasDTO(
                        rs.getInt("id_compra"),
                        rs.getInt("id_estado"),
                        rs.getInt("nrofact"),
                        rs.getInt("id_deposito"),
                        rs.getInt("id_articulo"),
                        rs.getString("art_descripcion"),
                        rs.getInt("precio_detcomp"),
                        rs.getInt("cantidad_detcomp")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(NotaCreComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldetallecnc);
    }
}
