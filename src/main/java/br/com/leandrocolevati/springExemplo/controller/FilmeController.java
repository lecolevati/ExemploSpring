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
import br.com.leandrocolevati.springExemplo.model.Filme;
import br.com.leandrocolevati.springExemplo.repository.FilmeRepository;

@RestController
@RequestMapping("/api/v1")
public class FilmeController {

	@Autowired
	private FilmeRepository fRep;
	
	@GetMapping("/filme")
	public List<Filme> getAllFilmes() {
		return fRep.findAll();
	}

	private Filme findFilme(int idFilme, int codGenero) throws ResourceNotFoundException {
		
		Filme f = new Filme();
		f = fRep.findFilmeIdGenero(idFilme, codGenero);
		return f;
	}
	
	@GetMapping("/filme/{idFilme}/{codGenero}")
	public ResponseEntity<Filme> getFilme(@PathVariable(value = "idFilme") int idFilme, @PathVariable(value = "codGenero") int codGenero)
			throws ResourceNotFoundException {
		Filme f = findFilme(idFilme, codGenero);
		return ResponseEntity.ok().body(f);
	}

	@PostMapping("/filme")
	public Filme createFilme(@Valid @RequestBody Filme f) {
		return fRep.save(f);
	}

	@PutMapping("/filme/{idFilme}/{codGenero}")
	public ResponseEntity<Filme> updateFilme(@PathVariable(value = "idFilme") int idFilme,
			@PathVariable(value = "codGenero") int codGenero,
			@Valid @RequestBody Filme fUpdt) throws ResourceNotFoundException {
		Filme f = findFilme(idFilme, codGenero);

		f.setIdFilme(fUpdt.getIdFilme());
		f.setNomeFilme(fUpdt.getNomeFilme());
		final Filme UpdatF = fRep.save(f);
		return ResponseEntity.ok(UpdatF);
	}

	@DeleteMapping("/filme/{idFilme}/{codGenero}")
	public Map<String, Boolean> deleteGenero(@PathVariable(value = "idFilme") int idFilme, @PathVariable(value = "codGenero") int codGenero)
			throws ResourceNotFoundException {
		Filme f = findFilme(idFilme, codGenero);

		fRep.delete(f);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Apagado", Boolean.TRUE);
		return response;
	}

}
