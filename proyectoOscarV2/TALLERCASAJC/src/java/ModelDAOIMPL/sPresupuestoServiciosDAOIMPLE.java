/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.sPresupuestoServiciosDAO;
import ModelDTO.sPresupuestoServiciosDTO;
import ModelDTO.sRecepcionDTO;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class sPresupuestoServiciosDAOIMPLE implements sPresupuestoServiciosDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarpresupuestoservicios(sPresupuestoServiciosDTO psDTO, Integer cod) {
        switch (cod) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO public.presupuestosservicios(\n"
                            + "            id_estado, id_diagnostico, id_usuario,observacion,id_condicionpago)\n"
                            + "    VALUES (3, ?, ?,?,?);";

                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, psDTO.getId_diagnostico());
                    preparedStatement.setObject(2, psDTO.getId_usuario());
                    preparedStatement.setObject(3, psDTO.getDiganostico());
                    preparedStatement.setObject(4, psDTO.getId_condicionpago());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(sPresupuestoServiciosDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.presupuestosservicios\n"
                            + "   SET  id_estado=3, id_diagnostico=?, id_usuario=?,observacion=?,id_condicionpago=?, \n"
                            + "       fecha=now()\n"
                            + " WHERE id_presuserv=?";

                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, psDTO.getId_diagnostico());
                    preparedStatement.setObject(2, psDTO.getId_usuario());
                    preparedStatement.setObject(3, psDTO.getDiganostico());
                    preparedStatement.setObject(4, psDTO.getId_condicionpago());
                    preparedStatement.setObject(5, psDTO.getId_presuserv());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(sPresupuestoServiciosDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }

        return false;
    }

    @Override
    public boolean insertarDetallePServicios(sPresupuestoServiciosDTO psDTO, Integer vcod) {
        switch (vcod) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO public.detpresupuestosservicios(\n"
                            + "            id_presuserv, id_articulo, precio, cantidad)\n"
                            + "    VALUES ((select id_presuserv from presupuestosservicios order by id_presuserv desc limit 1), ?, ?, ?);";

                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, psDTO.getId_articulo());
                    preparedStatement.setObject(2, psDTO.getPrecio());
                    preparedStatement.setObject(3, psDTO.getCantidad());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(sPresupuestoServiciosDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO public.detpresupuestosservicios(\n"
                            + "            id_presuserv, id_articulo, precio, cantidad)\n"
                            + "    VALUES (?, ?, ?, ?);";

                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, psDTO.getId_presuserv());
                    preparedStatement.setObject(2, psDTO.getId_articulo());
                    preparedStatement.setObject(3, psDTO.getPrecio());
                    preparedStatement.setObject(4, psDTO.getCantidad());

                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(sPresupuestoServiciosDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }

        return false;
    }

    @Override
    public boolean deleteDetallePServicio(sPresupuestoServiciosDTO psDTO) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM public.detpresupuestosservicios\n"
                    + " WHERE id_presuserv=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, psDTO.getId_presuserv());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(sPresupuestoServiciosDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public boolean actualizarEstadoPServicio(sPresupuestoServiciosDTO psDTO) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE public.presupuestosservicios SET  id_estado=?WHERE id_presuserv=?";

            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, psDTO.getId_estado());
            preparedStatement.setObject(2, psDTO.getId_presuserv());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(sPresupuestoServiciosDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getPServicio() {

        ResultSet rs;
        ArrayList<sPresupuestoServiciosDTO> allpservicio = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT p.id_presuserv, p.id_estado, p.id_diagnostico, p.id_usuario, p.fecha,\n"
                    + " (e.est_descripcion) as estado,\n"
                    + "(c.ruc||' / '||c.razonsocial) as cliente, (u.usu_nombre)as usuario\n"
                    + "  FROM public.presupuestosservicios p\n"
                    + "  left join diagnosticos d on p.id_diagnostico=d.id_diagnostico\n"
                    + "  left join recepciones	r on d.id_recepcion=r.id_recepcion\n"
                    + "  left join clientes c on r.id_cliente=c.id_cliente\n"
                    + "  left join estados e on p.id_estado = e.id_estado\n"
                    + " left join usuarios u on p.id_usuario=u.id_usuario\n"
                    + "  where p.id_estado in(1,3) order by p.id_presuserv desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allpservicio.add(new sPresupuestoServiciosDTO(rs.getInt("id_presuserv"),
                        rs.getString("fecha"),
                        rs.getString("cliente"),
                        rs.getString("estado"),
                        rs.getString("usuario"),
                        rs.getInt("id_estado")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(sPresupuestoServiciosDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allpservicio);
    }

    @Override
    public String getdetallePServicio(Integer codDiagnostico) {

        ResultSet rs;
        ArrayList<sPresupuestoServiciosDTO> alldetallePS = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT d.id_diagnostico, d.fecha::date, d.fecentreg,\n"
                    + "                    d.id_usuario, d.id_estado,(a.art_descripcion)  as articulo,r.id_cliente,\n"
                    + "                    (c.ruc||' / '||c.razonsocial) as cliente, \n"
                    + "                     d.diagnostico,dp.id_articulo, dp.cantidad,(a.art_descripcion) as articulo,\n"
                    + "                     (select id_diagnostico from presupuestosservicios where id_diagnostico = ?) as nrodiagnostico\n"
                    + "                      FROM public.diagnosticos d\n"
                    + "                      left join detdiagnosticos dp on d.id_diagnostico=dp.id_diagnostico\n"
                    + "                      left join articulos a on dp.id_articulo=a.id_articulo\n"
                    + "                      left join recepciones r on d.id_recepcion=r.id_recepcion\n"
                    + "                      left join clientes c on r.id_cliente=c.id_cliente\n"
                    + "                    where d.id_diagnostico=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, codDiagnostico);
            preparedStatement.setObject(2, codDiagnostico);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetallePS.add(new sPresupuestoServiciosDTO(rs.getInt("id_diagnostico"),
                        rs.getString("fecha"),
                        rs.getString("cliente"),
                        rs.getString("diagnostico"),
                        rs.getInt("id_articulo"),
                        rs.getInt("cantidad"),
                        rs.getString("articulo"),
                        rs.getInt("nrodiagnostico"),
                        rs.getInt("id_estado")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(sPresupuestoServiciosDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldetallePS);
    }

    @Override
    public String getdetallesPServicios(Integer codPServicio) {

        ResultSet rs;
        ArrayList<sPresupuestoServiciosDTO> allPserviciosDetlle = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT p.id_presuserv, p.id_estado, p.id_diagnostico, p.id_usuario, p.fecha::date,\n"
                    + "p.observacion, (d.fecha::date) as fechadiagnostico, pd.id_articulo, pd.precio, pd.cantidad,\n"
                    + " (a.art_descripcion)as articulo,\n"
                    + "(c.ruc||' / '||c.razonsocial) as cliente\n"
                    + "  FROM public.presupuestosservicios p\n"
                    + "  left join detpresupuestosservicios pd on p.id_presuserv=pd.id_presuserv\n"
                    + "  left join articulos a on pd.id_articulo=a.id_articulo\n"
                    + "left join diagnosticos d on p.id_diagnostico = d.id_diagnostico\n"
                    + "   left join recepciones r on d.id_recepcion=r.id_recepcion\n"
                    + "left join clientes c on r.id_cliente=c.id_cliente\n"
                    + "  where p.id_presuserv=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, codPServicio);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allPserviciosDetlle.add(new sPresupuestoServiciosDTO(rs.getInt("id_presuserv"),
                        rs.getString("fecha"),
                        rs.getInt("id_diagnostico"),
                        rs.getString("fechadiagnostico"),
                        rs.getString("cliente"),
                        rs.getString("observacion"),
                        rs.getInt("id_articulo"),
                        rs.getInt("precio"),
                        rs.getInt("cantidad"),
                        rs.getString("articulo")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(sPresupuestoServiciosDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allPserviciosDetlle);
    }

}
