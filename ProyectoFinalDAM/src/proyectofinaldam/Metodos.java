/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinaldam;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vanec
 */
public class Metodos 
{
    Gson gson = new Gson();
    
    public boolean login(String usuario, String contrasenia)
    {
        boolean result = false;
        
        try
        {
            if (!usuario.isEmpty() && !contrasenia.isEmpty()) {
                URL urlC = new URL("http://192.168.1.42/ProyectoDAM/login.php");

                Map<String, Object> params = new LinkedHashMap<>();

                params.put("usuario", usuario);
                params.put("contrasenia", contrasenia);

                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    if (postData.length() != 0) {
                        postData.append('&');
                    }
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                            "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                HttpURLConnection conn = (HttpURLConnection) urlC.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length",
                        String.valueOf(postDataBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);

                Reader in = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), "UTF-8"));
                
                String respuesta = "";
                for (int c = in.read(); c != -1; c = in.read()) {
                    System.out.print((char) c);
                    respuesta += (char) c;
                }
                
                if(respuesta.equals("{\"success\":true,\"roles\":\"administrador\"}"))
                {
                    result = true;
                }
                System.out.println("\n");
            } else {
                System.out.println("No puede haber campos vacíos");
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public void modificarPermisos(String usuario, String idRol)
    {
        try {
            if (!idRol.isEmpty() && !usuario.isEmpty()) {
                URL urlC = new URL("http://192.168.1.42/ProyectoDAM/modificarPermisos.php");

                Map<String, Object> params = new LinkedHashMap<>();

                params.put("usuario", usuario);
                params.put("idRol", idRol);

                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    if (postData.length() != 0) {
                        postData.append('&');
                    }
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                            "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                HttpURLConnection conn = (HttpURLConnection) urlC.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length",
                        String.valueOf(postDataBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);

                Reader in = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), "UTF-8"));
                for (int c = in.read(); c != -1; c = in.read()) {
                    System.out.print((char) c);
                }
                System.out.println("\n");

                //URL urlPermisos = new URL("http://192.168.1.39/ProyectoDAM/modificarPermisos.php?usuario=" + usuario + "&idRol=" + idRol);
                //System.out.println(idRol);
                //System.out.println(usuario);
                //URLConnection coneConnection = urlPermisos.openConnection();
                //coneConnection.setDoOutput(true);
            } else {
                System.out.println("No puede haber campos vacíos");
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarUsuario(String usuarioEliminar)
    {
        try {
                if (!usuarioEliminar.isEmpty()) {
                URL urlEliminarUsuario = new URL("http://192.168.1.42/ProyectoDAM/eliminarUsuario.php");

                Map<String, Object> params = new LinkedHashMap<>();

                params.put("usuario", usuarioEliminar);

                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    if (postData.length() != 0) {
                        postData.append('&');
                    }
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                            "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                HttpURLConnection conn = (HttpURLConnection) urlEliminarUsuario.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length",
                        String.valueOf(postDataBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);

                Reader in = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), "UTF-8"));
                for (int c = in.read(); c != -1; c = in.read()) {
                    System.out.print((char) c);
                }
                System.out.println("\n");
            } else {
                System.out.println("No puede haber campos vacíos");
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(ProyectoFinalDAM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoFinalDAM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void aniadirCorro(String pueblo, String dia, String hora, String lugar)
    {
        try {
            if (!pueblo.isEmpty() & !dia.isEmpty() & !hora.isEmpty() & !lugar.isEmpty()) {
                URL urlEliminarUsuario = new URL("http://192.168.1.42/ProyectoDAM/registrarCorro.php");

                Map<String, Object> params = new LinkedHashMap<>();

                params.put("pueblo", pueblo);
                params.put("dia", dia);
                params.put("hora", hora);
                params.put("lugar", lugar);

                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    if (postData.length() != 0) {
                        postData.append('&');
                    }
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                            "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                HttpURLConnection conn = (HttpURLConnection) urlEliminarUsuario.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length",
                        String.valueOf(postDataBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);

                Reader in = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), "UTF-8"));
                for (int c = in.read(); c != -1; c = in.read()) {
                    System.out.print((char) c);
                }
                System.out.println("\n");
            } else {
                System.out.println("No puede haber campos vacíos");
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(ProyectoFinalDAM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoFinalDAM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarCorro(String pueblo, String puebloNuevo, String dia, String hora, String lugar)
    {
        try {
                URL urlEliminarUsuario = new URL("http://192.168.1.42/ProyectoDAM/modificarCorro.php");

                Map<String, Object> params = new LinkedHashMap<>();

                params.put("pueblo", pueblo);
                params.put("nuevoPueblo", puebloNuevo);
                params.put("dia", dia);
                params.put("hora", hora);
                params.put("lugar", lugar);

                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    if (postData.length() != 0) {
                        postData.append('&');
                    }
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                            "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                HttpURLConnection conn = (HttpURLConnection) urlEliminarUsuario.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length",
                        String.valueOf(postDataBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);

                Reader in = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), "UTF-8"));
                for (int c = in.read(); c != -1; c = in.read()) {
                    System.out.print((char) c);
                }
                System.out.println("\n");

        } catch (MalformedURLException ex) {
            Logger.getLogger(ProyectoFinalDAM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoFinalDAM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarCorro(String pueblo, String dia)
    {
        try {
            if (!pueblo.isEmpty() & !dia.isEmpty()) {
                URL urlEliminarUsuario = new URL("http://192.168.1.42/ProyectoDAM/eliminarCorro.php");

                Map<String, Object> params = new LinkedHashMap<>();

                params.put("pueblo", pueblo);
                params.put("dia", dia);

                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    if (postData.length() != 0) {
                        postData.append('&');
                    }
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                            "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                HttpURLConnection conn = (HttpURLConnection) urlEliminarUsuario.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length",
                        String.valueOf(postDataBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);

                Reader in = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), "UTF-8"));
                for (int c = in.read(); c != -1; c = in.read()) {
                    System.out.print((char) c);
                }
                System.out.println("\n");
            } else {
                System.out.println("No puede haber campos vacíos");
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ProyectoFinalDAM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoFinalDAM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void aniadirLuchador(String nombre, String apellido1, String apellido2, String edad, String peso, String categoria)
    {
        try {
            if (!nombre.isEmpty() & !apellido1.isEmpty() & !apellido2.isEmpty() & !edad.isEmpty() & !peso.isEmpty() & !categoria.isEmpty()) {
                URL urlEliminarUsuario = new URL("http://192.168.1.42/ProyectoDAM/registrarLuchador.php");

                Map<String, Object> params = new LinkedHashMap<>();

                params.put("nombre", nombre);
                params.put("apellido1", apellido1);
                params.put("apellido2", apellido2);
                params.put("edad", edad);
                params.put("peso", peso);
                params.put("categoria", categoria);

                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    if (postData.length() != 0) {
                        postData.append('&');
                    }
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                            "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                HttpURLConnection conn = (HttpURLConnection) urlEliminarUsuario.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length",
                        String.valueOf(postDataBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);

                Reader in = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), "UTF-8"));
                for (int c = in.read(); c != -1; c = in.read()) {
                    System.out.print((char) c);
                }
                System.out.println("\n");
            } else {
                System.out.println("No puede haber campos vacíos");
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProyectoFinalDAM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ProyectoFinalDAM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoFinalDAM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarLuchador(String nombre, String apellido1, String nombreNuevo, String apellido11, String apellido2, String edad, String peso, String categoria)
    {
        try {
            if (!nombre.isEmpty() & !apellido1.isEmpty() & !apellido2.isEmpty() & !edad.isEmpty() & !peso.isEmpty() & !categoria.isEmpty()) {
                URL urlEliminarUsuario = new URL("http://192.168.1.42/ProyectoDAM/modificarLuchador.php");

                Map<String, Object> params = new LinkedHashMap<>();

                params.put("nombre", nombre);
                params.put("apellido1", apellido1);
                params.put("nombre1", nombreNuevo);
                params.put("apellido11", apellido11);
                params.put("apellido2", apellido2);
                params.put("edad", edad);
                params.put("peso", peso);
                params.put("categoria", categoria);

                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    if (postData.length() != 0) {
                        postData.append('&');
                    }
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                            "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                HttpURLConnection conn = (HttpURLConnection) urlEliminarUsuario.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length",
                        String.valueOf(postDataBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);

                Reader in = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), "UTF-8"));
                for (int c = in.read(); c != -1; c = in.read()) {
                    System.out.print((char) c);
                }
                System.out.println("\n");
            } else {
                System.out.println("No puede haber campos vacíos");
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProyectoFinalDAM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoFinalDAM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarLuchador(String nombre, String apellido1)
    {
        try {
            if (!nombre.isEmpty() & !apellido1.isEmpty()) {
                URL urlEliminarUsuario = new URL("http://192.168.1.42/ProyectoDAM/eliminarLuchador.php");

                Map<String, Object> params = new LinkedHashMap<>();

                params.put("nombre", nombre);
                params.put("apellido1", apellido1);

                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    if (postData.length() != 0) {
                        postData.append('&');
                    }
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                            "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                HttpURLConnection conn = (HttpURLConnection) urlEliminarUsuario.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length",
                        String.valueOf(postDataBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);

                Reader in = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), "UTF-8"));
                for (int c = in.read(); c != -1; c = in.read()) {
                    System.out.print((char) c);
                }
                System.out.println("\n");
            } else {
                System.out.println("No puede haber campos vacíos");
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProyectoFinalDAM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoFinalDAM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cambiarPuntuacion(String nombre, String apellido1, int puntuacuonSumar)
    {
        try {
            if (!nombre.isEmpty() & !apellido1.isEmpty()) {
                URL urlEliminarUsuario = new URL("http://192.168.1.42/ProyectoDAM/cambiarPuntuacion.php");

                Map<String, Object> params = new LinkedHashMap<>();

                params.put("nombre", nombre);
                params.put("apellido1", apellido1);
                params.put("puntuacion", puntuacuonSumar);

                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    if (postData.length() != 0) {
                        postData.append('&');
                    }
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                            "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                HttpURLConnection conn = (HttpURLConnection) urlEliminarUsuario.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length",
                        String.valueOf(postDataBytes.length));
                conn.setDoOutput(true);
                conn.getOutputStream().write(postDataBytes);

                Reader in = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), "UTF-8"));
                for (int c = in.read(); c != -1; c = in.read()) {
                    System.out.print((char) c);
                }
                System.out.println("\n");
            } else {
                System.out.println("No puede haber campos vacíos");
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProyectoFinalDAM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoFinalDAM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public void listarLuchadores()
    {
        try
        {
            URL urlLista = new URL("http://192.168.1.42/ProyectoDAM/listaLuchadores.php");

            URLConnection connection = urlLista.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String linea;
            String json = "";

            if ((linea = reader.readLine()) != null) {
                System.out.println(linea);
                json += linea;

                Luchador[] luchadorArray = gson.fromJson(json, Luchador[].class);

                for (Luchador luchador : luchadorArray) {
                    System.out.println(luchador.getNombre() + "\n" + luchador.getApellido1() + " "
                            + luchador.getApellido2() + " " + luchador.getEdad() + " " + luchador.getPeso() + " " + luchador.getCategoria());
                    System.out.println("");
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listarUsuarios()
    {
        try
        {
            URL urlLista = new URL("http://192.168.1.42/ProyectoDAM/listaUsuarios.php");

            URLConnection connection = urlLista.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String linea;
            String json = "";

            if ((linea = reader.readLine()) != null) {
                //System.out.println(linea);
                json += linea;

                Usuario[] userArray = gson.fromJson(json, Usuario[].class);

                for (Usuario user : userArray) {
                    System.out.println(user.getNombre());
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listarCorros()
    {
        try
        {
            URL urlLista = new URL("http://192.168.1.42/ProyectoDAM/listarCorros.php");

            URLConnection connection = urlLista.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String linea;
            String json = "";

            if ((linea = reader.readLine()) != null) {
                System.out.println(linea);
                json += linea;

                Corro[] corroArray = gson.fromJson(json, Corro[].class);

                for (Corro corro : corroArray) {
                    System.out.println(corro.getPueblo() + "\n" + corro.getDia() + " " + corro.getHora() + " " + corro.getLugar());
                }
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listaPuntuacion()
    {
        try
        {
            URL urlLista = new URL("http://192.168.1.42/ProyectoDAM/listaLuchadores.php");

            URLConnection connection = urlLista.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String linea;
            String json = "";

            if ((linea = reader.readLine()) != null) {
                System.out.println(linea);
                json += linea;

                Luchador[] luchadorArray = gson.fromJson(json, Luchador[].class);

                for (Luchador luchador : luchadorArray) {
                    System.out.println(luchador.getNombre() + " " + luchador.getApellido1() + " " + luchador.getApellido2() + "\n" + luchador.getPuntuacion());
                    System.out.println("");
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
