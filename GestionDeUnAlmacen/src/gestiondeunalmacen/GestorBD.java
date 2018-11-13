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

/**
 *
 * @author Frans
 */
public class GestorBD {
    private Connection cn;
    private Statement st;
    
    public GestorBD() {
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            cn = DriverManager.getConnection("jdbc:mysql://localhost/interfaces","root","manager");    
            st = cn.createStatement();
		
        }catch(Exception e){
            System.out.println("Error en driver");
            System.out.println(e.getMessage());
        }
       
    }
}
