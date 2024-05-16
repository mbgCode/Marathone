		
//Recibimos de ListarAdmin.html el id y op
function llamada (id, op){
		
    fetch ('SV_administrador?op='+op+'&idadministrador='+id) //envia la solicitud a SV_admin con el id y op recibidos
	.then (response => response.json())// La respuesta (response) se parsea a un .json 
	.then (data => pintarFormulario(data,op));	//Los datos de ese .json se pintan en la tabla.
   
}
		
		
		
function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	results = regex.exec(location.search);
	return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
		

//Pintamos la tabla con los datos del .json que vienen del controlador.
function pintarFormulario (data,op){
    if (op==2){
        
        document.getElementById("nombre").value = data.nombre;
        document.getElementById("apellidos").value = data.apellidos;
        document.getElementById("email").value = data.email;
        document.getElementById("poblacion").value = data.poblacion;
        document.getElementById("permiso").value = data.permiso;
    }


}



 
//Cuando se cargue la ventana sacamos (scado del http) los parametros id y op y los guardamos en la variable.
window.onload = function(){
    let op = getParameterByName("op")
    let id = getParameterByName("idadministrador")
    console.log(id,op);
	    llamada(id,op);  

}

