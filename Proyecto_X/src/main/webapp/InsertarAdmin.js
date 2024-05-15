	
	
		//Recibimos de ListarAdmin.html el id y op2
		function llamada (id, op){
		
            fetch ('SV_administrador?idadministrador='+id+ "&op="+op) //envia la solicitud a SV_admin con el id y op recibidos
			.then (response => response.json())// La respuesta (response) se parsea a un .json 
			.then (data => pintarFormulario(data));	//Los datos de ese .json se pintan en la tabla.
		}
		
		
		
        function getParameterByName(name) {
		    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		    results = regex.exec(location.search);
		    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
		}
		

		//Pintamos la tabla con los datos del .json que vienen del controlador.
        function pintarFormulario (datos){
            document.getElementById("idadministrador").value = datos.idadministrador;
            document.getElementById("nombre").value = datos.nombre;
            document.getElementById("apellidos").value = datos.apellidos;
            document.getElementById("email").value = datos.email;
            document.getElementById("poblacion").value = datos.poblacion;
            document.getElementById("permiso").value = datos.permiso;
        }

 
 		//Cuando se cargue la ventana sacamos (scado del http) los parametros id y op y los guardamos en la variable.
        window.onload = function(){
            let op = getParameterByName("op")
            let id = getParameterByName("idadministrador")
	     llamada(id,op);  
        }

