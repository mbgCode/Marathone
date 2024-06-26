      
 /* Funcion llamada para recibir datos del json. */      
 function llamada(id){
			
    let xhr = new XMLHttpRequest();
        let data; 
                    
            xhr.onreadystatechange = function () { 
                if (xhr.readyState === 4){
                    if (xhr.status === 200) { 
                         try{
                            data = JSON.parse(xhr.responseText);            
                            pintarDatos(data); //Pintamos los datos en este punto. 
                        }catch (e) {                            
                                   
                        }
                    }                    
                }
            };               
            xhr.open("GET", "SV_deporte?op=5&id="+id, true); 
            xhr.send();
                                                     
}


/* Funcion pintar los datos de json */
function pintarDatos(data){

    let section = document.getElementById("contenedorInferior");

    let contFoto = document.getElementById("contenedorFoto");
    let contDatos = document.getElementById("contenedorDatos");
    

    let foto = document.createElement("img");
    foto.src = "fotos_deporte/"+data.foto;
    foto.alt = "Foto de la entidad deportiva."
    contFoto.appendChild(foto);//anidamos foto en contFoto.
 
    //nombre del deporte
    let nombre = document.createElement("h1");
    nombre.textContent = data.nombre;
    nombre.id = "nombreDep"  
    contDatos.appendChild(nombre)//anidamos nombre en contDatos
    
    //Categoría
    let categoria = document.createElement("p");
    categoria.textContent = data.categoria;
    categoria.id = "categoriaDep"
    contDatos.appendChild(categoria);//anidadmos categoria en contDatos
   
    //Teléfono
    let telefono = document.createElement("p");
    telefono.textContent = data.telefono;
    telefono.id = "telefonoDep"
    contDatos.appendChild(telefono);//anidadmos teléfono en contDatos

    //Dirección
    let direccion = document.createElement("p")
    direccion.textContent = data.direccion;
    direccion.id = "direccionDep"
    contDatos.appendChild(direccion);

    //Descripción 
    let descripcion = document.createElement("p")
    descripcion.textContent = data.descripcion;
    descripcion.id = "descripcionDep";
    contDatos.appendChild(descripcion);
    

}



//Función para recoger los parametros de la url.
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}



//Función principal.
window.onload = function(){   

    var id = getParameterByName("id");//Guardamos el id en la variable.
    
    llamada(id);//Hacemo la llamada de la funcion con atributo id para enviar al SV
   
}