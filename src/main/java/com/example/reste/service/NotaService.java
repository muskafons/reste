package com.example.reste.service;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.reste.converter.ConvertidorNotaMNota;
import com.example.reste.entity.Nota;
import com.example.reste.model.ModeloNota;
import com.example.reste.repository.IRepositorioNota;

@Service("servicio")
public class NotaService {
	
	private static final Log logger = LogFactory.getLog(NotaService.class);

	@Autowired
	@Qualifier("repositorio")
	private IRepositorioNota repositorioNota;

	@Autowired
	@Qualifier("convertidor")	
	private ConvertidorNotaMNota convertidorNotaMNota;

	public boolean crear(Nota nota) {
		try {
			repositorioNota.save(nota);
			logger.info("NOTA CREADA");
			return true;
		} catch (Exception e) {
			logger.error("ERROR EN LA NOTA");
			return false;
		}
	}

	public boolean actualizar(Nota nota) {
		try {
			//Si el id =0 o null, quiere decir que no existe dicha nota para actualizar...
			repositorioNota.save(nota);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean borrar(String nombre, long id) {
		try {
			Nota nota = repositorioNota.findByNombreAndId(nombre, id);
			repositorioNota.delete(nota);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<ModeloNota> obtener(){
		List<ModeloNota> modeloNotas = convertidorNotaMNota.convertirListaModeloNota(repositorioNota.findAll());
		return modeloNotas;
	}

	public List<ModeloNota> obtenerListaByNombre(String nombre) {
		List<ModeloNota> modeloNotas = convertidorNotaMNota.convertirListaModeloNota(repositorioNota.findByNombre(nombre));
		return modeloNotas;
	}
	
	public List<ModeloNota> obtenerListaByTitulo(String titulo){
		List<ModeloNota> modeloNotas = convertidorNotaMNota.convertirListaModeloNota(repositorioNota.findByTitulo(titulo));
		return modeloNotas;
	}
	
//	public ModeloNota obtenerByNombre(String nombre) {
//		return new ModeloNota(repositorioNota.findByNombre(nombre));
//	}
	
	public ModeloNota obtenerByNombreTitulo(String nombre, String titulo) {
		return new ModeloNota(repositorioNota.findByNombreAndTitulo(nombre, titulo));
	}
	
	public ModeloNota obtenerByNombreId(String nombre, long id) {
		return new ModeloNota(repositorioNota.findByNombreAndId(nombre, id));
	}
	
	public List<ModeloNota> obtenerPorPaginacion(Pageable pageable){
		return convertidorNotaMNota.convertirListaModeloNota(repositorioNota.findAll(pageable).getContent());
	}

}
