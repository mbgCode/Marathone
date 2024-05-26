
/* JS pque no forma parte del programa. Utilizado para listar e insertar datos. */

/* Función para recibir los datos del Sv y pintar tabla */
function llamada1 (){
    fetch ('SV_administrador?op=1')
    .then (response => response.json())
    .then (data => pintarTabla(data)) 
}


/* Función de pintar la tabla con los datos de .JSON */
function pintarTabla(datos){
    let html = "<table border=1>";
       
    /* Bucle for para rellenar la tabla con la cantidad de datos que tenga el archivo */
    for (let i = 0;i<datos.length;i++){//Como datos (json) actua como un array.
        html +="<tr><td>"+datos[i].nombre+"</td><td>"+datos[i].apellidos+"</td>";
        html += "<td>"+datos[i].email+"</td><td>"+datos[i].poblacion+"</td>";
        html += "<td>"+datos[i].permiso+"</td><td>"+datos[i].foto+"</td>"              
        html += "<td>"+datos[i].idadministrador+"</td>";
        html += "<td><a href='administrador.html?idadministrador="+datos[i].idadministrador+"&op=2'>Editar</a></td>";
        html +="</tr>";

    }    
    html +="</table>";
    //le pedimos que busque el id (listado) que hemos llamado dentro de body.
    document.getElementById("listado").innerHTML = html;
}



/* Función para recibirt datos de la URL */
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}




/* Llamada para recibir los datos y pintar en el formulario */
function llamada2(op,id){
    fetch ('SV_administrador?op='+op+'&idadministrador='+id)
    .then (response => response.json())
    .then (data => pintarFormulario(data,op)) 
}


/* Pintamos los datos a modificar en el formulário */
function pintarFormulario(data,op){
    console.log("data es "+data+" y op es "+op)
    if (op==2){
        document.getElementById("nombre").value = data.nombre;
        document.getElementById("apellidos").value = data.apellidos;
        document.getElementById("email").value = data.email;
        document.getElementById("poblacion").value = data.poblacion;
        document.getElementById("permiso").value = data.permiso;
        
    }

}


/* Función principal. Se activa cuando carga la página */
window.onload = function(){

 llamada1();  

    let op = getParameterByName("op");
    let id = getParameterByName("idadministrador");
    console.log(op,id)
    llamada2(op,id);

 llamada1();

}


