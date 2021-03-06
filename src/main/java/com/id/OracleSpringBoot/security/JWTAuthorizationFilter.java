package com.id.OracleSpringBoot.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		String header = req.getHeader(SecurityConstants.HEADER_STRING);

		System.out.println("**********************2222");

		System.out.println("**********************" + header);

		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.HEADER_STRING);

		if (token != null) {

			token = token.replace(SecurityConstants.TOKEN_PREFIX, "");

			String user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET).parseClaimsJws(token).getBody()
					.getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}

			return null;
		}

		return null;
	}

	/*
	 * @Override protected void doFilterInternal(HttpServletRequest request,
	 * HttpServletResponse response, FilterChain filterChain) throws
	 * ServletException, IOException { // J'autorise tous les domaines ?? m'envoyer
	 * des requ??tes response.addHeader("Access-Control-Allow-Origin", "*");
	 * 
	 * // Les domaines autoris??s peuvent m'envoyer les m??thodes put delete post get
	 * response.addHeader("Access-Control-Allow-Methods",
	 * "GET, POST, PUT, DELETE, PATCH");
	 * 
	 * // Je t'autorise ?? m'envoyer ces ent??tes
	 * response.addHeader("Access-Control-Allow-Headers",
	 * "Origin, Accept, X-Request-Width, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, authorization"
	 * );
	 * 
	 * // Je t'autorise ?? lire ces ent??tes
	 * response.addHeader("Access-Control-Expose-Headers",
	 * "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization"
	 * );
	 * 
	 * 
	 * Si une requ??te est envoy?? avec OPTIONS, ou avec /login pour
	 * l'authentification on n'utilise pas les r??gles de securit??s definis ici.
	 * 
	 * if (request.getMethod().equals("OPTIONS")) {
	 * response.setStatus(HttpServletResponse.SC_OK); } else if
	 * (request.getRequestURI().equals("/login")) { filterChain.doFilter(request,
	 * response); return; } else {
	 * 
	 * On va regarder dans l'objet request s'il contient un header qui s'appelle
	 * Authorization. Recup??ration du token jwt
	 * 
	 * String jwt = request.getHeader(SecurityConstants.HEADER_STRING);
	 * System.out.println("Header du request : " + jwt);
	 * 
	 * // si jwt est null ou ne commence pas par le prefix Bearer if (jwt == null ||
	 * !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {
	 * filterChain.doFilter(request, response); return; }
	 * 
	 * // On signe avec le m??me secret JWTVerifier verifier =
	 * JWT.require(Algorithm.HMAC256(SecurityConstants.TOKEN_SECRET)).build(); // On
	 * decode le JWT et on supprime le prefixe DecodedJWT decodedJWT =
	 * verifier.verify(jwt.substring(SecurityConstants.TOKEN_PREFIX.length()));
	 * 
	 * // Recuperation des r??les et du username String username =
	 * decodedJWT.getSubject(); // Les r??les sont un tableau de String. On sp??cifie
	 * que c'est une liste de // String List<String> roles =
	 * decodedJWT.getClaim("roles").asList(String.class); List<GrantedAuthority>
	 * authorities = new ArrayList<>(); roles.forEach(roleName -> {
	 * authorities.add(new SimpleGrantedAuthority(roleName)); });
	 * 
	 * // On transmet le username et le role afin que spring security puisse //
	 * autentifier l'utilisateur UsernamePasswordAuthenticationToken
	 * authenticatedUser = new UsernamePasswordAuthenticationToken(username, null,
	 * authorities);
	 * 
	 * 
	 * On charge ensuite l'utilisateur authentifi?? dans le contexte de Spring
	 * Security. Je dis ?? Spring security, l'utilisateur qui a envoy?? la requ??te,
	 * voila son identit??. Ainsi, il va connaitre le username et les roles de
	 * l'utilisateur.
	 * 
	 * SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	 * filterChain.doFilter(request, response);
	 * 
	 * }
	 * 
	 * }
	 */

}
