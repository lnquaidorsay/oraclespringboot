package com.id.OracleSpringBoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.id.OracleSpringBoot.entite.RoleEntite;
import com.id.OracleSpringBoot.entite.UserEntite;
import com.id.OracleSpringBoot.repository.RoleRepository;
import com.id.OracleSpringBoot.repository.UserRepository;
import com.id.OracleSpringBoot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserEntite createUser(UserEntite user) {
		UserEntite checkUser = userRepository.findByEmail(user.getEmail());

		if (checkUser != null)
			throw new RuntimeException("L'utilisateur existe deja en Base de donnée !");

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		UserEntite newUser = userRepository.save(user);

		if (user.getRoles() != null) {

			for (int i = 0; i < user.getRoles().size(); i++) {

				RoleEntite role = user.getRoles().get(i);
				ajoutRoleToUser(newUser, role);
			}

		}

		return newUser;
	}

	@Override
	public UserEntite getUser(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserEntite getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajoutRoleToUser(UserEntite user, RoleEntite roleName) {
		RoleEntite role = roleRepository.findByNom(roleName.getNom());
		user.getRoles().add(role);

	}

	@Override
	public RoleEntite saveRole(RoleEntite role) {
		// TODO Auto-generated method stub
		return null;
	}

}
