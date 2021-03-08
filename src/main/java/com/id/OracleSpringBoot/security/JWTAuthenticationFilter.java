package com.id.OracleSpringBoot.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.id.OracleSpringBoot.SpringApplicationContext;
import com.id.OracleSpringBoot.entite.UserEntite;
import com.id.OracleSpringBoot.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("**********************11111");
		UserEntite appUser = null;

		/*
		 * ObjectMapper permet de prendre des objets JSON et de les stocker dans un
		 * Objet Spring. request.getInputStream() est le contenu de la requête.
		 * AppUser.class : on le deserialise dans un objet de type UserEntite.
		 */
		try {
			appUser = new ObjectMapper().readValue(request.getInputStream(), UserEntite.class);
		} catch (IOException e) {
			new RuntimeException(e);
		}

		System.out.println("**********************");
		System.out.println("nom:" + appUser.getNom());
		System.out.println("email:" + appUser.getEmail());
		System.out.println("password:" + appUser.getPassword());

		return authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(appUser.getEmail(), appUser.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		/*
		 * authResult va nous permettre de récuprer l'utilisateur qui s'est authentifié
		 * getPrincipal nous retourne le nom de l'utilisateur authentitié getAuthorities
		 * nous retourne les roles de l'utilisateur
		 */
		User user = (User) authResult.getPrincipal();

		String userName = ((User) authResult.getPrincipal()).getUsername();

		System.out.println("username : " + userName);

		UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");

		UserEntite userDto = userService.getUser(userName);

		// String userName = ((User) authResult.getPrincipal()).getUsername();

		// J'ajoute l'ensemble des roles qui se trouvent dans authResult dans ma liste
		// roles
		List<String> roles = new ArrayList<String>();
		authResult.getAuthorities().forEach(a -> {
			roles.add(a.getAuthority());
		});

		String token = Jwts.builder().setSubject(userName).claim("id", userDto.getId())
				.claim("name", userDto.getNom() + " " + userDto.getPrenom())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET).compact();

		/*
		 * Creation du jwt withIssuer : le nom de l'autorité de l'application qui a
		 * généré le token
		 */
		String jwt = JWT.create().withIssuer(request.getRequestURI()).withSubject(user.getUsername())
				.withClaim("name", userDto.getNom() + " " + userDto.getPrenom())
				.withArrayClaim("roles", roles.toArray(new String[roles.size()]))
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.sign(Algorithm.HMAC256(SecurityConstants.TOKEN_SECRET));

		// Envoie du jwt dans la réponse http
		// response.addHeader(SecurityConstants.HEADER_STRING,
		// SecurityConstants.TOKEN_PREFIX + token);
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + jwt);
		// response.addHeader(SecurityConstants.HEADER_STRING, jwt);
		response.addHeader("user_id", userDto.getId().toString());

		response.getWriter().write("{\"token\": \"" + jwt + "\", \"nom\": \"" + userDto.getNom() + "\",\"id\": \""
				+ userDto.getId() + "\"}");
	}
}
