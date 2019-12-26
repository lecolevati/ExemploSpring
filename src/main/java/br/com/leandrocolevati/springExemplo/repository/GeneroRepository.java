package br.com.leandrocolevati.springExemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.leandrocolevati.springExemplo.model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {

}
