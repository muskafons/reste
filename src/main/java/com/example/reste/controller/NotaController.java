package com.example.reste.controller;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.reste.entity.Nota;
import com.example.reste.model.ModeloNota;
import com.example.reste.service.NotaService;


@RestController
@RequestMapping("api")
public class NotaController {

	@Autowired
	@Qualifier("servicio")
	NotaService notaService;

	@PutMapping("PUTnota") // Actualizando a la DB por medio de PUT
	// Se debe agregar en el BODY el ID!!!
	public boolean PUTNota(@RequestBody @Validated Nota nota) {
		return notaService.actualizar(nota);
	}

	@PostMapping("POSTnota") // Agregando a la DB por medio de POST!!!
	public boolean POSTNota(@RequestBody @Validated Nota nota) {
		return notaService.crear(nota);
	}

	@DeleteMapping("DELETEnota/{id}/{nombre}")
	public boolean DELETENota(@PathVariable("id") long id, @PathVariable("nombre") String nombre) {
		return notaService.borrar(nombre, id);
	}

	@DeleteMapping("DELETEnota2")
	public boolean DELETENota2(@RequestBody @Validated Nota nota) {
		return notaService.borrar(nota.getNombre(), nota.getId());
	}

//	@GetMapping("GETnota") //Agregando a la DB por medio de GET!!!
//	public boolean GETNota(
//			@RequestParam(name="nombre")String nombre,
//			@RequestParam(name="titulo")String titulo,
//			@RequestParam(name="contenido")String contenido
//			) {
//		
//		Nota nota = new Nota();
//		nota.setNombre(nombre);
//		nota.setTitulo(titulo);
//		nota.setContenido(contenido);
//		
//		return notaService.crear(nota);
//	}
	@GetMapping("GETnota/{nombre}/{titulo}/{contenido}") // Agregando a la DB por medio de GET!!!
	// MALA PRACTICA - GET solo es para obtener y no para meter
	public boolean GETNota(@PathVariable("nombre") String nombre, @PathVariable("titulo") String titulo,
			@PathVariable("contenido") String contenido) {
		Nota nota = new Nota();
		nota.setNombre(nombre);
		nota.setTitulo(titulo);
		nota.setContenido(contenido);

		return notaService.crear(nota);
	}

	@GetMapping("GETnotas") // obtiene todas las notas
	// MALA PRACTICA si son mas de 10000 notas...
	public List<ModeloNota> modeloNotas() {
		return notaService.obtener();
	}
	
	@GetMapping("GETnotasPage") // obtiene todas las notas
	// BUENA PRACTICA obtener por paginacion
	public List<ModeloNota> modeloNotasPagina(Pageable pageable) {
		return notaService.obtenerPorPaginacion(pageable);
	}

	@GetMapping("GETnotasNombre/{nombre}") // obtiene todas las notas por nombre
	public List<ModeloNota> modeloNotasByNombre(@PathVariable("nombre") String nombre) {
		return notaService.obtenerListaByNombre(nombre);
	}
	
	@GetMapping("GETnotasTitulo/{titulo}") // obtiene todas las notas por nombre
	public List<ModeloNota> modeloNotasByTitulo(@PathVariable("titulo") String titulo) {
		return notaService.obtenerListaByTitulo(titulo);
	}

//	@GetMapping("GETnota/{nombre}") // Buscando a la DB por medio del nombre
//	// DARA ERROR si hay mas de dos resultados con el mismo nombre
//	public ModeloNota GETNota(@PathVariable("nombre") String nombre) {
//
//		return notaService.obtenerByNombre(nombre);
//	}
	
	@GetMapping("GETnotaNombreTitulo/{nombre}/{titulo}") // Buscando a la DB por medio del nombre y titulo
	// DARA ERROR si hay mas de dos resultados con el mismo nombre y mismo titulo
	public ModeloNota GETNota(@PathVariable("nombre") String nombre,@PathVariable("titulo") String titulo) {
		return notaService.obtenerByNombreTitulo(nombre,titulo);
	}
	
	@GetMapping("GETnotaNombreId/{nombre}/{id}") // Buscando a la DB por medio del nombre y ID
	public ModeloNota GETNota(@PathVariable("nombre") String nombre,@PathVariable("id") long id) {
		return notaService.obtenerByNombreId(nombre,id);
	}

}
