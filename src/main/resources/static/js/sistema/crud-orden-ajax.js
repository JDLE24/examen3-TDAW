let idOrdenEliminar=0;
let idOrdenActualizar=0;

function obtenerOrdenes() {
    $.ajax({
        method:"GET",
        url: "/v1/api/orden",
        data: {},
        success: function( resultado ) {
            if(resultado.estado===1){
                let tabla=$('#example').DataTable();
                ordenes = resultado.ordenes;

                ordenes.forEach(orden =>{
                    let botones ='<button type="button" class="btn btn-primary mb-2" data-bs-toggle="modal" data-bs-target="#editModal" onclick="seleccionarOrdenActualizar('+orden.id+');">Edit</button>';
                    botones = botones + ' <button type="button" class="btn btn-danger mb-2" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="seleccionarOrdenEliminar('+orden.id+');">Delete</button>\n';
                    tabla.row.add([
                        orden.id,
                        orden.ordenid,
                        orden.productoid,
                        orden.cantidad,
                        botones
                    ]).node().id='renglon_'+orden.id;
                    tabla.draw()
                })
            }
        },
        error:function (xhr,error,mensaje){

        }
    });
}

function guardarOrden(){
    ordenid_orden = document.getElementById("ordenid_orden").value;
    productoid_orden = document.getElementById("productoid_orden").value;
    cantidad_orden = document.getElementById("cantidad_orden").value;
    //Validar de forma simple los campos - EXPRESIONES REGULARES
    if(ordenid_orden>0 && productoid_orden>0 && cantidad_orden>0){
        $.ajax({
            url: "/v1/api/orden",
            contentType:"application/json",
            method:"POST",
            data: JSON.stringify({
                ordenid:ordenid_orden,
                productoid:productoid_orden,
                cantidad:cantidad_orden,
            }),
            success: function( resultado ) {
                if(resultado.estado==1){
                    let botones ='<button type="button" class="btn btn-primary mb-2" data-bs-toggle="modal" data-bs-target="#editModal" onclick="seleccionarOrdenActualizar('+resultado.orden.id+');">Edit</button>';
                    botones = botones + ' <button type="button" class="btn btn-danger mb-2" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="seleccionarOrdenEliminar('+resultado.orden.id+');">Delete</button>\n';

                    let tabla = $('#example').DataTable();
                    tabla.row.add([
                        resultado.orden.id,
                        resultado.orden.ordenid,
                        resultado.orden.productoid,
                        resultado.orden.cantidad,
                        botones
                    ]).node().id='renglon_'+resultado.orden.id;

                    tabla.draw()
                    //Ocultar la Modal JQuery
                    $('#basicModal').hide()
                    alert(resultado.mensaje);
                }else{
                    //Todo mal
                    alert(resultado.mensaje);
                }
            },
            error:function (xhr,error,mensaje) {
                //Se dispara la funcion si no conexion al servidor
                alert("Error de comunicacion: "+error);
            }
        });
    }else{
        alert("Ingresa los datos correctamente")
    }
}

function seleccionarOrdenActualizar(id) {
    idOrdenActualizar=id;

    $.ajax({
        method:"GET",
        url: "/v1/api/orden/actualizar/"+idOrdenActualizar,
        data: {},
        success: function( resultado ) {
            if(resultado.estado===1){
                let orden = resultado.orden;
                $('#ordenid_orden_editar').val(orden.ordenid);
                $('#productoid_orden_editar').val(orden.productoid);
                $('#cantidad_orden_editar').val(orden.cantidad);
            }else{
                alert(resultado.mensaje);
            }
        },
        error:function (xhr,error, mensaje) {
            alert(mensaje);
        }
    });
    //3.- Mostrar los datos en el Modal
}

function actualizarOrden() {
    //1.- Obtener los datos que existen en el modal
    ordenid_orden=$('#ordenid_orden_editar').val();
    productoid_orden=$('#productoid_orden_editar').val();
    cantidad_orden=$('#cantidad_orden_editar').val();
    if(ordenid_orden>0 && productoid_orden>0 && cantidad_orden>0){
        $.ajax({
            url: "/v1/api/orden/actualizar/"+idOrdenActualizar,
            contentType:"application/json",
            method:"POST",
            data: JSON.stringify({
                id:idOrdenActualizar,
                ordenid:ordenid_orden,
                productoid:productoid_orden,
                cantidad:cantidad_orden,
            }),
            success: function( resultado ) {
                if(resultado.estado==1){
                    let tabla = $('#example').DataTable();
                    datos = tabla.row("#renglon_"+idOrdenActualizar).data()
                    datos[1]=ordenid_orden;
                    datos[2]=productoid_orden;
                    datos[1]=cantidad_orden;
                    tabla.row("#renglon_"+idOrdenActualizar).data(datos);
                    tabla.draw()
                    alert(resultado.mensaje);
                }else{
                    //Todo mal
                    alert(resultado.mensaje);
                }
            },
            error:function (xhr,error,mensaje) {
                //Se dispara la funcion si no conexion al servidor
                alert("Error de comunicacion: "+error);
            }
        });
    }else{
        alert("Ingresa los datos correctamente")
    }

}

function seleccionarOrdenEliminar(id){
    let datosOrden=$('#example').DataTable().row('#renglon_'+id).data()
    $('#ordenid_eliminar').text(datosOrden[1]+' :(')
    idOrdenEliminar=id
}

function eliminarOrden() {
    $.ajax({
        method: "POST",
        url: "/v1/api/orden/eliminar",
        contentType:"application/json",
        data:JSON.stringify({
            id:idOrdenEliminar,
        }),
        success: function( resultado ) {
            if(resultado.estado===1){
                //Eliminar el renglon del DataTable
                $('#example').DataTable().row('#renglon_'+idOrdenEliminar).remove().draw();
                alert(resultado.mensaje);
            }else{
                alert(resultado.mensaje)
            }
        },
        error:function (xhr,error,mensaje){
            alert("Error de comunicacion "+error)
        }
    });
}
