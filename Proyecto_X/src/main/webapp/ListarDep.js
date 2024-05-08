
function llamada() {    
    fetch('SV_deporte?op=1')
    .then(response => response.json())
    .then(data => pintarLista(data));
}

function pintarLista(data) {
    let html = "<table border=1>";
    const nombreInput = document.getElementById('nombre')
    for (let i = 0; i < data.length; i++) {
        html += "<tr><td>" + data[i].id + "</td><td>" +data[i].nombre + "</td></tr>";
        html += "<tr><td>" + data[i].descripcion + "</td><td>" + data[i].telefono + "</td></tr>";
        html += "<tr><td>" + data[i].direccion + "</td><td>" + data[i].foto + "</td></tr>";
        html += "<tr><td>" + data[i].categoria + "</td>";
        html += "<td><a href='insertarDeporte.html?id=" + data[i].id + "&op=2'>Editar</a></td></tr>";
    }   

    html += "</table>";    
    
    document.getElementById("listado").innerHTML = html;
}



window.onload = function() {
    

    llamada();  
}