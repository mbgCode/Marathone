function llamada (){
    fetch('SV_miembro?op=1')//Se envia la solicitud con op=1
    .then(response => response.json())// La respuesta (response) se parsea a un .json 
    .then(data => pintarTabla(data))//Los datos de ese .json se pintan en la tabla.
}



function pintarTabla(datos){

    let html = "<table id='tabla'>";
        html +=  "<tr id='cabezaTabla'> <th>Id</th><th>Nombre</th><th>Apellidos</th><th>Mail</th><th>Población</th><th>Foto</th><th>Edad</th> </tr>"
        
    for (let i = 0;i<datos.length;i++){//Como datos (json) actua como un array.
        html += "<tr><td id = 'id'>"+datos[i].id+"</td><td id = 'nombre'>"+datos[i].nombre+"</td><td id ='apellidos'>"+datos[i].apellidos+"</td>";
        html += "<td id ='email'>"+datos[i].email+"</td><td id = 'poblacion'>"+datos[i].poblacion+"</td>";
        html += "<td id = 'foto'><img id='imagen' src='Fotos/"+datos[i].foto+"' alt='Foto de miembro'></td>"; 
        html += "<td id = 'edad'>"+datos[i].edad+"</td>";
        html += "<td><a href='insertarMiembro.html?idmiembro="+datos[i].id+"&op=2'><input id='btnEdit' type='button' name='editar' value='Editar'></a></td>";
        html += "<td><a href='ListarMiembro.html?idmiembro="+datos[i].id+"&op=4'><input id='btnBorr' type='button' name='borrar' value='Borrar'></a></td></tr>";

    }	
    
    html +="</table>";
    //le pedimos que busque el id (listado) que hemos llamado dentro de body.
    document.getElementById("listado").innerHTML = html;

}




//Con el id a borrar y op para hacer el borrado.
function borrar (op, id){	

    fetch ('SV_miembro?op='+op+'&idmiembro='+id) //envia la solicitud a SV_admin con el id y op recibidos
	.then (response => response.json())// La respuesta (response) se parsea a un .json 
	.then (data => pintarFormulario(data,op));	//Los datos de ese .json se pintan en la tabla.
   
}


function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	results = regex.exec(location.search);
	return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}




//Cuando se cargue la pantalle ejecutamos funcion "llamada"
window.onload = function(){

 llamada(); //Llamada para pintar listado.

 //Recogemos los parametros.
 let op = getParameterByName("op")
 let id = getParameterByName("idmiembro")

 //Si la op es 4 es que se va a borrar la info de esa id.
 if(op==4){
    borrar(op,id)
    llamada(); //refrescamos la tabla ya sin el id borrado
 }


 

 
}


