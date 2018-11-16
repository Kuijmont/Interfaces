/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeunalmacen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Frans
 */
public class GestorBD {
    private Connection cn;
    private Statement st;
    
    public void ConectarGestorBD() {
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();

                cn = DriverManager.getConnection("jdbc:mysql://localhost/interfaces","root","manager");
                
        } catch (Exception e) {
            System.out.println("Error en driver");
            System.out.println(e.getMessage());
        } 
    }
    
    public boolean valCod(String cod){
        try {
            st = cn.createStatement();
            String sql = "Select * from clientes where Código = '"+cod+"'";
            st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }
}
