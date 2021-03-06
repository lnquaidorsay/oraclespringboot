package com.id.OracleSpringBoot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.id.OracleSpringBoot.entite.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Integer> {
	public Livre findByIsbnIgnoreCase(String isbn);

	public Livre findByTitre(String titre);

	public Optional<Livre> findById(Integer id);

	public List<Livre> findByTitreLikeIgnoreCase(String title);
}
