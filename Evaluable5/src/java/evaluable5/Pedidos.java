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
public class Pedidos {
    String articulo;
    String descripcion;
    float unidades;
    double precio;
    float importe;

    public Pedidos() {
    }

    public Pedidos(String articulo, String descripcion, float unidades, double precio, float importe) {
        this.articulo = articulo;
        this.descripcion = descripcion;
        this.unidades = unidades;
        this.precio = precio;
        this.importe = importe;
    }
    
    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getUnidades() {
        return unidades;
    }

    public void setUnidades(float unidades) {
        this.unidades = unidades;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }
    
    
}
