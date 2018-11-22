/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeunalmacen;

/**
 *
 * @author Frans
 */
public class Cliente {
    private String cod;
    private String nif;
    private String letra;
    private String nombre;
    private String apell;
    private String dom;
    private String cp;
    private String local;
    private String movil;
    private String fax;
    private String email;
    private String total;

    public Cliente(String cod, String nif, String letra, String nombre, String apell, String dom, String cp, String local, String movil, String fax, String email, String total) {
        this.cod = cod;
        this.nif = nif;
        this.letra = letra;
        this.nombre = nombre;
        this.apell = apell;
        this.dom = dom;
        this.cp = cp;
        this.local = local;
        this.movil = movil;
        this.fax = fax;
        this.email = email;
        this.total = total;
    }


    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApell() {
        return apell;
    }

    public void setApell(String apell) {
        this.apell = apell;
    }

    public String getDom() {
        return dom;
    }

    public void setDom(String dom) {
        this.dom = dom;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    
    
}
