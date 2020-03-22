<?php

	$c = mysqli_connect("localhost", "vanessa", "vanessasantospuente", "proyectodam");
	
	if($c==null)
	{
		echo "Fallo de conexion";
	}
	else
	{		
		$sql="SELECT * FROM luchadores";
		$resultado=mysqli_query($c, $sql);
	
		if($resultado)
		{
	
			$filas=mysqli_num_rows($resultado); //muestra cuantas filas tiene el resultado
			
			if($filas==0)
			{
				echo "Consultas no encontradas";
			}
			else
			{
				echo "<table border='1'><tr>";
					echo "<th>ID</th>";
					echo "<th>NOMBRE</th>";
					echo "<th>APELLIDO</th>";
					echo "<th>IDPESO</th>";
					echo "<th>IDCATEGORIA</th>";
				echo "</tr>";
				
				while($registro=mysqli_fetch_array($resultado))
				{
					echo "<tr>";
					echo "<td>".$registro['id']."</td>";
					echo "<td>".$registro['Nombre']."</td>";
					echo "<td>".$registro['Apellidos']."</td>";
					echo "<td>".$registro['idPeso']."</td>";
					echo "<td>".$registro['idCategoria']."</td>";
					echo "</tr>";
				}
				echo "</table>";
			}
			
		}
		else
		{
			$error=mysqli_error($c);
			echo $error;
			
		}
			
		mysqli_close($c);
	}
?>
