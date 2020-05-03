package com.example.proyectodam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectodam.Adaptadores.Adaptador;
import com.example.proyectodam.Adaptadores.AdaptadorCorros;
import com.example.proyectodam.Adaptadores.AdapterUsuario;
import com.example.proyectodam.Adaptadores.AdaptorPuntuacion;
import com.example.proyectodam.Items.Item;
import com.example.proyectodam.Items.ItemCorro;
import com.example.proyectodam.Items.ItemPuntuacion;
import com.example.proyectodam.Items.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
                System.out.println(response);
                if(!response.isEmpty())
                {
                    try
                    {
                        JSONObject object = new JSONObject(response);
                        String rol = object.getString("roles");
                        boolean booleano = object.getBoolean("success");
                        String usuario = object.getString("nombre");
                        String email = object.getString("email");
                        String contrasenia = object.getString("contrasenia");

                        //System.out.println(rol);
                        //System.out.println(booleano);

                        if(booleano)
                        {
                            Intent intent = new Intent(context, Drawer.class);
                            intent.putExtra("rol", rol);
                            intent.putExtra("email", email);
                            intent.putExtra("nombre", usuario);
                            intent.putExtra("contrasenia", contrasenia);
                            context.startActivity(intent);
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

    public void comprobarUsuario(final Context context, String url, final String usuario, final String contrasenia, final String email)
    {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if(!response.isEmpty())
                {
                    System.out.println(response);
                    try
                    {
                        JSONObject objectRegistro = new JSONObject(response);
                        boolean comprobacionUsuario = objectRegistro.getBoolean("respuesta");

                        if(comprobacionUsuario)
                        {
                            registrarUsuario(context, "http://192.168.1.35/ProyectoDAM/registrar.php", usuario, contrasenia, email);

                            Toast.makeText(context, "Se ha enviado un enlace de verificación a su correo", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(context, MainActivity.class);
                            context.startActivity(intent);
                        }
                        else
                            {
                                Toast.makeText(context, "Usuario o email ya existentes", Toast.LENGTH_LONG).show();
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
                parametros.put("email", email);
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
                    //System.out.println(response);
                    try
                    {
                        JSONObject objectRegistro = new JSONObject(response);
                        boolean booleano = objectRegistro.getBoolean("respuesta");

                            Toast.makeText(context, "Se ha enviado el email de verificación", Toast.LENGTH_LONG).show();
                            Intent salida = new Intent(context, MainActivity.class);
                            context.startActivity(salida);
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

    public void registrarLuchador(final Context context, String url, final String nombre, final String apellido1,
                                  final String apellido2, final String edad, final String peso, final String categoria)
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
                        System.out.println(nombre);
                        System.out.println(apellido1);
                        System.out.println(apellido2);
                        System.out.println(edad);
                        System.out.println(peso);
                        System.out.println(categoria);
                        System.out.print(response);
                        JSONObject objectRegistro = new JSONObject(response);
                        boolean booleano = objectRegistro.getBoolean("respuesta");

                        if(booleano)
                        {
                            Toast.makeText(context, "Se ha registrado el luchador", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(context, "No existe el luchador", Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                        System.out.println(e);
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
                parametros.put("nombre", nombre);
                parametros.put("apellido1", apellido1);
                parametros.put("apellido2", apellido2);
                parametros.put("edad", edad);
                parametros.put("peso", peso);
                parametros.put("categoria", categoria);
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void listarLuchadoresLigeros(String url, final ListView ligerosList, final Context context)
    {
        final ArrayList<String> ligeros = new ArrayList<>();
        final ArrayList<Item> items = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if(!response.isEmpty())
                {
                    try
                    {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++)
                        {
                            JSONObject object = array.getJSONObject(i);

                            String nombre = object.getString("nombre");
                            String apellido1 = object.getString("apellido1");
                            String apellido2 = object.getString("apellido2");
                            int edad = Integer.parseInt(object.getString("edad"));
                            int puntuacion = Integer.parseInt(object.getString("puntuacion"));
                            String peso = object.getString("peso");
                            String categoria = object.getString("categoria");

                            Luchador luchador = new Luchador(nombre, apellido1, apellido2, edad, puntuacion, peso, categoria);
                            Item item = new Item(R.drawable.a512x512bb, nombre, apellido1, apellido2, edad, peso, categoria);

                            System.out.println(nombre);

                            if(peso.equals("Ligeros"))
                            {
                                items.add(item);
                                ligeros.add(nombre);
                            }
                        }

                        Adaptador adaptador = new Adaptador(context, items);

                        ArrayAdapter<String>adapterLigeros = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, ligeros);
                        //ArrayAdapter<String>adapterMedios = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, medios);
                        //ArrayAdapter<String>adapterPesados = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, pesados);

                        ligerosList.setAdapter(adaptador);
                        //mediosList.setAdapter(adapterMedios);
                        //pesadosList.setAdapter(adapterPesados);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(context, "No se han encontrado luchadores", Toast.LENGTH_LONG);
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
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void listarLuchadoresMedios(String url, final ListView mediosList, final Context context)
    {
        final ArrayList<String> medios = new ArrayList<>();
        final ArrayList<Item> items = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if(!response.isEmpty())
                {
                    try
                    {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++)
                        {
                            JSONObject object = array.getJSONObject(i);

                            String nombre = object.getString("nombre");
                            String apellido1 = object.getString("apellido1");
                            String apellido2 = object.getString("apellido2");
                            int edad = Integer.parseInt(object.getString("edad"));
                            int puntuacion = Integer.parseInt(object.getString("puntuacion"));
                            String peso = object.getString("peso");
                            String categoria = object.getString("categoria");

                            Luchador luchador = new Luchador(nombre, apellido1, apellido2, edad, puntuacion, peso, categoria);
                            Item item = new Item(R.drawable.a512x512bb, nombre, apellido1, apellido2, edad, peso, categoria);

                            if(peso.equals("Medios"))
                            {
                                items.add(item);
                                medios.add(nombre);
                            }
                        }

                        Adaptador adaptador = new Adaptador(context, items);

                        //ArrayAdapter<String>adapterLigeros = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, ligeros);
                        ArrayAdapter<String>adapterMedios = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, medios);
                        //ArrayAdapter<String>adapterPesados = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, pesados);

                        //ligerosList.setAdapter(adapterLigeros);
                        mediosList.setAdapter(adaptador);
                        //pesadosList.setAdapter(adapterPesados);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(context, "No se han encontrado luchadores", Toast.LENGTH_LONG);
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
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void listarLuchadoresPesados(String url, final ListView pesadosList, final Context context)
    {
        final ArrayList<String> pesados = new ArrayList<>();
        final ArrayList<Item> items = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if(!response.isEmpty())
                {
                    try
                    {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++)
                        {
                            System.out.println(response);
                            JSONObject object = array.getJSONObject(i);

                            System.out.println(object.getString("nombre"));

                            String nombre = object.getString("nombre");
                            String apellido1 = object.getString("apellido1");
                            String apellido2 = object.getString("apellido2");
                            int edad = Integer.parseInt(object.getString("edad"));
                            int puntuacion = Integer.parseInt(object.getString("puntuacion"));
                            String peso = object.getString("peso");
                            String categoria = object.getString("categoria");

                            Luchador luchador = new Luchador(nombre, apellido1, apellido2, edad, puntuacion, peso, categoria);
                            Item item = new Item(R.drawable.a512x512bb, nombre, apellido1, apellido2, edad, peso, categoria);

                            if(peso.equals("Pesados"))
                            {
                                items.add(item);
                                pesados.add(nombre);
                            }
                        }

                        Adaptador adaptador = new Adaptador(context, items);

                        //ArrayAdapter<String>adapterLigeros = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, ligeros);
                        //ArrayAdapter<String>adapterMedios = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, medios);
                        ArrayAdapter<String>adapterPesados = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, pesados);

                        //ligerosList.setAdapter(adapterLigeros);
                        //mediosList.setAdapter(adapterMedios);
                        pesadosList.setAdapter(adaptador);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(context, "No se han encontrado luchadores", Toast.LENGTH_LONG);
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
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void listarCorros(String url, final ListView listView, final Context context)
    {
        final ArrayList<Corro> corros = new ArrayList<>();
        final ArrayList<ItemCorro> itemCorros = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if(!response.isEmpty())
                {
                    try
                    {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++)
                        {
                            JSONObject object = array.getJSONObject(i);

                            String pueblo = object.getString("pueblo");
                            String fecha = object.getString("dia");
                            String hora = object.getString("hora");
                            String lugar = object.getString("lugar");

                            Corro corro = new Corro(pueblo, fecha, hora, lugar);
                            ItemCorro item = new ItemCorro(R.drawable.a00014151, pueblo, fecha, hora, lugar);

                                itemCorros.add(item);
                                corros.add(corro);

                        }

                        AdaptadorCorros adaptador = new AdaptadorCorros(context, itemCorros);

                        listView.setAdapter(adaptador);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(context, "No se han encontrado luchadores", Toast.LENGTH_LONG);
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
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void listarLigerosPuntuacion(String url, final ListView ligerosList, final Context context)
    {
        final ArrayList<Luchador> luchadores = new ArrayList<>();
        final ArrayList<ItemPuntuacion> items = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if(!response.isEmpty())
                {
                    try
                    {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++)
                        {
                            System.out.println(response);
                            JSONObject object = array.getJSONObject(i);

                            System.out.println(object.getString("nombre"));

                            String nombre = object.getString("nombre");
                            String apellido1 = object.getString("apellido1");
                            String apellido2 = object.getString("apellido2");
                            int edad = Integer.parseInt(object.getString("edad"));
                            int puntuacion = Integer.parseInt(object.getString("puntuacion"));
                            String peso = object.getString("peso");
                            String categoria = object.getString("categoria");

                            Luchador luchador = new Luchador(nombre, apellido1, apellido2, edad, puntuacion, peso, categoria);
                            ItemPuntuacion item = new ItemPuntuacion(R.drawable.a512x512bb, nombre, apellido1 + " " + apellido2, Integer.toString(puntuacion));

                            if(peso.equals("Ligeros"))
                            {
                                items.add(item);
                                luchadores.add(luchador);
                            }
                        }

                        AdaptorPuntuacion adaptador = new AdaptorPuntuacion(context, items);

                        //ArrayAdapter<String>adapterLigeros = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, ligeros);
                        //ArrayAdapter<String>adapterMedios = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, medios);
                        ArrayAdapter<Luchador>adapterPesados = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, luchadores);

                        //ligerosList.setAdapter(adapterLigeros);
                        //mediosList.setAdapter(adapterMedios);
                        ligerosList.setAdapter(adaptador);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(context, "No se han encontrado luchadores", Toast.LENGTH_LONG);
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
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void listarMediosPuntuacion(String url, final ListView mediosList, final Context context)
    {
        final ArrayList<Luchador> luchadores = new ArrayList<>();
        final ArrayList<ItemPuntuacion> items = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if(!response.isEmpty())
                {
                    try
                    {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++)
                        {
                            System.out.println(response);
                            JSONObject object = array.getJSONObject(i);

                            System.out.println(object.getString("nombre"));

                            String nombre = object.getString("nombre");
                            String apellido1 = object.getString("apellido1");
                            String apellido2 = object.getString("apellido2");
                            int edad = Integer.parseInt(object.getString("edad"));
                            int puntuacion = Integer.parseInt(object.getString("puntuacion"));
                            String peso = object.getString("peso");
                            String categoria = object.getString("categoria");

                            Luchador luchador = new Luchador(nombre, apellido1, apellido2, edad, puntuacion, peso, categoria);
                            ItemPuntuacion item = new ItemPuntuacion(R.drawable.a512x512bb, nombre, apellido1 + " " + apellido2, Integer.toString(puntuacion));

                            if(peso.equals("Medios"))
                            {
                                items.add(item);
                                luchadores.add(luchador);
                            }
                        }

                        AdaptorPuntuacion adaptador = new AdaptorPuntuacion(context, items);

                        //ArrayAdapter<String>adapterLigeros = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, ligeros);
                        //ArrayAdapter<String>adapterMedios = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, medios);
                        ArrayAdapter<Luchador>adapterPesados = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, luchadores);

                        //ligerosList.setAdapter(adapterLigeros);
                        //mediosList.setAdapter(adapterMedios);
                        mediosList.setAdapter(adaptador);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(context, "No se han encontrado luchadores", Toast.LENGTH_LONG);
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
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void listarPesadosPuntuacion(String url, final ListView pesadosList, final Context context)
    {
        final ArrayList<Luchador> luchadores = new ArrayList<>();
        final ArrayList<ItemPuntuacion> items = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if(!response.isEmpty())
                {
                    try
                    {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++)
                        {
                            System.out.println(response);
                            JSONObject object = array.getJSONObject(i);

                            System.out.println(object.getString("nombre"));

                            String nombre = object.getString("nombre");
                            String apellido1 = object.getString("apellido1");
                            String apellido2 = object.getString("apellido2");
                            int edad = Integer.parseInt(object.getString("edad"));
                            int puntuacion = Integer.parseInt(object.getString("puntuacion"));
                            String peso = object.getString("peso");
                            String categoria = object.getString("categoria");

                            Luchador luchador = new Luchador(nombre, apellido1, apellido2, edad, puntuacion, peso, categoria);
                            ItemPuntuacion item = new ItemPuntuacion(R.drawable.a512x512bb, nombre, apellido1 + " " + apellido2, Integer.toString(puntuacion));

                            if(peso.equals("Pesados"))
                            {
                                items.add(item);
                                luchadores.add(luchador);
                            }
                        }

                        final AdaptorPuntuacion adaptador = new AdaptorPuntuacion(context, items);

                        ArrayAdapter<ItemPuntuacion> itemsPuntuacion = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, items);

                        //ArrayAdapter<String>adapterLigeros = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, ligeros);
                        //ArrayAdapter<String>adapterMedios = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, medios);
                        ArrayAdapter<Luchador>adapterPesados = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, luchadores);

                        //ligerosList.setAdapter(adapterLigeros);
                        //mediosList.setAdapter(adapterMedios);
                        pesadosList.setAdapter(adaptador);
                        //pesadosList.setAdapter(adapterPesados);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(context, "No se han encontrado luchadores", Toast.LENGTH_LONG);
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
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void eliminarCorro(final Context context, String url, final String pueblo, final String dia)
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

                        System.out.println(response);
                        System.out.println(pueblo);
                        System.out.println(dia);

                        boolean booleano = object.getBoolean("respuesta");

                        if(booleano)
                        {
                            Toast.makeText(context, "Se ha borrado el corro", Toast.LENGTH_LONG).show();
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
                parametros.put("pueblo", pueblo);
                parametros.put("dia", dia);
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void modificarPermisos(final Context context, String url, final String usuario, final String idRol)
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
                        boolean booleano = object.getBoolean("success");

                        System.out.println(usuario);
                        System.out.println(idRol);

                        if(booleano)
                        {
                            Toast.makeText(context, "Se han modificado los permisos", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(context, "No existe el usuario", Toast.LENGTH_LONG).show();
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
                parametros.put("idRol", idRol);
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void listarUsuarios(final Context context, String url, final ListView listView)
                        {
                            final ArrayList<Usuario> usuarios = new ArrayList<>();

                            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response)
                                {
                                    if(!response.isEmpty())
                                    {
                                        try
                                        {
                                            JSONArray jsonArray = new JSONArray(response);

                                            for (int i = 0; i < jsonArray.length(); i++)
                                            {
                                                JSONObject object = jsonArray.getJSONObject(i);

                                                String nombre = object.getString("nombre");
                                                String rol = object.getString("rol");

                                                Usuario usuario = new Usuario(nombre, rol);

                                                usuarios.add(usuario);
                                            }
                                            AdapterUsuario adapterUsuario = new AdapterUsuario(context, usuarios);
                                            listView.setAdapter(adapterUsuario);
                                            //ArrayAdapter<Usuario> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, usuarios);

                                            //listView.setAdapter(adapter);
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
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void eliminarUsuario(final Context context, String url, final String usuario)
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
                        boolean booleano = object.getBoolean("respuesta");

                        if(booleano)
                        {
                            Toast.makeText(context, "Se han eliminado el usuario", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(context, "No existe el usuario", Toast.LENGTH_LONG).show();
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
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void registrarCorro(final Context context, String url, final String pueblo, final String dia, final String hora, final String lugar)
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
                            Toast.makeText(context, "Se ha registrado el corro", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(context, "Ya existe el corro en ese día", Toast.LENGTH_LONG).show();
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
                parametros.put("pueblo", pueblo);
                parametros.put("dia",dia);
                parametros.put("hora", hora);
                parametros.put("lugar", lugar);
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void modificarPuntuacion(final Context context, String url, final String nombre, final String apellido1, final String puntos)
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
                        boolean booleano = object.getBoolean("success");

                        System.out.println(nombre);
                        System.out.println(apellido1);
                        System.out.println(puntos);

                        if(booleano)
                        {
                            Toast.makeText(context, "Se han modificado la puntuación", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(context, "No existe el luchador", Toast.LENGTH_LONG).show();
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
                parametros.put("nombre", nombre);
                parametros.put("apellido1", apellido1);
                parametros.put("puntuacion", puntos);
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void listarLuchadores(String url, final ListView listView, final Context context)
    {
        final ArrayList<Item> items = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if(!response.isEmpty())
                {
                    try
                    {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++)
                        {
                            System.out.println(response);
                            JSONObject object = array.getJSONObject(i);

                            System.out.println(object.getString("nombre"));

                            String nombre = object.getString("nombre");
                            String apellido1 = object.getString("apellido1");
                            String apellido2 = object.getString("apellido2");
                            int edad = Integer.parseInt(object.getString("edad"));
                            int puntuacion = Integer.parseInt(object.getString("puntuacion"));
                            String peso = object.getString("peso");
                            String categoria = object.getString("categoria");

                            Luchador luchador = new Luchador(nombre, apellido1, apellido2, edad, puntuacion, peso, categoria);
                            Item item = new Item(R.drawable.a512x512bb, nombre, apellido1, apellido2, edad, peso, categoria);

                            items.add(item);
                        }

                        final Adaptador adaptador = new Adaptador(context, items);

                        ArrayAdapter<Item> itemsPuntuacion = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, items);
                        listView.setAdapter(adaptador);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(context, "No se han encontrado luchadores", Toast.LENGTH_LONG);
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
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void listarLuchadoresPuntuacion(String url, final ListView listView, final Context context)
    {
        final ArrayList<ItemPuntuacion> items = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if(!response.isEmpty())
                {
                    try
                    {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++)
                        {
                            System.out.println(response);
                            JSONObject object = array.getJSONObject(i);

                            System.out.println(object.getString("nombre"));

                            String nombre = object.getString("nombre");
                            String apellido1 = object.getString("apellido1");
                            String apellido2 = object.getString("apellido2");
                            int edad = Integer.parseInt(object.getString("edad"));
                            int puntuacion = Integer.parseInt(object.getString("puntuacion"));
                            String peso = object.getString("peso");
                            String categoria = object.getString("categoria");

                            Luchador luchador = new Luchador(nombre, apellido1, apellido2, edad, puntuacion, peso, categoria);
                            ItemPuntuacion item = new ItemPuntuacion(R.drawable.a512x512bb, nombre, apellido1 + " " + apellido2, Integer.toString(puntuacion));

                            items.add(item);
                        }

                        final AdaptorPuntuacion adaptador = new AdaptorPuntuacion(context, items);

                        ArrayAdapter<ItemPuntuacion> itemsPuntuacion = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, items);
                        listView.setAdapter(adaptador);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(context, "No se han encontrado luchadores", Toast.LENGTH_LONG);
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
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void modificarCorro(final Context context, String url, final String pueblo, final String nuevoPueblo, final String dia, final String hora, final String lugar)
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
                        System.out.println(response);
                        JSONObject object = new JSONObject(response);
                        boolean booleano = object.getBoolean("success");

                        if(booleano)
                        {
                            Toast.makeText(context, "Se han modificado el corro", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(context, "No existe el corro", Toast.LENGTH_LONG).show();
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
                parametros.put("pueblo", pueblo);
                parametros.put("nuevoPueblo", nuevoPueblo);
                parametros.put("dia", dia);
                parametros.put("hora", hora);
                parametros.put("lugar", lugar);
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void eliminarLuchador(final Context context, String url, final String nombre, final String apellido1)
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
                        boolean booleano = object.getBoolean("respuesta");

                        if(booleano)
                        {
                            Toast.makeText(context, "Se han eliminado el luchador", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(context, "No existe el luchador", Toast.LENGTH_LONG).show();
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
                parametros.put("nombre", nombre);
                parametros.put("apellido1", apellido1);
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void modificarLuchador(final Context context, String url, final String nombre, final String apellido1, final String nuevoNombre,
                                  final String apellido11, final String apellido2, final String edad, final String peso, final String categoria)
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
                        System.out.println(response);
                        JSONObject object = new JSONObject(response);
                        boolean booleano = object.getBoolean("success");

                        if(booleano)
                        {
                            Toast.makeText(context, "Se han modificado el luchador", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(context, "No existe el luchador", Toast.LENGTH_LONG).show();
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
                parametros.put("nombre", nombre);
                parametros.put("apellido1", apellido1);
                parametros.put("nombre1", nuevoNombre);
                parametros.put("apellido11", apellido11);
                parametros.put("apellido2", apellido2);
                parametros.put("edad", edad);
                parametros.put("peso", peso);
                parametros.put("categoria", categoria);
                return parametros;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }
}
