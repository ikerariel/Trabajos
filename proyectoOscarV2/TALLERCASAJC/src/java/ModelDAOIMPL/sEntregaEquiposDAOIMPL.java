/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.sEntregaEquiposDAO;
import ModelDTO.sEntregaEquiposDTO;
import ModelDTO.sOrdenTrabajoDTO;
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
public class sEntregaEquiposDAOIMPL implements sEntregaEquiposDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarEntrgaEquipos(sEntregaEquiposDTO DTO, Integer cod) {
        switch (cod) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO public.entrega_equpos(\n"
                            + "           id_cliente, id_estado, id_usuario, \n"
                            + "            observacion,id_ordenttabajo)\n"
                            + "    VALUES (?, 3, ?, ?,?)";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, DTO.getId_cliente());
                    preparedStatement.setObject(2, DTO.getId_usuario());
                    preparedStatement.setObject(3, DTO.getObservacion());
                    preparedStatement.setObject(4, DTO.getId_ordenttabajo());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(sEntregaEquiposDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.entrega_equpos\n"
                            + "   SET fecha_entrega=now(), id_cliente=?, id_estado=3, \n"
                            + "       id_usuario=?, observacion=?, id_ordenttabajo=?\n"
                            + " WHERE  id_entregaequipos=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, DTO.getId_cliente());
                    preparedStatement.setObject(2, DTO.getId_usuario());
                    preparedStatement.setObject(3, DTO.getObservacion());
                    preparedStatement.setObject(4, DTO.getId_ordenttabajo());
                    preparedStatement.setObject(5, DTO.getId_entregaequipos());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(sEntregaEquiposDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }

        return false;

    }

