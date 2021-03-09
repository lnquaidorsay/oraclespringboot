package com.id.OracleSpringBoot.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.id.OracleSpringBoot.entite.RoleEntite;
import com.id.OracleSpringBoot.entite.UserEntite;

public interface UserService extends UserDetailsService {
	UserEntite createUser(UserEntite user);

	UserEntite getUser(String email);

	UserEntite getUserByUserId(String userId);

	List<RoleEntite> getListRole(String email);

	void ajoutRoleToUser(UserEntite user, RoleEntite roleName);

	RoleEntite saveRole(RoleEntite role);

}
