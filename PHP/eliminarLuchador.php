<?php
    //$usu = $_POST['usuario'];
	//$contrasenia = $_POST['contrasenia'];
    $booleano = false;

    //Recibimos el nombre, la contrasenia y el email
    $nombre=$_POST["nombre"];
    $apellido1=$_POST["apellido1"];

    //Conectamos a la base de datos
    $conexion= mysqli_connect("localhost", "vanessa", "vanessasantospuente", "proyectodam");

    //Hacemos otra consulta para comprobar si el usuario existe y obtener sus datos
    $consulta="SELECT * FROM luchadores WHERE Nombre LIKE '$nombre' AND Apellido1 LIKE '$apellido1'";
    $result = mysqli_query($conexion, $consulta);

    if($result)
    {
        $filas = mysqli_num_rows($result);

        print($filas);

        if($filas > 0)
        {
            print($filas);
            $consultaModificacion="DELETE FROM luchadores WHERE Nombre LIKE '$nombre' AND Apellido1 LIKE '$apellido1'";
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