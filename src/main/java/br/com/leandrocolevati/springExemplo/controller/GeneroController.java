package br.com.leandrocolevati.springExemplo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.leandrocolevati.springExemplo.controller.exceptions.ResourceNotFoundException;
import br.com.leandrocolevati.springExemplo.model.Genero;
import br.com.leandrocolevati.springExemplo.repository.GeneroRepository;

@RestController
@RequestMapping("/api/v1")
public class GeneroController {

	@Autowired
	private GeneroRepository gRep;
	
	@GetMapping("/genero")
	public List<Genero> getAllGeneros() {
		return gRep.findAll();
	}

	private Genero findGenero(int codGenero) throws ResourceNotFoundException {
		Genero g = gRep.findById(codGenero)
				.orElseThrow(() -> new ResourceNotFoundException(codGenero + " não encontrado"));
		return g;
	}
	
	@GetMapping("/genero/{codGenero}")
	public ResponseEntity<Genero> getGenero(@PathVariable(value = "codGenero") int codGenero)
			throws ResourceNotFoundException {
		Genero g = findGenero(codGenero);
		return ResponseEntity.ok().body(g);
	}

	@PostMapping("/genero")
	public Genero createGenero(@Valid @RequestBody Genero g) {
		return gRep.save(g);
	}

	@PutMapping("/genero/{codGenero}")
	public ResponseEntity<Genero> updateGenero(@PathVariable(value = "codGenero") int codGenero,
			@Valid @RequestBody Genero gUpdt) throws ResourceNotFoundException {
		Genero g = findGenero(codGenero);

		g.setCodGenero(gUpdt.getCodGenero());
		g.setTipoGenero(gUpdt.getTipoGenero());
		final Genero UpdatG = gRep.save(g);
		return ResponseEntity.ok(UpdatG);
	}

	@DeleteMapping("/genero/{codGenero}")
	public Map<String, Boolean> deleteGenero(@PathVariable(value = "codGenero") int codGenero)
			throws ResourceNotFoundException {
		Genero g = findGenero(codGenero);

		gRep.delete(g);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Apagado", Boolean.TRUE);
		return response;
	}

}
