<%-- 
    Document   : ImprimirExtracto
    Created on : 12-feb-2019, 9:52:04
    Author     : alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 
<
<
<               I M P R I M R E X T R A C T O . J S P
<
<
-->
<%@page contentType="text/html"%>
<%@page import="evaluable5.*"%>
<%@page import="java.util.Vector"%>
<html>
    <head><title>Gesti&oacute;n de extractos</title></head>
<body>
        <%
            /* Tomo los datos del cliente del atributo Cliente del objeto
HttpSession. */
            HttpSession s=request.getSession();
Client c=(Client) s.getAttribute("Cliente");
            // Tomo los datos de los pedidos para visualizar en el extracto.
            Vector ext=(Vector) s.getAttribute("Extractos");
            /* La siguiente instrucción coge la fecha del sistema. Indico el 
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
            String fechaExtracto=fecha.substring(8,10);
            fechaExtracto = fechaExtracto + fecha.substring(4,7);
            fechaExtracto = fechaExtracto + "-" + fecha.substring(0,4);
        %>
        Fecha:  <%= fechaExtracto %>
        <hr>
        <center>
            <h2>Empresa Proyecto Web de clase, S.A.</h2>
<h3>N.I.F.: 28938475-J</h3
<h4>C/ Isla de S&aacute;lvora, 451 - 

                28400 Collado Villalba - Madrid
            </h4>
</center>
        <hr>
        <br>
<b>Cliente: </b><%= c.getCodigo() %> 
        <b>   N.I.F.: </b><%= c.getNif() %>
<br>
        <b>D./D&ntilde;a. </b>
<%= c.getNombre() %> <%= c.getApellido() %>
        <br>
        <%= c.getDireccion()%>
        <br>
        <%= c.getCp() %> <%= c.getLocalidad() %>
<br><br>
        <hr>
<center>
            <h2>Extracto de pedidos
                desde <%= request.getParameter("fecha1") %> 
                hasta <%= request.getParameter("fecha2") %>
</h2>
        </center>
        <table align=center>
<tr>
                <td><h3>Fecha</h3></td>
                <td><h3>Unidades</h3></td>
<td><h3>Art&iacute;culo</h3></td>
            </tr>
        <br><br>
        <%
            int i=0;
            fecha="";
            String articulo="";
            String unidades="";
            while (i<ext.size()) {
                fecha=(String)ext.elementAt(i);
                articulo=(String)ext.elementAt(i+1);
                unidades=(String)ext.elementAt(i+2);
%>
                 <tr>
                    <td><%= fecha %></td>
                    <td>&nbsp;&nbsp;&nbsp;<%= articulo %></td>
<td align="right">&nbsp;&nbsp;&nbsp;<%= unidades %></td>
                </tr>
<%  
                i=i+3;
            }
        %>
        </table>
        <br>
        <hr>
        <br><br>
        <%-- <jsp:useBean id="beanInstanceName" scope="session" 

                class="beanPackage.BeanClassName" /> --%>
        <%-- <jsp:getProperty name="beanInstanceName"  
                property="propertyName" /> --%>
    </body>

</html>
