package com.id.OracleSpringBoot.service;

import java.time.LocalDate;
import java.util.List;

import com.id.OracleSpringBoot.entite.Pret;

public interface IPret {
	public Pret nouveauPret(Pret pret);

	public List<Pret> findAllLoansByEndDateBefore(LocalDate maxEndDate);

	public List<Pret> getAllOpenLoansOfThisStudent(String email);

	public Pret saveLoan(Pret pret);

	public void closeLoan(Pret pret);
}
