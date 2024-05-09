function llamada(id,op) {

    fetch ('SV_deporte?id='+id+"&op="+op)
    .then (response => response.json() )
    .then (data => pintarFormulario(data))

}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}


function pintarFormulario(data) {
    document.getElementById("id").value = data.id;
    document.getElementById("nombre").value = data.nombre;
    document.getElementById("descripcion").value = data.descripcion;
    document.getElementById("telefono").value = data.telefono;
    document.getElementById("direccion").value = data.direccion;
    document.getElementById("categoria").value = data.categoria;


}



function llamada1() {    
    fetch('SV_deporte?op=1')
    .then(response => response.json())
    .then(data => pintarLista(data));
}

function pintarLista(data) {
    let html = "<table>";
         
    for (let i = 0; i < data.length; i++) {
        html += "<tr id='fila'><td id='columnaId'>" + data[i].id + "</td><td id = 'columnaNom'>" + data[i].nombre + "</td>";
        html += "<td id = 'columnaDesc'>" + data[i].descripcion + "</td><td id = 'columnaTel'>" + data[i].telefono + "</td>";
        html += "<td id = 'columnaDir'>" + data[i].direccion + "</td><td id = 'columnaFoto'>" + data[i].foto + "</td>";
        html += "<td id = 'columnaCate'>" + data[i].categoria + "</td>";
        html += "<td id = 'columnaEdit'><a href='insertarDep.html?id=" + data[i].id + "&op=2'>Editar</a></td>";
        html += "<td><a href='ListarDep.html?id="+ data[i].id +"&op=3'>Borrar</a></td></tr>";
    }   


    html += "</table>";    
    
    document.getElementById("listado").innerHTML = html;
}




window.onload = function(){
    llamada1();
    let id = getParameterByName("id")
    let op = getParameterByName("op")
  

    llamada(id,op);
}