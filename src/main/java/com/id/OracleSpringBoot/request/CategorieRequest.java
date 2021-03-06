package com.id.OracleSpringBoot.request;

import java.util.ArrayList;
import java.util.List;

import com.id.OracleSpringBoot.entite.Livre;

public class CategorieRequest {
	private String codeCategorie;

	private String nomCategorie;

	private List<Livre> livre = new ArrayList<Livre>();

	public CategorieRequest() {
		super();
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
