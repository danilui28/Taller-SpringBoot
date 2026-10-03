package com.krakedev.asistencias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.asistencias.entidades.Estudiante;
import com.krakedev.asistencias.servicios.ServicioEstudiantes;

public class ServicioEstudiantesTest {

	@Test
	public void agregarEstudianteNuevoTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		Estudiante estudiante = new Estudiante("1714616123", "Juan", "Perez");

		Estudiante resultado = servicio.agregar(estudiante);

		assertEquals(estudiante, resultado);
		assertEquals(1, servicio.listar().size());
	}

	@Test
	public void agregarEstudianteDuplicadoTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		Estudiante estudiante1 = new Estudiante("0801234567", "Luis", "Martinez");
		Estudiante estudiante2 = new Estudiante("0801234567", "Ana", "Castillo");

		servicio.agregar(estudiante1);

		Estudiante resultado = servicio.agregar(estudiante2);

		assertNull(resultado);
		assertEquals(1, servicio.listar().size());
	}

	@Test
	public void buscarPorCedulaExistenteTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		Estudiante estudiante = new Estudiante("1102345678", "Daniel", "Torres");

		servicio.agregar(estudiante);

		Estudiante resultado = servicio.buscarPorCedula("1102345678");

		assertEquals(estudiante, resultado);
		assertEquals("Daniel", resultado.getNombre());
	}

	@Test
	public void buscarPorCedulaInexistenteTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		Estudiante resultado = servicio.buscarPorCedula("0999999999");

		assertNull(resultado);
	}

	@Test
	public void listarEstudiantesTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		Estudiante estudiante1 = new Estudiante("1203456789", "Sofia", "Ramirez");
		Estudiante estudiante2 = new Estudiante("0604567890", "Miguel", "Vargas");

		servicio.agregar(estudiante1);
		servicio.agregar(estudiante2);

		assertEquals(2, servicio.listar().size());
		assertTrue(servicio.listar().contains(estudiante1));
		assertTrue(servicio.listar().contains(estudiante2));
	}

	@Test
	public void actualizarEstudianteExistenteTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		Estudiante estudiante = new Estudiante("1505678901", "Andrea", "Mendoza");

		servicio.agregar(estudiante);

		Estudiante nuevo = new Estudiante("9998887776", "Roberto", "Sanchez");

		Estudiante resultado = servicio.actualizar("1505678901", nuevo);

		assertEquals("1505678901", resultado.getCedula());
		assertEquals("Roberto", resultado.getNombre());
		assertEquals("Sanchez", resultado.getApellido());
	}

	@Test
	public void actualizarEstudianteInexistenteTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		Estudiante nuevo = new Estudiante("1306789012", "Gabriela", "Morales");

		Estudiante resultado = servicio.actualizar("1306789012", nuevo);

		assertNull(resultado);
	}

	@Test
	public void eliminarEstudianteExistenteTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		Estudiante estudiante = new Estudiante("0407890123", "Fernando", "Paredes");

		servicio.agregar(estudiante);

		boolean resultado = servicio.eliminar("0407890123");

		assertTrue(resultado);
		assertEquals(0, servicio.listar().size());
		assertNull(servicio.buscarPorCedula("0407890123"));
	}

	@Test
	public void eliminarEstudianteInexistenteTest() {
		ServicioEstudiantes servicio = new ServicioEstudiantes();

		boolean resultado = servicio.eliminar("0500000000");

		assertFalse(resultado);
		assertEquals(0, servicio.listar().size());
	}
}
