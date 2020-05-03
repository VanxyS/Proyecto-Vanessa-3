<?php
    $booleano = false;

    //Recivimos el nombre del luchador, sus apellidos y su nueva puntuación
    //$usu = $_POST['usuario'];
	//$contrasenia = $_POST['contrasenia'];
    $nombre=$_POST["nombre"];
    $apellido1=$_POST["apellido1"];
    $puntuacion=$_POST["puntuacion"];

    //Conectamos a la base de datos
    $conexion= mysqli_connect("localhost", "vanessa", "vanessasantospuente", "proyectodam");

    //$consultaComprobacion="SELECT * FROM usuarios WHERE nombre LIKE '$nombre'";
    //$resultComprobacion = mysqli_query($conexion, $consulta);

    //($resultComprobacion)
    //{
        //$filasComprobacion = mysqli_num_rows($result);

        //if($filasComprobacion > 0)
        //{
            //$row = mysqli_fetch_assoc($result);

            ///$contraseniaComprobar = $row['contrasenia'];
            //$rol = $row['idRol'];

            //if($contrasenia == $contraseniaComprobar && $rol == 2)
            //{
                //Hacemos otra consulta para comprobar si el usuario existe y obtener sus datos
                $consulta="SELECT * FROM luchadores WHERE Nombre LIKE '$nombre' AND Apellido1 LIKE '$apellido1'";
                $result = mysqli_query($conexion, $consulta);

                if($result)
                {
                    $filas = mysqli_num_rows($result);

                    if($filas > 0)
                    {
                        $row = mysqli_fetch_assoc($result);

                        $puntuacionInicial = $row['Puntuacion'];

                        $puntuacionFinal = $puntuacionInicial + $puntuacion;


                        $consultaModificacion="UPDATE luchadores SET Puntuacion = $puntuacionFinal WHERE Nombre LIKE '$nombre' AND Apellido1 LIKE '$apellido1'";
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
                        //}
                    //}
                //}
            //}
        //}
    //}

    $response = array();
    $response["success"] = $booleano;

    echo json_encode($response, JSON_UNESCAPED_UNICODE);
	
	mysqli_close($conexion);
?>