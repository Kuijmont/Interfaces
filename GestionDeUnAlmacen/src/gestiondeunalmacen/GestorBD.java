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
import javax.swing.JOptionPane;


/**
 *
 * @author Frans
 */
public class GestorBD {
    static Connection cn;
    private Statement st;
    private ResultSet result = null;
 
    public void ConectarGestorBD() {
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            //cn = DriverManager.getConnection("jdbc:mysql://localhost/interfaces","root","manager");
            cn = DriverManager.getConnection("jdbc:mysql://localhost/interfaces","root","");
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

    public void darAlta(String cod, String nif, String nombre, String apell, String dom, String cp, 
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
    
    public void modificar(String cod, String nif, String nombre, String apell, String dom, String cp, 
            String loc, String telf, String movil, String fax, String email, String total) throws SQLException { 
        
        st = cn.createStatement();
        String sql = "update clientes set Código='"+cod+"', nif='"+nif+"', nombre='"+nombre+"', apellido='"+apell+"', domicilio='"+dom+"', cp='"+cp+"', localidad='"+loc+"', telf='"+telf+"', movil='"+movil+"', fax='"+fax+"', email='"+email+"', total='"+total+"'";
        try {
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Cliente modificado correctamente.","Mensaje", 1);                        
            st.close(); // Cierra el statement
        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,"Error al modificar el cliente.","Error", 0);
        }

    }
    
}
