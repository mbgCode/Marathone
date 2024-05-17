		
//Recibimos de ListarAdmin.html el id y op
function llamada (id, op){
    fetch ('SV_administrador?idadministrador='+id+'&op='+op)
	.then (response => response.json())
	.then (data => pintarFormulario(data,op))
 
}
		
		
		
function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	results = regex.exec(location.search);
	return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
		

//Pintamos la tabla con los datos del .json que vienen del controlador.
function pintarFormulario (data,op){
    console.log("estamos aqui")
    if (op==2){
        
        document.getElementById("nombre").value = data.nombre;
        document.getElementById("apellidos").value = data.apellidos;
        document.getElementById("email").value = data.email;
        document.getElementById("poblacion").value = data.poblacion;
        document.getElementById("permiso").value = data.permiso;
        document.getElementById("idadministrador").value=data.idadministrador;
    
    }


}



 
//Cuando se cargue la ventana sacamos (scado del http) los parametros id y op y los guardamos en la variable.
window.onload = function(){
    let op = getParameterByName("op")
    let id = getParameterByName("idadministrador")
    console.log(id,op)
	    llamada(id,op);  

}

