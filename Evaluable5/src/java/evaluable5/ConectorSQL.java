/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluable5;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Frans
 */
    public class ConectorSQL {
    private static Connection con;
    public static Statement st; 
    boolean buscarCliente;
    private static ArrayList<String> articles;
    
    // This arraylist will serve to verify that there are no duplicate codes
    private static ArrayList<String> codes;
    
    public ConectorSQL() throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");     
    }
    
    public void conectBd() throws SQLException{
        con=DriverManager.getConnection("jdbc:mysql://localhost/interfaces", "root", "manager");
        //con.setAutoCommit(false); // Every time we do an operation, we need to make a commit () to save.
    }
    
    // create table Proveedores(Codigo char(6), nif char(9), nombre varchar(15), apellido varchar(35), domicilio varchar(40), Código_Postal char (5), Localidad varchar(20), teléfono char (9), móvil char (9), fax char (9), e_mail varchar(20), total_ventas float(7,2), CONSTRAINT PK_cod primary key(Codigo));
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
        st=con.createStatement();
        //ExecuteUpdate es para sentencias de tipo create, insert, delete o update.
        st.executeUpdate(sentencia);
         con.commit();
    }
    
    public void insertarVenta(String codigo, String nif, String apellidos, String nombre, String domicilio, String cp, String localidad, String telefono, String movil, String fax, String email, String ventas ) throws SQLException{
        String sentencia= "insert into clientes()"
                + "values('"+codigo+"','"+nif+"','"+apellidos+"','"+nombre+"','"+domicilio+"','"+cp+"','"+localidad+"','"+telefono+"','"+movil+"','"+fax+"','"+email+"','"+ventas+"')";
        st=con.createStatement();
        st.executeUpdate(sentencia);
         con.commit();
    }
  
    public void modCliente(String codigo, String nif, String apellidos, String nombre, String domicilio, String cp, String localidad, String telefono, String movil, String fax, String email, String ventas ) throws SQLException{
     String sentencia="update clientes set nif='"+nif+"',apellidos='"+apellidos+"',nombre='"+nombre+"',"+"domicilio='"+domicilio+"',Código_Postal='"+cp+"',localidad='"+localidad+"',teléfono='"+telefono+"',móvil='"+movil+"',fax='"+fax+"',e_mail='"+email+"',total_ventas='"+ventas+"' where Código='"+codigo+"'";
     st=con.createStatement();      
     st.executeUpdate(sentencia);
           con.commit();
    }
    
    public void borrarCliente(String codigo) throws SQLException{
        String sentencia ="delete from clientes where Código = "+codigo+";";
        st=con.createStatement();
        st.executeUpdate(sentencia);
         con.commit();
    }
    
    public boolean buscarCliente(String codigo) throws SQLException{
           String sentencia ="select * from clientes where Código = '"+codigo+"';";
       st=con.createStatement();
           st.executeQuery(sentencia);
        return st.getResultSet()==null;
       
      
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
        st=con.createStatement();
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

   // To check that codes there are not duplicates
    public boolean checkCode(String codigo) {
        try {
            codes=listCodes();
            for(int i=0; i<codes.size();i++){
                if(codigo.matches(codes.get(i))){
                    return true;
                }
            }
            codes.clear();
        } catch (SQLException ex) {
            //Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // To list all codes
    private static ArrayList<String> listCodes() throws SQLException {
        ArrayList<String> cods=new ArrayList<String>();
        // We created the Statement to be able to make queries
        st = con.createStatement();

        // La consulta es un String con código SQL
        String sql1 = "SELECT Código FROM clientes";

        // Execute a query that returns results             
        ResultSet rs = st.executeQuery(sql1);  
        while (rs.next()){
            cods.add(rs.getString(1));
        }
        return cods;
    }
    
    // To show client data
    public static Client showClient(String codigo) throws SQLException{
        // We created the Statement to be able to make queries
        Statement st = con.createStatement();

        // The query is a String with SQL code
        String sql1 = "SELECT * FROM clientes WHERE Código='"+codigo+"'";

        // Execute a query that returns results               
        ResultSet rs = st.executeQuery(sql1);
        Client cliente=null;
        while (rs.next()){
            cliente=new Client(rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
                        rs.getString(9),rs.getString(10),rs.getString(11),rs.getFloat(12));
        }
        return cliente;
    }
    
    // Rollback
    public void rollback() throws SQLException {
        con.rollback();
    }
    
    // Commit
    public void commit() throws SQLException {
        con.commit();
    }
    
    // To list all articles
   /* public ArrayList<Article> listadoArticulos(boolean cli,boolean prov) throws SQLException {
        ArrayList<Article> al = new ArrayList<>();
        String sql = "SELECT * FROM articulos";
        st=con.createStatement();
        try (ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                if (cli) {
                    al.add(new Article(rs.getString("Codigo"), rs.getString("Descripcion"), rs.getInt("Stock"), rs.getDouble("Precio_de_venta")));
                } else {
                    al.add(new Article(rs.getString("Codigo"), rs.getString("Descripcion"), rs.getInt("Stock"), rs.getDouble("Precio_de_compra")));
                }
                
            }
        }
        return al;
    }*/
    
    // This method is used to choose clients, suppliers and articles from a sentence that is passed
    public ResultSet select(String sql) throws SQLException {
       st = con.createStatement();
       ResultSet rs = st.executeQuery(sql);
       return rs;
    }

    void executeUpdate(String sql) throws SQLException {
       Statement st = con.createStatement();
       st.executeUpdate(sql);
    }

    void grabarHistorica(String codCli,String codPed,String codArt,String Unid,Date fecha) {
       
            PreparedStatement ps = null;
            String sqlNuevaCuenta = "INSERT INTO historica VALUES (?,?,?,?,?)";
           
            try {
                ps = con.prepareStatement(sqlNuevaCuenta);
                
            } catch (SQLException ex) {
                Logger.getLogger(ConectorSQL.class.getName()).log(Level.SEVERE, null, ex);
            }    
        try {
                ps.setString(1, codCli);
                ps.setString(2, codPed);
                ps.setString(3, codArt);
                ps.setString(4, Unid);
                ps.setDate(5,fecha);
                ps.executeUpdate();
            
         } catch (SQLException ex) {
            Logger.getLogger(ConectorSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
 }

    // Load an Item
    public Article loadItem(String code){
        Article a = null;
        String sql="SELECT * FROM articulos WHERE Codigo='"+code+"'";
        try {
            ResultSet rs=select(sql);
            while (rs.next()) {
                a = new Article(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(6));
            }           
            rs.close();
            return a;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }
   
    // Check Item
      public static boolean checkItem(String code) {
        try {
            articles=listArticles();
            for(int i=0; i<articles.size();i++){
                if(code.matches(articles.get(i))){
                    return true;
                }
            }
            articles.clear();
        } catch (SQLException ex) {
            //Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
      
    // List all Articles
    private static ArrayList<String> listArticles() throws SQLException {
        ArrayList<String> cods=new ArrayList<String>();
        // Creamos el Statement para poder hacer consultas
        st = con.createStatement();

        // La consulta es un String con código SQL
        String sql1 = "SELECT Codigo FROM articulos";

        // Ejecuta una consulta que devuelve resultados                
        ResultSet rs = st.executeQuery(sql1);  
        while (rs.next()){
            cods.add(rs.getString(1));
        }
        return cods;
    }
    
    // To show an Article
    public static Article showArticle(String code) throws SQLException{
        // Creamos el Statement para poder hacer consultas
        st = con.createStatement();

        // La consulta es un String con código SQL
        String sql1 = "SELECT * FROM articulos WHERE Codigo='"+code+"'";

        // Ejecuta una consulta que devuelve resultados                
        ResultSet rs = st.executeQuery(sql1);
        Article article=null;
        while (rs.next()){
            article=new Article(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(6));
        }
        return article;
    }
    
    // Save in Table PedidosInternet 
    public void GrabarPedidosInternet(Vector<PedidosInternet> v) throws SQLException{
        for (int i = 0; i < v.size(); i++) {
            String sql= "insert into pedidosInternet values('"+v.get(i).getCliente()+"', '"+v.get(i).getArticulo()+"',"+v.get(i).getUnidades()+", '"+v.get(i).getFecha()+"')";
            st=con.createStatement();
            st.executeUpdate(sql);
            
        }
    }
    
    public void GrabarTransacciones() throws SQLException{
        con.commit();
    }
    
    public Vector getExtractosPanImp(String code, String fecha1, String fecha2) throws SQLException{
        String sql = "Select * from pedidosInternet where Cliente='"+code+"' and Fecha >= '"+fecha1+"' and Fecha <= '"+fecha2+"'";
        Vector v = new Vector();
        Statement st=con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
           PedidosInternet p=new PedidosInternet(rs.getString(1),rs.getString(2),rs.getFloat(3),rs.getString(4));
            v.add(p);
        }
        rs.close();
        st.close();
        return v; 
    }
    
   }

