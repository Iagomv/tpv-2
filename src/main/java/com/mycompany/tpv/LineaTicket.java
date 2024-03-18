package com.mycompany.tpv;

/**
 * Esta clase representa una línea de producto en un ticket de venta.
 */
public class LineaTicket {
    String nombreProducto;
    double precioProducto;
    int cantidadProducto;

    /**
     * Constructor de la clase LineaTicket.
     * @param nombreProducto El nombre del producto.
     * @param precioProducto El precio del producto.
     * @param cantidadProducto La cantidad del producto.
     */
    public LineaTicket(String nombreProducto, double precioProducto, int cantidadProducto) {
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.cantidadProducto = cantidadProducto;
    }

    /**
     * Obtiene el nombre del producto.
     * @return El nombre del producto.
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Establece el nombre del producto.
     * @param nombreProducto El nuevo nombre del producto.
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Obtiene el precio del producto.
     * @return El precio del producto.
     */
    public double getPrecioProducto() {
        return precioProducto;
    }

    /**
     * Establece el precio del producto.
     * @param precioProducto El nuevo precio del producto.
     */
    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    /**
     * Obtiene la cantidad del producto.
     * @return La cantidad del producto.
     */
    public int getCantidadProducto() {
        return cantidadProducto;
    }

    /**
     * Establece la cantidad del producto.
     * @param cantidadProducto La nueva cantidad del producto.
     */
    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
}
