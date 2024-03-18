
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpv;

import java.awt.image.BufferedImage;

/**
 *
 * @author Moncho
 */
/**
 * Representa un producto en el sistema de punto de venta.
 */
public class Producto {
    /**
     * El nombre del producto.
     */
    public String nombre;

    /**
     * El precio del producto en formato de cadena.
     */
    public String precio;

    /**
     * La imagen del producto.
     */
    private BufferedImage imagen;

    /**
     * Crea un nuevo producto con el nombre y el precio especificados.
     * @param nombre El nombre del producto.
     * @param precio El precio del producto en formato de cadena.
     */
    public Producto(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Crea un nuevo producto con el nombre, precio e imagen especificados.
     * @param nombre El nombre del producto.
     * @param precio El precio del producto en formato de cadena.
     * @param imagen La imagen del producto.
     */
    public Producto(String nombre, String precio, BufferedImage imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }

    /**
     * Obtiene la imagen del producto.
     * @return La imagen del producto.
     */
    public BufferedImage getImagen() {
        return imagen;
    }

    /**
     * Obtiene el nombre del producto.
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * @param nombre El nuevo nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio del producto.
     * @return El precio del producto en formato de cadena.
     */
    public String getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * @param precio El nuevo precio del producto en formato de cadena.
     */
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    /**
     * Devuelve una representación en cadena del producto (su nombre).
     * @return El nombre del producto.
     */
    @Override
    public String toString() {
        return nombre;
    }

    /**
     * Obtiene el precio del producto como un valor de tipo double.
     * @return El precio del producto como un valor de tipo double.
     */
    public double getPrecioDouble(){
        return Double.parseDouble(precio);
    }
}
