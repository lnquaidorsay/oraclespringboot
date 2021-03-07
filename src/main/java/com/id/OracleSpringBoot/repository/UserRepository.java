package com.id.OracleSpringBoot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.id.OracleSpringBoot.entite.RoleEntite;
import com.id.OracleSpringBoot.entite.UserEntite;

@Repository
public interface UserRepository extends JpaRepository<UserEntite, Long> {
	UserEntite findByEmail(String email);

	@Modifying
	@Query(value = "SELECT ROLE_UTILISATEUR.NOM, ROLE_UTILISATEUR.ROLE_ID FROM ROLE_UTILISATEUR   INNER JOIN USERS_ROLES on users_roles.role_id = ROLE_UTILISATEUR.ROLE_ID   INNER JOIN UTILISATEURS on utilisateurs.utilisateur_id = USERS_ROLES.UTILISATEUR_ID  u WHERE UTILISATEURS.UTILISATEUR_ID = :userId", nativeQuery = true)
	List<RoleEntite> findAllUsersRole(@Param("userId") int userId);

}
