package com.id.OracleSpringBoot.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.id.OracleSpringBoot.entite.UserEntite;
import com.id.OracleSpringBoot.repository.UserRepository;
import com.id.OracleSpringBoot.request.UserRequest;
import com.id.OracleSpringBoot.response.UserResponse;
import com.id.OracleSpringBoot.service.UserService;

@RequestMapping("/users")
@Controller
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	/*
	 * @PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE,
	 * MediaType.APPLICATION_JSON_VALUE }, produces = {
	 * MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) throws Exception {

		ModelMapper modelMapper = new ModelMapper();
		UserEntite userDto = modelMapper.map(userRequest, UserEntite.class);

		UserEntite createUser = userService.createUser(userDto);

		int id = createUser.getId().intValue();

		UserResponse userResponse = modelMapper.map(createUser, UserResponse.class);

		/*
		 * List<RoleEntite> roles = userRepository.findAllUsersRole(id);
		 * createUser.setRoles(roles);
		 * 
		 * UserResponse userResponse = modelMapper.map(createUser, UserResponse.class);
		 * 
		 * if (userResponse.getRole() != null) { for (RoleEntiteResponse role :
		 * userResponse.getRole()) { System.out.println("role : " + role.getNom()); } }
		 */

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);

	}

	/*
	 * @RequestMapping(method = RequestMethod.POST, produces = "application/json",
	 * consumes = "application/json") public ResponseEntity<UserResponse>
	 * createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {
	 * 
	 * ModelMapper modelMapper = new ModelMapper(); UserEntite userDto =
	 * modelMapper.map(userRequest, UserEntite.class);
	 * 
	 * UserEntite createUser = userService.createUser(userDto);
	 * 
	 * UserResponse userResponse = modelMapper.map(createUser, UserResponse.class);
	 * 
	 * return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	 * 
	 * }
	 */
}
