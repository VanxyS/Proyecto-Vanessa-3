/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinaldam;

import java.util.Scanner;
/**
 *
 * @author Vanec
 */
public class ProyectoFinalDAM 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Scanner teclado = new Scanner(System.in);
        int opcion = 1;
        Metodos metodos = new Metodos();
        
        System.out.println("Usuario: ");
        String usuarioLogin = teclado.next();
        System.out.println("Contraseña: ");
        String contrasenia = teclado.next();
        
        if(metodos.login(usuarioLogin, contrasenia) == true)
        {
            while (opcion >= 1 && opcion <= 10) {
                System.out.println("1. Modificar permisos");
                System.out.println("2. Eliminar usuario");
                System.out.println("3. Añadir corro");
                System.out.println("4. Modificar corro");
                System.out.println("5. Eliminar corro");
                System.out.println("6. Añadir luchador");
                System.out.println("7. Modificar luchador");
                System.out.println("8. Cambiar puntuación");
                System.out.println("9. Eliminar luchador");
                System.out.println("10. Salir");

                opcion = teclado.nextInt();

                switch (opcion) {
                    case 1:
                        metodos.listarUsuarios();

                        System.out.println("Usuario a modificar: ");
                        String usuario = teclado.next();

                        System.out.println("Nuevo rol (usuario, administrador, luchador): ");
                        String nuevoRol = teclado.next();

                        String idRol = null;

                        switch (nuevoRol) {
                            case "usuario":
                                idRol = "1";
                                break;

                            case "administrador":
                                idRol = "2";
                                break;

                            case "luchador":
                                idRol = "3";
                                break;

                            default:
                                System.out.println("No es un rol valido");
                        }

                        metodos.modificarPermisos(usuario, idRol);
                        break;

                    case 2:
                        metodos.listarUsuarios();

                        System.out.println("Usuario a eliminar: ");
                        String usuarioEliminar = teclado.next();

                        metodos.eliminarUsuario(usuarioEliminar);
                        break;

                    case 3:
                        metodos.listarCorros();

                        System.out.println("Pueblo: ");
                        String pueblo = teclado.next();
                        System.out.println("Dia (dd/mm/aa): ");
                        String dia = teclado.next();
                        System.out.println("Hora (hh:mm:ss): ");
                        String hora = teclado.next();
                        System.out.println("Lugar: ");
                        String lugar = teclado.next();

                        metodos.aniadirCorro(pueblo, dia, hora, lugar);
                        break;

                    case 4:
                        metodos.listarCorros();

                        System.out.println("Pueblo: ");
                        String puebloModificar = teclado.next();
                        System.out.println("Nuevo Pueblo: ");
                        String puebloNuevo = teclado.next();
                        System.out.println("Nuevo Dia (dd/mm/aa): ");
                        String diaModificar = teclado.next();
                        System.out.println("Nueva Hora (hh:mm:ss): ");
                        String horaModificar = teclado.next();
                        System.out.println("Nuevo Lugar: ");
                        String lugarModificar = teclado.next();

                        metodos.modificarCorro(puebloModificar, puebloNuevo, diaModificar, horaModificar, lugarModificar);
                        break;

                    case 5:
                        metodos.listarCorros();

                        System.out.println("Pueblo: ");
                        String puebloEliminar = teclado.next();
                        System.out.println("Dia (dd/mm/aa): ");
                        String diaEliminar = teclado.next();

                        metodos.eliminarCorro(puebloEliminar, diaEliminar);
                        break;

                    case 6:
                        metodos.listarLuchadores();

                        System.out.println("Nombre: ");
                        String nombre = teclado.next();
                        System.out.println("Primer Apellido): ");
                        String apellido1 = teclado.next();
                        System.out.println("Segundo Apellido: ");
                        String apellido2 = teclado.next();
                        System.out.println("Edad: ");
                        String edad = teclado.next();
                        System.out.println("Peso: ");
                        String peso = teclado.next();
                        System.out.println("Categoría: ");
                        String categoria = teclado.next();

                        metodos.aniadirLuchador(nombre, apellido1, apellido2, edad, peso, categoria);
                        break;

                    case 7:
                        metodos.listarLuchadores();

                        System.out.println("Nombre: ");
                        String nombreModificar = teclado.next();
                        System.out.println("Primer Apellido): ");
                        String apellido1Modificar = teclado.next();
                        System.out.println("Nuevo Nombre: ");
                        String nombreNuevo = teclado.next();
                        System.out.println("Nuevo Primer Apellido): ");
                        String apellido11 = teclado.next();
                        System.out.println("Nuevo Segundo Apellido: ");
                        String apellido2Nuevo = teclado.next();
                        System.out.println("Nueva Edad: ");
                        String edadNueva = teclado.next();
                        System.out.println("Nuevo Peso: ");
                        String pesoNuevo = teclado.next();
                        System.out.println("Nueva Categoría: ");
                        String categoriaNueva = teclado.next();

                        metodos.modificarLuchador(nombreModificar, apellido1Modificar, nombreNuevo, apellido11, apellido2Nuevo, edadNueva, pesoNuevo, categoriaNueva);
                        break;

                    case 8:
                        metodos.listaPuntuacion();

                        System.out.println("Nombre: ");
                        String nombrePuntuacion = teclado.next();
                        System.out.println("Primer Apellido): ");
                        String apellido1Puntuacion = teclado.next();

                        System.out.println("");

                        System.out.println("1. Primer puesto");
                        System.out.println("2. Segundo puesto");
                        System.out.println("3. Tercer puesto");
                        System.out.println("4. Cuarto puesto");
                        System.out.println("5. Otro puesto");
                        int puesto = teclado.nextInt();

                        int puntuacuonSumar = 0;

                        switch (puesto) {
                            case 1:
                                puntuacuonSumar = 10;
                                break;

                            case 2:
                                puntuacuonSumar = 8;
                                break;

                            case 3:
                                puntuacuonSumar = 6;
                                break;

                            case 4:
                                puntuacuonSumar = 4;
                                break;

                            case 5:
                                puntuacuonSumar = 2;
                                break;
                            default:
                                System.out.println("No es un puesto valido");
                        }

                        metodos.cambiarPuntuacion(nombrePuntuacion, apellido1Puntuacion, puntuacuonSumar);
                        break;

                    case 9:
                        metodos.listarLuchadores();

                        System.out.println("Nombre: ");
                        String nombreEliminar = teclado.next();
                        System.out.println("Primer Apellido): ");
                        String apellido1Eliminar = teclado.next();

                        metodos.eliminarLuchador(nombreEliminar, apellido1Eliminar);
                        break;

                    case 10:
                        System.exit(0);
                }
            }
        }
        else
        {
            System.out.println("No es un administrador");
            System.exit(0);
        }
    }
}
