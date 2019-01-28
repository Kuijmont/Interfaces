/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiónpedidos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author Frans
 */

public class GestorBD {

    // Creation of variables
    private Connection con;
    public Statement st; 
    
    // Connection to the database
    public void conectarDB() throws Exception{
        // Driver mysql jdbc
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        // Connection to the database 'interfaces' user 'root' password 'manager'
        con=DriverManager.getConnection("jdbc:mysql://localhost/interfaces","root","manager");
        con.setAutoCommit(true); //Cada vez que hagamos una operación, necesitmaos hacer un commit() para guardar.
        st=con.createStatement();
    }
    
    // Create table 'clientes'
    public void crearTablaClientes() throws SQLException{
        String sentencia = "create table clientes(\n"
                        + "Código char(6),"
                        + "nif char(9),"
                        + "apellidos varchar(35),"
                        + "nombre varchar(15),"
                        + "domicilio varchar(40),"
                        + "Código_Postal char(5),"
                        + "localidad varchar(20),"
                        + "teléfono char(9),"
                        + "móvil char(9),"
                        + "fax char(9),"
                        + "e_mail varchar(20),"
                        + "total_ventas float(7,2),"
                        + "CONSTRAINT PK_cod primary key(Código));";
        //ExecuteUpdate es para sentencias de tipo create, insert, delete o update.
        st.executeUpdate(sentencia);
    }
    
    // Create table 'proveedores'
    public void crearTablaProv() throws SQLException{
        String sentencia = "create table proveedores(\n"
                        + "Código char(6),"
                        + "nif char(9),"
                        + "apellidos varchar(35),"
                        + "nombre varchar(15),"
                        + "domicilio varchar(40),"
                        + "Código_Postal char(5),"
                        + "localidad varchar(20),"
                        + "teléfono char(9),"
                        + "móvil char(9),"
                        + "fax char(9),"
                        + "e_mail varchar(20),"
                        + "total_ventas float(7,2),"
                        + "CONSTRAINT PK_cod primary key(Código));";
        //ExecuteUpdate es para sentencias de tipo create, insert, delete o update.
        st.executeUpdate(sentencia);
        // Create Table since mysql server
        /*
         * create table proveedores(Código varchar(6), NIF varchar(9), Apellidos varchar(35),
         * Domicilio varchar(40), Código_Postal varchar(5), Localidad varchar(20), Teléfono varchar(9),
         * Móvil varchar(9), Fax varchar (9), E_Mail varchar(20), Total_Ventas float(7,2),
         * CONSTRAINT PK_cod primary key(Código));
         */
    }
    
    // Query on Table 'clientes'
    public String[] consultaCli(String codigo) throws SQLException{
        // Judgment for mysql
        String sql = "select * from clientes where Código="+codigo;
        // Declare variables according to the columns in the table
        String nif = null;
        String ape = null;
        String nom = null;
        String dom = null;
        String cp = null;
        String loc = null;
        String tel = null;
        String movil = null;
        String fax = null;
        String email = null;
        String total = null;
        // Execution of the query
        ResultSet rs = st.executeQuery(sql);
        // Go through all the columns and add to the respective variables
        while (rs.next()) {
            nif = rs.getString("nif");
            ape = rs.getString("apellidos");
            nom = rs.getString("nombre");
            dom = rs.getString("domicilio");
            cp = rs.getString("Código_Postal");
            loc = rs.getString("localidad");
            tel = rs.getString("teléfono");
            movil = rs.getString("móvil");
            fax = rs.getString("fax");
            email = rs.getString("e_mail");
            total = String.valueOf(rs.getFloat("total_ventas"));
        }
        // Creation of array 'datos' to put all the previous variables
        String [] datos = {nif,ape,nom,dom,cp,loc,tel,movil,fax,email,total};
        // Return array 'datos'
        return datos;
    }
    
    // Query on Table 'proveedores'
    public String[] consultaProv(String codigo) throws SQLException{
        // Judgment for mysql
        String sql = "select * from proveedores where Código="+codigo;
        // Declare variables according to the columns in the table
        String nif = null;
        String ape = null;
        String nom = null;
        String dom = null;
        String cp = null;
        String loc = null;
        String tel = null;
        String movil = null;
        String fax = null;
        String email = null;
        String total = null;
        // Execution of the query
        ResultSet rs = st.executeQuery(sql);
        // Go through all the columns and add to the respective variables
        while (rs.next()) {
            nif = rs.getString("nif");
            ape = rs.getString("apellidos");
            nom = rs.getString("nombre");
            dom = rs.getString("domicilio");
            cp = rs.getString("Código_Postal");
            loc = rs.getString("localidad");
            tel = rs.getString("teléfono");
            movil = rs.getString("móvil");
            fax = rs.getString("fax");
            email = rs.getString("e_mail");
            total = String.valueOf(rs.getFloat("total_ventas"));
        }
        // Creation of array 'datos' to put all the previous variables
        String [] datos = {nif,ape,nom,dom,cp,loc,tel,movil,fax,email,total};
        // Return array 'datos'
        return datos;
    }
    
    // Get all articles
    public static Vector getTodosLosArticulos() {
        Vector v = new Vector();
        
        return v;
    }
    
    public String[] sacarDatos(String codigo, String table) throws SQLException{
        String all = "select * from "+table+" where Código="+codigo;
        String nif = null;
        String   ape = null;
        String nom = null;
        String dom = null;
        String  cp = null;
        String  loc = null;
        String  tel = null;
        String  movil = null;
        String  fax = null;
        String  email = null;
        ResultSet listaMovimientos = st.executeQuery(all);
        while (listaMovimientos.next()) {
        nif = listaMovimientos.getString("nif");
        ape = listaMovimientos.getString("apellidos");
        nom = listaMovimientos.getString("nombre");
        dom = listaMovimientos.getString("domicilio");
        cp = listaMovimientos.getString("Código_Postal");
        loc = listaMovimientos.getString("localidad");
        tel = listaMovimientos.getString("teléfono");
        movil = listaMovimientos.getString("móvil");
        fax = listaMovimientos.getString("fax");
        email = listaMovimientos.getString("e_mail");
        }
        nif = nif.substring(0, 8);
        String [] datos = {nif,ape,nom,dom,cp,loc,tel,movil,fax,email};
        return datos;
    
    }
 
}
