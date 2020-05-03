<?php
    $luchadores = array();
    $categoria = '';
    $peso = '';

    //Conectamos a la base de datos
    $conexion= mysqli_connect("localhost", "vanessa", "vanessasantospuente", "proyectodam");

	//Hacemos una consulta para comprobar si existe algun usuario con ese nombre
	$consulta="SELECT * FROM luchadores ORDER BY Puntuacion DESC";
    $result = mysqli_query($conexion, $consulta);
    
    if($result)
    {
        $filas = mysqli_num_rows($result);

        if($filas > 0)
        {
            while($registro=mysqli_fetch_array($result))
			{
                $luchador = new stdClass();

                $nombreLuchador = $registro['Nombre'];
                $apellido1 = $registro['Apellido1'];
                $apellido2 = $registro['Apellido2'];
                $edad = $registro['Edad'];
                $puntuacion = $registro['Puntuacion'];

                //Hacemos otra consulta para comprobar el peso al que pertenece
                $consultaCategoria="SELECT categorias.Nombre FROM luchadores INNER JOIN categorias ON luchadores.idCategoria=categorias.idCategoria WHERE luchadores.Nombre LIKE '$nombreLuchador'";
                $result2 = mysqli_query($conexion, $consultaCategoria);

                if($result2)
                {
                    $filas2 = mysqli_num_rows($result2);

                    if($filas2 > 0)
                    {
                        $row2 = mysqli_fetch_assoc($result2);
                        $categoria = $row2['Nombre'];
                    }
                }
                else
                    {
                        $error = mysqli_error($conexion);
                        echo $error;
                    }

                //Hacemos otra consulta para comprobar el peso al que pertenece
                $consultaPeso="SELECT pesos.Nombre FROM luchadores INNER JOIN pesos ON luchadores.idPeso=pesos.idPeso WHERE luchadores.Nombre LIKE '$nombreLuchador'";
                $result3 = mysqli_query($conexion, $consultaPeso);

                if($result3)
                {
                    $filas3 = mysqli_num_rows($result2);

                    if($filas3 > 0)
                    {
                        $row3 = mysqli_fetch_assoc($result3);
                        $peso = $row3['Nombre'];
                    }
                }
                else
                    {
                        $error = mysqli_error($conexion);
                        echo $error;
                    }

                //Obtenidos los valores del peso y de la categoría cremaos el objeto json
                $luchador->nombre = $nombreLuchador;
                $luchador->apellido1 = $apellido1;
                $luchador->apellido2 = $apellido2;
                $luchador->edad = $edad;
                $luchador->puntuacion = $puntuacion;
                $luchador->peso = $peso;
                $luchador->categoria = $categoria;

                array_push($luchadores, $luchador);
            }
        }
    }
    else
	{
		print mysqli_error($conexion);
		echo $error;
    }
    
    //Creamos un json con el array de luchadores y lo devolvemos
	echo json_encode($luchadores, JSON_UNESCAPED_UNICODE);
		
	//Cerramos la conexión
	mysqli_close($conexion);
?>