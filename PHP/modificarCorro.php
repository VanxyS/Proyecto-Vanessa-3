<?php
    $booleano = false;

    //Recivimos el nombre del luchador, sus apellidos y su nueva puntuación
    //$usu = $_POST['usuario'];
	//$contrasenia = $_POST['contrasenia'];
    $pueblo=$_POST["pueblo"];
    $nuevopueblo=$_POST["nuevoPueblo"];
    $dia=$_POST["dia"];
    $hora=$_POST["hora"];
    $lugar=$_POST["lugar"];

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
                $consulta="SELECT * FROM corros WHERE Pueblo LIKE '$pueblo'";
                $result = mysqli_query($conexion, $consulta);

                if($result)
                {
                    $filas = mysqli_num_rows($result);

                    print($filas);

                    if($filas > 0)
                    {
                        if(!empty($pueblo) or !empty($dia) or !empty($hora) or !empty($lugar))
                        {
                            while($registro=mysqli_fetch_array($result))
                            {
                                if(!empty($dia))
                                {
                                    print("dia");
                                    $consultaModificacion2="UPDATE corros SET Dia = '$dia' WHERE Pueblo = '$pueblo'";
                                    $resultModificacion2 = mysqli_query($conexion, $consultaModificacion2);

                                    if($resultModificacion2 > 0)
                                    {
                                        $booleano = true;
                                    }
                                }

                                if(!empty($hora))
                                {
                                    print("hora");
                                    $horaformat = date_create_from_format('H:i:s', $hora);
                                    $horaFinal = date_format($horaformat, 'H:i:s');

                                    $consultaModificacion3="UPDATE corros SET Hora = '$horaFinal' WHERE Pueblo = '$pueblo'";
                                    $resultModificacion3 = mysqli_query($conexion, $consultaModificacion3);

                                    if($resultModificacion3 > 0)
                                    {
                                        $booleano = true;
                                    }
                                }

                                if(!empty($lugar))
                                {
                                    print("lugar");
                                    $consultaModificacion4="UPDATE corros SET Lugar = '$lugar' WHERE Pueblo = '$pueblo'";
                                    $resultModificacion4 = mysqli_query($conexion, $consultaModificacion4);

                                    if($resultModificacion4 > 0)
                                    {
                                        $booleano = true;
                                    }
                                }

                                if(!empty($nuevopueblo))
                                {
                                    $consultaModificacion="UPDATE corros SET Pueblo = '$nuevopueblo' WHERE Pueblo = '$pueblo'";
                                    $resultModificacion = mysqli_query($conexion, $consultaModificacion);

                                    if($resultModificacion > 0)
                                    {
                                        print("entro");
                                        $booleano = true;
                                    }
                                }
                            }
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