/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinaldam;

/**
 *
 * @author Vanec
 */
public class Corro 
{
    private String pueblo;
    private String dia;
    private String hora;
    private String lugar;

    public Corro(String pueblo, String dia, String hora, String lugar) {
        this.pueblo = pueblo;
        this.dia = dia;
        this.hora = hora;
        this.lugar = lugar;
    }

    public String getPueblo() {
        return pueblo;
    }

    public void setPueblo(String pueblo) {
        this.pueblo = pueblo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    
    
}
