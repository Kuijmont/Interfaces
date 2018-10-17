/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario;

/**
 *
 * @author Frans
 */

public class Formulario {
    //Cálculo de la letra del dni/nif y validar NIF
    public static boolean dniCorrecto(String dni) {
	if (!dni.matches("[0-9]{8}"))
            return false;
        return true;
    }
    
    //Calcula la letra que corresponde a los 8 dígitos de un DNI
    public static char calcularLetraDni(String digitosDni){
	if (!digitosDni.matches("[0-9]{8}"))
            return ' '; //Devuelve espacio en blanco si no llegan 8 digitos 
	char letras[]={'T', 'R', 'W', 'A', 'G', 'M', 'Y',
		       'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z',
		       'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

	return letras[Integer.valueOf(digitosDni)%23];
    }

    //Validar Nombre
    public static boolean validarNombre(String nombre){
        String pattern = "[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+";
        if (!nombre.matches(pattern)||nombre.length()>15)
            return false;
        return true;
    }
    
    //Validar Código
    public static  boolean validarCodigo(String cod){
        String pattern = "[a-zA-ZñÑ0-9]";
        if (cod.matches(pattern)||(cod.length()<=6 && cod.length()>0))
            return true;
        return false;
    }
    
     //Validar Domicilio
    public static  boolean validarDomicilio(String domicilio){
        String pattern = "[a-zA-ZñÑáéíóúÁÉÍÓÚ\\sº/0-9ª,]+";
        if (!domicilio.matches(pattern)||domicilio.length()>50)
            return false;
        return true;
    }
    
    //Validar Código Postal
    public static boolean validarCodPostal(String codPost){
        String pattern = "[0-9]{5}";
        if(!codPost.matches(pattern)||codPost.length()>5)
            return false;
        return true;
    }
    
    //Validar Teléfono, fax, móvil
    public static boolean validarTelFaxMov(String num){
        String pattern = "[0-9]{9}";
        if(!num.matches(pattern)||num.length()>9)
            return false;
        return true;
    }
    
    //Validar Email "^[A-Za-z0-9+_.-]+@(.+)$"
    public static boolean validarEmail(String email){
        String pattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        if(!email.matches(pattern))
            return false;
        return true;
    }
}

 


