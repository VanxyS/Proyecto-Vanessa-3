<?php
    $usuarios = array();

    //Conectamos a la base de datos
    $conexion= mysqli_connect("localhost", "vanessa", "vanessasantospuente", "proyectodam");

	//Hacemos una consulta para comprobar si existe algun usuario con ese nombre
	$consulta="SELECT * FROM usuarios";
    $result = mysqli_query($conexion, $consulta);

    if($result)
    {
        $filas = mysqli_num_rows($result);

        if($filas > 0)
        {
            while($registro=mysqli_fetch_array($result))
			{
                $usuario = new stdClass();

                $idRol = $registro['idRol'];

                $consultaRol = "SELECT Tipo FROM roles NATURAL JOIN usuarios WHERE usuarios.idRol = $idRol";
                $resultRol = mysqli_query($conexion, $consultaRol);

                if($resultRol)
                {
                    $filasEol = mysqli_num_rows($resultRol);

                    if($filasEol > 0)
                    {
                        while($registroRol=mysqli_fetch_array($resultRol))
                        {
                            $tipo = $registroRol['Tipo'];
                        }
                    }
                }

                $nombre = $registro['nombre'];

                //Obtenidos los valores de sus coulmnas y cremaos el objeto json
                $usuario->nombre = $nombre;
                $usuario->rol = $tipo;

                array_push($usuarios, $usuario);
            }
        } 
    }
    else
	{
		print mysqli_error($conexion);
		echo $error;
    }
    
    //Creamos un json con el array de usuarios$usuarios y lo devolvemos
	echo json_encode($usuarios, JSON_UNESCAPED_UNICODE);
		
	//Cerramos la conexión
	mysqli_close($conexion);
?>