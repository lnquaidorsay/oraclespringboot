package com.id.OracleSpringBoot.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.id.OracleSpringBoot.entite.Categorie;

public class LivreRequest {
	private Integer id;

	@NotBlank(message = "Ce champ ne doit pas être null !")
	private String titre;

	@NotNull(message = "Ce champ ne doit pas être null !")
	@Size(min = 3, message = "Ce champ doit avoir au moins 3 Caracteres !")
	private String isbn;

	private LocalDate datePublication;

	private LocalDate dateEnregistrement;

	private Integer nbExemplaires;

	@NotNull(message = "Ce champ ne doit pas être null !")
	private String auteur;

	@NotNull(message = "Ce champ ne doit pas être null !")
	private Categorie categorie;

	public LivreRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public LocalDate getDatePublication() {
		return datePublication;
	}

	public void setDatePublication(LocalDate datePublication) {
		this.datePublication = datePublication;
	}

	public LocalDate getDateEnregistrement() {
		return dateEnregistrement;
	}

	public void setDateEnregistrement(LocalDate dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}

	public Integer getNbExemplaires() {
		return nbExemplaires;
	}

	public void setNbExemplaires(Integer nbExemplaires) {
		this.nbExemplaires = nbExemplaires;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

}
