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
        html += "<td><a href='insertarMiembro.html?idmiembro="+datos[i].id+"&op=3'><input id='btnBorr' type='button' name='borrar' value='Borrar'></a></td></tr>";

    }	
    
    html +="</table>";
    //le pedimos que busque el id (listado) que hemos llamado dentro de body.
    document.getElementById("listado").innerHTML = html;

}



window.onload = function(){

  llamada();

}