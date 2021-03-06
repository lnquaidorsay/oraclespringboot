package com.id.OracleSpringBoot.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.id.OracleSpringBoot.entite.Etudiant;
import com.id.OracleSpringBoot.request.EtudiantRequest;
import com.id.OracleSpringBoot.response.EtudiantResponse;
import com.id.OracleSpringBoot.service.IEtudiant;

@Controller
public class EtudiantController {
	@Autowired
	private IEtudiant etudiantService;

	@RequestMapping(value = "/ajoutetudiant", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<EtudiantResponse> ajouterNouveauEtudiant(@RequestBody EtudiantRequest etudiantRequest) {
		ModelMapper modelMapper = new ModelMapper();
		Etudiant etudDto = modelMapper.map(etudiantRequest, Etudiant.class);

		Etudiant etudiantEnregistre = etudiantService.sauvegarderEtudiant(etudDto);
		EtudiantResponse etudiantResponse = modelMapper.map(etudiantEnregistre, EtudiantResponse.class);
		return new ResponseEntity<EtudiantResponse>(etudiantResponse, HttpStatus.OK);
	}

}
