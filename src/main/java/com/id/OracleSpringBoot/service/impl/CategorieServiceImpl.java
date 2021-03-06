package com.id.OracleSpringBoot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.id.OracleSpringBoot.entite.Categorie;
import com.id.OracleSpringBoot.repository.CategorieRepository;
import com.id.OracleSpringBoot.service.ICategorie;

@Service
public class CategorieServiceImpl implements ICategorie {

	@Autowired
	private CategorieRepository categorieRepository;

	@Override
	public List<Categorie> recupererToutesCategories() {
		return categorieRepository.findAll();
	}

	@Override
	public Categorie chercherCategorieParCodeCat(String code) {
		return categorieRepository.findCategorieByCodeCategorie(code);
	}

}
