package pe.com.bcp.controller.util;

import pe.com.bcp.domain.reponse.SofttekException;

public class Utils {

	public static final String MENSAJE_ERROR = "Se produjo el siguiente error: ";
	public static final String MENSAJE_UPDATE = "Persona actualizada exitosamente: ";
	public static final String MENSAJE_CREATE = "Persona creada exitosamente: ";
	public static final String MENSAJE_DELETE = "Persona eliminada exitosamente.";

	
	private Utils() {
	}
	
	public static void generateException(String message, Object object){
            throw new SofttekException(message, object);
    }
	
}
