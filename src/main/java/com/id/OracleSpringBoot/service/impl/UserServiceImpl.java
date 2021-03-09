package com.id.OracleSpringBoot.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

		List<RoleEntite> mesRoles = new ArrayList<RoleEntite>();

		if (user.getRoles() != null) {

			for (RoleEntite role : user.getRoles()) {
				RoleEntite unRole = roleRepository.findByNom(role.getNom());
				mesRoles.add(unRole);
			}

			user.setRoles(mesRoles);

		}

		UserEntite newUser = userRepository.save(user);

		return newUser;
	}

	private void addUserRole(UserEntite user) {
		Set<RoleEntite> roles = new HashSet<RoleEntite>();
		RoleEntite roleUser = new RoleEntite();// initialisation du rôle ROLE_USER
		roleUser.setNom("USER");
		roles.add(roleUser);
		Set<RoleEntite> roleFromDB = extractRole_Java8(roles, roleRepository.getAllRolesStream());
		// user.set(roleFromDB);
	}

	private Set<RoleEntite> extractRole_Java8(Set<RoleEntite> rolesSetFromUser, Stream<RoleEntite> roleStreamFromDB) {
		// Collect UI role names
		Set<String> uiRoleNames = rolesSetFromUser.stream().map(RoleEntite::getNom)
				.collect(Collectors.toCollection(HashSet::new));
		// Filter DB roles
		return roleStreamFromDB.filter(role -> uiRoleNames.contains(role.getNom())).collect(Collectors.toSet());
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

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntite userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getPassword(), getGrantedAuthorities(userEntity));

		// return new User(userEntity.getEmail(), userEntity.getPassword(), new
		// ArrayList<>());
	}

	@Override
	public List<RoleEntite> getListRole(String email) {
		UserEntite user = userRepository.findByEmail(email);
		return userRepository.findAllUsersRole(user.getId().intValue());
	}

	private List<GrantedAuthority> getGrantedAuthorities(UserEntite user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<String> roleList = userRepository.findUsersRolesNative(user.getId());
		// Role role = user.getRole();
		for (String roleEntite : roleList) {
			authorities.add(new SimpleGrantedAuthority(roleEntite));
		}
		// authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		return authorities;
	}

}
