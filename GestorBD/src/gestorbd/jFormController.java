/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorbd;

/**
 *
 * @author alumno
 */
public class jFormController {

    public static final int CODIGO = 0;
    public static final int NIF = 1;
    public static final int NOMBRE = 2;
    public static final int APELLIDO = 3;
    public static final int DOMICILIO = 4;
    public static final int CP = 5;
    public static final int LOCALIDAD = 6;
    public static final int TELEFONO = 7;
    public static final int MOVIL = 8;
    public static final int FAX = 9;
    public static final int EMAIL = 10;

    public static final int NO_ERROR = 0;
    public static final int CONTENT_ERROR = 1;
    public static final int LONG_ERROR = 2;

    public static int validacion(int id, String content) {
        switch (id) {
            case CODIGO:
                return testCodigo(content);
            case NIF:
                return testNif(content);
            case NOMBRE:
                return testNombre(content);
            case APELLIDO:
                return testApellido(content);
            case DOMICILIO:
                return NO_ERROR;
            case CP:
                return testCP(content);
            case LOCALIDAD:
                return testLocalidad(content);
            case TELEFONO:
            case MOVIL:
            case FAX:
                return testTelefono(content);
            case EMAIL:
                return NO_ERROR;
            default:
                return NO_ERROR;
        }
    }

    private static int testCodigo(String content) {
        int resul = content.length() <= 6 && content.length() > 0 ? NO_ERROR : CONTENT_ERROR;
        return resul;
    }

    private static int testNif(String content) {
        if (content.matches("[0-9]{1,8}")) {
            return NO_ERROR;
        } else {
            return content.length() > 8 || content.length() <= 0 ? LONG_ERROR : CONTENT_ERROR;
        }
    }

    private static int testNombre(String content) {
        if (content.trim().matches("([A-Za-z]+[ ]?)+"))
            return NO_ERROR;
        else
            return CONTENT_ERROR;
    }

    private static int testCP(String content) {
        if(content.trim().matches("[0-9]{5}"))
            return NO_ERROR;
        else
            if(!content.trim().matches("[0-9]*"))
                return CONTENT_ERROR;
            else 
                return LONG_ERROR;
    }

    private static int testTelefono(String content) {
        if(!content.trim().matches("[0-9]+"))
            return CONTENT_ERROR;
        else if(content.trim().matches("[0-9]{9}"))
            return NO_ERROR;
        else
            return LONG_ERROR;
    }

    private static int testLocalidad(String content) {
        return testNombre(content);
    }

	static String letraNif(String text) {
		String[] letra = {"T","R", "W", "A", "G", "M", "Y", "F", "P", "D",
			"X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
		return letra[Integer.parseInt(text)%23];
	}

	private static int testApellido(String content) {
		        if (content.trim().matches("([A-Za-z.\\-]+[ ]?)+"))
            return NO_ERROR;
        else
            return CONTENT_ERROR;
	}


}
