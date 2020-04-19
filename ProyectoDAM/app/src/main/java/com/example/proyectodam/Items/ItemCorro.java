package com.example.proyectodam.Items;

public class ItemCorro
{
    private int imagen;
    private String pueblo;
    private String dia;
    private String hora;
    private String lugar;

    public ItemCorro(int imagen, String pueblo, String dia, String hora, String lugar) {
        this.imagen = imagen;
        this.pueblo = pueblo;
        this.dia = dia;
        this.hora = hora;
        this.lugar = lugar;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
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
