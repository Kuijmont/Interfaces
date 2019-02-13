<%-- 
    Document   : Pedidos
    Created on : 07-feb-2019, 11:35:43
    Author     : Frans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 
<
<
<                       P E D I D O S . J S P
<
<
-->
<!-- Esta página muestra todos los artículos y las unidades que el cliente
        ha solicitado. Desde esta página se puede anular todo el pedido, 
        solicitar nuevos artículos para el mismo cliente o confirmar el
        pedido actual, en cuyo caso se accede a la página Factura.jsp. -->
<%@page contentType="text/html"%>
<%@page import="evaluable5.*"%>
<%@page import="java.util.Vector"%>
<html>
    <head><title>Gesti&oacute;n de pedidos</title></head>
<body>
        <h1>Gesti&oacute;n de pedidos</h1>
        <%
            /* Tomo los datos del cliente del atributo Cliente del objeto
HttpSession. */
            HttpSession s=request.getSession();
Client c=(Client) s.getAttribute("Cliente");
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
47
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
        <br>
        <% 
            /* Guardo el último pedido en el vector del atributo Pedidos del 
                objeto Session. */
            // Recupero el vector del atributo Pedidos del objeto Session.
HttpSession sesion=request.getSession();
Vector pedidos=(Vector)sesion.getAttribute("Pedidos");
            // Creo un objeto de tipo Pedidos para almacenar el pedido.
            Pedidos pedido=new Pedidos();
            pedido.setArticulo(request.getParameter("txtArticulo"));
            pedido.setDescripcion(request.getParameter("txtDescripcion"));
            Float precio=new Float(request.getParameter("txtPrecio"));
            pedido.setPrecio(precio.floatValue());
            Float unidades=new Float(request.getParameter("txtUnidades"));
            pedido.setUnidades(unidades.floatValue());
            Float importe=new Float(request.getParameter("txtImporte"));
            pedido.setImporte(importe.floatValue());
            /* AÃ±ado el objeto de tipo Pedidos al vector para guardarlo en el 
                atributo Pedidos del objeto Session. */
            pedidos.addElement(pedido);
            sesion.setAttribute("Pedidos",pedidos);
        %>
        <!-- Muestro los datos de todos los pedidos -->
        <h2>Pedido realizado</h2>
<table>
            <tr>
                <td><b>Art&iacute;culo</b></td>
                <td><b>&nbsp;&nbsp;&nbsp;Descripci&oacute;n</b></td>
                <td align="right"><b>&nbsp;&nbsp;&nbsp;Unidades</b></td>
                <td align="right"><b>&nbsp;&nbsp;&nbsp;Precio</b></td>
                <td align="right"><b>&nbsp;&nbsp;&nbsp;Importe</b></td>
</tr>
            <%
                pedidos=(Vector)sesion.getAttribute("Pedidos");
int i=0;
                float total=0;
                while (i<pedidos.size()) {
                pedido=(Pedidos)pedidos.elementAt(i);

            %>
                    <tr>
                        <td><%= pedido.getArticulo() %></td>
<td>&nbsp;&nbsp;&nbsp;
<%= pedido.getDescripcion() %>
                        </td>
<td align="right">&nbsp;&nbsp;&nbsp;
<%= pedido.getUnidades() %>
                        </td>
<td align="right">&nbsp;&nbsp;&nbsp;
<%= pedido.getPrecio() %>
                        </td>
<td align="right">&nbsp;&nbsp;&nbsp;
<%= pedido.getImporte() %>
                        </td>
                    </tr>
            <%  
                    total=total + pedido.getImporte();    
i++;
                }
            %>
        </table>
        <br>
<h2>Importe del pedido: <%= total %></h2>
        <hr>
        <br>
        <form name="formPedidos" action="Factura.jsp" method="get">
<!-- Paso la opción que el usuario ha elegido en la página index.jsp
                    como una caja de texto, porque en la etiqueta form utilizo 
                    el método get para que se visualicen bien los acentos de los 
                    campos de la tabla en la siguiente página. -->
<input type=hidden name=opcion 
                value="<%= request.getParameter("opcion") %>">
            <input type="submit" name="cmdAceptar" value="Aceptar pedido">
</form>
        <a href="PedidosBuscarCliente.jsp?opcion=<%=request.getParameter
            ("opcion")%>&txtCodigo=<%= c.getCodigo() %>">
            Pedir otro art&iacute;culo
</a>
        |
        <a href="index.jsp">P&aacute;gina principal</a>
        <%-- <jsp:useBean id="beanInstanceName" scope="session" 
                class="beanPackage.BeanClassName" /> --%>
        <%-- <jsp:getProperty name="beanInstanceName"  
                property="propertyName" /> --%>
    </body>
