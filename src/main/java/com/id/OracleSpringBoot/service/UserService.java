package com.id.OracleSpringBoot.service;

import com.id.OracleSpringBoot.entite.RoleEntite;
import com.id.OracleSpringBoot.entite.UserEntite;

public interface UserService {
	UserEntite createUser(UserEntite user);

	UserEntite getUser(String email);

	UserEntite getUserByUserId(String userId);

	void ajoutRoleToUser(UserEntite user, RoleEntite roleName);

	RoleEntite saveRole(RoleEntite role);

}
