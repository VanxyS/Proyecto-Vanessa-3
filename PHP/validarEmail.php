<?php
	//$usu = $_POST['usuario'];
	//$contrasenia = $_POST['contrasenia'];
    $booleano = false;

	//Recibimos el nombre, la contrasenia y el email
	$nombre=$_GET["nombre"];
    $codigo=$_GET["codigo"];

	//Conectamos a la base de datos
	$conexion= mysqli_connect("localhost", "vanessa", "vanessasantospuente", "proyectodam");

	//Hacemos una consulta para comprobar si existe algun usuario con ese nombre
	$consulta="SELECT * FROM usuariostemp WHERE nombre LIKE '$nombre' AND Codigo = $codigo";
	$result = mysqli_query($conexion, $consulta);

	if($result)
	{
		$filas = mysqli_num_rows($result);

		if($filas > 0)
		{
            $row = mysqli_fetch_assoc($result);

            $nombre = $row['Nombre'];
            

            $contraseniaBD = $row['Contrasenia'];
            

            $email = $row['Email'];
            
            
			//Insertarmos los datos (por defecto los nuevos registros son usuarios normales)
			$sql="INSERT INTO usuarios (nombre, contrasenia, email, idRol) VALUES ('$nombre', '$contraseniaBD','$email', 1)";					
			$resultado=mysqli_query($conexion, $sql);

			//Si se ha insertado el usuario devolvemos un true
			if($resultado > 0)
			{
                $booleano = true;
                //Insertarmos los datos (por defecto los nuevos registros son usuarios normales)
                $sql2="DELETE FROM usuariostemp WHERE Nombre LIKE '$nombre' AND Codigo = $codigo";					
                $resultado2=mysqli_query($conexion, $sql2);
			}
			else
			{
				$booleano = false;
			}
		}
	}

	else
	{
		print mysqli_error($conexion);
		echo $error;
	}

    if($booleano)
    {
        echo "Se ha verificado el usuario";
    }
    else
    {
        echo "Ha habido un error al verificar el usuario";
    }
		
	//Cerramos la conexión
	mysqli_close($conexion);
?>