package com.id.OracleSpringBoot.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.id.OracleSpringBoot.entite.Etudiant;
import com.id.OracleSpringBoot.entite.Livre;
import com.id.OracleSpringBoot.entite.Pret;
import com.id.OracleSpringBoot.repository.EtudiantRepository;
import com.id.OracleSpringBoot.repository.LivreRepository;
import com.id.OracleSpringBoot.repository.PretRepository;
import com.id.OracleSpringBoot.service.IPret;

@Service
public class PretServiceImpl implements IPret {

	@Autowired
	private PretRepository pretRepository;

	@Autowired
	private EtudiantRepository etudiantRepository;

	@Autowired
	private LivreRepository livreRepository;

	@Override
	public List<Pret> findAllLoansByEndDateBefore(LocalDate maxEndDate) {
		// TODO Auto-generated method stub
		return pretRepository.findByFinPretBefore(maxEndDate);
	}

	@Override
	public List<Pret> getAllOpenLoansOfThisStudent(String email) {
		// TODO Auto-generated method stub
		return pretRepository.recupererTousLesPretsDunEtudiant(email);
	}

	@Override
	public Pret saveLoan(Pret pret) {
		return pretRepository.save(pret);
	}

	@Override
	public void closeLoan(Pret pret) {

	}

	@Override
	public Pret nouveauPret(Pret pret) {
		Etudiant et = pret.getEtudiant();
		Etudiant etudToSave = etudiantRepository.findByEmailIgnoreCase(et.getEmail());
		if (etudToSave != null) {
			pret.setEtudiant(etudToSave);
		} else {
			Etudiant etudSaved = etudiantRepository.save(et);
			pret.setEtudiant(etudSaved);
		}

		Livre liv = pret.getLivre();
		Livre livToSave = livreRepository.findByIsbnIgnoreCase(liv.getIsbn());
		if (livToSave != null) {
			pret.setLivre(livToSave);
		} else {
			Livre livSaved = livreRepository.save(liv);
			pret.setLivre(livSaved);
		}
		return pretRepository.save(pret);
	}

}
