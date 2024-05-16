function llamada (){
    fetch ('SV_miembro?op=1')
    .then (response => response.json())
    .then (data => pintarTabla(data))
    
}



function pintarTabla(datos){ //aqui se pintan los datos. creamos un codigo html.
    let html = "<table border=1>";
        
    for (let i = 0;i<datos.length;i++){//Como datos (json) actua como un array.
        html +="<tr><td>"+datos[i].nombre+"</td><td>"+datos[i].apellidos+"</td>";
        html += "<td>"+datos[i].email+"</td><td>"+datos[i].poblacion+"</td>";
        html += "<td>"+datos[i].permiso+"</td><td>"+datos[i].foto+"</td>"; 
        html += "<td>"+datos[i].permiso+"</td>";
        html += "<td>"+datos[i].edad+"</td><td>"+datos[i].id+"</td></tr>";
    }	
    
    html +="</table>";
    //le pedimos que busque el id (listado) que hemos llamado dentro de body.
    document.getElementById("listado").innerHTML = html;

}




window.onload = function(){
 llamada();  

}