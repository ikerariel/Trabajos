/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.notaremisiondao;
import DTO.estadodto;
import DTO.facturacompradto;
import DTO.notaremisiondto;
import DTO.proveedoresdto;
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
public class notaremisiondaoimple implements notaremisiondao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public Integer getUltimoCodigoNotaRemi1() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(idnotaremi),0 )+ 1 as codigoRemi  FROM nota_remision;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigoRemi");
            }
        } catch (SQLException ex) {
            Logger.getLogger(notaremisiondaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String listarEstadoNotaRemi2() {
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
            Logger.getLogger(notaremisiondaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEstad);
    }

    @Override
    public String listarUsuarioNotaRemi3() {
        ResultSet rs;
        ArrayList<usuariosdto> allUsua = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idusuario, usu_nombre, usu_clave, idfuncionario,\n"
                    + " idperfil, idsucursal FROM usuarios where idusuario=1;";
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
            Logger.getLogger(notaremisiondaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allUsua);
    }

    @Override
    public String listarProveedorNotaRemi4() {
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
            Logger.getLogger(notaremisiondaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allProvee);
    }

    @Override
    public String listarfacturaRemi5() {
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
                    + "where e.idestado = 2";
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
    public String listarDetalleFacturaRemi6(Integer id) {
        ResultSet rs;
        ArrayList<facturacompradto> alldetalleFactura = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT f.idcompra,f.id_prov,f.tipo_codigo, f.comp_cantcuota, f.comp_monto, f.comp_nrofact, f.comp_intervalo, \n"
                    + "				f.comp_fecha, t.tipo_descri, p.prov_nombre, u.usu_nombre, e.descri_estado,\n"
                    + "				o.ordenc_nro, d.idmercaderia, d.detfact_cantidad, d.detfact_precio, m.codigogenerico,\n"
                    + "				m.mer_descripcion, (select idcompra from nota_remision where idcompra=?) as nrocompra "
                    + "                         FROM factura_compra f\n"
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
            preparedStatement.setInt(2, id);
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
                        rs.getInt("nrocompra"),
                        rs.getString("mer_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(notaremisiondaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldetalleFactura);
    }

    @Override
    public String listarMercaderiaNotaRemi7() {
        ResultSet rs;
        ArrayList<notaremisiondto> allMercaderia = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "Select *from mercaderias";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allMercaderia.add(new notaremisiondto(rs.getInt("idmercaderia"),
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
    public boolean insertarCabeceraNotaRemi8(notaremisiondto dto, Integer cod) {
        switch (cod) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO nota_remision(observacionremi, fecharemi, \n"
                            + "idestado, idusuario, idcompra) VALUES (?, ?::date, ?, ?, ?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getObservacionremi());
                    preparedStatement.setObject(2, dto.getFecharemi());
                    preparedStatement.setObject(3, dto.getIdestado());
                    preparedStatement.setObject(4, dto.getIdusuario());
                    preparedStatement.setObject(5, dto.getIdcompra());
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
                    Logger.getLogger(notaremisiondaoimple.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.nota_remision\n"
                            + "   SET  observacionremi=?, fecharemi=?::date, idestado=1, \n"
                            + "       idusuario=?, idcompra=?\n"
                            + " WHERE idnotaremi=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getObservacionremi());
                    preparedStatement.setObject(2, dto.getFecharemi());
                    preparedStatement.setObject(3, dto.getIdusuario());
                    preparedStatement.setObject(4, dto.getIdcompra());
                    preparedStatement.setObject(5, dto.getIdnotaremi());
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
                    Logger.getLogger(notaremisiondaoimple.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }

        return false;
    }

    @Override
    public boolean insertarDetalleNotaRemi9(notaremisiondto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detnota_remi(idnotaremi, idmercaderia, cantidadremi) VALUES (?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdnotaremiRE());
            preparedStatement.setObject(2, dto.getIdmercaderia());
            preparedStatement.setObject(3, dto.getCantidadremi());
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
            Logger.getLogger(notaremisiondaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String listarNotaRemi10() {
        ResultSet rs;
        ArrayList<notaremisiondto> allNotacre = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT r.idnotaremi, r.observacionremi, r.fecharemi, p.prov_nombre, e.descri_estado, \n"
                    + "	u.usu_nombre, c.comp_nrofact FROM nota_remision r\n"
                    + "	left join usuarios u on r.idusuario = u.idusuario\n"
                    + "	left join proveedores p on r.id_prov = p.id_prov\n"
                    + "	left join factura_compra c on r.idcompra = c.idcompra\n"
                    + "	left join estado e on r.idestado = e.idestado where r.idestado in(1,2) order by idnotaremi desc;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allNotacre.add(new notaremisiondto(rs.getInt("idnotaremi"),
                        rs.getString("observacionremi"),
                        rs.getString("fecharemi"),
                        rs.getString("prov_nombre"),
                        rs.getString("descri_estado"),
                        rs.getString("usu_nombre"),
                        rs.getString("comp_nrofact")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(notaremisiondaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allNotacre);
    }

    @Override
    public boolean confirmarNotaRemi11(notaremisiondto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE nota_remision SET idestado=? WHERE idnotaremi=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdestado());
            preparedStatement.setObject(2, dto.getIdnotaremi());
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
            Logger.getLogger(notaremisiondaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String listarDetalleNotaRemi12(Integer id) {
        ResultSet rs;
        ArrayList<notaremisiondto> alldetalleNota = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT r.idnotaremi,r.idcompra, r.observacionremi, r.fecharemi, p.prov_nombre, \n"
                    + "e.descri_estado, u.usu_nombre, c.comp_nrofact, d.idmercaderia, \n"
                    + "d.cantidadremi, m.codigogenerico, m.mer_descripcion FROM nota_remision r\n"
                    + "left join proveedores p on r.id_prov = p.id_prov\n"
                    + "left join estado e on r.idestado = e.idestado\n"
                    + "left join usuarios u on r.idusuario = u.idusuario\n"
                    + "left join factura_compra c on r.idcompra = c.idcompra\n"
                    + "left join detnota_remi d on r.idnotaremi = d.idnotaremi\n"
                    + "left join mercaderias m on d.idmercaderia = m.idmercaderia\n"
                    + "where r.idnotaremi=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetalleNota.add(new notaremisiondto(
                        rs.getInt("idnotaremi"),
                        rs.getString("observacionremi"),
                        rs.getString("fecharemi"),
                        rs.getString("prov_nombre"),
                        rs.getString("descri_estado"),
                        rs.getString("usu_nombre"),
                        rs.getString("comp_nrofact"),
                        rs.getInt("idmercaderia"),
                        rs.getString("cantidadremi"),
                        rs.getString("codigogenerico"),
                        rs.getInt("idcompra"),
                        rs.getString("mer_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(notaremisiondaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldetalleNota);
    }

    @Override
    public boolean deleteNotaremision(notaremisiondto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM public.detnota_remi\n"
                    + " WHERE idnotaremi=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdnotaremi());
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
            Logger.getLogger(notaremisiondaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
