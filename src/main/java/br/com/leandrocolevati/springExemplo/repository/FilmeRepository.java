package br.com.leandrocolevati.springExemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.leandrocolevati.springExemplo.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Integer> {

	Filme findFilmeIdGenero(int idFilme, int codGenero);

}
