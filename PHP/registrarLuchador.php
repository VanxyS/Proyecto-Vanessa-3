<?php
    //$usu = $_POST['usuario'];
	//$contrasenia = $_POST['contrasenia'];
    $booleano = false;
    $idPeso = 0;
    $idCategoria = 0;

	//Recibimos el nombre, la contrasenia y el email
	$nombre=$_POST["nombre"];
    $apellido1=$_POST["apellido1"];
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

        //print($idPeso);

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
        //print($idCategoria);

    //Hacemos una consulta para comprobar si existe algun luchador con ese nombre y apellidos
	$consulta="SELECT * FROM luchadores WHERE Nombre LIKE '$nombre' AND Apellido1 LIKE '$apellido1' AND Apellido2 LIKE '$apellido2'";
    $result = mysqli_query($conexion, $consulta);

	if($result)
	{
        $filas = mysqli_num_rows($result);
        
        //print($filas);

		if($filas == 0)
		{
			//Insertarmos los datos (por defecto los nuevos registros son usuarios normales)
			$sql="INSERT INTO luchadores (Nombre, Apellido1, Apellido2, Edad, Puntuacion, idPeso, idCategoria) 
            VALUES ('$nombre', '$apellido1', '$apellido2', $edad, 0, $idPeso, $idCategoria)";

            $resultado=mysqli_query($conexion, $sql);

			//Si se ha insertado el luchador devolvemos un true
			if($resultado > 0)
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