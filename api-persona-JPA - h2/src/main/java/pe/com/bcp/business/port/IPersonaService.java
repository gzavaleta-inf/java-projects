package pe.com.bcp.business.port;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import pe.com.bcp.domain.Persona;

public interface IPersonaService {

	public List<Persona> getPersonas();
	public String addPersona(String jsonPersona) throws JsonProcessingException;
	public String removePersona(Integer id);
	public String updatePersona(Integer id, String nombre, String apellido, String dni, boolean empleado);
	
}
