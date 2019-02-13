<%-- 
    Document   : Factura
    Created on : 07-feb-2019, 12:08:45
    Author     : Frans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 
<
<
<                       F A C T U R A . 
J S P
<
<
-->
<!-- Esta página graba en la base de datos el pedido realizado por el cliente,
        y muestra la factura de dicho pedido. Si el cliente desea imprimir esta
        factura, se accede a la página ImprimirPedido.jsp. -->
<%@page contentType="text/html"%>
<%@page import="evaluable5.*"%>
<%@page import="java.util.Vector"%>
<%@page import="java.io.*"%>
<html>
    <head><title>Gesti&oacute;n de pedidos</title></head>
<body>
        <h2>Pedido finalizado</h2>
        Si desea imprimir el pedido se abrir&aacute; una nueva ventana con la 
        factura a imprimir.
        <br>
        En esta ventana, abra el men&uacute; Archivo y ejecute la opci&oacute;n
        Imprimir.
        <br>
        En la nueva ventana, seleccione su impresora y pulse Imprimir.
        <br>
        Despu&eacute;s, cierre la ventana que contiene la factura a imprimir.
        <br>
        <!-- El atributo target="_blank" de la etiqueta
            <a href="ImprimirPedido.jsp", se utiliza para que la página
            que se va cargar cuando se pulse sobre el enlace, se abra
            en una nueva ventana. -->
        Para imprimir el pedido pulse 
        <a href="ImprimirPedido.jsp" target="_blank">aqu&iacute;.</a>
         Si no va a imprimir, puede regresar a la 
<a href="index.jsp">p&aacute;gina principal.</a>
<br><br>
        <!-- Grabo los pedidos e imprimo la factura -->
<%
            HttpSession sesion=request.getSession();            
Vector vPedidos=(Vector)sesion.getAttribute("Pedidos");
            Client c=(Client) sesion.getAttribute("Cliente");
            Pedidos pedido=new Pedidos();
            PedidosInternet pedidoInternet=new PedidosInternet();
            Vector vPedidosInternet=new Vector();
            ConectorSQL g=new ConectorSQL();
            /* La siguiente instrucción coge la fecha del sistema. Indico el 
50
                paquete de la clase Date() que utilizo para que no se confunda 
                entre la clase Date() del paquete java.sql y la clase Date() 
                del paquete java.util. */
java.sql.Date fechaSistema=new java.sql.Date
                                        ((new java.util.Date()).getTime());
/* Convierto la fecha del sistema a String para guardarlo en la 
                tabla PedidosInternet, donde he definido el campo Fecha de tipo 
Text. */
            String fecha=String.valueOf(fechaSistema);
/* Le doy la vuelta a la fecha para imprimirla en el listado de la 
                factura. */
            String fecha1=fecha.substring(8,10);
            fecha1 = fecha1 + fecha.substring(4,7);
            fecha1 = fecha1 + "-" + fecha.substring(0,4);
            
            /* Abro el fichero NumFactura.dat para obtener el nï¿½mero de factura 
a imprimir. */
            FileInputStream NumFactura=null;
            FileOutputStream NumFactura2=null;
            try {
                NumFactura=new FileInputStream("NumFactura.dat");
            }catch (FileNotFoundException e2) {
                /*System.out.println(e2.getMessage());
System.out.println("El archivo no existe. Se crea"); */
                /* Si el fichero no existe, se crea y, en el siguiente bloque 
                    try ... catch, se inicializa con el valor 0 y se abre en 
                    modo lectura. Este bloque solo se ejecutará la primera vez 
                    que se imprima una factura, pues en este caso el fichero 
                    NumFactura.dat no existirï¿½. Las siguientes veces que se 
                    imprima una factura el fichero existirá y no se producirá 
                    esta excepción. 
*/
                File NumFactura1=new File("NumFactura.dat");
                try {
                  NumFactura2=new FileOutputStream("NumFactura.dat");
                  NumFactura2.write(0);
                  NumFactura2.close();
                  NumFactura=new FileInputStream("NumFactura.dat");
                }catch (Exception e3) {
                  /*System.out.println(e3.getMessage());
System.out.println("Error al abrir el archivo de salida");*/
                }
            }
            int numFactura=0;
            // Leo el fichero para obtener el nï¿½mero de factura a imprimir.
