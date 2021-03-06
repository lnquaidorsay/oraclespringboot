package com.id.OracleSpringBoot.entite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Etudiant")
public class Etudiant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Etudiant_ID")
	private Integer id;
	private String nom;
	private String prenom;
	private String email;
	private int numeroEtudiant;
	private String adresse;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "etudiant", cascade = CascadeType.ALL)
	private List<Pret> pret = new ArrayList<Pret>();

	public Etudiant() {
		super();
	}

	public Etudiant(String nom, String prenom, String email, int numeroEtudiant, String adresse, List<Pret> pret) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.numeroEtudiant = numeroEtudiant;
		this.adresse = adresse;
		this.pret = pret;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNumeroEtudiant() {
		return numeroEtudiant;
	}

	public void setNumeroEtudiant(int numeroEtudiant) {
		this.numeroEtudiant = numeroEtudiant;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public List<Pret> getPret() {
		return pret;
	}

	public void setPret(List<Pret> pret) {
		this.pret = pret;
	}

}
