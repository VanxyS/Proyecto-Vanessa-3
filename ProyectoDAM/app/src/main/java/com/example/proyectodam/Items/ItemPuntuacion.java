package com.example.proyectodam.Items;

public class ItemPuntuacion
{
    private int imagen;
    private String tituloPuntuacion;
    private String subtituloPuntuacion;
    private String puntuacion;

    public ItemPuntuacion(int imagen, String tituloPuntuacion, String subtituloPuntuacion, String puntuacion) {
        this.imagen = imagen;
        this.tituloPuntuacion = tituloPuntuacion;
        this.subtituloPuntuacion = subtituloPuntuacion;
        this.puntuacion = puntuacion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTituloPuntuacion() {
        return tituloPuntuacion;
    }

    public void setTituloPuntuacion(String tituloPuntuacion) {
        this.tituloPuntuacion = tituloPuntuacion;
    }

    public String getSubtituloPuntuacion() {
        return subtituloPuntuacion;
    }

    public void setSubtituloPuntuacion(String subtituloPuntuacion) {
        this.subtituloPuntuacion = subtituloPuntuacion;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }
}
