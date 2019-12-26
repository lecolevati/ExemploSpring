package br.com.leandrocolevati.springExemplo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;

@Entity
@Table(name = "Filme")
@NamedNativeQuery(name="Filme.findFilmeIdGenero", query="SELECT f.idFilme, f.nomeFilme, g.codGenero, g.tipoGenero, f.genero FROM Filme f INNER JOIN Genero g ON f.genero = g.codGenero WHERE f.idFilme = ?1 AND g.codGenero = ?2", resultClass = Filme.class)
public class Filme {

	@Id
	@Column
	private int idFilme;

	@Column
	private String nomeFilme;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "genero")
	private Genero genero;

	public Filme() {
	}

	public Filme(int idFilme, String nomeFilme) {
		this.idFilme = idFilme;
		this.nomeFilme = nomeFilme;
	}

	public int getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(int idFilme) {
		this.idFilme = idFilme;
	}

	public String getNomeFilme() {
		return nomeFilme;
	}

	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

}
