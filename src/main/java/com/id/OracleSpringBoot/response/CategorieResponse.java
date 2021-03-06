package com.id.OracleSpringBoot.response;

import java.util.ArrayList;
import java.util.List;

import com.id.OracleSpringBoot.entite.Livre;

public class CategorieResponse {
	private Integer id;

	private String codeCategorie;

	private String nomCategorie;

	private List<Livre> livre = new ArrayList<Livre>();

	public CategorieResponse() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeCategorie() {
		return codeCategorie;
	}

	public void setCodeCategorie(String codeCategorie) {
		this.codeCategorie = codeCategorie;
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public List<Livre> getLivre() {
		return livre;
	}

	public void setLivre(List<Livre> livre) {
		this.livre = livre;
	}

}
