<?php
	$usu = $_GET['usuario'];
	$contrasenia = $_GET['contrasenia'];
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

				$contraseniaBD = $row['contrasenia'];

				list($contraseniaBD, $enc_iv) = explode("::", $contraseniaBD);;
				$cipher_method = 'aes-128-ctr';
				$enc_key = openssl_digest(php_uname(), 'SHA256', TRUE);
				$token = openssl_decrypt($contraseniaBD, $cipher_method, $enc_key, 0, hex2bin($enc_iv));
				unset($contraseniaBD, $cipher_method, $enc_key, $enc_iv);

				if($token == $contrasenia)
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