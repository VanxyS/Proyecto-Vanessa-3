<?php
	use PHPMailer\PHPMailer\PHPMailer;
	use PHPMailer\PHPMailer\Exception;

	require 'PHPMailer/Exception.php';
	require 'PHPMailer/PHPMailer.php';
	require 'PHPMailer/SMTP.php';

	//$usu = $_POST['usuario'];
	//$contrasenia = $_POST['contrasenia'];
	$booleano = false;

	//Recibimos el nombre, la contrasenia y el email
	$nombre=$_POST["nombre"];
	$contrasenia=$_POST["contrasenia"];
	$email=$_POST["email"];
	$codigo = rand(1, 100000);

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
			//Insertarmos los datos (por defecto los nuevos registros son usuarios normales)
			$sql="INSERT INTO usuariostemp (Nombre, Contrasenia, Email, Codigo) VALUES ('$nombre', '$crypted_token','$email', $codigo)";					
			$resultado=mysqli_query($conexion, $sql);

			//Si se ha insertado el usuario devolvemos un true
			if($resultado > 0)
			{
				print($nombre);
				print($crypted_token);
				print($email);
				print($codigo);

				$mail = new PHPMailer(true);
				try 
				{
					//Server settings
					$mail->SMTPDebug = 2;                      // Enable verbose debug output
					$mail->isSMTP();                                            // Send using SMTP
					$mail->Host       = 'smtp.gmail.com';                    // Set the SMTP server to send through
					$mail->SMTPAuth   = true;                                   // Enable SMTP authentication
					$mail->Username   = 'vasanpue@gmail.com';                     // SMTP username
					$mail->Password   = 'Vane-1998';                               // SMTP password
					$mail->SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS;         // Enable TLS encryption; `PHPMailer::ENCRYPTION_SMTPS` encouraged
					$mail->Port       = 587;                                    // TCP port to connect to, use 465 for `PHPMailer::ENCRYPTION_SMTPS` above

					//Recipients
					$mail->setFrom('vasanpue@gmail.com', 'Vanessa');
					$mail->addAddress($email, $nombre);     // Add a recipient
					//$mail->addAddress('ellen@example.com');               // Name is optional
					//$mail->addReplyTo('info@example.com', 'Information');
					//$mail->addCC('cc@example.com');
					//$mail->addBCC('bcc@example.com');

					// Attachments
					//$mail->addAttachment('/var/tmp/file.tar.gz');         // Add attachments
					//$mail->addAttachment('/tmp/image.jpg', 'new.jpg');    // Optional name

					// Content
					$mail->isHTML(true);                                  // Set email format to HTML
					$mail->Subject = 'EMAIL DE VERIFICACION DEL USUARIO';
					$mail->Body    = 'Para activar su cuenta entre en el siguiente link: http://localhost/ProyectoDAM/validarEmail.php?nombre=' . $nombre . '&codigo=' . $codigo;
					//$mail->AltBody = 'This is the body in plain text for non-HTML mail clients';

					$mail->send();
				} 
				catch (Exception $e) 
				{
					echo "Message could not be sent. Mailer Error: {$mail->ErrorInfo}";
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
	echo json_encode($response, JSON_UNESCAPED_UNICODE);
		
	//Cerramos la conexión
	mysqli_close($conexion);
?>