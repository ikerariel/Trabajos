<%-- 
    Document   : pedidocompravista
    Created on : 28/04/2018, 01:43:11 PM
    Author     : Rafel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" href="../Recursos/css/estilos_1.css">
        <link rel="stylesheet" href="../Recursos/css/iconos.css">
        <link rel="stylesheet" href="../Recursos/css/bootstrap1.css">
        <link rel="stylesheet" href="../Recursos/css/font-mfizz.css">
        <link rel="stylesheet" href="../Recursos/css/font-awesome.css">
        <script src="../Recursos/js/main.js"></script>
        <script src="../Recursos/js/bootstrap.js"></script>
        <script src="../Recursos/js/jquery.backstretch.min.js"></script>
        <title>PEDIDO COMPRA</title>
        <link rel="stylesheet" href="../Recursos/css/bootstrap.css"/>
        <script type="text/javascript" src="../Recursos/js/jquery.js"></script>
        <script type="text/javascript" src="../Validadores/pedidocompravalidador.js"></script>
        <script type="text/javascript" src="../Validadores/buscadorvalidador.js"></script>
        <script type="text/javascript">
            function buscarMercad() {

                
                    $('#buscardorM').modal('show');
                
            }
        </script>

    </head>
    <body>
        <%@ include file="menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <legend>
                    <h4>Pedido de Compra</h4>
                </legend>
                <div>
                    <input  class="btn btn-md btn-success" title="Nuevo Registro de Compra" type="button" onclick="getcodigo();getusuario();getestado()" value="NUEVO"/>
                    <a class="btn btn-md btn-primary" href="#buscardorM" title="Limpiar los Campos" data-toggle="modal" type="button" onclick="buscarMercad()" value="CANCELAR"/>CANCELAR</a>
                    <input class="btn btn-md btn-primary" title="Eliminar un Registro" type="button" onclick="nuevoListarpedido()" value="ELIMINAR"/>
                    <input class="btn btn-md btn-primary" title="Modificar un Registro" type="button" value="MODIFICAR"/>
                    <input class="btn btn-md btn-primary" title="Guardar el Registro" type="button" onclick="insertar()" value="GUARDAR"/>
                    <input class="btn btn-md btn-primary" title="Reporte del Registro" type="button" value="REPORTE"/>
                </div>
                <br>
                <div class="">
                    <div class="form-group">
                        <label class="col-md-1 control-label">Codigo</label>  
                        <div class="col-md-2">
                            <input  id="codigo" name="codigo" type="text" placeholder="Registro" class="form-control input-sm" required=""  autofocus onkeydown="if (event.keyCode === 13) {
                                                   nuevoListarpedido();
                                               }">
                        </div>
                        <label class="col-md-1 control-label">Fecha</label>  
                        <div class="col-md-3">
                            <input disabled id="fechapedido"  type="datetime"  class="form-control input-sm" required=""  autofocus onkeydown="">
                        </div>
                        <label class="col-md-1 control-label">Estado</label>  
                        <div class="col-md-3">
                            <input disabled id="estado"  type="text" placeholder="estado" class="form-control input-sm alert-warning">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-1 control-label">Usuario</label>  
                    <div class="col-md-2" >
                        <input disabled id="Usuario"  type="text" placeholder="usuario..." class="form-control input-sm alert-warning">
                    </div>
                    <label class="col-md-2 control-label">Observaciòn: </label>  
                    <div class="col-md-7">
                        <input id="observacion"  type="text" placeholder="ingrese alguna observacion..." class="form-control input-sm">
                    </div>
                </div>    

                <div class="">
                    <legend></legend>
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="col-md-1 control-label">Producto</label>
                            <div class="col-md-2">
                                <input id="idproducto" type="text" placeholder="Codigo" onclick="buscarMercad()" class="form-control input-sm"
                                       onkeydown=" if (event.keyCode === 13) {
                                                   buscarMercad();
                                               }">
                            </div>
                            <label class="col-md-1 control-label">Cantidad</label>
                            <div class="col-md-2">
                                <input id="cantidad" type="text" placeholder="Cantidad" class="form-control input-sm" >
                            </div>
                            <label class="col-md-2 control-label">Precio U.</label>
                            <div class="col-md-3">
                                <input id="precioUnitario" name="precioUnitario" type="text" placeholder="PrecioU" class="form-control input-sm"
                                       onkeydown=" if (event.keyCode === 13) {
                                                   agregarFilaProducto();
                                }">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-1 control-label">Desciprción</label>
                        <div class="col-md-11">
                            <input class="form-control input-sm" id="descripcion" type="text" placeholder="Descirpcion del producto">
                        </div>
                    </div>
                </div>
                <!-- Tabla detalle -->
                <div class="">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover table input-md" id="miTablap">
                            <thead>
                                <tr class="alert-dismissible">
                                    <th>CODIGO</th>
                                    <th>DESCRIPCION</th>
                                    <th>PRECIO U.</th>
                                    <th>CANTIDAD</th>
                                    <th>SUB TOTAL</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody id="table_deta">
                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- Total a pagar -->
                <div class="col-xs-3 col-xs-offset-9 input-group input-group-sm">
                    <span class="input-group-addon">Total a Pagar:</span>
                    <input class="form-control" id="total" style="font-size: 15px" type="text" disabled>
                </div><br>



            </form>

<div class="modal fade" id="buscardorM">
            <div class="modal-dialog" style="width: 800px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarMaterial" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" class="form-control input-md" placeholder="Buscar Material..." >
                            </div>
                        </div>
                        <div class="table" id="scroll"style="height: 435px" >

                            <table  class="table table-striped table-bordered table-hover table input-md" id="miTablaMaterial" onclick="()">
                                <thead>
                                    <tr class="">
                                        <th>Cod.</th>
                                        <th>Cod.Material</th>
                                        <th>Matarial.</th>                      
                                        <th>Precio</th>                      
                                        <th>Imp.</th>                      
                                    </tr>
                                </thead>
                                <tbody id="table_deta">
                                </tbody>
                            </table>    
                        </div>
                    </div>
                </div>
            </div> 
        </div>

        
        </section>   
        
        
    </body>



</html>
