/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeunalmacen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

// Clases de JasperReports.
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.*;
/**
 *
 * @author Frans
 */
public class GestorBD {
    private Connection cn;
    private Statement st;
    private ResultSet result = null;
 
    public void ConectarGestorBD() {
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            cn = DriverManager.getConnection("jdbc:mysql://localhost/interfaces","root","manager");
            //cn = DriverManager.getConnection("jdbc:mysql://localhost/interfaces","root","");
        } catch (Exception e) {           
            System.out.println("Error en driver");
            System.out.println(e.getMessage());
        } 
    }
    public void close(){
        try {
            cn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public boolean valCod(String cod) throws Exception{
       try {      
           String s = null;
            String sql = "Select * from clientes where Código = '"+cod+"'";
            st=cn.createStatement();
            result = st.executeQuery(sql);
                while(result.next()){
                    s = result.getString("Código");
                    return !s.equals(cod);
                }
                return true;
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        } 
         
        return false;
    }              

    public void darAlta(String cod, String nif,  String apell,String nombre, String dom, String cp, 
            String loc, String telf, String movil, String fax, String email, String total) throws SQLException { 
        
        st = cn.createStatement();
        String sql = "INSERT INTO clientes VALUES('"+cod+"','"+nif+"','"+nombre+"','"+apell+"','"+dom+"','"+cp+"','"+loc+"','"+telf+"','"+movil+"','"+fax+"','"+email+"', '"+total+"')";
        try {
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Persona insertada correctamente.","Mensaje", 1);                        
            st.close(); // Cierra el statement
        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,"Persona insertada incorrectamente.","Error", 0);
        }

    }

    public void darBaja(String cod) throws SQLException{
        st = cn.createStatement();
		String sql = "DELETE FROM clientes where Código='"+cod+"'";
		try {
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(null,"Persona borrada correctamente.","Mensaje", 1);
			
			st.close(); // Cierra el statement
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Código no existe","Mensaje", 0);
		}
    }

    public Cliente consulta(String cod) throws SQLException{
        st = cn.createStatement();
	String sql = "select * FROM clientes where Código='"+cod+"'";
        Cliente client = null;
	try {
            result = st.executeQuery(sql);
            result.next();
            client = new Cliente(result.getString(1), result.getString(2).substring(0, 8),result.getString(2).substring(9), result.getString(3), result.getString(4),
            result.getString(5), result.getString(6), result.getString(7), result.getString(8), result.getString(9), result.getString(10), result.getString(11), result.getString(12));
            result.close();
            st.close(); // Cierra el statement
            return client;
	} catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,"Error al sacar la consulta.","Mensaje", 0);
	}
        return client;
    }
    
    public void modificar(String cod, String nif,String apell, String nombre,  String dom, String cp, 
            String loc, String telf, String movil, String fax, String email) throws SQLException { 
        
        st = cn.createStatement();
        String sql = "update clientes set NIF='"+nif+"', Apellidos='"+apell+"',Nombre='"+nombre+"',  Domicilio='"+dom+"', Código_Postal='"+cp+"', localidad='"+loc+"', Teléfono='"+telf+"', Móvil='"+movil+"', Fax='"+fax+"', E_mail='"+email+"' where Código='"+cod+"'";
        try {
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Cliente modificado correctamente.","Mensaje", 1);                        
            st.close(); // Cierra el statement
        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,e.getMessage(),"Error", 0);
        }

    }
    
    //Crear TABLA
    /*
       create table clientes(Código char(6),nif char(9),apellidos varchar(35),nombre varchar(15),domicilio varchar(40),
       Código_Postal char(5),localidad varchar(20),teléfono char(9),móvil char(9),fax char(9),e_mail varchar(20),total_ventas float(7,2),
       CONSTRAINT PK_cod primary key(codigo));
    */
    
   public JasperViewer ejecutarInforme() {
        /* Se crea el objeto JasperViewer que devolverá el método.
         * Este objeto contendrá la ventana de la vista previa del informe. */
        JasperViewer vistaInforme=null;
        try {
            /* Creamos una cadena que contendrá la ruta completa donde está
             * almacenado el archivo report1.jasper. */
            String archivoJasper = System.getProperty("user.dir") + ("/dist/report1.jasper");
                if (archivoJasper == null)
                {
                    System.out.println("El archivo report1.jasper no está en /dist.");
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

                /* Se crea un objeto de tipo JasperPrint que contendrá el informe
                 * cargado previamente con el filtrado del parámetro definido y con
                 * la conexión a la base de datos. */
                JasperPrint informe = JasperFillManager.fillReport(informeCargado,parametro,cn);
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

   public JasperViewer ejecutarInforme(String cadena1, String cadena2) {
       try {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            cn = DriverManager.getConnection("jdbc:mysql://localhost/interfaces","root","manager");
            //cn = DriverManager.getConnection("jdbc:mysql://localhost/interfaces","root","");
        } catch (Exception e) {           
            System.out.println("Error en driver");
            System.out.println(e.getMessage());
        } 
    /* Se crea el objeto JasperViewer que devolverá el método.
     * Este objeto contendrá la ventana de la vista previa del informe. */
        JasperViewer vistaInforme=null;
        try {
            /* Creamos una cadena que contendrá la ruta completa donde está
             * almacenado el archivo report1.jasper. */
            String archivoJasper = System.getProperty("user.dir") + ("/dist/report2.jasper");
                if (archivoJasper == null)
                {
                    System.out.println("El archivo report2.jasper no está en /dist.");
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
                JasperPrint informe = JasperFillManager.fillReport(informeCargado,parametro,cn);
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
   
   public JasperViewer ejecutarGráfica() {
    /* Se crea el objeto JasperViewer que devolverá el método.
     * Este objeto contendrá la ventana de la vista previa del informe. */
    JasperViewer vistaInforme=null;
    try {
        /* Creamos una cadena que contendrá la ruta completa donde está
         * almacenado el archivo report1.jasper. */
        String archivoJasper = System.getProperty("user.dir") + ("/dist/report4.jasper");
            if (archivoJasper == null)
            {
                System.out.println("El archivo report4.jrxml no está en /dist.");
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
           
            /* Se crea un objeto de tipo JasperPrint que contendrá el informe
             * cargado previamente con el filtrado del parámetro definido y con
             * la conexión a la base de datos. */
            JasperPrint informe = JasperFillManager.fillReport(informeCargado,parametro,cn);
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
