package com.id.OracleSpringBoot.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.id.OracleSpringBoot.entite.Livre;
import com.id.OracleSpringBoot.repository.LivreRepository;
import com.id.OracleSpringBoot.service.ILivre;

@Service
public class LivreServiceImpl implements ILivre {

	@Autowired
	private LivreRepository livreRepository;

	@Override
	public List<Livre> recupererTousLivres() {
		// TODO Auto-generated method stub
		return livreRepository.findAll();
	}

	@Override
	public Livre sauvegarderLivre(Livre livre) {
		// TODO Auto-generated method stub
		Livre newLivre = livreRepository.save(livre);
		return newLivre;
	}

	@Override
	public Livre recupererLivreParId(Livre livre) {
		Livre existLivre = livreRepository.findById(livre.getId()).get();
		return existLivre;
	}

	@Override
	public Livre miseAjourLivre(Livre livre) {
		Livre livreToMaj = recupererLivreParId(livre);
		livreToMaj.setAuteur(livre.getAuteur());
		livreToMaj.setTitre(livre.getTitre());
		livreToMaj.setIsbn(livre.getIsbn());
		livreToMaj.setDatePublication(livre.getDatePublication());
		livreToMaj.setDateEnregistrement(LocalDate.now());
		livreToMaj.setNbExemplaires(livre.getNbExemplaires());
		livreToMaj.setCategorie(livre.getCategorie());
		return sauvegarderLivre(livreToMaj);
	}

	@Override
	public void supprimerLivre(Integer idLivre) {
		livreRepository.deleteById(idLivre);

	}

	@Override
	public void supprimerUnLivre(Livre livre) {
		// TODO Auto-generated method stub
		livreRepository.deleteById(livre.getId());

	}

	@Override
	public List<Livre> chercherLivreParSonTitreOuPartieTitre(String titre) {
		List<Livre> listeResultat = new ArrayList<Livre>();
		List<Livre> myList = livreRepository
				.findByTitreLikeIgnoreCase((new StringBuilder()).append("%").append(titre).append("%").toString());
		for (int i = 0; i < myList.size(); i++) {
			listeResultat.add(myList.get(i));
		}
		return listeResultat;
	}

	@Override
	public Livre chercherLivreParIsbn(String isbn) {
		// TODO Auto-generated method stub
		return livreRepository.findByIsbnIgnoreCase(isbn);
	}

	@Override
	public boolean verifierSiIdExist(Integer id) {
		return livreRepository.existsById(id);
	}

	@Override
	public List<Livre> recupererLivreParCategorie(String codeCategory) {
		// TODO Auto-generated method stub
		return null;
	}

}
