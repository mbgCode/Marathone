		
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
		


//Pintamos el FORMULARIO  con los datos del .json que vienen del controlador.
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


//Funcion para recibir la foro del id logeado.
function fotoPersonal(){
    fetch('SV_administrador?op=4')
    .then(response => response.json())// La respuesta (response) se parsea a un .json 
    .then(data => fotoLogin(data))//Los datos de ese .json se pintan en la tabla.
}



//Codigo para añadir fotosuario al header.
function fotoLogin (data){
    let contenedorFoto = document.getElementById("fotoPersonal");
    if (contenedorFoto) {
        // Limpiar el contenido que teniamos como plantilla
        contenedorFoto.innerHTML = "";

        // Crear un nuevo elemento img y le damos la direccion de la foto.
        let foto = document.createElement("img");
        foto.id = "personalFoto";
        foto.src = "Fotos/" + data;

        // Agregar la nueva imagen al contenedor
        contenedorFoto.appendChild(foto);
    }    
}   




 
//Cuando se cargue la ventana sacamos (scado del http) los parametros id y op y los guardamos en la variable.
window.onload = function(){
    let op = getParameterByName("op")
    let id = getParameterByName("idadministrador")

	    llamada(id,op);  
        fotoPersonal()
}

