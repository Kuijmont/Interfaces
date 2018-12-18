/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.*;
/**
 *
 * @author coralio
 */
public class Informe extends javax.swing.JFrame {
    private Connection con;
    /** Creates new form formInforme */
    public Informe() {
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
    }catch (Exception e) {
      System.out.println("Error en Driver ODBC. Report2");
      System.out.println(e.getMessage());
      return;
    }
    try {
      con=DriverManager.getConnection("jdbc:mysql://localhost/interfaces","root","manager");
    }catch (SQLException e) {
      System.out.println("Error en la conexión");
            System.out.println(e.getMessage());
      return;
    }
}
public JasperViewer ejecutarInforme(String cadena1, String cadena2) {
    /* Se crea el objeto JasperViewer que devolverá el método.
     * Este objeto contendrá la ventana de la vista previa del informe. */
    
    JasperViewer vistaInforme=null;
    try {
        /* Creamos una cadena que contendrá la ruta completa donde está
         * almacenado el archivo report1.jasper. */
        String archivoJasper = System.getProperty("user.dir") + ("/dist/report2.jasper");
            if (archivoJasper == null)
            {
                System.out.println("El archivo report2.jrxml no está en /dist.");
            }
            // Se crea un objeto para cargar el informe.
            JasperReport informeCargado = null;
            try
            {
                informeCargado = (JasperReport) JRLoader.loadObjectFromFile(archivoJasper);
            }
            catch (Exception e)
            {
                System.out.println("Error al cargar el informe: " + e.getMessage());
            }
            /* El parámetro del informe toma valor del parámetro que recibe este
             * método, que es el contenido de la caja de texto en la que se introduce
             * el total. */
            Map parametro = new HashMap();
            parametro.put("c1",cadena1);
            parametro.put("c2",cadena2);
           
            /* Se crea un objeto de tipo JasperPrint que contendrá el informe
             * cargado previamente con el filtrado del parámetro definido y con
             * la conexión a la base de datos. */
            JasperPrint informe = JasperFillManager.fillReport(informeCargado,parametro,con);
           /* Se asigna valor al objeto JasperViewer que devuelve este método con
            * el informe almacenado previamente en el objeto JasperPrint. El valor
            * false del constructor indica que, al cerrar la vista previa, la
            * aplicación desde la que se ha llamado a esta vista previa continuará
            * ejecutándose. */
            vistaInforme = new JasperViewer(informe,false);
            vistaInforme.setTitle("Ejemplo de iReport");
            vistaInforme.show();
        }       
        catch (Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
        return vistaInforme;
}                 
}
