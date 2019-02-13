<%-- 
    Document   : PedidosBuscarArticulo
    Created on : 31-ene-2019, 13:19:56
    Author     : Frans
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 
<
<
<            P E D I D O S B U S C A R A R T I C U L O . J S P
<
<
-->
<!-- Página que busca el código de artículo introducido en la página
        PedidosBuscarCliente.jsp. Si el artículo existe, se solicitan las
        unidades. -->

<%@page import="evaluable5.*"%>
<html>
    <head><title>Gesti&oacute;n de pedidos</title></head>
    <h1>Gesti&oacute;n de pedidos</h1>
<%  
        /* Tomo los datos del cliente del atributo Cliente del objeto HttpSession. */
        HttpSession s=request.getSession();
        Client c=(Client) s.getAttribute("Cliente");
        String codeCli=c.getCodigo();
        String codeArt=request.getParameter("txtArticulo");
        ConectorSQL g=new ConectorSQL();
        g.conectBd();
        if (!g.checkItem(codeArt)) {               
%>         
    <body>
        <h2>El art&iacute;culo con c&oacute;digo <%= request.getParameter("txtArticulo")%> no existe</h2>
                 <a href="PedidosBuscarCliente.jsp?opcion=<%=request.getParameter
            ("opcion")%>&txtCodigo=<%= c.getCodigo() %>">
            Nuevo art&iacute;culo
</a>|<a href="index.jsp">P&aacute;gina principal</a>
    </body> 
<%  
    }else { 
%>
    <body onload="document.formPedidos.txtUnidades.focus()">

        <%
                /* Guardo el cliente para las próximas veces que entre en esta
                    página y no tenga que buscar el cliente. */
                Article a=g.showArticle(codeArt);
                Client cli=g.showClient(codeCli);
                s.setAttribute("Cliente", new Client());
                s.setAttribute("Cliente", cli);
                s.setAttribute("Articulo", a);
        %>
    <!-- Muestro los datos del cliente -->
    <h2>Datos del cliente</h2>
<table>
    <tr>
        <td><b>C&oacute;digo</b></td>
        <td><b>&nbsp;&nbsp;&nbsp;N.I.F.</b></td>
        <td><b>&nbsp;&nbsp;&nbsp;Nombre</b></td>
        <td><b>&nbsp;&nbsp;&nbsp;Apellidos</b></td>                
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
<br><hr>
<!-- Muestro los datos del artículo -->
<h2>Realizar pedido</h2>
<form name="formPedidos" action="Pedidos.jsp" method="get" onsubmit="return TodoCorrecto_submit()">
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
            <td>&nbsp;&nbsp;<input type="text" name="txtUnidades" size="9" maxlength="9" onkeyup="CalcularPrecio(event)" pattern="[0-9]+"></td>
            <td>&nbsp;&nbsp;&nbsp;<%= a.getPrecio() %></td>
            <td>&nbsp;&nbsp;<input type="text" name="txtImporte" size="9" maxlength="9" readonly></td>
        </tr>
    </table>
    <!-- Oculto las cajas de texto para pasarlas a la siguiente página -->
    <input type="hidden" name="txtArticulo" value="<%= request.getParameter("txtArticulo") %>">                    
    <input type="hidden" name="txtDescripcion" value="<%= a.getDescripcion() %>">
    <input type="hidden" name="txtPrecio" value="<%= a.getPrecio() %>">
    <!-- Paso la opción que el usuario ha elegido en la página 
         index.jsp como una caja de texto, porque en la 
         etiqueta form utilizo el método get para que se 
         visualicen bien los acentos de los campos de la 
         tabla en la siguiente página. -->
    <input type=hidden name=opcion value="<%= request.getParameter("opcion") %>">
    <br><br>
    <input type="submit" value="Aceptar">
    <input type="reset" value="Cancelar" onclick="document.formPedidos.txtUnidades.focus()">                    
    <br><br>
    <hr>
    <a href="PedidosBuscarCliente.jsp?opcion=<%=request.getParameter
            ("opcion")%>&txtCodigo=<%= c.getCodigo() %>">
    Nuevo art&iacute;culo
</a>
<a href="index.jsp">P&aacute;gina principal</a>
</form>
<%   } %>
         
        <%-- <jsp:useBean id="beanInstanceName" scope="session" 
                class="beanPackage.BeanClassName" /> --%>
        <%-- <jsp:getProperty name="beanInstanceName"  
                property="propertyName" /> --%>
<script language="JavaScript" type="text/javascript">
function CalcularPrecio(event) {
    // Compruebo si introduce números.
    with (document.formPedidos.txtUnidades) {
        if (!parseInt(value) && value!=0) {
            alert("Introduzca sólo números")
            /* Si introduce un caracter que no sea numérico anulo todo el campo. */
            value=""
            
        }else {
        // Calculo el importe del pedido.
            var importe=0
            importe = value * document.formPedidos.txtPrecio.value
            document.formPedidos.txtImporte.value = importe
        }
    }
}
function TodoCorrecto_submit() {
/* Si el usuario pulsa el botón submit sin introducir ningún dato, no se envía nada. */
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
