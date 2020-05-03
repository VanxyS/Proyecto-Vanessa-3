<?php
	//$usu = $_POST['usuario'];
    //$contrasenia = $_POST['contrasenia'];
    $booleanoLuchador = false;
	$booleano = false;

	//Recibimos el nombre, la contrasenia y el email
	$nombre=$_POST["nombre"];
	$contrasenia=$_POST["contrasenia"];
    $email=$_POST["email"];
    $nombreLuchador=$_POST["nombreLuchador"];
	$apellido=$_POST["apellido"];

	$cipher_method = 'aes-128-ctr';
	$enc_key = openssl_digest(php_uname(), 'SHA256', TRUE);
	$enc_iv = openssl_random_pseudo_bytes(openssl_cipher_iv_length($cipher_method));
	$crypted_token = openssl_encrypt($contrasenia, $cipher_method, $enc_key, 0, $enc_iv) . "::" . bin2hex($enc_iv);
	unset($contrasenia, $cipher_method, $enc_key, $enc_iv);

	//Conectamos a la base de datos
	$conexion= mysqli_connect("localhost", "vanessa", "vanessasantospuente", "proyectodam");

	//Hacemos una consulta para comprobar si existe algun usuario con ese nombre
	$consulta="SELECT * FROM usuarios WHERE (nombre LIKE '$nombre' OR email LIKE '$email')";
	$result = mysqli_query($conexion, $consulta);

	if($result)
	{
		$filas = mysqli_num_rows($result);

		if($filas == 0)
		{
            $booleano = true;
            
            //Hacemos una consulta para comprobar si existe algun usuario con ese nombre
	        $consulta2="SELECT * FROM luchadores WHERE Nombre LIKE '$nombreLuchador' AND Apellido1 LIKE '$apellido'";
            $result2 = mysqli_query($conexion, $consulta2);

            if($result2)
            {
                $filas2 = $filas = mysqli_num_rows($result2);

                if($filas2 > 0)
                {
                    //Insertarmos los datos (por defecto los nuevos registros son usuarios normales)
                    $sql="INSERT INTO usuarios (nombre, contrasenia, email, idRol) VALUES ('$nombre', '$crypted_token','$email', 3)";					
                    $resultado3=mysqli_query($conexion, $sql);

                    //Si se ha insertado el usuario devolvemos un true
                    if($resultado3 > 0)
                    {
                        $booleanoLuchador = true;
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

	//Creamos un json con el valor del booleano y lo devolvemos
	$response = array();
	$response["respuesta"] = $booleano;
	$response["respuesta2"] = $booleanoLuchador;
	echo json_encode($response, JSON_UNESCAPED_UNICODE);
		
	//Cerramos la conexión
	mysqli_close($conexion);
?>