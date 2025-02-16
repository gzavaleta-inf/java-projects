package pe.com.yape.controller.util;

import pe.com.yape.domain.SemillaResponse;
import pe.com.yape.domain.reponse.HandleException;

public class Utils {

	public static final String MENSAJE_ERROR = "Se produjo el siguiente error: ";
	public static final String MENSAJE_UPDATE = "Persona actualizada exitosamente: ";
	public static final String MENSAJE_CREATE = "Persona creada exitosamente: ";
	public static final String MENSAJE_DELETE = "Persona eliminada exitosamente.";
	public static final String MENSAJE_CLAVE_GENERADA = "Clave generada exitosamente.";
	
	private Utils() {
	}
	
	public static void generateException(String message, Object object){
            throw new HandleException(message, object);
    }

	public static String generateCode(SemillaResponse clave1, SemillaResponse clave2, SemillaResponse clave3, SemillaResponse clave4){
		return clave1.getId().concat(clave2.getId()).concat(clave3.getId()).concat(clave4.getId()).concat("-CLAVE");
	}
}
