package smartCity;

import java.util.ArrayList;

public class Deneme {
	public static void main(String[] args) {
		ArrayList<Integer>burak=new ArrayList<Integer>(4);
		System.out.println(burak.size());
		for(int i=0;i<4;i++) {
			burak.add(0);
		}
		System.out.println(burak.size());
		/*City newCity = new concreteCity();
		newCity.setName("Allah");
		
		City firstCity=new concreteCity();
		firstCity.setName("Diyarbakır");
		newCity.addCityPart(firstCity);
		
		City secondCity=new concreteCity();
		secondCity.setName("Izmir");
		newCity.addCityPart(secondCity);
		
		City thirdCity=new concreteCity();
		thirdCity.setName("Corona");
		newCity.addCityPart(thirdCity);
		
		newCity.displayParts();
		
		City newStreet=new Street();
		newStreet.setName("Bora");
		
		City firstApartment=new Apartment();
		firstApartment.setName("Birinci");
		newStreet.addCityPart(firstApartment);
		
		City secondApartment=new Apartment();
		secondApartment.setName("Ikinci");
		newStreet.addCityPart(secondApartment);
		
		City thirdApartment=new Apartment();
		thirdApartment.setName("Üçüncü");
		newStreet.addCityPart(thirdApartment);
		
		City firstPole=new Poles();
		firstPole.setName("First pole");
		newStreet.addCityPart(firstPole);
		
		
		newStreet.displayParts();*/

	}
}
