let an = {
    id: 0
}

//Funcion para recolectar el id
function setId(id) {
    an.id = id
}
$(document).ready(inicio);
//Se inicializa la funcion  para cargar los datos de la tabla Servicios
function inicio() {
    //Cargar los datos de la tabla servicio
    cargarDatos();
    //Evento  click con el boton guardar para el nuevo registro
    $("#btnGuardar").click(guardar);
    //Evento click del boton eliminar servicio
    $("#btnEliminar").click(function () {
        eliminar(an.id)
    });
    //Evento click del boton actualizar servicio
    $("#btnActualizar").click(modificar);
    //Evento click para cancelar
    $("#btnCancelar").click(reset);
}
// funcion para resetar el formulario con los inputs/
function reset() {
    $("#nombre").val(null);
    $("#descripcion").val(null);
    $("#categoria").val(null);

    $("#nombre2").val(null);
    $("#descripcion2").val(null);
    $("#categoria2").val(null);
}
// metodo para cargar los datos con una peticion ajax en la tabla de servicio//
function cargarDatos() {
    $.ajax({
        url: "http://localhost:8080/servicio/",
        method: "Get",
        data: null,
        success: function (response) {
            $("#datos").html("");

            for (let i = 0; i < response.length; i++) {
                $("#datos").append(
                    "<tr>" +
                    "<td>" + response[i].codigo + "</td>" +
                    "<td>" + response[i].nombre + "</td>" +
                    "<td>" + response[i].fechaCreacion + "</td>" +
                    "<td>" + response[i].descripcion + " </td>" +
                    "<td>" + response[i].categoria + "</td>" +
                    "<td>" +
                    "<button onclick='cargarRegistro(" + response[i].codigo +
                    ")'type='button' class='btn btn-warning ml-3 mt-1' data-toggle='modal' data-target='#editar'><i class='fas fa-edit'></i>Editar</button>" +
                    "<button onclick='setId(" + response[i].codigo +
                    ")' type='button' class='btn btn-danger ml-3 mt-1' style='color: black' data-toggle='modal' data-target='#eliminar'><i class='fas fa-trash-alt'></i> Eliminar</button>" +
                    "</td>" +
                    "</tr>"
                )
            }
        },
        error: function (response) {
            alert("Error: " + response);
        }
    });
}
// funcion para guardar un nuevo registro atraves de una peticion ajax//
function guardar(response) {
    var fecha = new Date();
    $.ajax({
        url: "http://localhost:8080/servicio/save/",
        method: "Get",
        data: {
            codigo: null,
            nombre: $("#nombre").val(),
            descripcion: $("#descripcion").val(),
            categoria: $("#categoria").val(),
            fecha: fecha

        },
        success: function () {
            cargarDatos();
            reset();
        },
        error: function (response) {
            alert("Error en la peticion: " + response)
        }
    })
}
//funcion para eliminar el registro//
function eliminar(id) {
    $.ajax({
        url: "http://localhost:8080/servicio/" + id,
        method: "Delete",
        success: function () {
            cargarDatos();
        },
        error: function (response) {
            alert("Error en la peticion: " + response)
        }
    })
}
//funcion cargar el registro mediante el id//
function cargarRegistro(id) {
    $.ajax({
        url: "http://localhost:8080/servicio/" + id,
        method: "Post",
        success: function (response) {
            $("#codigo2").val(response.codigo)
            $("#nombre2").val(response.nombre)
            $("#descripcion2").val(response.descripcion)
            $("#categoria2").val(response.categoria)
            $("#fecha2").val(response.fechaCreacion)

        },
        error: function (response) {
            alert("Error en la peticion: " + response)
        }
    })
}
//function para modificar el registro//
function modificar() {
    var id = $("#codigo2").val();
    var fecha = new Date();
    $.ajax({
        url: "http://localhost:8080/servicio/update/",
        method: "Put",
        data: {
            codigo: id,
            nombre: $("#nombre2").val(),
            descripcion: $("#descripcion2").val(),
            categoria: $("#categoria2").val(),
            fecha: fecha
        },
        success: function (response) {
            cargarDatos();
            reset();
        },
        error: function (response) {
            alert("Error en la peticion: " + response);
            console.log(id);
        }
    });
}