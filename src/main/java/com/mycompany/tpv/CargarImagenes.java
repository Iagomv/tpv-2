package com.mycompany.tpv;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
/**
 *
 * @author Iago
 */
/**
 * Esta clase proporciona métodos para cargar imágenes desde una base de datos.
 */
public class CargarImagenes {

    /**
     * Conecta con la base de datos, consulta y obtiene una imagen asociada a un producto.
     * 
     * @param nombreProducto El nombre del producto del que se quiere cargar la imagen.
     * @return Una imagen cargada desde la base de datos.
     */
    public static BufferedImage cargarImagenDesdeBaseDeDatos(String nombreProducto) {
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        BufferedImage imagen = null;
    
        try {
            // Establece conexión con la base de datos
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/entornos 2ev", "root", "");
    
            // Realiza la consulta para obtener el BLOB de la imagen
            String sql = "SELECT imagen_Producto FROM productos WHERE Nombre_Producto = ?";
            consulta = conexion.prepareStatement(sql);
            consulta.setString(1, nombreProducto);
            resultado = consulta.executeQuery();
    
            // Convierte el BLOB a un BufferedImage
            if (resultado.next()) {
                Blob blob = resultado.getBlob("imagen_Producto");
                if (blob != null) {
                    byte[] datosImagen = blob.getBytes(1, (int) blob.length());
                    BufferedImage imagenOriginal = ImageIO.read(new ByteArrayInputStream(datosImagen));
                    if (imagenOriginal != null) {
                        //System.out.println("Imagen cargada correctamente desde la base de datos.");
                        imagen = redimensionarImagen(imagenOriginal);
                    } else {
                        System.out.println("Error: La imagen original es nula.");
                    }
                } else {
                    System.out.println("Error: El BLOB de la imagen es nulo.");
                }
            } else {
                System.out.println("Error: No se encontró ninguna imagen para el producto " + nombreProducto);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            // Cierra los recursos
            try {
                if (resultado != null) resultado.close();
                if (consulta != null) consulta.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        return imagen;
    }
    
    
    /**
     * Redimensiona una imagen a un tamaño objetivo de 100x100 píxeles.
     * 
     * @param imagenOriginal La imagen original que se quiere redimensionar.
     * @return La imagen redimensionada.
     */
    private static BufferedImage redimensionarImagen(BufferedImage imagenOriginal) {
        int anchoObjetivo = 100;
        int altoObjetivo = 100;
        BufferedImage imagenRedimensionada = new BufferedImage(anchoObjetivo, altoObjetivo, BufferedImage.TYPE_INT_ARGB);
        imagenRedimensionada.createGraphics().drawImage(imagenOriginal.getScaledInstance(anchoObjetivo, altoObjetivo, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
        return imagenRedimensionada;
    }
}
