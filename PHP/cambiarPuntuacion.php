<?php
    $booleano = false;

    //Recivimos el nombre del luchador, sus apellidos y su nueva puntuación
    $nombre=$_GET["nombre"];
    $apellido1=$_GET["apellido1"];
    $apellido2=$_GET["apellido2"];
    $puntuacion=$_GET["puntuacion"];

    //Conectamos a la base de datos
    $conexion= mysqli_connect("localhost", "vanessa", "vanessasantospuente", "proyectodam");

    //Hacemos otra consulta para comprobar si el usuario existe y obtener sus datos
    $consulta="SELECT * FROM luchadores WHERE Nombre LIKE '$nombre' AND Apellido1 LIKE '$apellido1' AND Apellido2 LIKE '$apellido2'";
    $result = mysqli_query($conexion, $consulta);

    if($result)
    {
        $filas = mysqli_num_rows($result);

        if($filas > 0)
        {
            $consultaModificacion="UPDATE luchadores SET Puntuacion = $puntuacion WHERE Nombre LIKE '$nombre' AND Apellido1 LIKE '$apellido1' AND Apellido2 LIKE '$apellido2'";
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

    $response = array();
    $response["success"] = $booleano;

    echo json_encode($response, JSON_UNESCAPED_UNICODE);
	
	mysqli_close($conexion);
?>