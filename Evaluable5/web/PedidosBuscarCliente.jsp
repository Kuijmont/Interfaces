<%-- 
    Document   : PedidosBuscarCliente
    Created on : 29-ene-2019, 10:59:30
    Author     : Frans
--%>

<!-- 
<
<
<            P E D I D O S B U S C A R C L I E N T E . J S P
<
<
-->
<!-- Página que busca el código de cliente introducido en la página 
        PedExtCodCli.jsp. Si el cliente existe, en esta página se solicita
        el código de artículo. -->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="evaluable5.*"%>
<html>
    <head><title>Gesti&oacute;n de pedidos</title></head>
<h1>Gesti&oacute;n de pedidos</h1>        
<%  
    boolean existe=true;
    HttpSession s=request.getSession();
    Client c=(Client) s.getAttribute("Cliente");
    /* Si el código de cliente del objeto HttpSession está vacío,
       indica que es la primera vez que ejecuta esta página para un
       determinado cliente. Si  no está vacío, indica que no es la
       primera vez que ejecuta esta página para un determinado cliente
        y, por lo tanto, no busca el cliente en la base de datos. 
    */
    if (c.getCodigo()!="") {
        ConectorSQL g=new ConectorSQL();
        g.conectBd();
        c=new Client(request.getParameter("txtCodigo"),"","","","","","","","","","",0);
        c=g.showClient(request.getParameter("txtCodigo"));
    if (c.getCodigo()=="") {
%>
<body>
    <h2>El cliente con c&oacute;digo <%= request.getParameter("txtCodigo")%> no existe</h2>
<%  
    existe=false;
    }
}
    if (existe==true) {
%>
<body onload="document.formPedidos.txtArticulo.focus()">
<%
    /* Guardo el cliente para las próximas veces que entre en esta
       página y no tenga que buscar el cliente. */
    s.setAttribute("Cliente", c);
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
<br>
    <hr>
<br>
    <h2>Realizar pedido</h2>
    Art&iacute;culo
<br>
<form name="formPedidos" action="PedidosBuscarArticulo.jsp" 
    method="post" onsubmit="return TodoCorrecto_submit()">
<!-- Paso la opción que el usuario ha elegido en la página 
                        index.jsp como una caja de texto, porque en la etiqueta 
                        form utilizo el método get para que se visualicen bien 
                        los acentos de los campos de la tabla en la siguiente 
página. -->
                    <input type=hidden name=opcion 
                        value="<%= request.getParameter("opcion") %>">
                    <input type="text" name="txtArticulo" size="4" maxlength="6">
                    <br><br>
                    <input type="submit" value="Aceptar">

                    <input type="reset" value="Cancelar" 
onclick="document.formPedidos.txtArticulo.focus()">                    
<br><br>
                    <hr>
                </form>
         <%   } %>
         <a href="PedExtCodCli.jsp?opcion=<%=request.getParameter("opcion")%>">
Nuevo cliente
         </a>
|
         <a href="index.jsp">P&aacute;gina principal</a>         
        <%-- <jsp:useBean id="beanInstanceName" scope="session" 
                class="beanPackage.BeanClassName" /> --%>
        <%-- <jsp:getProperty name="beanInstanceName"  
                property="propertyName" /> --%>
        <script language="JavaScript" type="text/javascript">
            function TodoCorrecto_submit() {
                /* Si el usuario pulsa el botón submit sin introducir ningún 
                    dato, no se envía nada. */
                with (document.formPedidos.txtArticulo) {
                    if (value=="") {
                        alert("No ha introducido el artículo")
focus()
                        return false
                    }
                    else {
                        for (i=1; value.length<6; i++) {
                            value="0"+value
                        }
                        return true
}
                }
            }
        </script>        
    </body>
</html>

