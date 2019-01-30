<%-- 
    Document   : index
    Created on : 24-ene-2019, 13:54:56
    Author     : Frans
--%>

<!-- 
<
<
<                          I N D E X . J S P
<
<
-->
<!-- Página principal del proyecto. -->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="evaluable5.*"%>
<%@page import="java.util.Vector"%>
<% 
    /* Creo el objeto HttpSession, por eso el método getSession recibe el valor true */
    HttpSession sesion=request.getSession(true);
    /* Creo el atributo Pedidos en el objeto HttpSession. Este atributo es un
        vector que contendrá todos los pedidos realizados por un cliente. */
    sesion.setAttribute("Pedidos", new Vector());
    /* La siguiente instrucción coge la fecha del sistema. Indico el paquete de
        la clase Date() que utilizo para que no se confunda entre la clase 
        Date() del paquete java.sql y la clase Date() del paquete java.util. */
    java.sql.Date fechaSistema=new java.sql.Date((new java.util.Date()).getTime());
    /* Convierto la fecha del sistema a String para coger el año y guardarlo
        en el atributo anioActual del objeto HttpSession, que contendrá el año
        en el que se está ejecutando el proyecto. Este año se utiliza
        posteriormente en la página ExtractoBuscarCliente.jsp, para asignar
        valores a la lista desplegable que contiene los posibles años que
        puede seleccionar el cliente para realizar el extracto entre fechas. 
    */
    String fecha=String.valueOf(fechaSistema);
    String fecha1 = fecha.substring(0,4);
    int aa1=new Integer(fecha1).intValue();
    sesion.setAttribute("anioActual",aa1);
%>
<html>
    <head><title>Proyecto Web con Bases de Datos</title></head>
    <body>
        <h1>Proyecto Web con Bases de Datos</h1>
        <!-- Cuando se enlaca con la página PedExtCodCli.jsp, se pasa una p o 
            una s en la variable opcion para distinguir si el cliente va a
            realizar un pedido o un extracto. -->
        <a href="PedExtCodCli.jsp?opcion=p">Gesti&oacute;n de pedidos</a>
        <br><br>
        <a href="PedExtCodCli.jsp?opcion=e">Gesti&oacute;n de extractos</a>        
        <br><br>
        <%-- <jsp:useBean id="beanInstanceName" scope="session" 
                class="beanPackage.BeanClassName" /> --%>
        <%-- <jsp:getProperty name="beanInstanceName"  
                property="propertyName" /> --%>
    </body>
</html>
