package com.id.OracleSpringBoot.service;

import java.util.List;

import com.id.OracleSpringBoot.entite.Categorie;

public interface ICategorie {
	public List<Categorie> recupererToutesCategories();

	public Categorie chercherCategorieParCodeCat(String code);
}
