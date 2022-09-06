package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Persona;
import com.uce.edu.demo.service.IPersonaService;

@Controller
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	private IPersonaService iPersonaService;

	// GET BUSCAR
	@GetMapping("/buscar")
	public String buscarTodos(Model modelo) {
		List<Persona> lista = this.iPersonaService.buscarTodos();
		modelo.addAttribute("personas", lista);
		return "VistaListaPersonas";
	}

	// GET BUSCAR
	@GetMapping("/buscarUno/{idPersona}")
	public String buscarPersona(@PathVariable("idPersona") Integer id, Model modelo) {
		System.out.println("El ID es: " + id);
		Persona p = this.iPersonaService.buscarPorId(id);
		modelo.addAttribute("persona", p);
		return "vistaPersona";
	}

	// PUT ACTUALIZAR
	@PutMapping("/actualizar/{idPersona}")
	public String actualizarPersona(@PathVariable("idPersona") Integer id, Persona persona) {
		persona.setId(id);
		this.iPersonaService.actualizar(persona);
		return "redirect:/personas/buscar";
	}

	//DELETE BORRAR
	@DeleteMapping("/borrar/{idPersona}")
	public String borrarPersona(@PathVariable("idPersona") Integer id) {
		this.iPersonaService.eliminar(id);
		return "redirect:/personas/buscar";
	}
	
	@PostMapping("/insertar")
	public String insertarPersona(Persona persona) {
		this.iPersonaService.guardar(persona);
		return "redirect:/personas/buscar";
	}
	
	//Metodos de redireccionamiento a paginas
	@GetMapping("/nuevaPersona")
	public String paginaNuevaPersona(Persona persona) {
		return "vistaNuevaPersona";
	}
}
