package com.example.reste.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.example.reste.entity.Nota;
import com.example.reste.model.ModeloNota;


@Component("convertidor")
public class ConvertidorNotaMNota {
	
	public List<ModeloNota> convertirListaModeloNota(List<Nota> notas){
		
		List<ModeloNota> modeloNotas = new ArrayList<>();
		
		for (Nota nota : notas) {
			modeloNotas.add(new ModeloNota(nota));
		}
		
		return modeloNotas;

	}

}
