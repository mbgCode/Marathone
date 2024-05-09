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


function pintarFormulario(data){
   
    document.getElementById("nombre").value = data.nombre;
    document.getElementById("descripcion").value = data.descripcion;
    document.getElementById("telefono").value = data.telefono;
    document.getElementById("direccion").value = data.direccion;
    document.getElementById("categoria").value = data.categoria;
}


window.onload = function(){

    let id = getParameterByName("id")
    let op = getParameterByName("op")
  

    llamada(id,op);
}