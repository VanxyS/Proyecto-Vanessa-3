<?php
    $corros = array();

    //Conectamos a la base de datos
    $conexion= mysqli_connect("localhost", "vanessa", "vanessasantospuente", "proyectodam");

	//Hacemos una consulta para comprobar si existe algun usuario con ese nombre
	$consulta="SELECT * FROM corros";
    $result = mysqli_query($conexion, $consulta);

    if($result)
    {
        $filas = mysqli_num_rows($result);

        if($filas > 0)
        {
            while($registro=mysqli_fetch_array($result))
			{
                $corro = new stdClass();

                $pueblo = $registro['Pueblo'];
                $dia = $registro['Dia'];
                $hora = $registro['Hora'];
                $lugar = $registro['Lugar'];

                //Obtenidos los valores de sus coulmnas y cremaos el objeto json
                $corro->pueblo = $pueblo;
                $corro->dia = $dia;
                $corro->hora = $hora;
                $corro->lugar = $lugar;

                array_push($corros, $corro);
            }
        } 
    }
    else
	{
		print mysqli_error($conexion);
		echo $error;
    }
    
    //Creamos un json con el array de corros y lo devolvemos
	echo json_encode($corros, JSON_UNESCAPED_UNICODE);
		
	//Cerramos la conexión
	mysqli_close($conexion);
?>