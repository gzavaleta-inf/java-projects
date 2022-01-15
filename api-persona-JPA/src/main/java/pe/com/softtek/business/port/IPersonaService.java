package pe.com.softtek.business.port;

import java.util.List;

import pe.com.softtek.domain.Persona;

public interface IPersonaService {

	public List<Persona> getPersonas();
	public String addPersona(String nombre, String apellido, String dni, boolean empleado);
	public String removePersona(Integer id);
	public String updatePersona(Integer id, String nombre, String apellido, String dni, boolean empleado);
	
}
