package br.com.leandrocolevati.springExemplo.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the genero database table.
 * 
 */
@Entity
@Table(name="genero")
@NamedQuery(name="Genero.findAll", query="SELECT g FROM Genero g")
public class Genero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	private int codGenero;
	
	@Column
	private String tipoGenero;

	public Genero() {
	}
	
	public Genero(int codGenero, String tipoGenero) {
		this.codGenero = codGenero;
		this.tipoGenero = tipoGenero;
	}

	public int getCodGenero() {
		return this.codGenero;
	}

	public void setCodGenero(int codGenero) {
		this.codGenero = codGenero;
	}

	public String getTipoGenero() {
		return this.tipoGenero;
	}

	public void setTipoGenero(String tipoGenero) {
		this.tipoGenero = tipoGenero;
	}

}