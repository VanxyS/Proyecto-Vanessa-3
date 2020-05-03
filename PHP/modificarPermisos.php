<?php
    $booleano = false;

    //Recivimos el nombre del luchador, sus apellidos y su nueva puntuación
    $usu = $_POST['usuario'];
    $idRol = $_POST['idRol'];

    //Conectamos a la base de datos
    $conexion= mysqli_connect("localhost", "vanessa", "vanessasantospuente", "proyectodam");

    $consultaComprobacion="SELECT * FROM usuarios WHERE nombre LIKE '$usu'";
    $resultComprobacion = mysqli_query($conexion, $consultaComprobacion);

    if($resultComprobacion)
    {
        $filasComprobacion = mysqli_num_rows($resultComprobacion);

        if($filasComprobacion > 0)
        {
            $consultaModificacion="UPDATE usuarios SET idRol = $idRol WHERE nombre LIKE '$usu'";
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