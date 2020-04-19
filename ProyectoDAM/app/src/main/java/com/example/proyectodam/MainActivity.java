package com.example.proyectodam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText editcontrasenia, editusuario;
    String usuario, contrasenia;
    APIMethods api = new APIMethods();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editusuario = findViewById(R.id.diaAniadir);
        editcontrasenia = findViewById(R.id.horaAniadir);

        Button registro = findViewById(R.id.registrarse);
        TextView olvidoContrasenia = findViewById(R.id.olvidoContrasenia);
        Button acceder = findViewById(R.id.acceso);
        final CheckBox checkBox = findViewById(R.id.checkBox2);

        final SharedPreferences sharedPreferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        if(!sharedPreferences.getString("preferencias", "NULL").equals("NULL"))
        {
            editusuario.setText(sharedPreferences.getString("preferencias", ""));
        }

        registro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), Registro.class);
                startActivity(intent);
                finish();
            }
        });

        olvidoContrasenia.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), RecuperarContrasenia.class);
                startActivity(intent);
            }
        });

        acceder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                usuario=editusuario.getText().toString();
                contrasenia = editcontrasenia.getText().toString();

                if(!usuario.isEmpty() && !contrasenia.isEmpty())
                {
                    if(checkBox.isChecked())
                    {

                        editor.putString("preferencias", editusuario.getText().toString());
                        editor.commit();
                    }
                    else
                        {
                            editor.putString("preferencias", "NULL");
                            editor.commit();
                        }

                    api.validarUsuario(MainActivity.this, "http://192.168.1.39/ProyectoDAM/login.php", usuario, contrasenia);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No se admiten campos vacios", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
