package com.id.OracleSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.id.OracleSpringBoot.entite.RoleEntite;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntite, Long> {
	public RoleEntite findByNom(String nomRole);
}
