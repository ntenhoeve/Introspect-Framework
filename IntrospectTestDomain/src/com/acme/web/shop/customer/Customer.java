package com.acme.web.shop.customer;

import nth.introspect.generic.titlebuilder.TitleBuilder;

public class Customer {
	private String givenName;
	private String familyName;
	private boolean bonusMember;

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFullName() {
		return new TitleBuilder().append(givenName).append(familyName)
				.toString();
	}

	public boolean isBonusMember() {
		return bonusMember;
	}

	public void setBonusMember(boolean bonusMember) {
		this.bonusMember = bonusMember;
	}
}