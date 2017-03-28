
package com.herprogramacion.restaurantericoparico.modelo;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Comida {
    private float Rating;
    private String nombre;
    private int idDrawable;
    private String descripcion;
    private int position;
    private int voteCount;
    private String date;

    public Comida(float Rating, String nombre, int idDrawable , String descripcion , float rating , int position,int voteCount, String date) {
        this.Rating = Rating;
        this.nombre = nombre;
        this.idDrawable = idDrawable;
        this.descripcion = descripcion;
        this.position = position;
        this.voteCount=voteCount;
        this.date=date;
    }
    public float getRating() {
        return Rating;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public String getDescripcion(){
        return  descripcion;
    }
    public int getVoteCount(){return voteCount;}
    public String getDate(){return date;}

    public int getPosition(){return position;}
}
