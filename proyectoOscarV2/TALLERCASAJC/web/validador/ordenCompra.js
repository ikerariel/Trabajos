/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function  verDetalle() {
    $('#mitablaOrdendetalle').find('tbody').find('tr').each(function () {
        ds = {
//            "opcion": 3,
//            "codigoD": $('#codigo').val(),
            "idartiD": $(this).find("td").eq(0).html(),
            "precioD": $(this).find("td").eq(2).html(),
            "cantiD": $(this).find("td").eq(3).html()
        };
        alert(ds.idartiD);
        alert(ds.precioD);
        alert(ds.cantiD);
       
//        $.ajax({
//            url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
//            type: 'POST',
//            data: ds,
//            cache: false,
//            dataType: 'text',
//            success: function () {
//            },
//            error: function () {
//            }
//        });
    });

}
