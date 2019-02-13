<%-- 
    Document   : ExtractosValidarFechas
    Created on : 12-feb-2019, 9:22:28
    Author     : Frans
--%>

<%@page import="evaluable5.PedidosInternet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 
<
<
<            E X T R A C T O V A L I D A R F E C H A S . 
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
        <%! String ponerCero(String MesDia) {
/* Función para añadir un 0 al mes o al día si son menores
                    que 9. Esto lo hago porque en la base de datos están
                    grabados con un 0 (01, 02, 03, ...). 
*/
                String Cero="";
                if (MesDia.length()==1) Cero="0";
return Cero;
            }
        %>
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
            /* Doy formato a las fechas para que tengan el mismo con el que
                se han grabado en la base de datos. 
*/
            String Cero="";
            String fecha1=request.getParameter("Anio1")+"-";
Cero=ponerCero(request.getParameter("Mes1"));
            fecha1=fecha1+Cero+request.getParameter("Mes1")+"-";
            Cero=ponerCero(request.getParameter("Dia1"));            
            fecha1=fecha1+Cero+request.getParameter("Dia1");
            String fecha2=request.getParameter("Anio2")+"-";
            Cero=ponerCero(request.getParameter("Mes2"));            
            fecha2=fecha2+Cero+request.getParameter("Mes2")+"-";
            Cero=ponerCero(request.getParameter("Dia2"));            
            fecha2=fecha2+Cero+request.getParameter("Dia2");
            Vector ext=new Vector();
            ConectorSQL g=new ConectorSQL();
            ext=g.getExtractosPanImp(c.getCodigo(),fecha1,fecha2);
if (ext.size()==0) { %>
<h1>No ha hecho pedidos entre las fechas</h1>
                <h1><%= fecha1 %> y <%= fecha2 %></h1>
        <%
            }else { 
                /* Me guardo los pedidos entre las fechas en el atributo
                    Extractos del objeto HttpSession para visualizarlos
                    en la página ImprimirExtracto.jsp. */
                s.setAttribute("Extractos",ext);
        %>
                <h2>Extracto de pedidos</h2>
                <h3>Del <%= fecha1 %> al <%= fecha2 %></h3>
<table>
                    <tr>
                        <td><b>Fecha</b></td>                    

                        <td><b>&nbsp;&nbsp;&nbsp;Art&iacute;culo</b></td>
                        <td align="right"><b>&nbsp;&nbsp;&nbsp;Unidades</b></td>
                    </tr>
                    <%
                        int i=0;
                        String fecha="";
                        String articulo="";
                        String unidades="";
                        while (i<ext.size()) {
                            PedidosInternet p = (PedidosInternet)ext.get(i);
                            fecha=p.getFecha();
                            articulo=p.getArticulo();
                            unidades=String.valueOf(p.getUnidades());
%>
                            <tr>
                                <td><%= fecha %></td>
                                <td>&nbsp;&nbsp;&nbsp;<%= articulo %></td>
<td align="right">&nbsp;&nbsp;&nbsp;
<%= unidades %>
                                </td>
                            </tr>
<%  
                            i++;
                        }
                    %>
                </table>
<br>
                <hr>
                <h2>Extracto finalizado</h2>
                Si desea imprimir el extracto se abrir&aacute; una nueva ventana 
                con el extracto a imprimir.
                <br>
                En esta ventana, abra el men&uacute; Archivo y ejecute la 
                opci&oacute;n Imprimir.
                <br>
                En la nueva ventana, seleccione su impresora y pulse Imprimir.
                <br>
                Despu&eacute;s, cierre la ventana que contiene el extracto 
                a imprimir.
                <br>
                <!-- El atributo target="_blank" de la etiqueta
                    <a href="ImprimirPedido.jsp", se utiliza para que la página
                    que se va cargar cuando se pulse sobre el enlace, se abra
                    en una nueva ventana. -->
                Para imprimir el extracto pulse 
                <a href="ImprimirExtracto.jsp?fecha1=<%= fecha1 %>&fecha2=<%= fecha2 %>
"target="_blank">aqu&iacute;.
</a>
                <br><br>
        <%
            }

        %>
        <br>
        <hr>
        <br><br>
        <form name="formExtractos" action="ExtractosBuscarCliente.jsp" 
            method="get">
            <input type=submit name=cmdAceptar value="Nuevas fechas">
</form>
        <a href="PedExtCodCli.jsp?opcion=e">Nuevo cliente</a>
|
        <a href="index.jsp">P&aacute;gina principal</a>
        <%-- <jsp:useBean id="beanInstanceName" scope="session" 
                class="beanPackage.BeanClassName" /> --%>
        <%-- <jsp:getProperty name="beanInstanceName"  
                property="propertyName" /> --%>
    </body>

</html>
