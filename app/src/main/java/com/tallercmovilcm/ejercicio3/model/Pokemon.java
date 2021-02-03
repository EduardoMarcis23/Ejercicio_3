package com.tallercmovilcm.ejercicio3.model;

import java.io.Serializable;
import java.util.Hashtable;

public class Pokemon implements Serializable
{

    private int id;
    private String url, nombre;

    public Pokemon(int id, String nombre, String url )
    {
        this.id = id;
        this.nombre = nombre;
        this.url = url;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
}