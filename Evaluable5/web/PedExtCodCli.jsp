<%-- 
    Document   : GestionPedidos
    Created on : 29-ene-2019, 9:44:58
    Author     : alumno
--%>

<!-- 
<
<
<                   P E D E X T C O D C L I . J S P
<
<
-->
<!-- Página que solicita el código del cliente que va a realizar el pedido
    o el extracto. -->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="evaluable5.*"%>
<%@page import="java.util.Vector"%>
<html>
<%
    /* Se crea un objeto HttpSession con los valores asignados en la sesión actual. */
    HttpSession sesion=request.getSession();
    /* Creo un atributo, llamado Clientes, en el objeto HttpSession, que
       contendrá un objeto de tipo CliPro. En este atributo se guardará
       el cliente que está realizando el pedido o el extracto. */
    sesion.setAttribute("Cliente",new Client());
    /* Borro el atributo Pedidos del objeto HttpSession con los que pedidos
       que se hubieran podido realizar por el cliente anterior.
       Esta opción solo es necesaria para cuando se acceda a esta página
       desde las páginas posteriores. */
    sesion.setAttribute("Pedidos", new Vector());        
    /* Las siguientes líneas crean la variable url, que contendrá la url
       que se cargará cuando el usuario haga clic en el botón submit del
       formulario. El contenido de esta variable será distinto, dependiendo
       de la opción que haya elegido el cliente (pedido o extracto).
       También se crea la variable mensaje, para escribir en esta página
       la opción que el cliente ha elegido (pedido o extracto). */
    String mensaje="Gesti&oacute;n de ";
    String url="";
    if (request.getParameter("opcion").compareTo("p")==0) {
        mensaje=mensaje+"pedidos";
        url="PedidosBuscarCliente.jsp?opcion=p";
    } else {
        mensaje=mensaje+"extractos";
        url="ExtractosBuscarCliente.jsp?opcion=e";
    }
%>
    <head><title><%= mensaje %></title></head>
    <body onload="document.formPedidos.txtCodigo.focus()">
        <h1><%= mensaje %></h1>
        <br>
        <form name="formPedidos" action="<%= url %>" method="post" onsubmit="return TodoCorrecto_submit()">
            C&oacute;digo de cliente
            <input type="text" name="txtCodigo" size="4" maxlength="6">
            <br><br>
            <input type="submit" value="Aceptar">
            <input type="reset" value="Cancelar" 
            onclick="document.formPedidos.txtCodigo.focus()">
        </form>
        <a href="index.jsp">P&aacute;gina principal</a>
        <%-- <jsp:useBean id="beanInstanceName" scope="session" 
                class="beanPackage.BeanClassName" /> --%>
        <%-- <jsp:getProperty name="beanInstanceName"  
                property="propertyName" /> --%>
        <script language="JavaScript" type="text/javascript">
            function TodoCorrecto_submit() {
                /* Si el usuario pulsa el botón submit sin introducir ningún 
                    dato, no se envía nada. */
                with (document.formPedidos.txtCodigo) {               
                    if (value=="") {
                        alert("No ha introducido el código").focus();
                        return false;
                    }
                    else {
                        for (i=1; value.length<6; i++) {
                            value="0"+value;
                        }
                        return true;
                    }
                }
            }
        </script>
    </body>

</html>