try {
                numFactura=NumFactura.read();
                NumFactura.close();
            }catch (Exception e4) {
                /*System.out.println(e4.getMessage());
                System.out.println("Error al leer o cerrar el archivo");*/

            }
            numFactura+=1;
            // Grabo el ï¿½ltimo nï¿½mero de factura que se ha imprimido.
try {
            NumFactura2=new FileOutputStream("NumFactura.dat");
            NumFactura2.write(numFactura);
            NumFactura2.close();
            }catch (Exception e5) {
                /*System.out.println(e5.getMessage());
System.out.println
                    ("Error al abrir, escribir o cerrar el archivo");*/
            }
            /* Guardo el atributo numFactura para recuperarlo al imprimir la
                factura en la página ImprimirPedido.jsp. */
            sesion.setAttribute("numFactura", numFactura);
int i=0;
            int numLinea=571;
            float total=0;
            float importe=0;
            while (i<vPedidos.size()) {
/* Recupero los pedidos del vector del atributo Pedidos del 
                    objeto Session. */
                pedido=(Pedidos)vPedidos.elementAt(i);
                /* Creo un objeto de tipo PedidosInternet con los valores del 
                    pedido que tengo que grabar en la tabla. */
                pedidoInternet=new PedidosInternet();
                pedidoInternet.setArticulo(pedido.getArticulo());
                pedidoInternet.setCliente(c.getCodigo());
                pedidoInternet.setUnidades(pedido.getUnidades());
                    pedidoInternet.setFecha(fecha1);
                /* Guardo el objeto de tipo PedidosInternet en el vector que se 
                    pasará como parámetro al método de la clase GestorBD que 
                    graba en la tabla PedidosInternet de la base de datos. */
                vPedidosInternet.addElement(pedidoInternet);
                // Imprimo las cabeceras.
                if (numLinea>570) {
                    /* La página se escribe en el pie de página. Por lo tanto,
                        si es la primera página, el número de página no se
                        escribe. */
                    numLinea=30;
        %>
                    <hr>
                    Fecha:  <%= fecha1 %>

                    <br>
                    FACTURA N.: <%= numFactura %>
<br>
                    <hr>
                    <center>
<h2>Empresa Proyecto Web de clase, S.A.</h2>
<h3>N.I.F.: 28938475-J</h3
<h4>C/ Isla de S&aacute;lvora, 451 - 
                            28400 Collado Villalba - Madrid
                        </h4>
                    </center>
                    <hr>
                    <b>Cliente: </b><%= c.getCodigo() %> 
                    <b>   N.I.F.: </b><%= c.getNif() %>
<br>
                    <b>D./D&ntilde;a. </b>
<%= c.getNombre() %> <%= c.getApellido() %>
                    <br>
                    <%= c.getDireccion()%>
                    <br>
                    <%= c.getCp() %> <%= c.getLocalidad() %>
<hr>
                    <table align=center>
                        <tr>
                            <td><h3>C&oacute;digo</h3></td>
                            <td><h3>Descripci&oacute;n</h3></td>
                            <td><h3>Precio</h3></td>
<td><h3>Unidades</h3></td>
<td><h3>Importe</h3></td>
                        </tr>
<%                        
                }
                // Imprimo la factura con los pedidos realizados por el cliente.
        %>
                    <tr>
                        <td><%= pedido.getArticulo() %></td>
                        <td><%= pedido.getDescripcion() %></td>
<td align=right>
                            <%= String.valueOf(pedido.getPrecio()) %>
                        </td>
                        <td align=right>
<%= String.valueOf(pedido.getUnidades()) %>
</td>
                        <td align=right>
                            <%= String.valueOf(pedido.getImporte()) %>
                        </td>
</tr>
        <%
                total = total + pedido.getImporte();
                i++;
            }
        %>
        </table>
        <hr>
        <h2 align=right>
            Total Factura (I.V.A. inc.): <%= String.valueOf(total) %>
</h2>
        <hr>
    <%            
        /* Grabo los pedidos de internet en la tabla PedidosInternet y ejecuto 
            commit(). */
        g.GrabarPedidosInternet(vPedidosInternet);
        g.GrabarTransacciones();
    %>
        <%-- <jsp:useBean id="beanInstanceName" scope="session" 
                class="beanPackage.BeanClassName" /> --%>
        <%-- <jsp:getProperty name="beanInstanceName"  
                property="propertyName" /> --%>
    </body>

</html>
