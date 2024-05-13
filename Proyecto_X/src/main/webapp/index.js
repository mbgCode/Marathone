
function llamada(){
			
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

    // Con InnerHTML eliminamos todo los contenidos hijos que pueda tener contenedorTarjetas
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
        deporteParrafo.textContent = data[i].telefono // Suponiendo que data[i].deporte contiene el tipo de deporte

        const direccionParrafo = document.createElement("p");
        direccionParrafo.textContent = data[i].categoria;

        // Añadir elementos al artículo con el appendChild como hijos
        tarjeta.appendChild(imagen);//
        tarjeta.appendChild(nombreParrafo);
        tarjeta.appendChild(deporteParrafo);
        tarjeta.appendChild(direccionParrafo);

        // Añadir artículo al contenedor con appendchild-
        contenedorTarjetas.appendChild(tarjeta);
    }
   
}




function redireccionar() {//El selctor de categorías.
    var select = document.getElementById("categoria");
    var selectedOption = select.options[select.selectedIndex];
    if (selectedOption.value !== "...") {
        window.location.href = selectedOption.value;
    }
}


/* Metodo principal */
window.onload = function(){   
   
    llamada();

    //Barra Busqueda, para recoger los datos de 
    var botonLupa = document.getElementById("imgLupa");
    botonLupa.addEventListener("click", function() {
        var palabraBuscar = document.querySelector(".barraBuscar").value; /* El . de barraBuscar es porque lo sacamos de la clase de CSS */
       /* Queryselector devuelve el primer elemento que coincida con barraBuscar.*/
        console.log(palabraBuscar)
    });
}