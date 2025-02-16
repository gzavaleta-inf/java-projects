package pe.com.yape.controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.web.bind.annotation.*;

import pe.com.yape.business.port.ITransactionService;
import pe.com.yape.config.OAuthToken;
import pe.com.yape.domain.Persona;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping (path="/persona")
public class PersonaController {
	
	@Autowired
	private ITransactionService methodsService;

	@Autowired
	private OAuthToken oAuthToken;

	@GetMapping(path="/readAll")
	public Mono<List<Persona>> getPersonas() {
		return Mono.just(methodsService.getPersonas());
	}

	@GetMapping(path="/read-by-id")
	public Mono<List<Persona>> getPersonaById(
			@RequestParam(value = "id") int id
	) {

		return Mono.just(methodsService.getPersonaById(id));
	}

	@GetMapping(path="/concat-names")
	public Mono<List<Persona>> getPersonaConcatNames() {

		return Mono.just(methodsService.getPersonasConcatNames());
	}
	
	@PostMapping(path="/create")
	public Flux<String> agregarPersona(
			@RequestHeader(name = "Authorization") String authorization,
			@RequestBody String jsonPersona
			) throws JsonProcessingException {

		if (oAuthToken.checkValidToken(authorization)){
			return Flux.just(methodsService.addPersona(jsonPersona));
		}
		else {
			throw new OAuth2AuthenticationException(new OAuth2Error("invalid_token"));
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

	@GetMapping(path="/generar-clave")
	public Flux<String> generarClave(
			@RequestParam(value = "id") int id
	) {
		return Flux.just(methodsService.generarClave(id));
	}
}
