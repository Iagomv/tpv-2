package com.mycompany.tpv;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * La clase Productos representa una lista de productos que se pueden mostrar en la pantalla principal de la aplicación.
 * Contiene métodos para gestionar la paginación de los productos, así como para imprimir un ticket de compra.
 */
public class Productos {
    /**
    * Lista que contiene los productos.
    */
    private List<Producto> productos = new ArrayList<>();
    /**
    * Número máximo de productos por página.
    */
    final int PRODUCTOSxPAGINA = 9;
    /**
    * Número de filas para mostrar los productos.
    */
    final int FILAS = 3;
    /**
    * Número de columnas para mostrar los productos.
    */
    final int COLUMNAS = 3;
    /**
    * Página actual de productos.
    */
    private int paginaActual;
    /**
    * Número total de páginas.
    */
    private int numeroPaginas;
    
    JPanel panelProductos;
    Container contenedorGeneral;
    JPanel pladur;
    @SuppressWarnings("unused")
    private PantallaPrincipal pantallaPrincipal;

    /**
     * Constructor.
     * 
     * @param contenedorGeneral El contenedor general donde se mostrarán los productos.
     * @param panelProductos El panel donde se mostrarán los productos.
     * @param pantallaPrincipal La instancia de la clase PantallaPrincipal.
     */
    public Productos(Container contenedorGeneral, JPanel panelProductos, PantallaPrincipal pantallaPrincipal) {

        this.panelProductos = panelProductos;
        this.contenedorGeneral = contenedorGeneral;
        this.pantallaPrincipal = pantallaPrincipal;
        botonesPasaPaginaProducto();
        botonBorrar();
    }


	/**
    * @return La página actual de productos.
    */
    public int getPaginaActual() {
        return paginaActual;
    }

