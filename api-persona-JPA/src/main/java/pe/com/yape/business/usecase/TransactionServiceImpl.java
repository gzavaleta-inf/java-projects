package pe.com.yape.business.usecase;

import java.util.List;

import java.util.ArrayList;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import org.springframework.web.reactive.function.client.WebClient;
import pe.com.yape.business.port.ITransactionService;
import pe.com.yape.controller.util.Utils;
import pe.com.yape.domain.Persona;
import pe.com.yape.domain.SemillaResponse;
import pe.com.yape.domain.dao.IPersistence;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
public class TransactionServiceImpl implements ITransactionService {
	
	@Autowired
	private IPersistence persistence;

	WebClient webClient = WebClient.builder().baseUrl("https://api.chucknorris.io").build();;

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

	public List<Persona> getPersonasConcatNames() {

		List<Persona> listaPersona = new ArrayList<>();;

		try {
			listaPersona = (List<Persona>) persistence.findAll();

			listaPersona.forEach(persona -> persona.setCompleto(persona.getNombre().toUpperCase() + " " + persona.getApellido().toUpperCase()));
		}
		catch (Exception ex){
			Utils.generateException(
					Utils.MENSAJE_ERROR + ex.getMessage(),
					null);
		}

		return listaPersona;
	}

	@Override
	public List<Persona> getPersonaById(int id) {

		Optional<Persona> listaPersona = null;

		try {
			listaPersona = persistence.findById(id);
		}
		catch (Exception ex){
			Utils.generateException(
					Utils.MENSAJE_ERROR + ex.getMessage(),
					null);
		}

		return listaPersona.stream().toList();
	}

	@Override
	public String addPersona(String jsonPersona) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();

		Persona persona = objectMapper.readValue(jsonPersona, Persona.class);

		try {
			persona = persistence.save(persona);
		}
		catch (Exception ex){
			return Utils.MENSAJE_ERROR + ex.getMessage();
		}
		
		return Utils.MENSAJE_CREATE + new Gson().toJson(persona);
		
	}

	@Override
	public String generarClave(int id) {
		String claveGenerada = "";
		var persona = new Persona();

		Mono<SemillaResponse> semillaResponse1 = webClient.get()
				.uri("/jokes/random")
				.retrieve()
				.bodyToMono(SemillaResponse.class)
				.subscribeOn(Schedulers.boundedElastic());

		Mono<SemillaResponse> semillaResponse2 = webClient.get()
				.uri("/jokes/random")
				.retrieve()
				.bodyToMono(SemillaResponse.class)
				.subscribeOn(Schedulers.boundedElastic());

		Mono<SemillaResponse> semillaResponse3 = webClient.get()
				.uri("/jokes/random")
				.retrieve()
				.bodyToMono(SemillaResponse.class)
				.subscribeOn(Schedulers.boundedElastic());

		Mono<SemillaResponse> semillaResponse4 = webClient.get()
				.uri("/jokes/random")
				.retrieve()
				.bodyToMono(SemillaResponse.class)
				.subscribeOn(Schedulers.boundedElastic());


		claveGenerada = Mono.zip(semillaResponse1, semillaResponse2, semillaResponse3, semillaResponse4).map(
				tupla -> Utils.generateCode(tupla.getT1(), tupla.getT2(), tupla.getT3(), tupla.getT4())
		).block();

		persona = persistence.findById(id).orElseThrow();

		persistence.save(Persona.builder()
				.id(id)
				.nombre(persona.getNombre())
				.apellido(persona.getApellido())
				.dni(persona.getDni())
				.empleado(persona.isEmpleado())
				.clave(claveGenerada)
				.build());

		return Utils.MENSAJE_CLAVE_GENERADA;
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
			persistence.findById(id).orElseThrow();
			
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
