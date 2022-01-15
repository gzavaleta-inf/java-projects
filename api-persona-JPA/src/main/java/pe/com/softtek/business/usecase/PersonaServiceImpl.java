package pe.com.softtek.business.usecase;

import java.util.List;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import pe.com.softtek.business.port.IPersonaService;
import pe.com.softtek.controller.util.Utils;
import pe.com.softtek.domain.Persona;
import pe.com.softtek.domain.dao.IPersistence;

@Service
public class PersonaServiceImpl implements IPersonaService {
	
	@Autowired
	private IPersistence persistence;

	@Override
	public List<Persona> getPersonas() {
		
		List<Persona> listaPersona = new ArrayList<>();
		
		try {
			listaPersona = (List<Persona>) persistence.findAll();
		}
		catch (Exception ex){
			Utils.generateException(
					Utils.MENSAJE_ERROR + ex.getMessage(), 
					null);		
		}
		
		return listaPersona;
	}

	@Override
	public String addPersona(String nombre, String apellido, String dni, boolean empleado) {
		
		var persona = new Persona();
		
		try {
			persona = persistence.save(
					Persona.builder()
					.nombre(nombre)
					.apellido(apellido)
					.dni(dni)
					.empleado(empleado)
					.build());
		}
		catch (Exception ex){
			return Utils.MENSAJE_ERROR + ex.getMessage();
		}
		
		return Utils.MENSAJE_CREATE + new Gson().toJson(persona);
		
	}

	@Override
	public String removePersona(Integer id) {
		
		try {
			persistence.deleteById(id);			
		}
		catch (Exception ex){
			return Utils.MENSAJE_ERROR + ex.getMessage();
		}
		
		return Utils.MENSAJE_DELETE;
	}

	@Override
	public String updatePersona(Integer id, String nombre, String apellido, String dni, boolean empleado) {
		
		var persona = new Persona();
		
		try {
			persona = persistence.findById(id).orElseThrow();
			
			persona = persistence.save(Persona.builder()
					.id(id)
					.nombre(nombre)
					.apellido(apellido)
					.dni(dni)
					.empleado(empleado)
					.build());
		}
		catch (Exception ex){
			return Utils.MENSAJE_ERROR + ex.getMessage();
		}
		
		return Utils.MENSAJE_UPDATE + new Gson().toJson(persona);
	}
	
}
