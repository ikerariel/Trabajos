/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.ventaDAO;
import ModelDTO.Coloresdto;
import ModelDTO.notaDebitoDTO;
import ModelDTO.ventaDTO;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ventaDAOIMPLE implements ventaDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public String getfactura(String caja) {
        ResultSet rs;
        ArrayList<ventaDTO> allfactura = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT f.idfactura, f.numerofac, f.id_estado, f.id_timbrado, t.fac_establecimiento, t.fac_caja,\n"
                    + "                    (t.fac_establecimiento||'-'||t.fac_caja||'-'||f.numerofac)as factura\n"
                    + "                    FROM public.factura f\n"
                    + "                    INNER JOIN timbrados t on f.id_timbrado=t.id_timbrado\n"
                    + "                    WHERE t.id_estado=7 and f.id_estado=5 and t.fac_caja = ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setString(1, caja);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allfactura.add(new ventaDTO(rs.getInt("idfactura"),
                        rs.getString("factura")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allfactura);
    }

    @Override
    public String getvendedor() {
        ResultSet rs;
        ArrayList<ventaDTO> allvendedor = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT v.idvendedor, v.fechaentrada, v.fechasalida, v.id_estado, v.id_empleado,\n"
                    + "(e.nombre||' '||e.apellido) vendedor\n"
                    + "  FROM public.vendedor v\n"
                    + "  INNER JOIN empleados e on e.id_empleado=v.id_empleado\n"
                    + "where v.id_estado=7";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allvendedor.add(new ventaDTO(rs.getInt("idvendedor"),
                        rs.getString("vendedor")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allvendedor);
    }

    @Override
    public String gettipopago() {
        ResultSet rs;
        ArrayList<ventaDTO> alltipago = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idtipopag, descripcion\n"
                    + "  FROM public.tipopago;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alltipago.add(new ventaDTO(rs.getInt("idtipopag"),
                        rs.getString("descripcion")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alltipago);
    }

    @Override
    public boolean insertarventa(ventaDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.ventas(\n"
                    + "             nrofactura, idtipopag, id_cliente,id_deposito, \n"
                    + "             id_estado, id_usuario,idvendedor,id_apcica)\n"
                    + "    VALUES (?, ?, ?, ?, 1, ?, ?, ?)";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getNumerofac());
            preparedStatement.setObject(2, Dto.getTipoog());
            preparedStatement.setObject(3, Dto.getId_cliente());
            preparedStatement.setObject(4, Dto.getId_deposito());
            preparedStatement.setObject(5, Dto.getId_usuario());
            preparedStatement.setObject(6, Dto.getIdvendedor());
            preparedStatement.setObject(7, Dto.getId_apcica());
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
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertardetalle(ventaDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.detventas(\n"
                    + "            id_articulo, id_venta, cantidad, precio, id_impuesto)\n"
                    + "    VALUES (?, ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_articulo());
            preparedStatement.setObject(2, Dto.getId_venta());
            preparedStatement.setObject(3, Dto.getCantidad());
            preparedStatement.setObject(4, Dto.getPreciounitario());
            preparedStatement.setObject(5, Dto.getId_impuesto());
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
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean actualizarfactura(ventaDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE public.factura\n"
                    + "   SET  id_estado=?\n"
                    + " WHERE idfactura=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_estado());
            preparedStatement.setObject(2, Dto.getId_factura());

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
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getcliente(String cedula) {
        ResultSet rs;
        ArrayList<ventaDTO> allcliente = new ArrayList<>();
        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT id_cliente, (ruc||'-'||cv) as cedula, razonsocial, telefono, direccion, web, id_barrio, \n"
                    + "                          id_ciudad\n"
                    + "                      FROM public.clientes\n"
                    + "                     where ruc = ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setString(1, cedula);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allcliente.add(new ventaDTO(rs.getInt("id_cliente"),
                        rs.getString("cedula"),
                        rs.getString("razonsocial")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allcliente);
    }

    @Override
    public String getidvendedor(Integer codVendedor) {
        ResultSet rs;
        ArrayList<ventaDTO> allcodvendedor = new ArrayList<>();
        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT v.idvendedor, v.fechaentrada, v.fechasalida, v.id_estado, v.id_empleado,\n"
                    + "(e.nombre||' '||e.apellido) vendedor\n"
                    + "  FROM public.vendedor v\n"
                    + "  INNER JOIN empleados e on e.id_empleado=v.id_empleado\n"
                    + "where v.id_estado=7 and  v.idvendedor=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, codVendedor);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allcodvendedor.add(new ventaDTO(rs.getInt("idvendedor"),
                        rs.getString("vendedor")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allcodvendedor);
    }

    @Override
    public Integer getcodigo() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(id_venta),0 )+ 1 as codigo  FROM ventas;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosComprasDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean grabarcliente(ventaDTO Dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.clientes(\n"
                    + "            ruc, razonsocial, telefono,direccion,cv)\n"
                    + "    VALUES (?, ?,?,?,?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getRuc());
            preparedStatement.setObject(2, Dto.getRazonsocial());
            preparedStatement.setObject(3, Dto.getTelefono());
            preparedStatement.setObject(4, Dto.getDireccion());
            preparedStatement.setObject(5, Dto.getCv());

            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean anularfactura(ventaDTO Dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE public.ventas\n"
                    + "   SET id_estado=?\n"
                    + " WHERE nrofactura=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_estado());
            preparedStatement.setObject(2, Dto.getNumerofac());

            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String gettimbrado() {
        ResultSet rs;
        ArrayList<ventaDTO> alltimbrado = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT t.id_timbrado, t.numero, t.inicio_fecha::date, t.final_fecha, t.vencimientos, \n"
                    + "       t.id_estado, t.id_usuario, t.fac_establecimiento, t.fac_caja, t.fac_desde, \n"
                    + "       t.fac_hasta, e.est_descripcion\n"
                    + "  FROM public.timbrados t\n"
                    + "  INNER JOIN estados e on t.id_estado = e.id_estado";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alltimbrado.add(new ventaDTO(rs.getInt("id_timbrado"),
                        rs.getInt("numero"),
                        rs.getString("inicio_fecha"),
                        rs.getString("vencimientos"),
                        rs.getString("est_descripcion"),
                        rs.getString("fac_caja")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alltimbrado);
    }

    public boolean insertartimbrado(ventaDTO Dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.timbrados(\n"
                    + "            numero, vencimientos,id_estado, \n"
                    + "            id_usuario, fac_establecimiento, fac_caja, fac_desde, \n"
                    + "            fac_hasta)\n"
                    + "    VALUES (?, ?::date, 7, ?, ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getNumero());
            preparedStatement.setObject(2, Dto.getVencimientos());
            preparedStatement.setObject(3, Dto.getId_usuario());
            preparedStatement.setObject(4, Dto.getFac_establecimiento());
            preparedStatement.setObject(5, Dto.getFac_caja());
            preparedStatement.setObject(6, Dto.getFac_desde());
            preparedStatement.setObject(7, Dto.getFac_hasta());

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
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insertardetallefacturas(ventaDTO Dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.factura(\n"
                    + "      id_estado, id_timbrado, numerofac)\n"
                    + "    VALUES (5, (select id_timbrado from timbrados where numero=?), ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getNumero());
            preparedStatement.setObject(2, Dto.getNumerofac());

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
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getfac(String user) {
        ResultSet rs;
        ArrayList<ventaDTO> allfac = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT a.id_apcica, a.apertura_fecha::date\n"
                    + "FROM public.aperturacierrecajas a\n"
                    + "INNER JOIN cajero c  on a.idcajero = c.idcajero\n"
                    + "INNER JOIN cajas j     on a.id_caja = j.id_caja\n"
                    + "INNER JOIN usuarios u  on c.id_usuario= u.id_usuario\n"
                    + "WHERE  u.usu_nombre=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setString(1, user);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allfac.add(new ventaDTO(rs.getInt("id_apcica"),
                        rs.getString("apertura_fecha")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allfac);
    }

    @Override
    public boolean insertarcobro(ventaDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.cobros(\n"
                    + "            importe, idtipopag,id_venta)\n"
                    + "    VALUES (?, ?, ?)";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getImporte());
            preparedStatement.setObject(2, Dto.getId_cobro());
            preparedStatement.setObject(3, Dto.getId_venta());

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
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertarcobrotarjeta(ventaDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.cobrostarjetas(\n"
                    + "            id_cobro, tarjnrobol, \n"
                    + "            id_entiemi, id_tipotarjeta)\n"
                    + "    VALUES ((select id_cobro from cobros where id_venta=? and idtipopag = 2), ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getId_venta());
            preparedStatement.setObject(2, Dto.getTarjnrobol());
            preparedStatement.setObject(3, Dto.getId_entiemi());
            preparedStatement.setObject(4, Dto.getId_tipotarjeta());

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
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public boolean insertarcobrocheque(ventaDTO Dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO public.cobroscheques(\n"
                    + "             nrochque, id_tipocheque, \n"
                    + "            id_cobro, id_bancocheque)\n"
                    + "    VALUES (?, ?, (select id_cobro from cobros where id_venta=? and idtipopag = 3), ? )";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, Dto.getNrochque());
            preparedStatement.setObject(2, Dto.getId_tipocheque());
            preparedStatement.setObject(3, Dto.getId_venta());
            preparedStatement.setObject(4, Dto.getId_bancocheque());

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
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getpedidoventafactura(Integer pedidoVentaFactura) {
        ResultSet rs;
        ArrayList<ventaDTO> allpventaFac = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = " SELECT p.id_pedidoven, p.fechapedido::date, p.id_cliente, p.id_estado, p.idvendedor, \n"
                    + "                    e.est_descripcion,(u.usu_nombre) as vendedor,p.observacion,\n"
                    + "                    (c.ruc) as cedula,c.cv, (c.ruc||'-'||c.cv||' / '||c.razonsocial) as cliente,\n"
                    + "                    d.id_articulo, d.cantidad, d.precio, (a.art_descripcion) as articulo,a.id_impuesto,\n"
                    + "                    (i.imp_descripcion) as impuesto\n"
                    + "                    FROM public.pedidosventas p\n"
                    + "                    left join detpedidosventas d on p.id_pedidoven=d.id_pedidoven\n"
                    + "                    left join articulos a on d.id_articulo=a.id_articulo\n"
                    + "		    left join impuestos i on a.id_impuesto=i.id_impuesto\n"
                    + "                    left join clientes c on p.id_cliente = c.id_cliente\n"
                    + "                    left join vendedor v on p.idvendedor = v.idvendedor\n"
                    + "                    left join empleados emp on v.id_empleado = emp.id_empleado\n"
                    + "                    left join usuarios u on emp.id_empleado = u.id_empleado\n"
                    + "                    left join estados e on p.id_estado=e.id_estado\n"
                    + "                    where p.id_estado in(1,3) and p.id_pedidoven=? order by p.id_pedidoven desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, pedidoVentaFactura);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allpventaFac.add(new ventaDTO(rs.getString("cedula"),
                         rs.getInt("idvendedor"),
                         rs.getInt("id_articulo"),
                         rs.getString("articulo"),
                         rs.getInt("cantidad"),
                         rs.getInt("precio"),
                         rs.getInt("id_estado"),
                         rs.getString("impuesto"),
                         rs.getInt("id_impuesto")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ventaDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allpventaFac);
    }

    @Override
    public String nletra(Integer numero) {
         String cadena = new String();

        //AQUI SE INDENTIFICA SI LLEVA MILLONES
        if ((numero / 1000000) > 0) {
            if ((numero / 1000000) == 1) {
                cadena = " Un millon " + nletra(numero % 1000000);
            } else {
                cadena = nletra(numero / 1000000) + " Millones " + nletra(numero % 1000000);
            }
            //AQUI SE INDENTIFICA SI LLEVA MILES
        } else {
            if ((numero / 1000) > 0) {
                if ((numero / 1000) == 1) {
                    cadena = " Mil " + nletra(numero % 1000);
                } else {
                    cadena = nletra(numero / 1000) + " Mil " + nletra(numero % 1000);
                }
//                }

                //AQUI SE INDENTIFICA SI LLEVA CIENTOS
            } else {
                if ((numero / 100) > 0) {
                    if ((numero / 100) == 1) {
                        if ((numero % 100) == 0) {
                            cadena = " Cien ";
                        } else {
                            cadena = " Ciento " + nletra(numero % 100);
                        }
                    } else {
                        if ((numero / 100) == 5) {
                            cadena = " Quinientos " + nletra(numero % 100);
                        } else {
                            if ((numero / 100) == 9) {
                                cadena = " Novecientos " + nletra(numero % 100);
                            } else {
                                cadena = nletra(numero / 100) + " Cientos " + nletra(numero % 100);
                            }
                        }
                    }
                    //AQUI SE IDENTIFICA LAS DECENAS
                } else {
                    if ((numero / 10) > 0) {
                        switch ((int) (numero / 10)) {
                            case 1:
                                switch ((int) (numero % 10)) {
                                    case 0:
                                        cadena = " Diez ";//sdfasfasdfsf
                                        break;
                                    case 1:
                                        cadena = " Once ";
                                        break;
                                    case 2:
                                        cadena = " Doce ";
                                        break;
                                    case 3:
                                        cadena = " Trece ";
                                        break;
                                    case 4:
                                        cadena = " Catorce ";
                                        break;
                                    case 5:
                                        cadena = " Quince ";
                                        break;
                                    default:
                                        cadena = " Diez y " + nletra(numero % 10);
                                        break;
                                }
                                break;
                            case 2:
                                switch ((int) (numero % 10)) {
                                    case 0:
                                        cadena = " Veinte ";
                                        break;
                                    default:
                                        cadena = " Veinti " + nletra(numero % 10);
                                        break;

                                }
                                break;
                            case 3:
                                switch ((int) (numero % 10)) {
                                    case 0:
                                        cadena = " Treinta ";
                                        break;
                                    default:
                                        cadena = " Treinta y " + nletra(numero % 10);
                                        break;
                                }
                                break;
                            case 4:
                                switch ((int) (numero % 10)) {
                                    case 0:
                                        cadena = " Cuarenta ";
                                        break;
                                    default:
                                        cadena = " Cuarenta y " + nletra(numero % 10);
                                        break;
                                }
                                break;
                            case 5:
                                switch ((int) (numero % 10)) {
                                    case 0:
                                        cadena = " Cincuenta ";
                                        break;
                                    default:
                                        cadena = " Cincuenta y " + nletra(numero % 10);
                                        break;
                                }
                                break;
                            case 6:
                                switch ((int) (numero % 10)) {
                                    case 0:
                                        cadena = " Sesenta ";
                                        break;
                                    default:
                                        cadena = " Sesenta y " + nletra(numero % 10);
                                        break;
                                }
                                break;
                            case 7:
                                switch ((int) (numero % 10)) {
                                    case 0:
                                        cadena = " Noventa ";
                                        break;
                                    default:
                                        cadena = " Noventa y " + nletra(numero % 10);
                                        break;
                                }
                            case 8:
                                switch ((int) (numero % 10)) {
                                    case 0:
                                        cadena = " Ochenta ";
                                        break;
                                    default:
                                        cadena = " Ochenta y " + nletra(numero % 10);
                                        break;
                                }
                            case 9:
                                switch ((int) (numero % 10)) {
                                    
                                    case 0:
                                        cadena = " Setenta ";
                                        break;
                                    default:
                                        cadena = " Setenta y " + nletra(numero % 10);
                                        break;
                                }
                                break;
                        }
                    } else {
                        switch ((int) numero) {
                            case 0:
                                cadena = "";
                                break;
                            case 1:
                                cadena = "Uno";
                                break;
                            case 2:
                                cadena = "Dos";
                                break;
                            case 3:
                                cadena = "Tres";
                                break;
                            case 4:
                                cadena = "Cuatro";
                                break;
                            case 5:
                                cadena = "Cinco";
                                break;
                            case 6:
                                cadena = "Seis";
                                break;
                            case 7:
                                cadena = "Siete";
                                break;
                            case 8:
                                cadena = "Ocho";
                                break;
                            case 9:
                                cadena = "Nueve";
                                break;

                        }
                    }
                }
            }
        }

        return cadena;
    }
}
