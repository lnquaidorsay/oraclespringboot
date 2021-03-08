package com.id.OracleSpringBoot.response;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {
	private Long id;
	private String nom;
	private String prenom;
	private String email;
	private String password;
	boolean actived;
	// private List<RoleEntite> roles = new ArrayList<RoleEntite>();
	private List<RoleEntiteResponse> roles = new ArrayList<RoleEntiteResponse>();

	public UserResponse() {
		super();
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

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<RoleEntiteResponse> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntiteResponse> roles) {
		this.roles = roles;
	}

}
