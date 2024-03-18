package com.mycompany.tpv;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * Clase que representa la pantalla principal de la aplicación TPV.
 */
public class PantallaPrincipal extends javax.swing.JFrame {

    private static final long serialVersionUID = -2669217118218093817L;
	/**
     * Lista que contiene los nombres de las familias de productos.
     */
    private final List<String> familias = new ArrayList<>();
    /**
     * HashMap que mapea el nombre de la familia de productos con su respectivo objeto de Productos.
     */
    private final HashMap<String, Productos> productosHM = new HashMap<>();
    /**
     * Número de familias de productos que se mostrarán por página.
     */
    final int FAMILIASxPAGINA = 6;
    /**
     * Página actual de las familias de productos.
     */
    int paginaActual;
    /**
     * Número total de páginas de familias de productos.
     */
    int numeroPaginas;
    /**
     * Panel que contiene las familias de productos.
     */
    JPanel panelFamilias;
    /**
     * Panel que contiene los productos de una familia seleccionada.
     */
    JPanel panelProductos;
    /**
     * Panel que sirve como contenedor principal de otros elementos.
     */
    JPanel pladur;

    /**
     * Constructor de la clase PantallaPrincipal.
     * Inicializa la interfaz gráfica y carga las familias de productos.
     */
    public PantallaPrincipal() {
        initComponents();
        setSize(800,800);
        setLocationRelativeTo(null);
        creaPaneles();
        leerFamilias();
        bontonesPasaPaginaFamilia();
        muestraPaginaFamilias();
        mostrarProductosPrimeraFamilia();
        getContentPane().add(new ZonaTicket(600, 0, 400, 500));
    }

