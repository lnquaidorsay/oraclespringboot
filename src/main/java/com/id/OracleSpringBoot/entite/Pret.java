package com.id.OracleSpringBoot.entite;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Pret")
public class Pret {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_Pret")
	private Integer id;

	private LocalDate debutPret;

	private LocalDate finPret;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUT")
	private PretStatut statut;

	@ManyToOne
	@JoinColumn(name = "livre")
	private Livre livre;

	@ManyToOne
	@JoinColumn(name = "etudiant")
	private Etudiant etudiant;

	public Pret() {
		super();
	}

	public Pret(LocalDate debutPret, LocalDate finPret, PretStatut statut, Livre livre, Etudiant etudiant) {
		super();
		this.debutPret = debutPret;
		this.finPret = finPret;
		this.statut = statut;
		this.livre = livre;
		this.etudiant = etudiant;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDebutPret() {
		return debutPret;
	}

	public void setDebutPret(LocalDate debutPret) {
		this.debutPret = debutPret;
	}

	public LocalDate getFinPret() {
		return finPret;
	}

	public void setFinPret(LocalDate finPret) {
		this.finPret = finPret;
	}

	public PretStatut getStatut() {
		return statut;
	}

	public void setStatut(PretStatut statut) {
		this.statut = statut;
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

}
