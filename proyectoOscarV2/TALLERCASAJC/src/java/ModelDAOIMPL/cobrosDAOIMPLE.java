/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.cobrosDAO;
import ModelDTO.cobrosDTO;
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
public class cobrosDAOIMPLE implements cobrosDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public String getcobros(String ci) {

        ResultSet rs;
        ArrayList<cobrosDTO> alldetallePS = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT c.id_cuencob, c.fecha_cuencob::date,c.id_venta, c.secuencia, c.monto_cuencob, c.saldo_cuencob, c.vencimiento_cuencob, \n"
                    + "       c.id_venta, c.id_estado, v.nrofactura, (cl.ruc||'-'||cl.cv) as cedula, (cl.razonsocial) as nombrecliente,\n"
                    + " (cl.ruc||'-'||cl.cv||' / '|| cl.razonsocial) as cliente,v.id_condicionpago,(cp.descripcion)as tipoventa\n"
                    + "  FROM public.cuentascobrar c\n"
                    + "  left join ventas v on c.id_venta=v.id_venta\n"
                    + "  left join clientes cl on v.id_cliente= cl.id_cliente\n"
                    + "left join condicion_pago cp on v.id_condicionpago=cp.id_condicionpago\n"
                    + "where cl.ruc=? and c.id_estado = 3 order by  v.nrofactura asc, c.secuencia ";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, ci);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetallePS.add(new cobrosDTO(rs.getInt("id_cuencob"),
                        rs.getString("fecha_cuencob"),
                        rs.getString("nrofactura"),
                        rs.getString("cliente"),
                        rs.getString("nombrecliente"),
                        rs.getInt("saldo_cuencob"),
                        rs.getInt("secuencia"),
                        rs.getInt("id_venta"),
                        rs.getString("tipoventa")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(sPresupuestoServiciosDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldetallePS);

    }

}
