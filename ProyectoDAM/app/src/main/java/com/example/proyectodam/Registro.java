package com.example.proyectodam;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.regex.Pattern;

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
        //TextView textView = findViewById(R.id.textLuchador);

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
                    boolean comprobar;

                    Pattern pattern = Patterns.EMAIL_ADDRESS;
                    comprobar = pattern.matcher(email).matches();

                    if(comprobar)
                    {
                        api.comprobarUsuario(Registro.this, "http://192.168.1.35/ProyectoDAM/comprobarUsuario.php", nombre, contrasenia, email);
                    }
                    else
                        {
                            editemail.setError("Email no válido");
                        }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No se admiten campos vacios", Toast.LENGTH_LONG).show();
                }
            }
        });

//        textView.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(getApplicationContext(), RegistrarLuchador.class);
//                startActivity(intent);
//            }
//        });
    }
}
