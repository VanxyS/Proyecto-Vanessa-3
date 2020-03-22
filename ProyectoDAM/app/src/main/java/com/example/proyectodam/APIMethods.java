package com.example.proyectodam;

import android.content.Context;
import android.content.Intent;
import android.se.omapi.Session;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class APIMethods
{
    private static final int MY_DEFAULT_TIMEOUT = 50000;

    public void validarUsuario(final Context context, String url, final String usuario, final String contrasenia)
    {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if(!response.isEmpty())
                {
                    try
                    {
                        JSONObject object = new JSONObject(response);
                        String rol = object.getString("roles");
                        boolean booleano = object.getBoolean("success");

                        //System.out.println(rol);
                        //System.out.println(booleano);

                        if(booleano)
                        {
                            if(rol.equals("usuario"))
                            {
                                Intent usuario = new Intent(context, PantallaPrincipalUsuario.class);
                                context.startActivity(usuario);
                            }
                            else if(rol.equals("administrador"))
                            {
                                Intent administrador = new Intent(context, PantallaPrincipalAdministrador.class);
                                context.startActivity(administrador);
                            }
                        }
                        else
                        {
                            Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("usuario", usuario);
                parametros.put("contrasenia",contrasenia);
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void registrarUsuario(final Context context, String url, final String usuario, final String contrasenia, final String email)
    {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if(!response.isEmpty())
                {
                    try
                    {
                        JSONObject objectRegistro = new JSONObject(response);
                        boolean booleano = objectRegistro.getBoolean("respuesta");

                        System.out.println(booleano);

                        if(booleano)
                        {
                            Toast.makeText(context, "Se ha registrado el usuario", Toast.LENGTH_LONG).show();
                            Intent salida = new Intent(context, MainActivity.class);
                            context.startActivity(salida);
                        }
                        else
                        {
                            Toast.makeText(context, "El usuario ya existe", Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("nombre", usuario);
                parametros.put("contrasenia",contrasenia);
                parametros.put("email", email);
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void enviarEmail(final Context context, String url, final String nombre, final TextView textView)
    {
        textView.setVisibility(View.INVISIBLE);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if(!response.isEmpty())
                {

                    System.out.println(response);

                    textView.setVisibility(View.INVISIBLE);

                    Toast.makeText(context, "El correo se ha enviado", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
                else
                {
                    //Si hay un error aparece el texto
                    textView.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("nombre", nombre);
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }
}
