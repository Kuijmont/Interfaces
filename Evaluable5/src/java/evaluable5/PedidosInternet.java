/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluable5;

/**
 *
 * @author Frans
 */
public class PedidosInternet {
    
     String cliente;
     String articulo;
     float unidades;
     String fecha;

    public PedidosInternet() {
    }

    public PedidosInternet(String cliente, String articulo, float unidades, String fecha) {
        this.cliente = cliente;
        this.articulo = articulo;
        this.unidades = unidades;
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public float getUnidades() {
        return unidades;
    }

    public void setUnidades(float unidades) {
        this.unidades = unidades;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
     
    
}
