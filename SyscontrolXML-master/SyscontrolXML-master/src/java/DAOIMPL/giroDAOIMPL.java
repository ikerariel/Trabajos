/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPL;

import DAO.giroDAO;
import DTO.aperturaDTO;
import DTO.giroDTO;
import DTO.recargaDTO;
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
public class giroDAOIMPL implements giroDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public String getoperacion() {
        ResultSet rs;
        ArrayList<giroDTO> alloperacion = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = " SELECT idtipooperacion, descripcion\n"
                    + "  FROM tipooperacion";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alloperacion.add(new giroDTO(rs.getInt("idtipooperacion"),
                        rs.getString("descripcion")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(giroDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alloperacion);
    }

    @Override
    public String getcliente(String ci) {
        ResultSet rs;
        ArrayList<giroDTO> obtenercliente = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idcliente, nombrecliente, cedula\n"
                    + "  FROM cliente where cedula=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setString(1, ci);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                obtenercliente.add(new giroDTO(rs.getInt("idcliente"),
                        rs.getString("nombrecliente"),
                        rs.getString("cedula")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(giroDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(obtenercliente);
    }

    @Override
    public boolean insertarcliente(giroDTO dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO cliente(\n"
                    + "             nombrecliente, cedula)\n"
                    + "    VALUES (?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getNombrecliente());
            preparedStatement.setObject(2, dto.getCedula());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(giroDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertargiro(giroDTO dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detallegiro_billetera(\n"
                    + "             nrodestino, nroorigen, montogirobilletera, idtipooperadora, \n"
                    + "            nrotransaccion, nrooperacion, idcliente, idtipooperacion, porcentaje, montototaenvio, fecha, idapertura,idestado,idusuario)\n"
                    + "    VALUES (?, ?, ?, ?, \n"
                    + "            ?, ?, ?, ?,?,?,now(),(select idapertura from apertura_recarga_giro where idestado=1 order by idapertura desc limit 1),?,?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getNrodestino());
            preparedStatement.setObject(2, dto.getNroorigen());
            preparedStatement.setObject(3, dto.getMontogirobilletera());
            preparedStatement.setObject(4, dto.getIdtipooperadora());
            preparedStatement.setObject(5, dto.getNrotransaccion());
            preparedStatement.setObject(6, dto.getNrooperacion());
            preparedStatement.setObject(7, dto.getIdcliente());
            preparedStatement.setObject(8, dto.getIdtipooperacion());
            preparedStatement.setObject(9, dto.getPorcentaje());
            preparedStatement.setObject(10, dto.getMontoenvio());
            preparedStatement.setObject(11, dto.getIdestado());
            preparedStatement.setObject(12, dto.getIdusuario());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(giroDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getgiros() {
        ResultSet rs;
        ArrayList<giroDTO> obtenergiros = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT g.idapertura,g.iddetallegiro, g.nrodestino,g.nroorigen,  (o.descripcion) as operadora,\n"
                    + "                     (p.descripcion) as operacion, g.montogirobilletera, g.idtipooperadora, \n"
                    + "                          g.nrotransaccion, g.nrooperacion, g.idcliente, g.idtipooperacion, g.porcentaje, \n"
                    + "                          g.montototaenvio, g.fecha::date\n"
                    + "                     FROM detallegiro_billetera g\n"
                    + "                     LEFT JOIN tipooperadora o on g.idtipooperadora = o.idtipooperadora\n"
                    + "                     LEFT JOIN tipooperacion p on g.idtipooperacion = p.idtipooperacion \n"
                    + "		     LEFT JOIN apertura_recarga_giro e on g.idapertura = e.idapertura\n"
                    + "		     where e.idestado=1 order by g.iddetallegiro desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                obtenergiros.add(new giroDTO(rs.getInt("iddetallegiro"),
                        rs.getInt("nroorigen"),
                        rs.getInt("nrodestino"),
                        rs.getString("operadora"),
                        rs.getString("operacion"),
                        rs.getInt("montogirobilletera"),
                        rs.getString("porcentaje"),
                        rs.getInt("montototaenvio"),
                        rs.getInt("idapertura"),
                        rs.getString("fecha")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(giroDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(obtenergiros);
    }

    @Override
    public boolean eliminargiro(giroDTO dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM detallegiro_billetera\n"
                    + " WHERE iddetallegiro = ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIddetallegiro());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(giroDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String consultagiro(Integer codapertura) {
        ResultSet rs;
        ArrayList<giroDTO> allgiro = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT d.iddetallegiro \n"
                    + "  FROM detallegiro_billetera d\n"
                    + "  left join apertura_recarga_giro a on d.idapertura=a.idapertura\n"
                    + "  where d.idapertura=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, codapertura);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allgiro.add(new giroDTO(rs.getInt("iddetallegiro")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(giroDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allgiro);
    }

    @Override
    public String getconsultagiro(Integer nrolineadestino, Integer nroliaorigen) {
        ResultSet rs;
        ArrayList<giroDTO> getconsultag = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT g.idapertura, g.iddetallegiro, g.nrodestino,g.nroorigen,  (o.descripcion) as operadora,\n"
                    + "                                        (p.descripcion) as operacion, g.montogirobilletera, g.idtipooperadora, \n"
                    + "                                             g.nrotransaccion, g.nrooperacion, g.idcliente, g.idtipooperacion, g.porcentaje, \n"
                    + "                                            g.montototaenvio, g.fecha\n"
                    + "                                        FROM detallegiro_billetera g\n"
                    + "                                        JOIN tipooperadora o on g.idtipooperadora = o.idtipooperadora\n"
                    + "                                        JOIN tipooperacion p on g.idtipooperacion = p.idtipooperacion \n"
                    + "                    	      JOIN apertura_recarga_giro e on g.idapertura = e.idapertura\n"
                    + "                   		     where (g.nrodestino = ? or g.nroorigen=?) and e.idestado in(1,2)";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, nrolineadestino);
            preparedStatement.setInt(2, nroliaorigen);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                getconsultag.add(new giroDTO(rs.getInt("iddetallegiro"),
                        rs.getInt("nroorigen"),
                        rs.getInt("nrodestino"),
                        rs.getString("operadora"),
                        rs.getString("operacion"),
                        rs.getInt("montogirobilletera"),
                        rs.getString("porcentaje"),
                        rs.getInt("montototaenvio"),
                        rs.getInt("idapertura"),
                        rs.getString("fecha")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(giroDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(getconsultag);
    }

    @Override
    public String getopago() {
        ResultSet rs;
        ArrayList<giroDTO> allpago = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT * FROM public.estado";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allpago.add(new giroDTO(rs.getInt("idestado"),
                        rs.getString("descripcion")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(giroDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allpago);
    }

    @Override
    public String getconsultagiroci(String ci) {
        ResultSet rs;
        ArrayList<giroDTO> getconsultag = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT g.idapertura, g.iddetallegiro, g.nrodestino,g.nroorigen,  (o.descripcion) as operadora,  \n"
                    + "(p.descripcion) as operacion, g.montogirobilletera, g.idtipooperadora,  \n"
                    + " g.nrotransaccion, g.nrooperacion, g.idcliente, g.idtipooperacion, g.porcentaje, \n"
                    + " g.montototaenvio, g.fecha\n"
                    + "FROM detallegiro_billetera g                                     \n"
                    + "  JOIN tipooperadora o on g.idtipooperadora = o.idtipooperadora \n"
                    + "                                     LEFT JOIN tipooperacion p on g.idtipooperacion = p.idtipooperacion \n"
                    + " join cliente c on g.idcliente=c.idcliente\n"
                    + "                   	     LEFT JOIN apertura_recarga_giro e on g.idapertura = e.idapertura                  		\n"
                    + "     where  c.cedula=? and e.idestado in(1,2)";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setString(1, ci);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                getconsultag.add(new giroDTO(rs.getInt("iddetallegiro"),
                        rs.getInt("nroorigen"),
                        rs.getInt("nrodestino"),
                        rs.getString("operadora"),
                        rs.getString("operacion"),
                        rs.getInt("montogirobilletera"),
                        rs.getString("porcentaje"),
                        rs.getInt("montototaenvio"),
                        rs.getInt("idapertura"),
                        rs.getString("fecha")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(giroDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(getconsultag);
    }

    @Override
    public String getgirospendientes(Integer id) {
        ResultSet rs;
        ArrayList<giroDTO> obtenerpagos = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = " SELECT pg.idestado,pg.idpago, r.iddetallegiro,concat(cl.cedula||' / '|| cl.nombrecliente) as cliente, e.idapertura,\n"
                    + "                    (e.fecha_apertura) as fechaApertura,e.fechacierre, r.fecha, pg.fechapago, (o.descripcion) as operadora, \n"
                    + "                    (n.descripcion) as operacion,(g.descripcion)as estado,\n"
                    + "                    concat('0',r.nroorigen) as nroorigen, concat('0',r.nrodestino) as nrodestino,\n"
                    + "                    r.montogirobilletera, r.porcentaje, r.montototaenvio\n"
                    + "                    FROM apertura_recarga_giro e \n"
                    + "                    left join detallegiro_billetera r on e.idapertura = r.idapertura\n"
                    + "                    left join tipooperadora o on r.idtipooperadora=o.idtipooperadora\n"
                    + "                    left join tipooperacion n on r.idtipooperacion = n.idtipooperacion\n"
                    + "                    left join estado p on r.idestado=p.idestado\n"
                    + "                    left join cliente cl on r.idcliente=cl.idcliente \n"
                    + "                    left join estado g on r.idestado=g.idestado \n"
                    + "		    left join pago pg on r.iddetallegiro = pg.iddetallegiro	\n"
                    + "                    where  pg.idestado=? and e.idestado in(1,2)";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                obtenerpagos.add(new giroDTO(rs.getInt("iddetallegiro"),
                        rs.getString("cliente"),
                        rs.getString("operacion"),
                        rs.getInt("montototaenvio"),
                        rs.getInt("idestado"),
                        rs.getString("fechapago"),
                        rs.getInt("idpago"),
                        rs.getString("fecha")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(giroDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(obtenerpagos);
    }

    @Override
    public boolean insertarpago(giroDTO dto, Integer cod) {
        switch (cod) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.pago\n"
                            + "   SET idestado=?, fechapago=now(), \n"
                            + "       montopagado=?,nrooperacionpago=?\n"
                            + " WHERE  iddetallegiro=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getIdestado());
                    preparedStatement.setObject(2, dto.getMontopagado());
                    preparedStatement.setObject(3, dto.getVnrooperacion());
                    preparedStatement.setObject(4, dto.getIddetallegiro());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                        return false;
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(giroDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
                
                
            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.pago SET fechapago=null, montopagado=?,nrooperacionpago=?,idestado=? WHERE  iddetallegiro=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, 0);
                    preparedStatement.setObject(2, 0);
                    preparedStatement.setObject(3, dto.getIdestado());
                    preparedStatement.setObject(4, dto.getIddetallegiro());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                        return false;
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(giroDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                break;
        }
        return false;

    }

}
