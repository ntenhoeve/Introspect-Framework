package nth.introspect.junit.layer5provider.validation;

import java.util.Locale;

import javax.validation.constraints.NotNull;

import nth.introspect.layer5provider.validation.ConstraintViolations;

public class Address {

	private static final String DUTCH_ZIP_CODE = "^[0-9]{4}[a-zA-Z]{2}$";
	private static final String FRENCH_ZIP_CODE = "^[0-9]{5}$";
	private Country country;
	private String street;
	private String zipCode;

	
	
	
	public Address(String street, String zipCode, Country country) {
		this.street = street;
		this.zipCode = zipCode;
		this.country = country;
	}


	public ConstraintViolations addressExistsValidation() {
		ConstraintViolations constraintViolations = new ConstraintViolations();
		if (!GoogleMapsClient.exists(this)) {
			constraintViolations.add("address does not exist", this);
		}
		return constraintViolations;
	}
	
	
	@NotNull
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public ConstraintViolations zipCodeValidation() {
		ConstraintViolations constraintViolations = new ConstraintViolations();
		if (country.getCode() == "NL"
				&& !zipCode.matches(DUTCH_ZIP_CODE)) {
			constraintViolations.add(
					"must contain 4 numbers followed by 2 letters", zipCode);
		} else if (country.getCode() == "FR"
				&& !zipCode.matches(FRENCH_ZIP_CODE)) {
			constraintViolations.add("must contain 5 numbers", zipCode);
		}
		// ETC
		return constraintViolations;
	}

}
