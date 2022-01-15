package pe.com.softtek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.softtek.business.port.IPersonaService;
import pe.com.softtek.domain.Persona;

@RestController
@RequestMapping (path="/persona")
public class PersonaController {
	
	@Autowired
	private IPersonaService methodsService;

	@GetMapping(path="/read")
	public ResponseEntity<List<Persona>> getPersonas() {
		
		return new ResponseEntity<>(methodsService.getPersonas(), HttpStatus.OK);
	}
	
	@GetMapping(path="/create")
	public ResponseEntity<String> agregarPersona(
			@RequestParam(value = "nombre") String nombre,
			@RequestParam(value = "apellido") String apellido,
			@RequestParam(value = "dni") String dni,
			@RequestParam(value = "empleado") boolean empleado
			) {
			
		return new ResponseEntity<>(methodsService.addPersona(nombre, apellido, dni, empleado), HttpStatus.OK);
	}
	
	@GetMapping(path="/delete")
	public ResponseEntity<String> eliminarPersona(@RequestParam(value = "id") Integer id) {
		
		return new ResponseEntity<>(methodsService.removePersona(id), HttpStatus.OK);
	}
	
	@GetMapping(path="/update")
	public ResponseEntity<String> editarPersona(
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "nombre") String nombre,
			@RequestParam(value = "apellido") String apellido,
			@RequestParam(value = "dni") String dni,
			@RequestParam(value = "empleado") boolean empleado
			) {
		
		return new ResponseEntity<>(methodsService.updatePersona(id, nombre, apellido, dni, empleado), HttpStatus.OK);
	}
}
