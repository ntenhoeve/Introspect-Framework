package nth.reflect.ui.vaadin.tab.grid;

public class Address {

	private String nr;
	private String city;

	public Address(String nr, String city) {
		this.nr = nr;
		this.city = city;
	}

	@Override
	public String toString() {
		return nr + " " + city;
	}
}
