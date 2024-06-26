
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

        //el ancala para la imagen que lleva a latarjetaDep.
        const ancla = document.createElement("a")
        ancla.href = "tarjetaDep.html?op=5&id="+data[i].id
        // Crear imagen
        const imagen = document.createElement("img");
        imagen.src =  "fotos_deporte/" + data[i].foto; 
        imagen.alt = "Imagen";

      // Crear párrafos para los datos
      const nombreParrafo = document.createElement("p");
      nombreParrafo.textContent = data[i].nombre;
      nombreParrafo.id = "pNombre";

      const categoriaParrafo = document.createElement("p");
      categoriaParrafo.textContent = data[i].categoria;
      categoriaParrafo.id = "pcat";

      // Añadir elementos al artículo con el appendChild como hijos
      tarjeta.appendChild(ancla);
      ancla.appendChild(imagen);//imagen esta como hijo de ancla para que al pulsar nos lleve a la otra pagina.
      tarjeta.appendChild(nombreParrafo);
      tarjeta.appendChild(categoriaParrafo);

      // Añadir artículo al contenedor con appendchild-
      contenedorTarjetas.appendChild(tarjeta);
    }
}






/* Metodo principal */
window.onload = function(){   
    
    let cat = getParameterByName("categoria");
   
    llamada1(cat);//s vuelve a refrescar la lista para estar actualizada. 


}

   
