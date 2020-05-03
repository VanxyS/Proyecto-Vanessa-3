<?php
    $usu = $_POST['usuario'];
	$contrasenia = $_POST['contrasenia'];

    $booleano = false;

    //Recibimos el nombre del usuario o su email
    $nombre=$_POST["nombre"];

    //Conectamos a la base de datos
    $conexion= mysqli_connect("localhost", "vanessa", "vanessasantospuente", "proyectodam");

    //Hacemos otra consulta para comprobar el peso al que pertenece
    $consulta="SELECT * FROM usuarios WHERE nombre LIKE '$nombre'";
    $result = mysqli_query($conexion, $consulta);

    if($result)
    {
        $filas = mysqli_num_rows($result);

        if($filas > 0)
        {
            $consultaModificacion="UPDATE usuarios SET idRol = 2 WHERE nombre LIKE '$nombre'";
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