package com.id.OracleSpringBoot.service;

import java.util.List;

import com.id.OracleSpringBoot.entite.Livre;

public interface ILivre {
	public List<Livre> recupererTousLivres();

	public Livre sauvegarderLivre(Livre livre);

	public Livre recupererLivreParId(Livre livre);

	public Livre miseAjourLivre(Livre livre);

	public void supprimerLivre(Integer idLivre);

	public void supprimerUnLivre(Livre livre);

	public List<Livre> chercherLivreParSonTitreOuPartieTitre(String titre);

	public Livre chercherLivreParIsbn(String isbn);

	public boolean verifierSiIdExist(Integer id);

	public List<Livre> recupererLivreParCategorie(String codeCategory);
}
