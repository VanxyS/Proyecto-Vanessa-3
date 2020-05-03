<?php
    use PHPMailer\PHPMailer\PHPMailer;
    use PHPMailer\PHPMailer\Exception;
    
    require 'PHPMailer/Exception.php';
    require 'PHPMailer/PHPMailer.php';
    require 'PHPMailer/SMTP.php';

    $usuario = '';
    $contrasenia = '';
    $email = '';
    $booleano = false;

    //Recibimos el nombre del usuario o su email
    $nombre=$_GET["nombre"];

    //Conectamos a la base de datos
    $conexion= mysqli_connect("localhost", "vanessa", "vanessasantospuente", "proyectodam");
    
    //Hacemos una consulta para comprobar si existe algun usuario con ese nombre
	$consulta="SELECT * FROM usuarios WHERE (nombre LIKE '$nombre' OR email LIKE '$nombre')";
    $result = mysqli_query($conexion, $consulta);
    
    if($result)
	{
        $filas = mysqli_num_rows($result);

        if($filas > 0)
        {
            $booleano = true;
            $row = mysqli_fetch_assoc($result);

            $email = $row['email'];
            $contrasenia = $row['contrasenia'];
            $usuario = $row['nombre'];

            list($contrasenia, $enc_iv) = explode("::", $contrasenia);;
            $cipher_method = 'aes-128-ctr';
            $enc_key = openssl_digest(php_uname(), 'SHA256', TRUE);
            $token = openssl_decrypt($contrasenia, $cipher_method, $enc_key, 0, hex2bin($enc_iv));
            unset($contrasenia, $cipher_method, $enc_key, $enc_iv);

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
                $mail->addAddress($email, $usuario);     // Add a recipient
                //$mail->addAddress('ellen@example.com');               // Name is optional
                //$mail->addReplyTo('info@example.com', 'Information');
                //$mail->addCC('cc@example.com');
                //$mail->addBCC('bcc@example.com');

                // Attachments
                //$mail->addAttachment('/var/tmp/file.tar.gz');         // Add attachments
                //$mail->addAttachment('/tmp/image.jpg', 'new.jpg');    // Optional name

                // Content
                $mail->isHTML(true);                                  // Set email format to HTML
                $mail->Subject = 'RECORDAR CONTRASENIA';
                $mail->Body    = '<b>La contraseña del usuario ' . $usuario . ' es ' . $token . '.</b>';
                //$mail->AltBody = 'This is the body in plain text for non-HTML mail clients';

                $mail->send();
            } 
            catch (Exception $e) 
            {
                echo "Message could not be sent. Mailer Error: {$mail->ErrorInfo}";
            }
        }
        else
        {
            $booleano = false;
        }
    }
    else
    {
        echo mysqli_error($conexion);
    }

    //Creamos un json con el valor del booleano y lo devolvemos
	//$response = array();
    //$response['verificacion'] = $booleano;
    //echo json_encode($response, JSON_UNESCAPED_UNICODE);
    echo $booleano;
		
	//Cerramos la conexión
    mysqli_close($conexion);
?>