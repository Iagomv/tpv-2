package com.mycompany.tpv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa la impresión de tickets para las ventas realizadas.
 */
public class ImpresionTickets {
    private List<ProductoVenta> productosVendidos;
    private static final double IVA = 0.1;

    /**
     * Constructor de la clase ImpresionTickets.
     * Inicializa la lista de productos vendidos.
     */
    public ImpresionTickets() {
        this.productosVendidos = new ArrayList<>();
    }

    /**
     * Agrega un producto a la lista de productos vendidos.
     * @param producto El producto a agregar.
     */
    public void agregarProducto(ProductoVenta producto) {
        productosVendidos.add(producto);
    }

    /**
     * Imprime el ticket de venta con los detalles de los productos vendidos y el total.
     */
    public void imprimirTicket() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ticket.txt"))) {
            double total = 0.0;
            DecimalFormat df = new DecimalFormat("#.00");

            // Escribir encabezado del ticket
            writer.write("Cantidad\tDescripción\t\t\tPrecio\tTotal\n");

            // Escribir detalles de los productos vendidos
            for (ProductoVenta producto : productosVendidos) {
                double precioTotal = producto.getCantidad() * producto.getPrecio();
                total += precioTotal;
                writer.write(producto.getCantidad() + "\t\t" + producto.getDescripcion() + "\t\t\t"
                        + producto.getPrecio() + "\t" + df.format(precioTotal) + "\n");
            }

            // Calcular el IVA y el total final
            double iva = total * IVA;
            double totalConIVA = total + iva;

            // Escribir total y desglose del IVA
            writer.write("\nTotal:\t\t\t\t\t\t" + df.format(total) + "\n");
            writer.write("IVA (10%):\t\t\t\t\t" + df.format(iva) + "\n");
            writer.write("Total con IVA:\t\t\t\t" + df.format(totalConIVA) + "\n");

            System.out.println("Ticket impreso correctamente en ticket.txt");
        } catch (IOException e) {
            System.err.println("Error al imprimir el ticket: " + e.getMessage());
        }
    }
}

