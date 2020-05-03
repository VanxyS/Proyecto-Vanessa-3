<?php
    //$usu = $_POST['usuario'];
	//$contrasenia = $_POST['contrasenia'];
    $booleano = false;

    //Recibimos el nombre, la contrasenia y el email
    $pueblo=$_POST["pueblo"];
    $dia=$_POST["dia"];

    //Conectamos a la base de datos
    $conexion= mysqli_connect("localhost", "vanessa", "vanessasantospuente", "proyectodam");

    //Hacemos otra consulta para comprobar si el usuario existe y obtener sus datos
    $consulta="SELECT * FROM corros WHERE Pueblo LIKE '$pueblo' AND Dia LIKE '$dia'";
    $result = mysqli_query($conexion, $consulta);

    if($result)
    {
        $filas = mysqli_num_rows($result);

        if($filas > 0)
        {
            $consultaModificacion="DELETE FROM corros WHERE Pueblo LIKE '$pueblo' AND Dia LIKE '$dia'";
            $resultModificacion = mysqli_query($conexion, $consultaModificacion);

            if($resultModificacion > 0)
            {
                $booleano = true;
            }
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