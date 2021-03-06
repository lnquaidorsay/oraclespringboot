package com.id.OracleSpringBoot.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.id.OracleSpringBoot.entite.Pret;

@Repository
public interface PretRepository extends JpaRepository<Pret, Integer> {
	public List<Pret> findByFinPretBefore(LocalDate maxEndDate);

	@Query("SELECT p " + "FROM Pret p " + "INNER JOIN Etudiant e "
			+ "WHERE p.etudiant = e.id and UPPER(e.email) = UPPER(?1) ")
	public List<Pret> recupererTousLesPretsDunEtudiant(String email);
}
