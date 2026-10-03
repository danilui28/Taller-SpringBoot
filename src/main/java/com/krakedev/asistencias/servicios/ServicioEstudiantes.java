package com.krakedev.asistencias.servicios;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.krakedev.asistencias.entidades.Estudiante;

@Service
public class ServicioEstudiantes {
	
	private ArrayList<Estudiante> estudiantes = new ArrayList<>();

	public Estudiante agregar(Estudiante estudiante) {
		Estudiante existente = buscarPorCedula(estudiante.getCedula());
		if(existente != null) {
			return null;
		}else {
			estudiantes.add(estudiante);
			return estudiante;
		}
	}
	
	public Estudiante buscarPorCedula(String cedula) {
		for(Estudiante c: estudiantes) {
			if(c.getCedula().equals(cedula)) {
				return c;
			}
		}
		return null;
	}
	
	public boolean eliminar(String cedula) {
		Estudiante estudiante = buscarPorCedula(cedula);
		if (estudiante != null) {
			estudiantes.remove(estudiante);
			return true;
		}else {
			return false;
		}
	}
	
	public Estudiante actualizar(String cedula, Estudiante nuevo) {
		Estudiante estudiante = buscarPorCedula(cedula);
		if(estudiante != null) {
			estudiante.setNombre(nuevo.getNombre());
			estudiante.setApellido(nuevo.getApellido());
		}
		return estudiante;
	}
	
	public ArrayList<Estudiante> listar() {
		return estudiantes;
	}
}
