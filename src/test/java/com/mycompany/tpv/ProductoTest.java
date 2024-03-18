package com.mycompany.tpv;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class ProductoTest {

    @Test
    public void testConstructor() {
        Producto producto = new Producto("Manzana", "1.50");
        assertEquals("Manzana", producto.getNombre());
        assertEquals(1.50, producto.getPrecioDouble(), 0.001);
    }

    @Test
    public void testSetNombre() {
        Producto producto = new Producto("Manzana", "1.50");
        producto.setNombre("Pera");
        assertEquals("Pera", producto.getNombre());
    }

    @Test
    public void testSetPrecio() {
        Producto producto = new Producto("Manzana", "1.50");
        producto.setPrecio("2.00");
        assertEquals(2.00, producto.getPrecioDouble(), 0.001);
    }

    @Test
    public void testToString() {
        Producto producto = new Producto("Manzana", "1.50");
        assertEquals("Manzana", producto.toString());
    }

    @Test
    public void testGetPrecioNegative() {
        Producto producto = new Producto("Manzana", "-1.50");
        assertEquals(-1.50, producto.getPrecioDouble(), 0.001);
    }

    @Test
    public void testGetPrecioZero() {
        Producto producto = new Producto("Manzana", "0.00");
        assertEquals(0.0, producto.getPrecioDouble(), 0.001);
    }
}
