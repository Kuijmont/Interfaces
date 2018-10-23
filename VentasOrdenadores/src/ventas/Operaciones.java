/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import java.util.Vector;

/**
 *
 * @author Frans
 */
public class Operaciones {
    
    public static boolean validarNombre(String nombre){
        String patron = "[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+";
        if (!nombre.matches(patron))
            return false;
        return true;
    }
    
 }


