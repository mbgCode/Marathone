
function llamada1(){
			
    let xhr = new XMLHttpRequest();
        let data;
            
            xhr.onreadystatechange = function () { // ejecuta cada estado de la conexxion, la final es la 4
                  if (xhr.readyState === 4){
                      if (xhr.status === 200) { // 200
                        try{
                            data = JSON.parse(xhr.responseText);            
             
                        }catch (e) {
                           // TODO: handle exception
                           
                       }
                      }                    
                  }
              };

              xhr.open("GET", "SV_deporte?op=1", false); 
              xhr.send();
              
              pintarLista(data);
            
}


/* Utilizamos el for para pontar todos los datos del json. */
function pintarLista(data) {
    const contenedorTarjetas = document.getElementById("contenedorTarjetas");

    // Limpiar contenido previo del contenedor
    contenedorTarjetas.innerHTML = "";

    // Iterar sobre los datos y crear una tarjeta para cada uno
    for (let i = 0; i < data.length; i++) {
<<<<<<< HEAD

=======
>>>>>>> 28c1e9745099950395506ac479b697e1d5a96eaf
        // Crear un nuevo elemento article
        const tarjeta = document.createElement("article");
        tarjeta.id = "tarjeta";

<<<<<<< HEAD
        //el ancala para la imagen que lleva a latarjetaDep.
        const ancla = document.createElement("a")
        ancla.href = "tarjetaDep.html?op=5&id="+data[i].id
        
=======
>>>>>>> 28c1e9745099950395506ac479b697e1d5a96eaf
        // Crear imagen
        const imagen = document.createElement("img");
        imagen.src =  "fotos_deporte/" + data[i].foto; 
        imagen.alt = "Imagen";

        // Crear párrafos para los datos
        const nombreParrafo = document.createElement("p");
        nombreParrafo.textContent = data[i].nombre;
<<<<<<< HEAD
        nombreParrafo.id = "pNombre";

        const categoriaParrafo = document.createElement("p");
        categoriaParrafo.textContent = data[i].categoria;
        categoriaParrafo.id = "pcat";

        // Añadir elementos al artículo con el appendChild como hijos
        tarjeta.appendChild(ancla);
        ancla.appendChild(imagen);//imagen esta como hijo de ancla para que al pulsar nos lleve a la otra pagina.
        tarjeta.appendChild(nombreParrafo);
        tarjeta.appendChild(categoriaParrafo);
=======

        const deporteParrafo = document.createElement("p");
        deporteParrafo.textContent = data[i].telefono // Suponiendo que data[i].deporte contiene el tipo de deporte

        const direccionParrafo = document.createElement("p");
        direccionParrafo.textContent = data[i].categoria;

        // Añadir elementos al artículo con el appendChild
        tarjeta.appendChild(imagen);//
        tarjeta.appendChild(nombreParrafo);
        tarjeta.appendChild(deporteParrafo);
        tarjeta.appendChild(direccionParrafo);
>>>>>>> 28c1e9745099950395506ac479b697e1d5a96eaf

        // Añadir artículo al contenedor con appendchild-
        contenedorTarjetas.appendChild(tarjeta);
    }
}





/* Metodo principal */
window.onload = function(){   

    llamada1();//s vuelve a refrescar la lista para estar actualizada. 
}