function llamada (){
    fetch('SV_administrador?op=1')//Se envia la solicitud con op=1
    .then(response => response.json())// La respuesta (response) se parsea a un .json 
    .then(data => pintarTabla(data))//Los datos de ese .json se pintan en la tabla.
}



function pintarTabla(datos){//aqui se pintan los datos. creamos un codigo html.
    let html = "<table id='tabla'>";
        html += "<tr><th>Id</th><th>Nombre</th><th>Apellidos</th><th>Email</th><th>Población</th><th>Permisos</th><th>Fotos</th><tr>"
    for (let i = 0;i<datos.length;i++){//Como datos (json) actua como un array.
        html += "<tr><td id='id'>"+datos[i].idadministrador+"</td>";
        html += "<td id='nombre'>"+datos[i].nombre+"</td><td id='aèllidos'>"+datos[i].apellidos+"</td>";
        html += "<td id='email'>"+datos[i].email+"</td><td id=''poblacion>"+datos[i].poblacion+"</td>";
        html += "<td id=permiso'>"+datos[i].permiso+"</td>";
        html += "<td id='foto'><img id='imagen' src='Fotos/" + datos[i].foto + "' alt='Foto del Administrador'></td>";
        html += "<td><a href='InsertarAdmin.html?idadministrador="+datos[i].idadministrador+"&op=2'> <input type='button' name='editar' value ='Editar' id='btnEdit'> </a></td>";
        html += "<td><a href='ListarAdmin.html?idadministrador="+datos[i].idadministrador+"&op=3'> <input type='button' name='borrar' value ='Borrar' id='btnBorr'> </a></td>";
        html +="</tr>";

    }    
    html +="</table>";
    
    
    //le pedimos que busque el id (listado) que hemos llamado dentro de body.
    document.getElementById("listado").innerHTML = html;
}



//Con el id a borrar y op para hacer el borrado.
function borrar (id, op){	
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





//Cuando se cargue la pantalle ejecutamos funcion "llamada"
window.onload = function(){

 llamada(); //Llamada para pintar listado.
 fotoPersonal()
 
 //Recogemos los parametros.
 let op = getParameterByName("op")
 let id = getParameterByName("idadministrador")

 //Si la op es 3 es que se va a borrar la info de esa id.
 if(op==3){
    borrar(id,op)
 }
 
 //refrescamos la tabla ya sin el id borrado
 llamada();

 
}


