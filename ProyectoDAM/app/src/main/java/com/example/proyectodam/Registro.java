package com.example.proyectodam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Registro extends Activity
{
    APIMethods api = new APIMethods();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        Button registro = (Button) findViewById(R.id.registro);
        final EditText editnombre = findViewById(R.id.diaAniadir);
        final EditText editcontrasenia = findViewById(R.id.horaAniadir);
        final EditText editemail = findViewById(R.id.editText);
        TextView textView = findViewById(R.id.textLuchador);

        registro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String nombre = editnombre.getText().toString();
                String contrasenia = editcontrasenia.getText().toString();
                String email = editemail.getText().toString();

                if(!nombre.isEmpty() && !contrasenia.isEmpty() && !email.isEmpty())
                {
                    api.registrarUsuario(Registro.this, "http://192.168.1.39/ProyectoDAM/registrar.php",nombre, contrasenia, email);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No se admiten campos vacios", Toast.LENGTH_LONG).show();
                }
            }
        });

        textView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), RegistrarLuchador.class);
                startActivity(intent);
            }
        });
    }
}