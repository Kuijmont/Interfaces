<%-- 
    Document   : PedidosBuscarArticulo
    Created on : 31-ene-2019, 13:19:56
    Author     : alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 
<
<
<            P E D I D O S B U S C A R A R T I C U L O . 
J S P
<
<
-->
<!-- Página que busca el código de artículo introducido en la página
        PedidosBuscarCliente.jsp. Si el artículo existe, se solicitan las
        unidades. -->
<%@page contentType="text/html"%>
<%@page import="evaluable5.*"%>
<html>
    <head><title>Gesti&oacute;n de pedidos</title></head>
<h1>Gesti&oacute;n de pedidos</h1>
<%
            ConectorSQL g=new ConectorSQL();
            Article a=new Article(request.getParameter("txtArticulo"),"",0,0,0,0);
            a=g.BuscarArticulo(a);
if (a.getCodigo()=="") {%>         
<body>
                <h2>El art&iacute;culo con c&oacute;digo 
                    <%= request.getParameter("txtArticulo")%> no existe
                </h2>
         <%   }else { %>
                <body onload="document.formPedidos.txtUnidades.focus()">
                <!-- Muestro los datos del cliente -->
                <%
                    /* Tomo los datos del cliente del atributo Cliente del
objeto HttpSession. */
                    HttpSession s=request.getSession();
Client c=(Client) s.getAttribute("Cliente");
                %>
                <h2>Datos del cliente</h2>
<table>
                    <tr>
                        <td><b>C&oacute;digo</b></td>
                        <td><b>&nbsp;&nbsp;&nbsp;N.I.F.</b></td>
<td><b>&nbsp;&nbsp;&nbsp;Nombre</b></td>
                        <td><b>&nbsp;&nbsp;&nbsp;Apellidos</b></td>                
43
                    </tr>
                    <tr>
                        <td><%= c.getCodigo() %></td>
                        <td>&nbsp;&nbsp;&nbsp;<%= c.getNif() %></td>
                        <td>&nbsp;&nbsp;&nbsp;<%= c.getNombre() %></td>
                        <td>&nbsp;&nbsp;&nbsp;<%= c.getApellido() %></td>
</tr>
                </table>
                <table>
                    <tr>
                        <td><b>Domicilio</b></td>
                        <td><b>&nbsp;&nbsp;&nbsp;C.P.</b></td>
                        <td><b>&nbsp;&nbsp;&nbsp;Localidad</b></td>
                        <td><b>&nbsp;&nbsp;&nbsp;Total</b></td>
</tr>
                    <tr>
                        <td><%= c.getDireccion()%></td>
                        <td>&nbsp;&nbsp;&nbsp;<%= c.getCp() %></td>
                        <td>&nbsp;&nbsp;&nbsp;<%= c.getLocalidad() %></td>
                        <td>&nbsp;&nbsp;&nbsp;<%= c.getTotal() %></td>
</tr>
                </table>
                <br>
<hr>
                <!-- Muestro los datos del artï¿½culo -->
                <h2>Realizar pedido</h2>
<form name="formPedidos" action="Pedidos.jsp" method="get"
                        onsubmit="return TodoCorrecto_submit()">
                    <table>
                        <tr>
                            <td><b>Art&iacute;culo</b></td>
                            <td><b>&nbsp;&nbsp;&nbsp;Descripci&oacute;n</b></td>
                            <td><b>&nbsp;&nbsp;&nbsp;Unidades</b></td>
<td><b>&nbsp;&nbsp;&nbsp;Precio</b></td>
<td><b>&nbsp;&nbsp;&nbsp;Importe</b></td>
                        </tr>
                        <tr>
                            <td><%= request.getParameter("txtArticulo") %></td>
                            <td>&nbsp;&nbsp;&nbsp;<%= a.getDescripcion() %></td>
                            <td>
                                &nbsp;&nbsp;
                                <input type="text" name="txtUnidades" size="9" 
                                    maxlength="9" 
                                    onkeyup="CalcularPrecio(event)">            
                            </td>
                            <td>&nbsp;&nbsp;&nbsp;<%= a.getPrecioVenta() %></td>
                            <td>
                                &nbsp;&nbsp;
                                <input type="text" name="txtImporte" size="9" 
                                    maxlength="9" readonly>                             
                            </td>
</tr>
                    </table>
                    <!-- Oculto las cajas de texto para pasarlas a la siguiente 
44
página -->
                    <input type="hidden" name="txtArticulo"
                        value="<%= request.getParameter("txtArticulo") %>">                    
                    <input type="hidden" name="txtDescripcion"
                        value="<%= a.getDescripcion() %>">
                    <input type="hidden" name="txtPrecio"
value="<%= a.getPrecioVenta() %>">
                    <!-- Paso la opción que el usuario ha elegido en la página 
                            index.jsp como una caja de texto, porque en la 
                            etiqueta form utilizo el método get para que se 
                            visualicen bien los acentos de los campos de la 
                            tabla en la siguiente página. -->
<input type=hidden name=opcion 
                        value="<%= request.getParameter("opcion") %>">
                    <br><br>
                    <input type="submit" value="Aceptar">
                    <input type="reset" value="Cancelar" 
onclick="document.formPedidos.txtArticulo.focus()">                    
<br><br>
                    <hr>
</form>
         <%   } %>
         <a href="PedidosBuscarCliente.jsp?opcion=<%=request.getParameter
            ("opcion")%>&txtCodigo=<%= request.getParameter("txtCodigo") %>">
            Nuevo art&iacute;culo
</a>
         |
         <a href="index.jsp">P&aacute;gina principal</a>         
        <%-- <jsp:useBean id="beanInstanceName" scope="session" 
                class="beanPackage.BeanClassName" /> --%>
        <%-- <jsp:getProperty name="beanInstanceName"  
                property="propertyName" /> --%>
        <script language="JavaScript" type="text/javascript">
function CalcularPrecio(event) {
                // Compruebo si introduce números.
                with (document.formPedidos.txtUnidades) {
if (event.keyCode < 48 || event.keyCode > 57) {
                        alert("Introduzca sólo números")
                        /* Si introduce un caracter que no sea numérico anulo 
                            todo el campo. */
                        value=""
                    }
                    else {
                        // Calculo el importe del pedido.
                        var importe=0
                        importe = value * document.formPedidos.txtPrecio.value
45
                        document.formPedidos.txtImporte.value = importe
}
                }
            }
            function TodoCorrecto_submit() {
                /* Si el usuario pulsa el botón submit sin introducir ningún 
                    dato, no se envía nada. */
                with (document.formPedidos.txtUnidades) {               
                    if (value=="") {
                        alert("No han introducido las unidades")
focus()
                        return false
                    }
                    else return true
                }
}            
        </script>
    </body>

</html>
