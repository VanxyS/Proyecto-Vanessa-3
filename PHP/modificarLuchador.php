<?php
    $booleano = false;

    //Recivimos el nombre del luchador, sus apellidos y su nueva puntuación
    //$usu = $_POST['usuario'];
	//$contrasenia = $_POST['contrasenia'];
    $nombre=$_POST["nombre"];
    $nombre1=$_POST["nombre1"];
    $apellido1=$_POST["apellido1"];
    $apellido11=$_POST["apellido11"];
    $apellido2=$_POST["apellido2"];
    $edad=$_POST["edad"];
    $peso=$_POST["peso"];
    $categoria=$_POST["categoria"];

    //Conectamos a la base de datos
    $conexion= mysqli_connect("localhost", "vanessa", "vanessasantospuente", "proyectodam");

    //Hacemos otra consulta para comprobar el peso al que pertenece
    $consultaPeso="SELECT * FROM pesos WHERE Nombre LIKE '$peso'";
    $result2 = mysqli_query($conexion, $consultaPeso);

    if($result2)
    {
        $filas2 = mysqli_num_rows($result2);

        if($filas2 > 0)
        {
            $row = mysqli_fetch_assoc($result2);

            $idPeso = $row['idPeso'];
        }
    }
    else
		{
			$error = mysqli_error($conexion);
			echo $error;
        }

    //Hacemos otra consulta para comprobar la categoría a la que pertenece
    $consultaCategoria="SELECT * FROM categorias WHERE Nombre LIKE '$categoria'";
    $result3 = mysqli_query($conexion, $consultaCategoria);

    if($result3)
    {
        $filas3 = mysqli_num_rows($result3);

        if($filas3 > 0)
        {
            $row2 = mysqli_fetch_assoc($result3);

            $idCategoria = $row2['idCategoria'];
        }
    }
    else
		{
			$error = mysqli_error($conexion);
			echo $error;
        }

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

                    print($filas);

                    if($filas > 0)
                    {
                        if(!empty($nombre) or !empty($apellido1) or !empty($apellido11) or !empty($apellido2) or !empty($edad) or !empty($peso) or !empty($categoria) or !empty($nombre1))
                        {
                            while($registro=mysqli_fetch_array($result))
                            {
                                if(!empty($apellido2))
                                {
                                    $consultaModificacion3="UPDATE luchadores SET Apellido2 = '$apellido2' WHERE Nombre = '$nombre' AND Apellido1 = '$apellido1'";
                                    $resultModificacion3 = mysqli_query($conexion, $consultaModificacion3);

                                    if($resultModificacion3 > 0)
                                    {
                                        $booleano = true;
                                    }
                                }

                                if(!empty($edad))
                                {
                                    print("lugar");
                                    $consultaModificacion4="UPDATE luchadores SET Edad = '$edad' WHERE Nombre = '$nombre' AND Apellido1 = '$apellido1'";
                                    $resultModificacion4 = mysqli_query($conexion, $consultaModificacion4);

                                    if($resultModificacion4 > 0)
                                    {
                                        $booleano = true;
                                    }
                                }

                                if(!empty($peso))
                                {
                                    print("lugar");
                                    $consultaModificacion5="UPDATE luchadores SET idPeso = $idPeso WHERE Nombre = '$nombre' AND Apellido1 = '$apellido1'";
                                    $resultModificacion5 = mysqli_query($conexion, $consultaModificacion5);

                                    if($resultModificacion5 > 0)
                                    {
                                        $booleano = true;
                                    }
                                }

                                if(!empty($categoria))
                                {
                                    print("lugar");
                                    $consultaModificacion6="UPDATE luchadores SET idCategoria = $idCategoria WHERE Nombre = '$nombre' AND Apellido1 = '$apellido1'";
                                    $resultModificacion6 = mysqli_query($conexion, $consultaModificacion6);

                                    if($resultModificacion6 > 0)
                                    {
                                        $booleano = true;
                                    }
                                }

                                if(!empty($apellido11))
                                {

                                    $consultaModificacion="UPDATE luchadores SET Apellido1 = '$apellido11' WHERE Nombre = '$nombre' AND Apellido1 = '$apellido1'";
                                    $resultModificacion = mysqli_query($conexion, $consultaModificacion);

                                    if($resultModificacion > 0)
                                    {
                                        print("entro");
                                        $booleano = true;
                                    }
                                }

                                if(!empty($nombre1))
                                {
                                    print("dia");
                                    $consultaModificacion2="UPDATE luchadores SET Nombre = '$nombre1' WHERE Nombre = '$nombre' AND Apellido1 = '$apellido11'";
                                    $resultModificacion2 = mysqli_query($conexion, $consultaModificacion2);

                                    if($resultModificacion2 > 0)
                                    {
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