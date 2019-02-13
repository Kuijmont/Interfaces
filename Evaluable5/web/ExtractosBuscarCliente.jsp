<%-- 
    Document   : ExtractosBuscarCliente
    Created on : 12-feb-2019, 9:07:51
    Author     : Frans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 
<
<
<            E X T R A C T O S B U S C A R C L I E N T E . 
J S P
<
<
-->
<%@page contentType="text/html"%>
<%@page import="evaluable5.*"%>
<%@page import="java.util.Vector"%>
<html>
    <head><title>Gesti&oacute;n de extractos</title></head>
<h1>Gesti&oacute;n de extractos</h1>
<body>
         <%  
            boolean existe=true;
            HttpSession s=request.getSession();
            Client c=(Client) s.getAttribute("Cliente");
            // Borro el atributo Extractos para almacenar los nuevos pedidos.
            s.setAttribute("Extractos",new Vector());
            /* Si el código de cliente del objeto HttpSession está vacío,
                indica que es la primera vez que ejecuta esta página para un
                determinado cliente. Si  no está vacío, indica que no es la
                primera vez que ejecuta esta página para un determinado cliente
                y, por lo tanto, no busca el cliente en la base de datos. 
*/
            
                ConectorSQL g=new ConectorSQL();
                g.conectBd();
                if(existe=g.checkCode(request.getParameter("txtCodigo"))) {
                    c=new Client(request.getParameter("txtCodigo"),"","","","","","","","","","",0);
                    c=g.showClient(c.getCodigo());
        
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
                <h2>Fechas del extracto</h2>
<form name="formExtractos" action="ExtractosValidarFechas.jsp" 
                        method="get">
                    <table>
                        <tr>
                            <td>Desde</td>
                            <td>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
                            <td>Hasta</td>
</tr>
                    </table>
                    <table>

                        <tr>
                            <td><b>A&ntilde;o</b></td>
<td><b>Mes</b></td>
<td><b>D&iacute;a</b></td>
                            <td>&nbsp;&nbsp;&nbsp;</td>
                            <td><b>A&ntilde;o</b></td>
<td><b>Mes</b></td>
<td><b>D&iacute;a</b></td>
                        </tr>
                        <tr>
                            <td>                                
                                <select name="Anio1" onchange="ActualizarFecha1()">
                                    <%  
                                        int Anio=(Integer)s.getAttribute("anioActual");
                                        int HastaAnio=Anio - 5;
                                        while (Anio>HastaAnio) {%>
                                            <option value="<%= Anio %>"><%= Anio %>
<%      Anio=Anio-1;
                                        } %>
                                </select>
</td>
                            <td>                                
                                <select name="Mes1" onchange="ActualizarFecha1()">
                                    <%  
                                        for (int i=1;i<=12;i++) {%>
                                            <option value="<%= i %>"><%= i %>
                                    <%  } %>
                                </select>
                            </td>                                
                            <td>
                                <select name="Dia1">
                                    <%  
                                        for (int i=1;i<=31;i++) {%>
                                            <option value="<%= i %>"><%= i %>
                                    <%  } %>
                                </select>
                            </td>
                            <td>&nbsp;&nbsp;&nbsp;</td>                            
<td>                                
                                <select name="Anio2" onchange="ActualizarFecha2()">
                                    <%  
                                        Anio=(Integer)s.getAttribute("anioActual");
                                        HastaAnio=Anio - 5;
                                        while (Anio>HastaAnio) {%>
                                            <option value="<%= Anio %>"><%= Anio %>
                                    <%      Anio=Anio-1;

} %>
                                </select>
                            </td>                                                            
                            <td>                                
                                <select name="Mes2" onchange="ActualizarFecha2()">
                                    <%  
                                        for (int i=1;i<=12;i++) {%>
                                            <option value="<%= i %>"><%= i %>
                                    <%  } %>
                                </select>
                            </td>                                
                            <td>
                                <select name="Dia2">
                                    <%  
                                        for (int i=1;i<=31;i++) {%>
                                            <option value="<%= i %>"><%= i %>
                                    <%  } %>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <br><br>
                    <input type="submit" name="cmdAceptar" value="Aceptar">
                    <br><br>
                    <hr>
</form>
        <%   } else{%>
        <body>
            <h2>El cliente con c&oacute;digo <%= request.getParameter("txtCodigo")%> no existe</h2>
        <% }// Cierra el if de mostrar los datos cuando el cliente existe.
         %>
         <a href="PedExtCodCli.jsp?opcion=e?">Nuevo cliente</a>
|
         <a href="index.jsp">P&aacute;gina principal</a>         
        <%-- <jsp:useBean id="beanInstanceName" scope="session" 
                class="beanPackage.BeanClassName" /> --%>
        <%-- <jsp:getProperty name="beanInstanceName"  
                property="propertyName" /> --%>
        <script language="JavaScript" type="text/javascript">
function ActualizarFecha1() {
                dias=devuelveDias(document.formExtractos.Mes1.value,
                                    document.formExtractos.Anio1.value)
with (document.formExtractos.Dia1) {
                    i=length
                    if (dias < length) {

                        while (i >= dias) {
                            options[i]=null
                            i=i-1
                        }
                    }else {
                        while (i < dias) {
/* En new Option tengo que utilizar i+1 porque si 
                                pongo i y, en la instrucción antes del bucle 
                                donde toma valor i, utilizo 
                                document.formExtractos.Dia1.length+1, me deja
                                una opción en blanco entre el día 29 y 30. 
                                No sé por qué. 
*/
                           options[i]=new Option(i+1,i+1)
                            i=i+1
                        }
                    }
                }
            }
            function ActualizarFecha2() {
dias=devuelveDias(document.formExtractos.Mes2.value,
                                    document.formExtractos.Anio2.value)
with (document.formExtractos.Dia2) {
                    i=length
                    if (dias < length) {
                        while (i >= dias) {
                            options[i]=null
                            i=i-1
                        }
                    }else {
                        while (i < dias) {
/* En new Option tengo que utilizar i+1 porque si 
                                pongo i y, en la instrucción antes del bucle 
                                donde toma valor i, utilizo 
                                document.formExtractos.Dia1.length+1, me deja
                                una opción en blanco entre el día 29 y 30. 
                                No sé por qué. 
*/
                            options[i]=new Option(i+1,i+1)

i=i+1
                        }
                    }
                }
            }
            function devuelveDias(mes,anio) {
                dias=0
                switch (mes) {
                    case "1":;case "3":;case "5":;case "7":;case "8":;case "10":
                        ;case "12":
                        dias=31
                        break
                    case "4":;case "6":;case "9":;case "11":
                        dias=30
                        break
                    case "2":
                        if (anio % 4 == 0) {
                            dias=29
                        }else {
                            dias=28
                        }
                        break
                }
                return (dias)
            }
        </script>        
</body>

</html>
