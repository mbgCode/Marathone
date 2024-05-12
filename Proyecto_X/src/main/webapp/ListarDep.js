
function llamada1(cat) {    
    fetch('SV_deporte?op=4&categoria='+cat)
    .then(response => response.json())
    .then(data => pintarLista(data));
}



function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}




/* Utilizamos el for para pontar todos los datos del json. */
function pintarLista(data) {
    const contenedorTarjetas = document.getElementById("contenedorTarjetas");
    

    //Este es el header.
    const centro = document.getElementById("centro");
    centro.textContent = data[0].categoria;

    // Limpiar contenido previo del contenedor
    contenedorTarjetas.innerHTML = "";

    // Iterar sobre los datos y crear una tarjeta para cada uno
    for (let i = 0; i < data.length; i++) {
        // Crear un nuevo elemento article
        const tarjeta = document.createElement("article");
        tarjeta.id = "tarjeta";

        // Crear imagen
        const imagen = document.createElement("img");
        imagen.src =  "fotos_deporte/" + data[i].foto; 
        imagen.alt = "Imagen";

        // Crear párrafos para los datos
        const nombreParrafo = document.createElement("p");
        nombreParrafo.textContent = data[i].nombre;

        const deporteParrafo = document.createElement("p");
        deporteParrafo.textContent = data[i].telefono 

        const direccionParrafo = document.createElement("p");
        direccionParrafo.textContent = data[i].categoria;

        // Añadir elementos al artículo con el appendChild
        tarjeta.appendChild(imagen);//
        tarjeta.appendChild(nombreParrafo);
        tarjeta.appendChild(deporteParrafo);
        tarjeta.appendChild(direccionParrafo);

        // Añadir artículo al contenedor con appendchild-
        contenedorTarjetas.appendChild(tarjeta);
    }
}





/* Metodo principal */
window.onload = function(){   
    
    let cat = getParameterByName("categoria");
   
    llamada1(cat);//s vuelve a refrescar la lista para estar actualizada. 
   
}