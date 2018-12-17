/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alumno
 */
public class ConectorSQL {
    private Connection con;
    public Statement st; 
    boolean buscarCliente;
    
    public void conectarDB() throws Exception{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con=DriverManager.getConnection("jdbc:mysql://localhost/interfaces","root","");
        con.setAutoCommit(true); //Cada vez que hagamos una operación, necesitmaos hacer un commit() para guardar.
        st=con.createStatement();
    }
    
    public void crearTabla() throws SQLException{
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
    
    public void insertarVenta(String codigo, String nif, String apellidos, String nombre, String domicilio, String cp, String localidad, String telefono, String movil, String fax, String email, String ventas ) throws SQLException{
        String sentencia= "insert into clientes()"
                + "values('"+codigo+"','"+nif+"','"+apellidos+"','"+nombre+"','"+domicilio+"','"+cp+"','"+localidad+"','"+telefono+"','"+movil+"','"+fax+"','"+email+"','"+ventas+"')";
        st.executeUpdate(sentencia);
    }
  
    public void modCliente(String codigo, String nif, String apellidos, String nombre, String domicilio, String cp, String localidad, String telefono, String movil, String fax, String email, String ventas ) throws SQLException{
     String sentencia="update clientes set nif='"+nif+"',apellidos='"+apellidos+"',nombre='"+nombre+"',"+"domicilio='"+domicilio+"',Código_Postal='"+cp+"',localidad='"+localidad+"',teléfono='"+telefono+"',móvil='"+movil+"',fax='"+fax+"',e_mail='"+email+"',total_ventas='"+ventas+"' where Código='"+codigo+"'";
           st.executeUpdate(sentencia);
    }
    
    public void borrarCliente(String codigo) throws SQLException{
        String sentencia ="delete from clientes where Código = "+codigo+";";
        st.executeUpdate(sentencia);
    }
    public boolean buscarCliente(String codigo) throws SQLException{
           String sentencia ="select * from clientes where Código = "+codigo+";";
        st.executeQuery(sentencia);
        if ( st.getResultSet()==null) {
            return true;
        }else{
            return false;
        }
       
      
      }
    
    public String[] sacarDatos(String codigo) throws SQLException{
        String all = "select * from clientes where Código="+codigo;
        String ape = "select apellidos from clientes where Código="+codigo;
        String nom = "select nombre from clientes where Código="+codigo;
        String dom = "select domicilio from clientes where Código="+codigo;
        String cp = "select Código_Postal from clientes where Código="+codigo;
        String loc = "select localidad from clientes where Código="+codigo;
        String tel = "select teléfono from clientes where Código="+codigo;
        String movil = "select móvil from clientes where Código="+codigo;
        String fax = "select nif from clientes where Código="+codigo;
        String email = "select e_mail from clientes where Código="+codigo;
        String nif = null;
            ape = null;
        nom = null;
        dom = null;
         cp = null;
        loc = null;
        tel = null;
       movil = null;
        fax = null;
        email = null;
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
