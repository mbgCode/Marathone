/* Llamada para update y pintar los datos del formulario. */
function llamada(id,op) {
    fetch ('SV_deporte?id='+id+"&op="+op)
    .then (response => response.json())
    .then (data => pintarFormulario(data,op))

}


function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}


/* Esta funcion pinta el formulario para modificar */
function pintarFormulario(data,op) {
    if(op==2){/* Si opcion es igual 2 es que se quieren modificar datos */
        document.getElementById("id").value = data.id;
        document.getElementById("nombre").value = data.nombre;
        document.getElementById("descripcion").value = data.descripcion;
        document.getElementById("telefono").value = data.telefono;
        document.getElementById("direccion").value = data.direccion;
        document.getElementById("categoria").value = data.categoria;
    }

}

/* -------------------------------------------------------------------------------------------------------------------------------- */
/* Fetch para pintar la TABLA*/
function llamada1() {    
    fetch('SV_deporte?op=1')
    .then(response => response.json())
    .then(data => pintarLista(data));
}


/* Esta es la funcion que pinta la tabla */
function pintarLista(data) {
    let html = "<table>";
         
    for (let i = 0; i < data.length; i++) {
        html += "<tr id='fila'><td id='columnaId'>" + data[i].id + "</td><td id = 'columnaNom'>" + data[i].nombre + "</td>";
        html += "<td id = 'columnaDesc'>" + data[i].descripcion + "</td><td id = 'columnaTel'>" + data[i].telefono + "</td>";
        html += "<td id = 'columnaDir'>" + data[i].direccion +"</td>";
        html += "<td id = 'columnaFoto'> <img id='imagen' src='fotos_deporte/" + data[i].foto + "' alt='Foto del deporte'></img></td>"
        html += "<td id = 'columnaCate'>" + data[i].categoria + "</td>";
        html += "<td id = 'columnaEdit'><a href='insertarDep.html?id=" + data[i].id + "&op=2'><input type='button' id='btnEdit' value='Editar'></a></td>";
        html += "<td><a href='insertarDep.html?id="+ data[i].id +"&op=3'><input type='button' id='btnBorrar' value='Borrar'></a></td></tr>";
    }   

  

    html += "</table>";    
    document.getElementById("listado").innerHTML = html;
}


/* ---------------------------------------------------------------------------------------------------------------------------------------- */





/* Metodo principal */
window.onload = function(){   
    llamada1();

    let id = getParameterByName("id")
    let op = getParameterByName("op")

    llamada(id,op);

    llamada1();//s vuelve a refrescar la lista para estar actualizada. 
}