    /**
    * @param paginaActual La página actual de productos a establecer.
    */
    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }

    /**
    * @return El número total de páginas.
    */
    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    /**
    * @param numeroPaginas El número total de páginas a establecer.
    */
    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    /**
    * @return La lista de productos.
    */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
    * @param productos La lista de productos a establecer.
    */
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /**
 * Configura el botón para borrar el último producto añadido.
 * @see ZonaTicket#actualizarTabla()
 */
    public void botonBorrar(){
        JButton botonBorrar = new JButton();
        botonBorrar.setBounds(0, 0, 75, 50);
        botonBorrar.setText("Borrar");
        botonBorrar.setVisible(true);
        contenedorGeneral.add(botonBorrar);
        
        botonBorrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!VariablesGenerales.lineasTicket.isEmpty()) {
                    VariablesGenerales.restar = true;
                    System.out.println("Restar está en true");
                    
                    // Obtener el precio del último ítem del ticket
                    double precioUltimoItem = VariablesGenerales.lineasTicket.get(VariablesGenerales.lineasTicket.size() - 1).getPrecioProducto();
                    
                    // Restar el precio del último ítem del total del ticket
                    VariablesGenerales.totalTicket -= precioUltimoItem;
                    
                    // Eliminar el último ítem del ticket
                    VariablesGenerales.lineasTicket.remove(VariablesGenerales.lineasTicket.size() - 1);
                    
                    ZonaTicket.actualizarTabla();
                    
                } else {
                    System.out.println("No hay elementos en el ticket para borrar.");
                }
            }
        });
    }
    
    /**
    *Configura los botones para navegar entre páginas de productos.
    flechaAnterior  /  flechaSiguiente
    */
    public void botonesPasaPaginaProducto() {

        

        JLabel flechaAnterior = new JLabel();
        flechaAnterior.setIcon(new ImageIcon("recursos\\imagenes\\anterior.png"));
        flechaAnterior.setVisible(true);
        flechaAnterior.setBounds(0, 180, 50, 50);

        contenedorGeneral.add(flechaAnterior);

        flechaAnterior.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                paginaActual = (--paginaActual + numeroPaginas) % numeroPaginas;
                muestraPaginaProductos();
            }

        });

        JLabel flechaSiguiente = new JLabel();
        flechaSiguiente.setIcon(new ImageIcon("recursos\\imagenes\\siguiente.png"));
        flechaSiguiente.setVisible(true);
        flechaSiguiente.setBounds(440, 180, 50, 50);

        contenedorGeneral.add(flechaSiguiente);

        flechaSiguiente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                paginaActual = (++paginaActual) % numeroPaginas;
                muestraPaginaProductos();
            }

        });
    }

    /**
     * utiliza {@code mostrarProductos} para mostrar la lista por consola
     * Muestra la página actual de productos en el panel correspondiente.
     *@see PantallaPrincipal#panelProductos
    */
    public void muestraPaginaProductos() {
        mostrarProductos();
        panelProductos.removeAll();
        for (int i = paginaActual * PRODUCTOSxPAGINA; i < paginaActual * PRODUCTOSxPAGINA + PRODUCTOSxPAGINA && i < productos.size(); i++) {
            final int PRODUCTOACTUAL = i;
            JPanel panel = new JPanel();
            JLabel imagen = new JLabel();
            JLabel texto = new JLabel();
    
            panel.setLayout(null);
            panelProductos.add(panel);
            panel.add(imagen);
            panel.add(texto);
    
            panel.setOpaque(true);
            panel.setBounds(110 * ((i - paginaActual * PRODUCTOSxPAGINA) % COLUMNAS),
                    125 * ((i - paginaActual * PRODUCTOSxPAGINA) / FILAS), 100, 115);
            imagen.setOpaque(true);
            imagen.setBounds(0, 0, 100, 100);
            texto.setBounds(0, 100, 100, 15);
            texto.setHorizontalAlignment(SwingConstants.CENTER);
            texto.setVerticalAlignment(SwingConstants.CENTER);
    
            // Obtener la imagen desde la base de datos y redimensionarla
            BufferedImage imagenProducto = CargarImagenes.cargarImagenDesdeBaseDeDatos(productos.get(i).getNombre());
            if (imagenProducto != null) {
                ImageIcon icono = new ImageIcon(imagenProducto);
                imagen.setIcon(icono);
            }
    
            texto.setText(productos.get(i).toString());
            imagen.setVisible(true);
            texto.setVisible(true);
    
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Producto producto = productos.get(PRODUCTOACTUAL);
                    imprimePrecio(producto);
                }
            });
    
            panelProductos.repaint();
        }
    }
    
    /**
     * Imprime el precio de un producto seleccionado.
     * 
     * @param producto El producto del cual se imprimirá el precio.
     */
    private void imprimePrecio(Producto producto) {
        boolean encontrado = false;
        for (int i = 0; i < VariablesGenerales.lineasTicket.size(); i++) {
            if (VariablesGenerales.lineasTicket.get(i).getNombreProducto().equals(producto.getNombre())) {
                if (VariablesGenerales.lineasTicket.get(i).getCantidadProducto() > 1 && VariablesGenerales.restar) {
                    VariablesGenerales.lineasTicket.get(i)
                            .setCantidadProducto(VariablesGenerales.lineasTicket.get(i).getCantidadProducto() - 1);
                    VariablesGenerales.modeloTablaTicket
                            .setValueAt(VariablesGenerales.lineasTicket.get(i).getCantidadProducto(), i, 2);
                } else if (VariablesGenerales.lineasTicket.get(i).getCantidadProducto() == 1
                        && VariablesGenerales.restar) {
                    VariablesGenerales.lineasTicket.remove(i);
                    VariablesGenerales.modeloTablaTicket.removeRow(i);
                } else {
                    VariablesGenerales.lineasTicket.get(i)
                            .setCantidadProducto(VariablesGenerales.lineasTicket.get(i).getCantidadProducto() + 1);
                    VariablesGenerales.modeloTablaTicket
                            .setValueAt(VariablesGenerales.lineasTicket.get(i).getCantidadProducto(), i, 2);
                }
                encontrado = true;

                break;
            }
        }
        if (!encontrado && !VariablesGenerales.restar) {
            VariablesGenerales.lineasTicket.add(new LineaTicket(producto.getNombre(), producto.getPrecioDouble(), 1));
            VariablesGenerales.modeloTablaTicket.addRow(
                    new Object[] { producto.getNombre(), producto.getPrecio(), "1" });
        }
        VariablesGenerales.restar = false;

        VariablesGenerales.totalTicket += producto.getPrecioDouble();
        System.out.println("Producto seleccionado: " + producto.getNombre() + " (" + producto.getPrecio() + ")");
        System.out.println("Total actual: " + VariablesGenerales.totalTicket);
    }

    /**
     * Muestra los productos en la consola.
     */
    private void mostrarProductos() {
        for (Producto producto : productos) {
            System.out.println(producto.nombre);
        }
    }

    /**
     * Imprime el ticket en un archivo de texto plano.
     * Utilizando FileWriter
     */
    public static void imprimirTicket() {
        try {
            FileWriter writer = new FileWriter("ticket.txt");

            // Cabecera del ticket
            writer.write("Cantidad\tDescripción\t\tTotal\n");

            // Líneas de productos
            for (LineaTicket linea : VariablesGenerales.lineasTicket) {
                String cantidad = Integer.toString(linea.getCantidadProducto());
                String descripcion = linea.getNombreProducto();
                String total = String.format("%.2f", linea.getCantidadProducto() * linea.getPrecioProducto());

                writer.write(String.format("%-8s\t%-20s\t%-10s\n", cantidad, descripcion, total));
            }

            // Línea del total y el IVA
            double total = VariablesGenerales.totalTicket;
            double iva = total * 0.1;
            double totalConIva = total + iva;

            writer.write("\n");
            writer.write(String.format("\t\t\tTotal: %.2f\n", total));
            writer.write(String.format("\t\t\tIVA (10%%): %.2f\n", iva));
            writer.write(String.format("\t\t\tTotal con IVA: %.2f\n", totalConIva));

            writer.close();
            
            System.out.println("Ticket impreso correctamente en el archivo 'ticket.txt'");
        } catch (IOException e) {
            System.out.println("Error al imprimir el ticket: " + e.getMessage());
        }
    }
}
