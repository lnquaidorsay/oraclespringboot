package com.id.OracleSpringBoot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	/*
	 * Pour enlever l'erreur lié à intellij Au lieu d'injecter l'interface, on
	 * injecte un instance de la classe UserDetailsServiceImpl: private
	 * UserDetailsServiceImpl userDetailsService Dans mon cas je laisse l'erreur
	 * Remarque : avec les autres IDE, il n'y aurait pas cette erreur:
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * On utilise l'implementation userDetailsService pour gérer l'accès aux
		 * utilisateurs en encodant le password en bcrypt. Pour un utilisateur qui tente
		 * de s'autentifier il va recuperer les informations de l'utilisateur, s'il
		 * existe, en utilisant la methode loadUserByUsername de userDetailsService
		 */
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	protected JWTAuthenticationFilter getAuthenticationFilter() throws Exception {
		final JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll().anyRequest().authenticated()
				.and().addFilter(getAuthenticationFilter())
				.addFilter(new JWTAuthorizationFilter(authenticationManager())).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * 
	 * Pour utiliser une authentification basé sur Json Web Token On lui demande de
	 * plus générer le csrf (Utile contre les attaques CSRF) D'utiliser un
	 * authentification de type Stateless: On utilisera plus les sessions Il ne
	 * gardera pas la session de l'utilisateur en mémoire
	 * 
	 * // http.formLogin(); http.csrf().disable();
	 * http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.
	 * STATELESS);
	 * 
	 * // N'impose pas une authentification pour /users/ et register
	 * http.authorizeRequests().antMatchers(SecurityConstants.SIGN_UP_URL,
	 * "/register/**").permitAll();
	 * 
	 * // Pour la gestion des utilisateur et les rôles, il faut être ADMIN
	 * http.authorizeRequests().antMatchers("/appUsers/**",
	 * "/appRoles/**").hasAuthority("ADMIN");
	 * 
	 * http.authorizeRequests().anyRequest().authenticated();
	 * 
	 * // Ajout des filtres JWTAuthenticationFilter et JWTAutorisationFilter
	 * 
	 * }
	 */
}
