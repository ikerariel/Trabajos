/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.facturacompradao;
import DTO.estadodto;
import DTO.facturacompradto;
import DTO.ordencompradto;
import DTO.proveedoresdto;
import DTO.usuariosdto;
import Genericos.Conexion;
import com.google.gson.Gson;
import java.sql.CallableStatement;
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
public class facturacompradaoimple implements facturacompradao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public Integer getUltimoCodigoCompra() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(idcompra),0 )+ 1 as codigo  FROM factura_compra;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String ListarEstadoCompra2() {
        ResultSet rs;
        ArrayList<estadodto> allEstad = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idestado, descri_estado\n"
                    + "  FROM estado WHERE idestado=1;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allEstad.add(new estadodto(rs.getInt("idestado"),
                        rs.getString("descri_estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEstad);
    }

    @Override
    public String ListarUsuarioCompra3() {
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
            Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allUsua);
    }

    @Override
    public String ListarProveedorCompras4() {
        ResultSet rs;
        ArrayList<proveedoresdto> allProvee = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "Select *from proveedores";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allProvee.add(new proveedoresdto(rs.getInt("id_prov"),
                        rs.getString("prov_nombre"),
                        rs.getString("prov_direc"),
                        rs.getString("prov_imail"),
                        rs.getString("prov_telf"),
                        rs.getString("prov_ruc")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allProvee);
    }

    @Override
    public String ListarOrdenCompra5() {
        ResultSet rs;
        ArrayList<facturacompradto> allOrden = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT o.ordenc_nro, o.ordenc_fecha, u.usu_nombre,"
                    + "  e.descri_estado FROM orden_compra o\n"
                    + "  inner join usuarios u on o.idusuario=u.idusuario\n"
                    + "  inner join estado e on o.idestado=e.idestado where e.idestado=2";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allOrden.add(new facturacompradto(rs.getInt("ordenc_nro"),
                        rs.getString("ordenc_fecha"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allOrden);
    }

    @Override
    public String ListarDetalleOrdenC6(Integer id) {
        ResultSet rs;
        ArrayList<ordencompradto> alldetalleOrden = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT o.ordenc_fecha, o.id_prov, p.prov_nombre, u.usu_nombre, pc.pcomp_nro, e.descri_estado, d.idmercaderia,\n"
                    + "d.cant_orden, d.precio_orden, m.codigogenerico, m.mer_descripcion,"
                    + "   (select ordenc_nro from factura_compra where ordenc_nro = ?) as nroorden\n"
                    + "FROM orden_compra o\n"
                    + "inner join det_orden_compra d on o.ordenc_nro = d.ordenc_nro\n"
                    + "inner join proveedores p on o.id_prov = p.id_prov\n"
                    + "inner join usuarios u on o.idusuario = u.idusuario\n"
                    + "inner join pedido_compra pc on o.pcomp_nro = pc.pcomp_nro\n"
                    + "inner join estado e on o.idestado = e.idestado\n"
                    + "inner join mercaderias m on m.idmercaderia=d.idmercaderia\n"
                    + "where o.ordenc_nro=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetalleOrden.add(new ordencompradto(
                        rs.getString("ordenc_fecha"),
                        rs.getString("prov_nombre"),
                        rs.getString("usu_nombre"),
                        rs.getInt("pcomp_nro"),
                        rs.getString("descri_estado"),
                        rs.getInt("idmercaderia"),
                        rs.getInt("cant_orden"),
                        rs.getInt("precio_orden"),
                        rs.getString("codigogenerico"),
                        rs.getInt("id_prov"),
                        rs.getInt("nroorden"),
                        rs.getString("mer_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ordencompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Gson().toJson(alldetalleOrden);
    }

    @Override
    public String ListarSucursalCompra7() {
        ResultSet rs;
        ArrayList<facturacompradto> allEstad = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idsucursal, suc_descripcion, idciudad\n"
                    + "  FROM sucursales WHERE idsucursal=1;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allEstad.add(new facturacompradto(rs.getInt("idsucursal"),
                        rs.getString("suc_descripcion"),
                        rs.getInt("idciudad")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEstad);
    }

    @Override
    public String ListarMercaderiaCompra8() {
        ResultSet rs;
        ArrayList<facturacompradto> allMercaderia = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "Select *from mercaderias";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allMercaderia.add(new facturacompradto(rs.getInt("idmercaderia"),
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
            Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allMercaderia);
    }

    @Override
    public boolean insertarCabeceraCompra9(facturacompradto dto, Integer cod) {
        switch (cod) {
            case 1:

                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO factura_compra( comp_cantcuota, comp_monto, comp_nrofact, comp_intervalo, \n"
                            + "     id_prov, idusuario, idestado, tipo_codigo, ordenc_nro,iddeposito,comp_fecha)\n"
                            + "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?::date);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getComp_cantcuota());
                    preparedStatement.setObject(2, dto.getComp_monto());
                    preparedStatement.setObject(3, dto.getComp_nrofact());
                    preparedStatement.setObject(4, dto.getComp_intervalo());
                    preparedStatement.setObject(5, dto.getId_prov());
                    preparedStatement.setObject(6, dto.getIdusuario());
                    preparedStatement.setObject(7, dto.getIdestado());
                    preparedStatement.setObject(8, dto.getTipo_codigo());
                    preparedStatement.setObject(9, dto.getOrdenc_nro());
                    preparedStatement.setObject(10, dto.getIddeposito());
                       preparedStatement.setObject(11, dto.getComp_fecha());
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
                    Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.factura_compra\n"
                            + "   SET comp_cantcuota=?, comp_monto=?, comp_nrofact=?, comp_intervalo=?, \n"
                            + "        id_prov=?, idusuario=?, idestado=1, tipo_codigo=?, \n"
                            + "       ordenc_nro=?,iddeposito=?,comp_fecha=?\n"
                            + " WHERE idcompra=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getComp_cantcuota());
                    preparedStatement.setObject(2, dto.getComp_monto());
                    preparedStatement.setObject(3, dto.getComp_nrofact());
                    preparedStatement.setObject(4, dto.getComp_intervalo());
                    preparedStatement.setObject(5, dto.getId_prov());
                    preparedStatement.setObject(6, dto.getIdusuario());
                    preparedStatement.setObject(7, dto.getTipo_codigo());
                    preparedStatement.setObject(8, dto.getOrdenc_nro());
                    preparedStatement.setObject(9, dto.getIddeposito());
                      preparedStatement.setObject(10, dto.getComp_fecha());
                    preparedStatement.setObject(11, dto.getIdcompra());
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
                    Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }

        return false;
    }

    @Override
    public boolean insertarDetalleCompra10(facturacompradto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO det_factura_compra(idcompra, idmercaderia, detfact_cantidad, detfact_precio,idimpuesto)\n"
                    + "    VALUES (?, ?, ?, ?,?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdcompraD());
            preparedStatement.setObject(2, dto.getIdmercaderia());
            preparedStatement.setObject(3, dto.getDetfact_cantidad());
            preparedStatement.setObject(4, dto.getDetfact_precio());
            preparedStatement.setObject(5, dto.getIdimpuesto());
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
            Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String ListarFacturaCompra11() {
        ResultSet rs;
        ArrayList<facturacompradto> allFacturaC = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT f.idcompra, f.comp_nrofact, f.comp_fecha, t.tipo_descri,\n"
                    + "p.prov_nombre, u.usu_nombre, e.descri_estado\n"
                    + "FROM factura_compra f\n"
                    + "inner join tipo_documentos t on f.tipo_codigo = t.tipo_codigo\n"
                    + "inner join proveedores p on f.id_prov = p.id_prov\n"
                    + "inner join usuarios u on f.idusuario = u.idusuario\n"
                    + "inner join estado e on f.idestado = e.idestado\n"
                    + "where f.idestado in(1,2)\n"
                    + "order by f.idcompra desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allFacturaC.add(new facturacompradto(rs.getInt("idcompra"),
                        rs.getString("comp_nrofact"),
                        rs.getString("comp_fecha"),
                        rs.getString("tipo_descri"),
                        rs.getString("prov_nombre"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allFacturaC);
    }

    @Override
    public boolean confirmarFacturaCompra12(facturacompradto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE factura_compra SET idestado=? WHERE idcompra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdestado());
            preparedStatement.setObject(2, dto.getIdcompra());
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
            Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String listarDetalleCompra13(Integer id) {
        ResultSet rs;
        ArrayList<facturacompradto> alldetalleFactura = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT f.idcompra,f.id_prov,f.tipo_codigo, f.comp_cantcuota, f.comp_monto, f.comp_nrofact, f.comp_intervalo, \n"
                    + "				f.comp_fecha, t.tipo_descri, p.prov_nombre, u.usu_nombre, e.descri_estado,\n"
                    + "				o.ordenc_nro, d.idmercaderia,d.idimpuesto, d.detfact_cantidad, d.detfact_precio, m.codigogenerico,\n"
                    + "				m.mer_descripcion FROM factura_compra f\n"
                    + "				left join proveedores p on f.id_prov = p.id_prov\n"
                    + "				left join tipo_documentos t on f.tipo_codigo = t.tipo_codigo\n"
                    + "				left join usuarios u on f.idusuario = u.idusuario\n"
                    + "				left join estado e on f.idestado = e.idestado\n"
                    + "				left join orden_compra o on f.ordenc_nro = o.ordenc_nro\n"
                    + "				left join det_factura_compra d on f.idcompra = d.idcompra\n"
                    + "				left join mercaderias m on d.idmercaderia = m.idmercaderia\n"
                    + "				where f.idcompra=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetalleFactura.add(new facturacompradto(
                        rs.getInt("idcompra"),
                        rs.getInt("comp_cantcuota"),
                        rs.getInt("comp_monto"),
                        rs.getString("comp_nrofact"),
                        rs.getString("comp_intervalo"),
                        rs.getString("comp_fecha"),
                        rs.getString("tipo_descri"),
                        rs.getString("prov_nombre"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado"),
                        rs.getInt("ordenc_nro"),
                        rs.getInt("idmercaderia"),
                        rs.getInt("detfact_cantidad"),
                        rs.getInt("detfact_precio"),
                        rs.getString("codigogenerico"),
                        rs.getInt("id_prov"),
                        rs.getInt("tipo_codigo"),
                        rs.getInt("idimpuesto"),
                        rs.getString("mer_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Gson().toJson(alldetalleFactura);
    }

    @Override
    public String ListarTipoCompras14() {
        ResultSet rs;
        ArrayList<facturacompradto> allTipoC = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT tipo_codigo, tipo_descri FROM tipo_documentos;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allTipoC.add(new facturacompradto(rs.getInt("tipo_codigo"),
                        rs.getString("tipo_descri")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allTipoC);
    }

    @Override
    public boolean deletfacturaCompra(facturacompradto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM public.det_factura_compra WHERE idcompra=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdcompra());
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
            Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean generarCtasapagar(facturacompradto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            CallableStatement call = conexion.getConexion().prepareCall("{call sp_generarctaspagar(?)}");
            call.setInt(1, dto.getIdcompra());
            call.execute();
            conexion.comit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
}
