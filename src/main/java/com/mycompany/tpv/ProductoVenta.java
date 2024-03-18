package com.mycompany.tpv;

/**
 * Esta clase representa un producto vendido en una transacción.
 */
class ProductoVenta {
    private String descripcion;
    private int cantidad;
    private double precio;

    /**
     * Constructor de la clase ProductoVenta.
     * @param descripcion La descripción del producto.
     * @param cantidad La cantidad del producto vendido.
     * @param precio El precio unitario del producto.
     */
    public ProductoVenta(String descripcion, int cantidad, double precio) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    /**

     * @return La descripción del producto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return La cantidad del producto vendido.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @return El precio unitario del producto.
     */
    public double getPrecio() {
        return precio;
    }
}
