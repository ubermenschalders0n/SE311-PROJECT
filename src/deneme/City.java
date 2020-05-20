package deneme;

import java.util.ArrayList;

abstract class City {
	protected ArrayList<City> cityParts = new ArrayList<City>();
	protected ArrayList<City> smartCityParts=new ArrayList<City>();
	protected String city_part_name;

	public City(String city_part_name) {
		this.city_part_name = city_part_name;
	}

	public void displayParts() {
		System.out.println("Listing all city parts contained in this class");
		cityParts.forEach(p -> System.out.println("\t" + p.getName()));
	}

	public String getName() {
		return city_part_name;
	}

	public void setName(String city_name) {
		this.city_part_name = city_name;

	}

	public ArrayList<City> getCityPart() {
		return cityParts;
	}

	public void addCityPart(City city) {
		cityParts.add(city);
	}

	
	public String toString() {
		String report="The name of the current city part is "+this.getName();
		return report;
	}
}



class mainCity extends City {
	public mainCity(String city_part_name) {
		super(city_part_name);
	}
}

class Neighborhood extends City {
	public Neighborhood(String city_part_name) {
		super(city_part_name);
	}
}

class Street extends City {

	public Street(String city_part_name) {
		super(city_part_name);
	}

}

class Apartment extends City {

	public Apartment(String city_part_name) {
		super(city_part_name);
	}

}

class Poles extends City {

	public Poles(String city_part_name) {
		super(city_part_name);
	}

}
