package com.mycompany.tpv;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase representa un panel que contiene una tabla para mostrar el ticket de compra.
 * La tabla muestra el nombre, el precio y la cantidad de cada producto en el ticket.
 * Además, hay una etiqueta para mostrar información adicional sobre el ticket.
 */
public class ZonaTicket extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6996019853845143417L;

	/**
     * La tabla que muestra los detalles del ticket.
     */
    private static JTable tablaTicket;

    /**
     * Las coordenadas x e y del panel en la ventana.
     */
    @SuppressWarnings("unused")
    private int x, y;

    /**
     * El ancho y el alto del panel.
     */
    private int ancho, alto;

    /**
     * Crea un nuevo panel de zona de ticket con las coordenadas y dimensiones especificadas.
     *
     * @param x     La coordenada x del panel en la ventana.
     * @param y     La coordenada y del panel en la ventana.
     * @param ancho El ancho del panel.
     * @param alto  La altura del panel.
     */
    public ZonaTicket(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;

        // Configura el diseño y el fondo del panel
        setLayout(null);
        setBounds(x, y, ancho, alto);
        setOpaque(true);
        setBackground(Color.white);
        setVisible(true);

        // Añade la tabla de ticket y una etiqueta al panel
        ponTablaTicket();

        JLabel jlabel = new JLabel();
        jlabel.setText("Hooooola");
        jlabel.setBounds(0, 0, 100, 10);
        jlabel.setVisible(true);
        add(jlabel);
    }

    /**
     * Crea y configura la tabla de ticket dentro del panel.
     */
    private void ponTablaTicket() {
        tablaTicket = new JTable();
        tablaTicket.setBounds(0, 0, ancho, alto);

        // Define las columnas de la tabla
        Object[] header = new Object[]{"Nombre", "Precio", "Cantidad"};
        VariablesGenerales.modeloTablaTicket = new DefaultTableModel(header, 0);

        // Asigna el modelo de tabla al JTable
        tablaTicket.setModel(VariablesGenerales.modeloTablaTicket);
        tablaTicket.setVisible(true);
        add(tablaTicket);
    }

    // Método para actualizar la tabla con la información actualizada del ticket
    public static void actualizarTabla() {
        // Limpiar la tabla
        DefaultTableModel modelo = (DefaultTableModel) tablaTicket.getModel();
        modelo.setRowCount(0);
        
        // Volver a agregar las líneas del ticket actualizadas
        for (LineaTicket linea : VariablesGenerales.lineasTicket) {
            modelo.addRow(new Object[]{linea.getNombreProducto(), linea.getPrecioProducto(), linea.getCantidadProducto()});
        }
    }

}