    @Override
    public boolean insertarDetalleEntrgaEquipos(sEntregaEquiposDTO DTO, Integer cod) {
        switch (cod) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO public.detentregaequipos(\n"
                            + "          id_articulo, cantidad,id_entregaequipos)\n"
                            + "    VALUES (?, ?, "
                            + "(select id_entregaequipos from entrega_equpos order by id_entregaequipos desc limit 1));";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, DTO.getId_articulo());
                    preparedStatement.setObject(2, DTO.getCatidad());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(sEntregaEquiposDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO public.detentregaequipos(\n"
                            + "         id_articulo, cantidad, id_entregaequipos)\n"
                            + "    VALUES (?, ?, ?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, DTO.getId_articulo());
                    preparedStatement.setObject(2, DTO.getCatidad());
                    preparedStatement.setObject(3, DTO.getId_entregaequipos());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(sEntregaEquiposDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }

        return false;
    }

    @Override
    public boolean deleteEQ(sEntregaEquiposDTO DTO) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM public.detentregaequipos\n"
                    + " WHERE id_entregaequipos=? ";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, DTO.getId_entregaequipos());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(sEntregaEquiposDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getOrdenTrabajoEQ() {
        ResultSet rs;
        ArrayList<sEntregaEquiposDTO> alloteq = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT ot.id_ordenttabajo, ot.fecha::date, ot.diagnostico, ot.orden, ot.id_garantia, ot.id_usuario, \n"
                    + "                           ot.id_estado, d.diagnostico, (p.fecha) as fechapresupuesto, ot.id_presuserv, ot.fechaentrega::date, (c.ruc||' / '||c.razonsocial) as cliente\n"
                    + "                     FROM public.ordentrabajos ot\n"
                    + "                    inner join presupuestosservicios p on ot.id_presuserv= p.id_presuserv\n"
                    + "                    inner join diagnosticos d on p.id_diagnostico=d.id_diagnostico \n"
                    + "                    inner join  recepciones r on d.id_recepcion=r.id_recepcion\n"
                    + "                    inner join clientes c on r.id_cliente=c.id_cliente\n"
                    + "                   where  ot.id_estado in(1) order by ot.id_ordenttabajo desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alloteq.add(new sEntregaEquiposDTO(rs.getInt("id_ordenttabajo"),
                        rs.getString("fecha"),
                        rs.getString("cliente")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(sEntregaEquiposDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alloteq);
    }

    @Override
    public String getdetalleOTeq(Integer codOT) {

        ResultSet rs;
        ArrayList<sEntregaEquiposDTO> ALLOTEQ = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT ot.id_ordenttabajo, ot.fecha::date, ot.diagnostico, ot.orden, ot.id_garantia, ot.id_usuario, \n"
                    + "ot.id_estado, d.diagnostico, (p.fecha) as fechapresupuesto, ot.id_presuserv, \n"
                    + "ot.fechaentrega::date, (c.ruc||' / '||c.razonsocial) as cliente,r.id_cliente,(select id_ordenttabajo from entrega_equpos where id_ordenttabajo = ?) as nrOOT,\n"
                    + "dr.id_articulo,dr.cantidad, (a.art_descripcion) as articulo\n"
                    + "FROM public.ordentrabajos ot\n"
                    + "inner join presupuestosservicios p on ot.id_presuserv= p.id_presuserv\n"
                    + "inner join diagnosticos d on p.id_diagnostico=d.id_diagnostico \n"
                    + "inner join  recepciones r on d.id_recepcion=r.id_recepcion\n"
                    + "inner join detrecepciones dr on r.id_recepcion=dr.id_recepcion\n"
                    + "inner join articulos a on dr.id_articulo=a.id_articulo\n"
                    + "inner join clientes c on r.id_cliente=c.id_cliente\n"
                    + "where ot.id_ordenttabajo = ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, codOT);
            preparedStatement.setObject(2, codOT);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ALLOTEQ.add(new sEntregaEquiposDTO(rs.getInt("id_ordenttabajo"),
                        rs.getString("fecha"),
                        rs.getString("cliente"),
                        rs.getInt("id_cliente"),
                        rs.getString("orden"),
                        rs.getInt("id_articulo"),
                        rs.getString("articulo"),
                        rs.getInt("cantidad"),
                        rs.getInt("nrOOT")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(sEntregaEquiposDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(ALLOTEQ);
    }

    @Override
    public String getEQ() {

        ResultSet rs;
        ArrayList<sEntregaEquiposDTO> ALLOTEQ = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT eq.id_entregaequipos, eq.fecha_entrega::date, eq.id_cliente, eq.id_estado,(e.est_descripcion) as estado, eq.id_usuario, \n"
                    + "       eq.observacion, eq.id_ordenttabajo,  (c.ruc||' / '||c.razonsocial) as cliente,\n"
                    + "       deq.id_articulo,deq.cantidad, (a.art_descripcion) as articulo, (u.usu_nombre) as usuario\n"
                    + "  FROM public.entrega_equpos eq\n"
                    + "  inner join ordentrabajos ot on eq.id_ordenttabajo=ot.id_ordenttabajo\n"
                    + "  inner join detentregaequipos deq on eq.id_entregaequipos=deq.id_entregaequipos\n"
                    + "inner join articulos a on deq.id_articulo=a.id_articulo\n"
                    + "  inner join clientes c on eq.id_cliente=c.id_cliente\n"
                    + "inner join estados e on eq.id_estado=e.id_estado\n"
                    + "inner join usuarios u on eq.id_usuario=u.id_usuario order by eq.id_entregaequipos asc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ALLOTEQ.add(new sEntregaEquiposDTO(rs.getInt("id_entregaequipos"),
                        rs.getString("fecha_entrega"),
                        rs.getString("cliente"),
                        rs.getString("estado"),
                        rs.getString("usuario"),
                        rs.getInt("id_estado")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(sEntregaEquiposDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(ALLOTEQ);
    }

    @Override
    public boolean updateEQ(sEntregaEquiposDTO DTO) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE public.entrega_equpos\n"
                    + "   SET  id_estado=?\n"
                    + "        WHERE id_entregaequipos=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, DTO.getId_estado());
            preparedStatement.setObject(2, DTO.getId_entregaequipos());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(sEntregaEquiposDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getdetalleEQ(Integer codOT) {
        ResultSet rs;
        ArrayList<sEntregaEquiposDTO> alldetalleEQ = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT eq.id_entregaequipos, eq.fecha_entrega::date, (ot.fecha::date)as fechaordentrabajo, eq.id_cliente, eq.id_estado,(e.est_descripcion) as estado, eq.id_usuario, \n"
                    + "                           eq.observacion, eq.id_ordenttabajo,  (c.ruc||' / '||c.razonsocial) as cliente,\n"
                    + "                           deq.id_articulo,deq.cantidad, (a.art_descripcion) as articulo, (u.usu_nombre) as usuario\n"
                    + "                      FROM public.entrega_equpos eq\n"
                    + "                      inner join ordentrabajos ot on eq.id_ordenttabajo=ot.id_ordenttabajo\n"
                    + "                      inner join detentregaequipos deq on eq.id_entregaequipos=deq.id_entregaequipos\n"
                    + "                   inner join articulos a on deq.id_articulo=a.id_articulo\n"
                    + "                      inner join clientes c on eq.id_cliente=c.id_cliente\n"
                    + "                    inner join estados e on eq.id_estado=e.id_estado\n"
                    + "                    inner join usuarios u on eq.id_usuario=u.id_usuario \n"
                    + "			where eq.id_entregaequipos=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, codOT);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetalleEQ.add(new sEntregaEquiposDTO(rs.getInt("id_entregaequipos"),
                        rs.getString("fecha_entrega"),
                        rs.getInt("id_ordenttabajo"),
                        rs.getString("fechaordentrabajo"),
                        rs.getString("cliente"),
                        rs.getInt("id_cliente"),
                        rs.getString("observacion"),
                        rs.getInt("id_articulo"),
                        rs.getString("articulo"),
                        rs.getInt("cantidad")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(sEntregaEquiposDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldetalleEQ);
    }

}