     /**
     * Agrega los botones de navegación para pasar entre las páginas de las familias de productos.
     * modificando el valor de 
     * @see PantallaPrincipal#paginaActual
     */
    private void bontonesPasaPaginaFamilia() {
        JLabel flechaAnterior = new JLabel();
        flechaAnterior.setIcon(new ImageIcon("recursos\\imagenes\\anterior.png"));
        flechaAnterior.setVisible(true);
        flechaAnterior.setBounds(75, 110, 50, 50);
        getContentPane().add(flechaAnterior);

        flechaAnterior.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (paginaActual > 0) {
                    paginaActual--;
                    muestraPaginaFamilias();
                }
            }
        });

        JLabel flechaSiguiente = new JLabel();
        flechaSiguiente.setIcon(new ImageIcon("recursos\\imagenes\\siguiente.png"));
        flechaSiguiente.setVisible(true);
        flechaSiguiente.setBounds(490, 110, 50, 50);
        getContentPane().add(flechaSiguiente);

        flechaSiguiente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (paginaActual < numeroPaginas - 1) {
                    paginaActual++;
                    muestraPaginaFamilias();
                }
            }
        });
    }

    /**
     * Muestra la página actual de familias de productos en el panelFamilias.
     */
    private void muestraPaginaFamilias() {
        panelFamilias.removeAll();

        for (int i = paginaActual * FAMILIASxPAGINA; i < paginaActual * FAMILIASxPAGINA + FAMILIASxPAGINA && i < familias.size(); i++) {
            JPanel panel = new JPanel();
            JLabel imagen = new JLabel();
            JLabel texto = new JLabel();

            panel.setLayout(null);
            panelFamilias.add(panel);
            panel.add(imagen);
            panel.add(texto);

            panel.setOpaque(true);
            panel.setBounds(110 * ((i - paginaActual * FAMILIASxPAGINA) % 3), 125 * ((i - paginaActual * FAMILIASxPAGINA) / 3), 100, 115);
            imagen.setOpaque(true);
            imagen.setBounds(0, 0, 100, 100);
            texto.setBounds(0, 100, 100, 15);
            texto.setHorizontalAlignment(SwingConstants.CENTER);
            texto.setVerticalAlignment(SwingConstants.CENTER);

            if (i < familias.size()) {
                imagen.setIcon(new ImageIcon("recursos\\imagenes\\" + familias.get(i) + ".jpg"));
                texto.setText(familias.get(i));

                imagen.setVisible(true);
                texto.setVisible(true);
            }

            panelFamilias.repaint();

            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Component[] componentes = pladur.getComponents();

                    for (Component componente : componentes) {
                        if (componente instanceof JLabel) {
                            pladur.remove(componente);
                        }
                    }

                    productosHM.get(texto.getText()).setPaginaActual(0);
                    productosHM.get(texto.getText()).muestraPaginaProductos();
                    productosHM.get(texto.getText()).botonesPasaPaginaProducto();
                }
            });
        }

    }

    /**
     * Maneja la acción de imprimir el ticket cuando se presiona el botón correspondiente.
     * @param evt El evento de acción asociado al botón de impresión de ticket.
     */
    private void imprimirTicketButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Lógica para imprimir el ticket
        JOptionPane.showMessageDialog(rootPane, "Imprimiendo ticket...");
        Productos.imprimirTicket();
    }
    
    /**
     * Inicializa los componentes de la interfaz de usuario.
     * Este método es llamado desde el constructor de la clase para configurar y mostrar la GUI.
     */
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        JButton imprimirTicketButton = new javax.swing.JButton();
        imprimirTicketButton.setText("Imprimir Ticket");
        imprimirTicketButton.setBounds(75, 650, 150, 30); // Define la posición y tamaño del botón
        imprimirTicketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirTicketButtonActionPerformed(evt);
            }
        });
        getContentPane().add(imprimirTicketButton);


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 652, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 343, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    /**
     * Lee las familias de productos desde la base de datos y las carga en la interfaz gráfica.
     * determina el número de páginas que tiene cada familia y rellena el hashMap 
     * @see PantallaPrincipal#productosHM
     */
    private void leerFamilias() {
        String url = "jdbc:mysql://localhost:3306/entornos 2ev";
        String user = "root";
        String password = "";
        String query = "SELECT DISTINCT Familia_Producto AS familia FROM productos";
    
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
    
            while (rs.next()) {
                String familia = rs.getString("familia");
                familias.add(familia);
    
                Productos productos = new Productos(pladur, panelProductos, this);
                String queryProductos = "SELECT Nombre_Producto, Precio_Producto, Imagen_Producto FROM productos WHERE Familia_Producto = '" + familia + "'";
                try (Statement stmtProductos = conn.createStatement();
                        ResultSet rsProductos = stmtProductos.executeQuery(queryProductos)) {
                         while (rsProductos.next()) {
                        String nombreProducto = rsProductos.getString("Nombre_Producto");
                        String precioProducto = rsProductos.getString("Precio_Producto");
                        // Assuming the image is stored as a byte array in the database
                        byte[] imagenBytes = rsProductos.getBytes("Imagen_Producto");
                        BufferedImage imagenProducto = ImageIO.read(new ByteArrayInputStream(imagenBytes));
                        productos.getProductos().add(new Producto(nombreProducto, precioProducto, imagenProducto));
                    }

                } catch (SQLException | IOException ex) {
                    Logger.getLogger(PantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
    
                int numPaginas = 1 + productos.getProductos().size() / productos.PRODUCTOSxPAGINA;
                if (numPaginas < 1) {
                    numPaginas = 1;
                }
                System.out.println("Número de páginas para la familia " + familia + ": " + numPaginas);
                productos.setNumeroPaginas(numPaginas);
    
                numeroPaginas += numPaginas;
    
                productosHM.put(familia, productos);
            }
    
        } catch (SQLException ex) {
            Logger.getLogger(PantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Crea los paneles necesarios para la interfaz gráfica, familias, productos y pladur.
     */
    private void creaPaneles() {
        panelFamilias = new JPanel();
        panelFamilias.setLayout(null);
        panelFamilias.setBounds(150, 10, 320, 240);
        panelFamilias.setVisible(true);

        getContentPane().add(panelFamilias);

        pladur = new JPanel();
        pladur.setLayout(null);
        pladur.setBounds(75, 260, 490, 365);
        pladur.setVisible(true);
        pladur.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(pladur);

        panelProductos = new JPanel();
        panelProductos.setLayout(null);
        panelProductos.setBounds(75, 0, 320, 365);
        panelProductos.setVisible(true);
        panelProductos.setBackground(Color.GRAY);

        pladur.add(panelProductos);
    }

    /**
     * Muestra los productos de la primera familia en la interfaz gráfica.
     */
    private void mostrarProductosPrimeraFamilia() {
        if (!familias.isEmpty()) {
            String primeraFamilia = familias.get(0);
            if (productosHM.containsKey(primeraFamilia)) {
                productosHM.get(primeraFamilia).muestraPaginaProductos();
            }
        }
    }
}
