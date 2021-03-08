package com.id.OracleSpringBoot.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.id.OracleSpringBoot.entite.RoleEntite;
import com.id.OracleSpringBoot.entite.UserEntite;

public interface UserService extends UserDetailsService {
	UserEntite createUser(UserEntite user);

	UserEntite getUser(String email);

	UserEntite getUserByUserId(String userId);

	void ajoutRoleToUser(UserEntite user, RoleEntite roleName);

	RoleEntite saveRole(RoleEntite role);

}
