package com.id.OracleSpringBoot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.id.OracleSpringBoot.entite.Etudiant;

public interface IEtudiant {
	public Etudiant sauvegarderEtudiant(Etudiant etudiant);

	public Etudiant miseAjourEtudiant(Etudiant etudiant);

	public void supprimerEtudiant(Integer etudiantId);

	public boolean checkIfIdexists(Integer id);

	public Etudiant recupererEtudiantParEmail(String email);

	public List<Etudiant> recupererEtudiantParPrenom(String prenom);

	public Etudiant recupererEtudiantParSonId(Integer etudiantId);

	public Page<Etudiant> recupererListEtudiantPaginee(int begin, int end);
}
