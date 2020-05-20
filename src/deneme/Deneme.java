package deneme;

public class Deneme {

	public static void main(String[] args) {
		cityPartFactoryFacade cityInterface=cityPartFactoryFacade.newFacade();
		
		apartmentFactory apartmentInterface=cityInterface.getApartmentFactory();
		
		streetFactory streetInterface=cityInterface.getStreetFactory();
		
		cityInterface.createCityPart(apartmentInterface,"Apartman bir");
		
		cityInterface.createCityPart(streetInterface, "Sokak bir");
		
		cityInterface.createCityPart(apartmentInterface, "Apartman 3");
		
		cityInterface.displaySensors();
		
		apartmentInterface.createCityPart("Apartman 2 ");
		
		cityInterface.displaySensors();
	}

}
