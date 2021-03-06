package com.id.OracleSpringBoot.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
	@NotBlank(message = "Ce champ ne doit etre null !")
	private String nom;

	@NotNull(message = "Ce champ ne doit etre null !")
	@Size(min = 3, message = "Ce champ doit avoir au moins 3 Caracteres !")
	private String prenom;

	@NotNull(message = "Ce champ ne doit etre null !")
	@Email(message = "ce champ doit respecter le format email !")
	private String email;

	@NotNull(message = "Ce champ ne doit etre null !")
	@Size(min = 8, message = "mot de passe doit avoir au moins 8 caracteres !")
	@Size(max = 12, message = "mot de passe doit avoir au max 12 caracteres !")
	@Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "ce mot de passe doit avoir des lettres en Maj et Minsc et numero")
	private String password;

	@NotNull(message = "Ce champ ne doit etre null !")
	private boolean actived;

	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
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

}