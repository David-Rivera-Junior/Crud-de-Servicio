package com.certificacion.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.certificacion.model.Servicio;
import com.certificacion.repository.IServicioRepository;

@Controller
@CrossOrigin
@RequestMapping("servicio") // se le asigna un nombre al controlado//
public class ServicioController {
	// sobreescriendo el repository para la manipulacion de datos de la base //
	@Autowired
	IServicioRepository rServicio;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public List<Servicio> getAll() {
		return (List<Servicio>) rServicio.findAll();
	}
// metodo para guardar el registro de la tabla servicio//
	@GetMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public HashMap<String, String> save(@RequestParam Long codigo, @RequestParam String nombre,
			@RequestParam Date fecha, @RequestParam String descripcion, @RequestParam String categoria) {
				//creando objeto de la clase animales//
		Servicio an = new Servicio();

		codigo = rServicio.count() + 5;

		an = new Servicio(codigo, nombre, fecha, descripcion, categoria);

		HashMap<String, String> jsonReturn = new HashMap<>();

		try {
			//Guardando objeto de la clase servicio
			rServicio.save(an);
			
			jsonReturn.put("Estado", "OK");
			jsonReturn.put("Mensaje", "Registro guardado");

			return jsonReturn;
			//Mensaje de error
		} catch (Exception e) {

			jsonReturn.put("Estado", "Error");
			jsonReturn.put("Mensaje", "Registro no guardado");

			return jsonReturn;
		}
	}

	@PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public HashMap<String, String> update(@RequestParam Long codigo, @RequestParam String nombre,
			@RequestParam String descripcion, @RequestParam String categoria) {

		Servicio an = new Servicio(codigo, nombre, descripcion, categoria);

		HashMap<String, String> jsonReturn = new HashMap<>();

		try {

			rServicio.save(an);

			jsonReturn.put("Estado", "OK");
			jsonReturn.put("Mensaje", "Registro guardado");
			//Retornando Mensaje
			return jsonReturn;
		} catch (Exception e) {

			jsonReturn.put("Estado", "Error");
			jsonReturn.put("Mensaje", "Registro no guardado");

			return jsonReturn;

		}
	}
	//Metodo para buscar codigo
	@PostMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Servicio getMethodName(@PathVariable Long id) {
		return rServicio.findById(id).get();
	}
	//Metodo para eliminar los registros
	@DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean delete(@PathVariable Long id) {
		Servicio an = rServicio.findById(id).get();
		rServicio.delete(an);
		return true;
	}

}
