/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.NotaRemisionDAO;
import ModelDTO.Articulosdto;
import ModelDTO.Estadosdto;
import ModelDTO.FacturasComprasdto;
import ModelDTO.NotaRemisionDTO;
import ModelDTO.Proveedoresdto;
import ModelDTO.Usuariosdto;
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
public class NotaRemisionDAOIMPL implements NotaRemisionDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public Integer getUltimoCodigoNotaRemision1() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_notaremi),0 )+ 1 as codigo  FROM notaremision";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String ListarEstadosNotaRemision2() {
        ResultSet rs;
        ArrayList<Estadosdto> allEstados = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_estado, est_descripcion\n"
                    + "  FROM estados WHERE id_estado=1;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allEstados.add(new Estadosdto(
                        rs.getInt("id_estado"),
                        rs.getString("est_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEstados);
    }

    @Override
    public String ListarUsuariosNotaRemision3() {
        ResultSet rs;
        ArrayList<Usuariosdto> allUsuarios = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_usuario, usu_nombre, usu_clave, id_empleado, id_sucursal, id_perfil\n"
                    + " FROM usuarios where id_usuario=3";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allUsuarios.add(new Usuariosdto(
                        rs.getInt("id_usuario"),
                        rs.getString("usu_nombre"),
                        rs.getString("usu_clave"),
                        rs.getInt("id_empleado"),
                        rs.getInt("id_sucursal"),
                        rs.getInt("id_perfil")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allUsuarios);
    }

    @Override
    public String ListarProveedoresNotaRemision4() {
        ResultSet rs;
        ArrayList<Proveedoresdto> allProveedores = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "Select *from proveedores";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allProveedores.add(new Proveedoresdto(
                        rs.getInt("id_proveedor"),
                        rs.getString("ras_social"),
                        rs.getString("direccion"),
                        rs.getString("pag_web"),
                        rs.getString("telefono"),
                        rs.getString("ruc"),
                        rs.getInt("id_ciudad")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allProveedores);
    }

    @Override
    public String ListarFacturasComprasNotaRemision5() {
        ResultSet rs;
        ArrayList<NotaRemisionDTO> allNotaRemision = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT f.id_compra, f.co_nrofact, \n"
                    + " p.ras_social, s.suc_descripcion, u.usu_nombre, e.est_descripcion\n"
                    + " FROM facturascompras f \n"
                    + " inner join proveedores p on f.id_proveedor = p.id_proveedor\n"
                    + " inner join sucursales s on f.id_sucursal = s.id_sucursal\n"
                    + " inner join usuarios u on f.id_usuario = u.id_usuario\n"
                    + " inner join estados e on f.id_estado = e.id_estado\n"
                    + " order by id_compra desc;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allNotaRemision.add(new NotaRemisionDTO(
                        rs.getInt("id_compra"),
                        rs.getInt("co_nrofact"),
                        rs.getString("ras_social"),
                        rs.getString("suc_descripcion"),
                        rs.getString("usu_nombre"),
                        rs.getString("est_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allNotaRemision);
    }

    @Override
    public String ListarDetFacturasComprasRemision6(Integer id) {
        ResultSet rs;
        ArrayList<NotaRemisionDTO> allDetNotaRemision = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();

            sintaxiSql = " SELECT f.id_compra,  f.co_nrofact, f.co_intervalo,  f.id_proveedor,\n"
                    + "                     p.ras_social, f.id_sucursal, s.suc_descripcion, f.id_usuario, u.usu_nombre, e.est_descripcion,  \n"
                    + "                     d.id_articulo, d.cantidad_detcomp, d.precio_detcomp, a.codigenerico, a.art_descripcion\n"
                    + "                    FROM facturascompras f\n"
                    + "                     inner join proveedores p on f.id_proveedor = p.id_proveedor\n"
                    + "                     inner join sucursales s on f.id_sucursal = s.id_sucursal\n"
                    + "                     inner join usuarios u on f.id_usuario = u.id_usuario\n"
                    + "                    inner join estados e on f.id_estado = e.id_estado\n"
                    + "                     inner join detfacturascompras d on f.id_compra = d.id__compra\n"
                    + "                    inner join articulos a on d.id_articulo = a.id_articulo\n"
                    + "                     where f.id_compra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allDetNotaRemision.add(new NotaRemisionDTO(
                        rs.getInt("id_compra"),
                        rs.getInt("co_nrofact"),
                        rs.getInt("id_proveedor"),
                        rs.getString("ras_social"),
                        rs.getInt("id_sucursal"),
                        rs.getString("suc_descripcion"),
                        rs.getInt("id_usuario"),
                        rs.getString("usu_nombre"),
                        rs.getString("est_descripcion"),
                        rs.getInt("id_articulo"),
                        rs.getInt("cantidad_detcomp"),
                        rs.getInt("precio_detcomp"),
                        rs.getString("codigenerico"),
                        rs.getString("art_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allDetNotaRemision);
    }

    @Override
    public String ListarSucursalesNotaRemision7() {
        ResultSet rs;
        ArrayList<FacturasComprasdto> allSucursales = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_sucursal, suc_descripcion\n"
                    + "FROM sucursales WHERE id_sucursal=1;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allSucursales.add(new FacturasComprasdto(
                        rs.getInt("id_sucursal"),
                        rs.getString("suc_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allSucursales);
    }

    @Override
    public String ListarArticulosNotaRemision8() {
        ResultSet rs;
        ArrayList<Articulosdto> allArticulos = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_articulo, art_descripcion, preccompras, precventas, id_impuesto, id_marca, codigenerico\n"
                    + "FROM articulos;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allArticulos.add(new Articulosdto(
                        rs.getInt("id_articulo"),
                        rs.getString("art_descripcion"),
                        rs.getInt("preccompras"),
                        rs.getInt("precventas"),
                        rs.getInt("id_impuesto"),
                        rs.getInt("id_marca"),
                        rs.getString("codigenerico")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allArticulos);
    }

    @Override
    public boolean insertarCabeceraNotaRemision9(NotaRemisionDTO dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO notaremision( fecha_notaremi, nro_notaremi, obser_notaremi, \n"
                    + " id_estado, id_usuario, id_proveedor, id_sucursal, id_compra)\n"
                    + " VALUES ( ?::date, ?, ?, ?,  ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getFecha_notaremi());
            preparedStatement.setObject(2, dto.getNro_notaremi());
            preparedStatement.setObject(3, dto.getObser_notaremi());
            preparedStatement.setObject(4, dto.getId_estado());
            preparedStatement.setObject(5, dto.getId_usuario());
            preparedStatement.setObject(6, dto.getId_proveedor());
            preparedStatement.setObject(7, dto.getId_sucursal());
            preparedStatement.setObject(8, dto.getId_compra());
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
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificarCabeceraNotaRemision10(NotaRemisionDTO dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE notaremision\n"
                    + "SET fecha_notaremi=?, nro_notaremi=?, obser_notaremi=?, \n"
                    + "id_estado=?, id_usuario=?, id_proveedor=?, id_sucursal=?, id_compra=?\n"
                    + " WHERE id_notaremi=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getFecha_notaremi());
            preparedStatement.setObject(2, dto.getNro_notaremi());
            preparedStatement.setObject(3, dto.getObser_notaremi());
            preparedStatement.setObject(4, dto.getId_estado());
            preparedStatement.setObject(5, dto.getId_usuario());
            preparedStatement.setObject(6, dto.getId_proveedor());
            preparedStatement.setObject(7, dto.getId_sucursal());
            preparedStatement.setObject(8, dto.getId_compra());
            preparedStatement.setObject(9, dto.getId_notaremi());
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
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertarDetNotaRemision11(NotaRemisionDTO dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detnoremision(id_notaremi, id_articulo, cantinotaremi, precionotaremi)\n"
                    + " VALUES (?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_notaremiD());
            preparedStatement.setObject(2, dto.getId_articulo());
            preparedStatement.setObject(3, dto.getCantinotaremi());
            preparedStatement.setObject(4, dto.getPrecionotaremi());
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
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String ListarNotaRemision12() {
        ResultSet rs;
        ArrayList<NotaRemisionDTO> allNotaRemision = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT r.id_notaremi, r.fecha_notaremi, r.nro_notaremi, r.obser_notaremi, \n"
                    + "  e.est_descripcion, u.usu_nombre, p.ras_social, s.suc_descripcion, f.co_nrofact\n"
                    + "  FROM notaremision r\n"
                    + "  inner join estados e on r.id_estado = e.id_estado\n"
                    + "  inner join usuarios u on r.id_usuario = u.id_usuario\n"
                    + "  inner join proveedores p on r.id_proveedor = p.id_proveedor\n"
                    + "  inner join sucursales s on r.id_sucursal = s.id_sucursal\n"
                    + "  inner join facturascompras f on r.id_compra = f.id_compra\n"
                    + "  order by id_notaremi desc;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allNotaRemision.add(new NotaRemisionDTO(
                        rs.getInt("id_notaremi"),
                        rs.getString("fecha_notaremi"),
                        rs.getInt("nro_notaremi"),
                        rs.getString("obser_notaremi"),
                        rs.getString("est_descripcion"),
                        rs.getString("usu_nombre"),
                        rs.getString("ras_social"),
                        rs.getString("suc_descripcion"),
                        rs.getInt("co_nrofact")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allNotaRemision);
    }

    @Override
    public boolean confirmarNotaRemision13(NotaRemisionDTO dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE notaremision SET id_estado=? WHERE id_notaremi=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_estado());
            preparedStatement.setObject(2, dto.getId_notaremi());
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
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String listarDetNotaRemision14(Integer id) {
        ResultSet rs;
        ArrayList<NotaRemisionDTO> allDetNotaRemision = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT r.id_notaremi, r.fecha_notaremi, r.nro_notaremi, r.obser_notaremi,\n"
                    + " r.id_estado, e.est_descripcion, r.id_usuario, u.usu_nombre, r.id_proveedor, p.ras_social, r.id_sucursal, s.suc_descripcion,\n"
                    + " r.id_compra, d.id_articulo, d.cantinotaremi, d.precionotaremi, a.codigenerico, a.art_descripcion \n"
                    + " FROM notaremision r\n"
                    + " inner join estados e on r.id_estado = e.id_estado\n"
                    + " inner join usuarios u on r.id_usuario = u.id_usuario\n"
                    + " inner join proveedores p on r.id_proveedor = p.id_proveedor\n"
                    + " inner join sucursales s on r.id_sucursal = s.id_sucursal\n"
                    + " inner join facturascompras f on r.id_compra = f.id_compra\n"
                    + " inner join detnoremision d on r.id_notaremi = d.id_notaremi\n"
                    + " inner join articulos a on d.id_articulo = a.id_articulo\n"
                    + " where r.id_notaremi=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allDetNotaRemision.add(new NotaRemisionDTO(
                        rs.getInt("id_notaremi"),
                        rs.getString("fecha_notaremi"),
                        rs.getInt("nro_notaremi"),
                        rs.getString("obser_notaremi"),
                        rs.getString("est_descripcion"),
                         rs.getInt("id_usuario"),
                        rs.getString("usu_nombre"),
                        rs.getInt("id_proveedor"),
                        rs.getString("ras_social"),
                        rs.getInt("id_sucursal"),
                        rs.getString("suc_descripcion"),
                        
                        rs.getInt("id_compra"),
                        
                        rs.getInt("id_articulo"),
                        rs.getInt("cantinotaremi"),
                        rs.getInt("precionotaremi"),
                        rs.getString("codigenerico"),
                        rs.getString("art_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaRemisionDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Gson().toJson(allDetNotaRemision);
    }

}
