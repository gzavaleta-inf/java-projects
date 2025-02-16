package pe.com.bcp.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.com.bcp.business.port.IPersonaService;
import pe.com.bcp.config.OAuthToken;
import pe.com.bcp.domain.Persona;

@RestController
@RequestMapping (path="/persona")
public class PersonaController {
	
	@Autowired
	private IPersonaService methodsService;

	@Autowired
	private OAuthToken oAuthToken;

	@GetMapping(path="/read")
	public ResponseEntity<List<Persona>> getPersonas() {
		
		return new ResponseEntity<>(methodsService.getPersonas(), HttpStatus.OK);
	}
	
	@PostMapping(path="/create")
	public ResponseEntity<String> agregarPersona(
			@RequestHeader(name = "Authorization") String authorization,
			@RequestBody String jsonPersona
			) throws JsonProcessingException {

		if (oAuthToken.checkValidToken(authorization)){
			return new ResponseEntity<>(methodsService.addPersona(jsonPersona), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
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
