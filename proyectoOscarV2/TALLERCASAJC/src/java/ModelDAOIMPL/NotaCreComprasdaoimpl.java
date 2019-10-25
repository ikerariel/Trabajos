/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.NotaCreComprasdao;
import ModelDTO.NotaCreComprasdto;
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
public class NotaCreComprasdaoimpl implements NotaCreComprasdao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public String getNotaCreCompras() {
        ResultSet rs;
        ArrayList<NotaCreComprasdto> allNotasC = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT nc.id_notacrecompra, nc.fecha_nocred::date, nc.id_estado, e.est_descripcion\n"
                    + " FROM notacrecompras nc\n"
                    + " inner join estados e on nc.id_estado = e.id_estado\n"
                    + " order by id_notacrecompra desc ";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allNotasC.add(new NotaCreComprasdto(
                        rs.getInt("id_notacrecompra"),
                        rs.getString("fecha_nocred"),
                        rs.getInt("id_estado"),
                        rs.getString("est_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaCreComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allNotasC);
    }

    @Override
    public String getDetNotaCreCompras(Integer id) {
        ResultSet rs;
        ArrayList<NotaCreComprasdto> allDetalleNC = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT nc.id_notacrecompra,nc.id_deposito, nc.fecha_nocred::date, nc.nro_nocred, nc.nro_timbrado, \n"
                    + "                     nc.obs_nocred, nc.id_compra, (f.co_nrofact) as factura, \n"
                    + "                     nc.id_usuario,(a.art_descripcion) as articulo, (u.usu_nombre) as usuarios, nc.id_estado, (e.est_descripcion) as estados,\n"
                    + "                     dnc. id_notacrecompra, dnc.id_articulo, dnc.cantidad_detnocre, dnc. montouni_detnocre        \n"
                    + "                     FROM notacrecompras nc\n"
                    + "                     INNER JOIN detnotacrecompras dnc on nc.id_notacrecompra = dnc.id_notacrecompra\n"
                    + "                      INNER JOIN articulos a on dnc.id_articulo=a.id_articulo\n"
                    + "                     INNER JOIN facturascompras f on nc.id_compra = f.id_compra\n"
                    + "                     INNER JOIN usuarios u on nc.id_usuario = u.id_usuario\n"
                    + "                    INNER JOIN estados e on nc.id_estado = e.id_estado\n"
                    + "                    where nc.id_notacrecompra = ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allDetalleNC.add(new NotaCreComprasdto(
                        rs.getInt("id_notacrecompra"),
                        rs.getString("fecha_nocred"),
                        rs.getInt("nro_nocred"),
                        rs.getInt("nro_timbrado"),
                        rs.getString("obs_nocred"),
                        rs.getInt("id_compra"),
                        rs.getInt("factura"),
                        rs.getInt("id_usuario"),
                        rs.getString("usuarios"),
                        rs.getInt("id_estado"),
                        rs.getString("estados"),
                        rs.getInt("id_articulo"),
                        rs.getInt("id_deposito"),
                        rs.getInt("cantidad_detnocre"),
                        rs.getString("articulo"),
                        rs.getInt("montouni_detnocre")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaCreComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allDetalleNC);
    }

    @Override
    public boolean insertarNC(NotaCreComprasdto Dto, Integer cod) {
        switch (cod) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO notacrecompras(nro_nocred,  nro_timbrado, \n"
                            + " obs_nocred, id_compra,  id_usuario, id_estado,id_deposito)\n"
                            + " VALUES (?, ?, ?, ?, ?, 3, );";
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
                        System.out.println("Comit() Realizado");
                        return true;
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                    }
                    conexion.desConectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(NotaCreComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE notacrecompras\n"
                            + " SET nro_nocred=?, nro_timbrado=?,obs_nocred=?,\n"
                            + " id_compra=?, id_usuario=?,  id_estado=3, id_deposito=?\n"
                            + " WHERE id_notacrecompra=?;";
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
                        System.out.println("Comit() Realizado");
                        return true;
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                    }
                    conexion.desConectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(NotaCreComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }

        return false;
    }

    @Override
    public boolean updateCabeceraNC(NotaCreComprasdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE notacrecompras\n"
                    + " SET nro_nocred=?, nro_timbrado=?,obs_nocred=?,\n"
                    + " id_compra=?, id_usuario=?,  id_estado=3\n"
                    + " WHERE id_notacrecompra=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getNro_nocred());
            preparedStatement.setObject(2, Dto.getNro_timbrado());
            preparedStatement.setObject(3, Dto.getObs_nocred());
            preparedStatement.setObject(4, Dto.getId_compra());
            preparedStatement.setObject(5, Dto.getId_usuario());
            preparedStatement.setObject(6, Dto.getId_notacrecompra());

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
            Logger.getLogger(NotaCreComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateNC(Integer _estadoNC, Integer _idNC) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE notacrecompras\n"
                    + " SET  id_estado=?\n"
                    + " WHERE id_notacrecompra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, _estadoNC);
            preparedStatement.setInt(2, _idNC);

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
            Logger.getLogger(NotaCreComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertarDetalleNC(NotaCreComprasdto Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detnotacrecompras(id_notacrecompra, id_articulo, cantidad_detnocre, montouni_detnocre)\n"
                    + "VALUES (?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_notacrecompra());
            preparedStatement.setObject(2, Dto.getId_articulo());
            preparedStatement.setObject(3, Dto.getCantidad_detnocre());
            preparedStatement.setObject(4, Dto.getMontouni_detnocre());

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
            Logger.getLogger(NotaCreComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertarDetllaNc(NotaCreComprasdto DTO) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detnotacrecompras(id_notacrecompra, id_articulo, cantidad_detnocre, montouni_detnocre)\n"
                    + " VALUES (?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, DTO.getId_notacrecompra());
            preparedStatement.setObject(2, DTO.getId_articulo());
            preparedStatement.setObject(3, DTO.getCantidad_detnocre());
            preparedStatement.setObject(4, DTO.getMontouni_detnocre());

            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaCreComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Integer getUltimoCodigo() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_notacrecompra),0 )+ 1 as codigo  FROM notacrecompras;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaCreComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    @Override
    public String getfactura(Integer facturaNRO) {
        ResultSet rs;
        ArrayList<NotaCreComprasdto> consultarfactura = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT f.id_compra,f.id_estado, df.id_articulo,f.id_deposito, df.cantidad_detcomp, df.precio_detcomp,(a.art_descripcion)as articulo,\n"
                    + "(select n.id_compra from notacrecompras n left join facturascompras f on n.id_compra=f.id_compra \n"
                    + " where f.co_nrofact=? and n.id_estado in(1,3)) as co_nrofact\n"
                    + "                     FROM facturascompras f\n"
                    + "                     left join detfacturascompras df on f.id_compra=df.id_compra\n"
                    + "			left join articulos a on df.id_articulo=a.id_articulo\n"
                    + "                     where f.co_nrofact=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, facturaNRO);
            preparedStatement.setInt(2, facturaNRO);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                consultarfactura.add(new NotaCreComprasdto(
                        rs.getInt("id_compra"),
                        rs.getInt("cantidad_detcomp"),
                        rs.getInt("precio_detcomp"),
                        rs.getString("articulo"),
                        rs.getInt("id_articulo"),
                        rs.getInt("id_deposito"),
                        rs.getInt("id_estado"),
                        rs.getInt("co_nrofact")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaCreComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(consultarfactura);
    }

    @Override
    public boolean deleteNc(NotaCreComprasdto DTO) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM public.detnotacrecompras\n"
                    + " WHERE id_notacrecompra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, DTO.getId_notacrecompra());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaCreComprasdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
