package pe.com.yape;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.reactive.function.client.WebClient;
import pe.com.yape.business.usecase.PersonaServiceImpl;
import pe.com.yape.controller.util.Utils;
import pe.com.yape.domain.Persona;
import pe.com.yape.domain.dao.IPersistence;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

public class ApiPersonaJpaApplicationTests {

	@Mock
	private IPersistence persistence;

	@Mock
	private WebClient webClient;

	@InjectMocks
	private PersonaServiceImpl personaService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetPersonas() {
		List<Persona> personasMock = new ArrayList<>();
		personasMock.add(Persona.builder().nombre("Juan").apellido("Perez").empleado(true).dni("70971352").clave("asdasd").completo("Juan Perez").id(1).build());
		when(persistence.findAll()).thenReturn(personasMock);

		List<Persona> result = personaService.getPersonas();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("Juan", result.get(0).getNombre());
	}

	@Test
	void testGetPersonasConcatNames() {
		List<Persona> personasMock = new ArrayList<>();
		personasMock.add(Persona.builder().nombre("Juan").apellido("Perez").empleado(true).dni("70971352").clave("asdasd").completo("Juan Perez").id(1).build());
		when(persistence.findAll()).thenReturn(personasMock);

		List<Persona> result = personaService.getPersonasConcatNames();

		assertNotNull(result);
		assertEquals("JUAN PEREZ", result.get(0).getCompleto());
	}

	@Test
	void testGetPersonaById() {
		Persona personaMock = Persona.builder().nombre("Juan").apellido("Perez").empleado(true).dni("70971352").clave("asdasd").completo("Juan Perez").id(1).build();
		when(persistence.findById(1)).thenReturn(Optional.of(personaMock));

		List<Persona> result = personaService.getPersonaById(1);

		assertNotNull(result);
		assertEquals("Juan", result.get(0).getNombre());
	}

	@Test
	void testAddPersona() throws JsonProcessingException {
		String jsonPersona = "{\"nombre\":\"Juan\", \"apellido\":\"Perez\", \"dni\":\"12345678\", \"empleado\":true}";
		Persona personaMock = Persona.builder().nombre("Juan").apellido("Perez").empleado(true).dni("70971352").clave("asdasd").completo("Juan Perez").id(1).build();
		when(persistence.save(any(Persona.class))).thenReturn(personaMock);

		String result = personaService.addPersona(jsonPersona);

		assertTrue(result.contains("\"nombre\":\"Juan\""));
	}

/*
	@Test
	void testGenerarClave() {
		Persona personaMock = Persona.builder().nombre("Juan").apellido("Perez").empleado(true).dni("70971352").clave("asdasd").completo("Juan Perez").id(1).build();
		when(persistence.findById(1)).thenReturn(Optional.of(personaMock));
		when(persistence.save(any(Persona.class))).thenReturn(personaMock);

		String result = personaService.generarClave(1);

		assertEquals(Utils.MENSAJE_CLAVE_GENERADA, result);
		verify(persistence, times(1)).save(any(Persona.class));
	}
*/

	@Test
	void testRemovePersona() {
		doNothing().when(persistence).deleteById(1);

		String result = personaService.removePersona(1);

		assertEquals(Utils.MENSAJE_DELETE, result);
	}

	@Test
	void testUpdatePersona() {
		Persona personaMock = Persona.builder().nombre("Juan").apellido("Perez").empleado(true).dni("70971352").clave("asdasd").completo("Juan Perez").id(1).build();
		when(persistence.findById(1)).thenReturn(Optional.of(personaMock));
		when(persistence.save(any(Persona.class))).thenReturn(personaMock);

		String result = personaService.updatePersona(1, "Juan", "Perez", "70971352", true);

		assertTrue(result.contains("\"nombre\":\"Juan\""));
	}
}
