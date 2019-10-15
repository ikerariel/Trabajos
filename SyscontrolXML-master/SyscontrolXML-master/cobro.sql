SELECT c.idcobro, c.importe, c.fecha_cobro, c.idtipocobro, c.idventa, c.saldo, c.fechasaldo, 
       c.idestado, (cl.cedula||' - '||cl.nombrecliente) as cliente, (e.descripcion) as estado
  FROM ventas.cobro c
  left join ventas.venta v on c.idventa=v.idventa
  left join cliente cl on v.idcliente = cl.idcliente
  left join estado e on c.idestado=e.idestado
left join ventas.documentos_facturas df on v.iddocfactura=df.iddocfactura

where (df.numerodocumento='001-001-0000061') or (cl.cedula='4782644')
