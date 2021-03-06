package com.id.OracleSpringBoot.entite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Utilisateurs")
public class UserEntite {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nom;

	private String prenom;

	private String email;

	private String password;

	// Permettant d'activer ou désactiver le compte
	private boolean actived;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<RoleEntite> roles = new ArrayList<RoleEntite>();

	public UserEntite() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public List<RoleEntite> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntite> roles) {
		this.roles = roles;
	}

}
