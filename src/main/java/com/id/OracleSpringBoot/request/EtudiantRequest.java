package com.id.OracleSpringBoot.request;

import java.util.ArrayList;
import java.util.List;

import com.id.OracleSpringBoot.entite.Pret;

public class EtudiantRequest {
	private Integer id;
	private String nom;
	private String prenom;
	private String email;
	private int numeroEtudiant;
	private String adresse;
	private List<Pret> pret = new ArrayList<Pret>();

	public EtudiantRequest() {
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
