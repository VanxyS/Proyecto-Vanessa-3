package com.example.proyectodam;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class RecuperarContrasenia extends Activity
{
    EditText usuario;
    APIMethods api = new APIMethods();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recuperarcontrasenia);

        usuario = findViewById(R.id.editText2);
        Button enviarEmail = findViewById(R.id.button);
        final TextView error = findViewById(R.id.textView4);

        enviarEmail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                api.enviarEmail(RecuperarContrasenia.this, "http://192.168.1.39/ProyectoDAM/recuperarContrasenia.php",usuario.getText().toString(), error);
            }
        });
    }
}
