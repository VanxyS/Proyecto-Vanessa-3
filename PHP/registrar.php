<?php
	$booleano = false;

	//Recibimos el nombre, la contrasenia y el email
	$nombre=$_POST["nombre"];
	$contrasenia=$_POST["contrasenia"];
	$email=$_POST["email"];

	$contraseniaEncriptada = password_hash($contrasenia, PASSWORD_DEFAULT);

	//Conectamos a la base de datos
	$conexion= mysqli_connect("localhost", "vanessa", "vanessasantospuente", "proyectodam");

	//Hacemos una consulta para comprobar si existe algun usuario con ese nombre
	$consulta="SELECT * FROM usuarios WHERE nombre LIKE '$nombre'";
	$result = mysqli_query($conexion, $consulta);

	if($result)
	{
		$filas = mysqli_num_rows($result);

		if($filas == 0)
		{
			//Insertarmos los datos (por defecto los nuevos registros son usuarios normales)
			$sql="INSERT INTO usuarios (nombre, contrasenia, email, idRol) VALUES ('$nombre', '$contraseniaEncriptada','$email', 1)";					
			$resultado=mysqli_query($conexion, $sql);

			//Si se ha insertado el usuario devolvemos un true
			if($resultado > 0)
			{
				$booleano = true;
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

	//Creamos un json con el valor del booleano y lo devolvemos
	$response = array();
	$response["respuesta"] = $booleano;
	echo json_encode($response, JSON_UNESCAPED_UNICODE);
		
	//Cerramos la conexión
	mysqli_close($conexion);
?>