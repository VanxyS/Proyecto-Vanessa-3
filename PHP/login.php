	 <?php
	$usu = $_POST['usuario'];
	$contrasenia = $_POST['contrasenia'];
	$idRol = 0;
	$booleano = false;
	$roles = '';

	$c = mysqli_connect("localhost", "vanessa", "vanessasantospuente", "proyectodam");
	
	//Comprobamos que la conexion funciona
	if($c == null)
	{
		echo "Fallo en la conexion";
	}

	else
	{
		$sql="SELECT * FROM usuarios WHERE (nombre LIKE '$usu' OR email LIKE '$usu')"; /*AND contrasenia LIKE $contrasenia"*/

		//Le damos a entender que son strings ambos parametros
		//$sql->bind_param('ss', $usu, $contrasenia);
		
		$result = mysqli_query($c, $sql);

		if($result)
		{
			$filas = mysqli_num_rows($result);

			if($filas > 0)
			{
				
				//echo json_encode($filas, JSON_UNESCAPED_UNICODE);
				$row = mysqli_fetch_assoc($result);

				if(password_verify($contrasenia, $row['contrasenia']))
				{
					$booleano = true;

					$idRol = $row['idRol'];

					if($idRol == 1)
					{
						//echo "usuario";
						$roles = 'usuario';
					}

					else if($idRol == 2)
					{
						//echo "administrador";
						$roles = 'administrador';
					}
				}
			}
		}

		else
		{
			$error = mysqli_error($c);
			echo $error;
		}

		$response = array();
		$response["success"] = $booleano;
		$response["roles"] = $roles;
		//echo json_encode($response);
		//print($idRol);
		//print($roles);
		echo json_encode($response, JSON_UNESCAPED_UNICODE);
	
		mysqli_close($c);
	}
?>