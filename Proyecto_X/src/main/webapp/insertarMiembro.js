
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
        document.getElementById("edad").value=data.edad;
        document.getElementById("idmiembro").value=data.id;
        console.log(data.id);
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



window.onload = function(){


 let op = getParameterByName("op");
 console.log("esto es op " +op+ "--")
 let id = getParameterByName("idmiembro");
 


    if (op!=0){
        fotoPersonal();
        console.log(" los datos son " +op)
        modificar(op,id);
        fotoPersonal();
    }


}