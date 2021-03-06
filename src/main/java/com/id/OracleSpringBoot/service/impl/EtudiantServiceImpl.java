package com.id.OracleSpringBoot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.id.OracleSpringBoot.entite.Etudiant;
import com.id.OracleSpringBoot.entite.Pret;
import com.id.OracleSpringBoot.repository.EtudiantRepository;
import com.id.OracleSpringBoot.service.IEtudiant;

@Service
public class EtudiantServiceImpl implements IEtudiant {

	@Autowired
	private EtudiantRepository etudiantRepository;

	@Override
	public Etudiant sauvegarderEtudiant(Etudiant etudiant) {
		if (etudiant.getPret() != null) {

			for (int i = 0; i < etudiant.getPret().size(); i++) {

				Pret pretEtudiant = etudiant.getPret().get(i);
				pretEtudiant.setEtudiant(etudiant);
				etudiant.getPret().set(i, pretEtudiant);
			}

		}
		return etudiantRepository.save(etudiant);
	}

	@Override
	public Etudiant miseAjourEtudiant(Etudiant etudiant) {
		// TODO Auto-generated method stub
		Etudiant etudToMaj = recupererEtudiantParSonId(etudiant.getId());
		etudToMaj.setAdresse(etudiant.getAdresse());
		etudToMaj.setEmail(etudiant.getEmail());
		etudToMaj.setNom(etudiant.getNom());
		etudToMaj.setNumeroEtudiant(etudiant.getNumeroEtudiant());
		etudToMaj.setPrenom(etudiant.getPrenom());
		return sauvegarderEtudiant(etudToMaj);
	}

	@Override
	public void supprimerEtudiant(Integer etudiantId) {
		etudiantRepository.deleteById(etudiantId);

	}

	@Override
	public boolean checkIfIdexists(Integer id) {
		// TODO Auto-generated method stub
		return etudiantRepository.existsById(id);
	}

	@Override
	public Etudiant recupererEtudiantParEmail(String email) {
		// TODO Auto-generated method stub
		return etudiantRepository.findByEmailIgnoreCase(email);
	}

	@Override
	public List<Etudiant> recupererEtudiantParPrenom(String prenom) {
		// TODO Auto-generated method stub
		return etudiantRepository.findByPrenomIgnoreCase(prenom);
	}

	@Override
	public Etudiant recupererEtudiantParSonId(Integer etudiantId) {
		return etudiantRepository.findById(etudiantId).get();
	}

	@Override
	public Page<Etudiant> recupererListEtudiantPaginee(int begin, int end) {
		Pageable page = PageRequest.of(begin, end);
		return etudiantRepository.findAll(page);
	}

}
