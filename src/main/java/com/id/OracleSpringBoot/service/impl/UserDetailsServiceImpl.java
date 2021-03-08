package com.id.OracleSpringBoot.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.id.OracleSpringBoot.entite.UserEntite;
import com.id.OracleSpringBoot.repository.UserRepository;

/*
Si spring security veut savoir si un utilisateur existe ou pas
il appelera la méthode loadUserByUsername
*/

// Cette Classe va être instancier au démarrage de l'application en tant que
// service
//@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntite user = userRepository.findByEmail(username);
		if (user == null)
			throw new UsernameNotFoundException("Invalid user");
		// les rôles pour Spring Security sont des objets d'une Collection de type
		// GrantedAuthority
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(r -> {
			authorities.add(new SimpleGrantedAuthority(r.getNom()));
		});

		// On va retourner à Spring Security un objet de type User (un objet User de
		// Spring)
		return new User(user.getNom(), user.getPassword(), authorities);
	}

}
