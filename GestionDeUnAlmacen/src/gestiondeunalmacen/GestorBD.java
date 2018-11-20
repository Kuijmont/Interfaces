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

            cn = DriverManager.getConnection("jdbc:mysql://localhost/interfaces","root","manager");
                
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
                    if(s.equals(cod)){
                         JOptionPane.showMessageDialog(null, "El código introducido ya existe.", "Código incorrecto", 0);
                        return false;
                    } else
                        return true;
                }
                return true;
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        } 
         
        return false;
    }              
}
