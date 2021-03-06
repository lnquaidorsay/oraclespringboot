package com.id.OracleSpringBoot.entite;

public enum PretStatut {
	OPEN("Open"), CLOSE("Close");

	private String status;

	PretStatut(String pretStatus) {
		this.status = pretStatus;
	}

	public String getStatus() {
		return status;
	}

	// Get all enums
	/*
	 * for(
	 * 
	 * PretStatut env:PretStatut.values()) { System.out.println(env.name() + " :: "
	 * + env.getStatus()); } OPEN :: Open CLOSE :: Close
	 */

	// Using enum constant reference
	/*
	 * String openStatus = PretStatut.OPEN.getStatus();
	 * 
	 * System.out.println(prodUrl); => Open
	 * https://howtodoinjava.com/java/enum/java-enum-string-example/
	 */
}
