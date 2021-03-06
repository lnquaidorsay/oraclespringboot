package com.id.OracleSpringBoot.entite;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Livre")
public class Livre {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Livre_ID")
	private Integer id;

	private String titre;

	private String nomEditeur;

	private String isbn;

	private LocalDate datePublication;

	private LocalDate dateEnregistrement;

	private Integer nbExemplaires;

	private String auteur;

	@ManyToOne(optional = false)
	@JoinColumn(name = "CAT_CODE", referencedColumnName = "ID")
	private Categorie categorie;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "livre", cascade = CascadeType.ALL)
	private List<Pret> pret = new ArrayList<Pret>();

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

	public String getNomEditeur() {
		return nomEditeur;
	}

	public void setNomEditeur(String nomEditeur) {
		this.nomEditeur = nomEditeur;
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

	public List<Pret> getPret() {
		return pret;
	}

	public void setPret(List<Pret> pret) {
		this.pret = pret;
	}

}
