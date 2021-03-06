package com.id.OracleSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.id.OracleSpringBoot.entite.UserEntite;

@Repository
public interface UserRepository extends JpaRepository<UserEntite, Long> {
	UserEntite findByEmail(String email);

	UserEntite findByUserId(String userId);

	/*
	 * @Query(value =
	 * "SELECT * FROM utilisateurs u WHERE (u.prenom LIKE %:search% OR u.nom LIKE %:search%) AND u.email_verification_status = :status"
	 * , nativeQuery = true) Page<UserEntite> findAllUserByCriteria(Pageable
	 * pageableRequest, @Param("search") String search,
	 * 
	 * @Param("status") int status);
	 */
}
