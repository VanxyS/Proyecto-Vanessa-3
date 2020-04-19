package com.example.proyectodam;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class RegistrarLuchador extends Activity
{
    APIMethods apiMethods = new APIMethods();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_luchador);

        Button registro = (Button) findViewById(R.id.registro);
        final EditText editnombre = findViewById(R.id.diaAniadir);
        final EditText nombreLuchador = findViewById(R.id.nombreLuchador);
        final EditText editApellido = findViewById(R.id.apellidoLuchador);
        final EditText editcontrasenia = findViewById(R.id.horaAniadir);
        final EditText editemail = findViewById(R.id.editText);

        registro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String nombreUsuario = editnombre.getText().toString();
                String nombre = nombreLuchador.getText().toString();
                String apellido = editApellido.getText().toString();
                String contrasenia = editcontrasenia.getText().toString();
                String email = editemail.getText().toString();

                if(!nombre.isEmpty() && !contrasenia.isEmpty() && !email.isEmpty() && !nombreUsuario.isEmpty() && !email.isEmpty()&& !apellido.isEmpty())
                {
                    //apiMethods.registrarLuchador(RegistrarLuchador.this, "http://192.168.1.39/ProyectoDAM/registrar.php",nombreUsuario, contrasenia, email, nombre, apellido);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No se admiten campos vacios", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
