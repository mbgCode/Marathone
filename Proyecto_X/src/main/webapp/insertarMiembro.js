
/* Fetch para recibir datos de id concreto */
function modificar (op,id){
    fetch ('SV_miembro?op='+op+"&idmiembro="+id)
    .then(response => response.json())
    .then(data=>pintarFormulario(data,op))
}




function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	results = regex.exec(location.search);
	return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}



function pintarFormulario(data,op){
    
    if(op==2){/* Si op es igual a 2 es que viene del boton EDITAR */
        document.getElementById("nombre").value = data.nombre;
        document.getElementById("apellidos").value = data.apellidos;
        document.getElementById("email").value = data.email;
        document.getElementById("poblacion").value = data.poblacion;
        document.getElementById("permiso").value= data.permiso;
        document.getElementById("edad").value=data.edad;
        document.getElementById("idmiembro").value=data.id;
    }
  


}


window.onload = function(){
 

 let op = getParameterByName("op");
 let id = getParameterByName("idmiembro");
 

 modificar(op,id);


}