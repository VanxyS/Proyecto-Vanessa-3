<?php
    $booleano = false;

	//Recibimos el nombre
    $nombre=$_POST["nombre"];
    $email=$_POST["email"];

    //Conectamos a la base de datos
	$conexion= mysqli_connect("localhost", "vanessa", "vanessasantospuente", "proyectodam");
    
    //Hacemos una consulta para comprobar si existe algun usuario con ese nombre
	$consulta="SELECT * FROM usuarios WHERE (nombre LIKE '$nombre' OR email LIKE '$email')";
    $result = mysqli_query($conexion, $consulta);
    
    if($result)
	{
		$filas = mysqli_num_rows($result);

		if($filas == 0)
		{
            $booleano = true;
        }
    }

    else
	{
		print mysqli_error($conexion);
		echo $error;
	}

    //Creamos un json con el valor del booleano y lo devolvemos
	$response = array();
	$response["respuesta"] = $booleano;
	echo json_encode($response, JSON_UNESCAPED_UNICODE);
		
	//Cerramos la conexión
	mysqli_close($conexion);
?>