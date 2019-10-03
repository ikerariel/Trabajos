<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">
    </head>
    <body>
         <center>
                
          <form>
              <table border="0" >
             <CENTER><img src="recursos/LOGO_VEMAY_tablas.png" width="100" height="50" alt="logo_1"/></CENTER>
       
                <tbody>
                    <tr>
                        <td><label> Codigo </label></td>
                        <td><input type="text" id="pkpedidocompra" size="8" onkeydown="         
                                  if (event.keyCode === 13) {                                   
                                   if(modifica===true){
                                       
                                       RecuperoDatoPedidos();
                                       RecuperoDatoDetalle();
                                     
                                   }else{
                                      RecuperoDatosEliminarPedidosCab();
                                      RecuperoDatosEliminarPedidosDet();
                                   }
                                       

                                }"/><label> Fecha</label></td>
                                    <td><input type="text"  id="datepicker"  size="13" onkeydown="
                                   if (event.keyCode === 13) {    
                                   
                                   
                                   }"> 
                    
                        </tr>
                  
                    <tr>
                        <td><label> Funcionario</label></td>
                        <td><input type="text"  id="idFuncionario" size="8" onkeydown="
                                if (event.keyCode === 13) {
                                    if (agregar === true) {
                                        recuperardatofuncionario();
                                        $('#funcio_nombre').focus();
                                        $('#grabar').prop('disabled', false);
                                    } else {
                                        recuperardatofuncionario();
                                    }
                                }"/></td>
                      
                        <td><input type="text"  id="funcio_nombre" size="15" onkeydown="
                                if (event.keyCode === 13) {
                                    if (validac()) {
                                        $('#grabar').prop('disabled', false);
                                    }
                                
                                  } "/></td>
                    </tr>
                    
                    <tr>
                        <td><label> Nro. Pedido </label></td>
                        <td><input type="text"  id="pedidonumero" size="8" onkeydown="
                                                         if(event.keyCode === 13){
                                                             if (validanumeropositivo()) {
                                                      $('#listarComboSucu').focus();
                                                          //frmCargarbusquedas('busquedacliente.jsp','Clientes');
                                                 }           
                               } "/><label> Sucursal </label></td>
                        <td><div id="combosucu" onkeydown="
                                 if (event.keyCode === 32) {
                                   cargarComboSucursal();
            
                              }"/></div></td>

                    </tr>
                
             </tbody>
            </table>       

        <br>
        <div style="  width: 740px; height: 50px;">
            
            <fieldset>
                <legend>Datos del Producto</legend>
                    <label> Codido</label>
                    <input type="text" id="codProducto" size="10" onkeydown="
                        
                                                        if(event.keyCode === 176){
                                                            frmCargarbusquedas('busqueda_mercaderias.jsp');
                                                            
                                                           } "/>
                    

                    <label >Descripcion</label>
                    <input type="text" id="descripedido" size="18" />
                    
               
                    <label >Cantidad</label>
                    <input type="text" id="cantpedido" size="5" onkeydown="
                                                                if(event.keyCode === 13){
                                                                  if(verificarFilasTabla()){
                                                                    agregarFilasPedido();
                                                                    limpiar_deta();
                                                                  }
                                                                    
                                                                }
                                                                " />
                
                    
            </fieldset>
        
        </div>
        <br>
        <div style="border: 1px solid;  width: 740px; height: 230px;">
            <table border="1" id="tabladetalleproductos"> 
                <thead>
                    <tr>
                        <th style=" width: 5%;">Codigo</th>
                        <th style=" width: 40%;">Descripcion</th>
                        <th style=" width: 5%;">Cantidad</th>
                        
                        <th style=" width: 5%;"><img src="recursos/Delete-icon.png"/></th>
                    </tr>
                </thead>
            </table>
        </div>
        <br>
        
         <br>
         <br>
        
        <button id="agregar" type="button" onclick="autonumpedidos();
                    operacion = 1;
                    agregar = true;
                    $('#datepicker').focus();
                    deshabilitarBotonpedido();
                    ">Agregar</button>
            <button id="modificar" type="button" onclick="
          
                    modifica = true;
                    elimina = false;
                    operacion = 2;
                     
                    $('#pkpedidocompra').prop('disabled', false);
                    $('#pkpedidocompra').focus();

                    deshabilitarBotonpedido();
                    ">Modificar</button>
            <button id="eliminar"  type="button" onclick="
                    $('#pkpedidocompra').prop('disabled', false);
                    $('#pkpedidocompra').focus();
                    modifica = false;
                    elimina = true;
                    deshabiliEliminarfuncio();
                    ">Eliminar</button>
            <button id="grabar" type="button" onclick="
                grabarPedido();
                 ModificarPedido();
                if (validacion()) {
                    alert('Guardado con exito');
                    limpiezaped();
                    limpiar_deta();
                    habilitarBotoncitos();
                }
                    ">Grabar</button>
            <button id="cancelar" type="button" onclick="limpiezaped(); cargargrillafuncionario();">Cancelar</button>
            <button type="button" onclick="ver_reporte();">Buscar pedidos</button> 
            </form>
    </center>
</body>

    </body>
</html>
