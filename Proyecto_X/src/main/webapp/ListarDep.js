
function llamada() {    
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



window.onload = function() {

    llamada();  


} 