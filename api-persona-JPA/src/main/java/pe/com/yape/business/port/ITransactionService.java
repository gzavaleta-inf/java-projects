package pe.com.yape.business.port;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import pe.com.yape.domain.Persona;

public interface ITransactionService {

	public List<Persona> getPersonas();
	public List<Persona> getPersonasConcatNames();
	public List<Persona> getPersonaById(int id);
	public String addPersona(String jsonPersona) throws JsonProcessingException;
	public String generarClave(int id);
	public String removePersona(Integer id);
	public String updatePersona(Integer id, String nombre, String apellido, String dni, boolean empleado);
	
}
