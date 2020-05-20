package smartCity;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Abstract factory that defines each and every factory method that will be implemented differently
interface CityFactory {
	abstract public City createCityPart(String city_part_name);
}

//Concrete Factory for each type of a city part
//Each factory will be using thread safe singleton design pattern
//There cannot be more than one factory for each type,it is not needed and would be a waste of memory



class mainCityFactory implements CityFactory {
	private static volatile mainCityFactory staticCityPartFactory = null;
	private static final Lock locker = new ReentrantLock();

	private mainCityFactory() {
	}

	public static mainCityFactory newMainCityFactory() {
		if (staticCityPartFactory == null) {
			locker.lock();
			try {
				if (staticCityPartFactory == null) {
					staticCityPartFactory = new mainCityFactory();
				}
			} finally {
				locker.unlock();
			}
		}
		return staticCityPartFactory;
	}

	@Override
	public City createCityPart(String city_part_name) {
		return new mainCity(city_part_name);
	}
}

class neighborhoodFactory implements CityFactory {
	private static volatile neighborhoodFactory staticneighborhoodPartFactory = null;
	private static final Lock locker = new ReentrantLock();

	private neighborhoodFactory() {
	}

	public static neighborhoodFactory newNeighborhoodFactory() {
		if (staticneighborhoodPartFactory == null) {
			locker.lock();
			try {
				if (staticneighborhoodPartFactory == null) {
					staticneighborhoodPartFactory = new neighborhoodFactory();
				}
			} finally {
				locker.unlock();
			}
		}
		return staticneighborhoodPartFactory;
	}

	@Override
	public City createCityPart(String city_part_name) {
		return new Neighborhood(city_part_name);
	}
}

class streetFactory implements CityFactory {
	private static volatile streetFactory staticStreetPartFactory = null;
	private static final Lock locker = new ReentrantLock();

	private streetFactory() {
	}

	public static streetFactory newStreetFactory() {
		if (staticStreetPartFactory == null) {
			locker.lock();
			try {
				if (staticStreetPartFactory == null) {
					staticStreetPartFactory = new streetFactory();
				}
			} finally {
				locker.unlock();
			}
		}
		return staticStreetPartFactory;
	}

	@Override
	public City createCityPart(String city_part_name) {
		return new Street(city_part_name);
	}
}

class apartmentFactory implements CityFactory {
	private static volatile apartmentFactory staticApartmentPartFactory = null;
	private static final Lock locker = new ReentrantLock();
	private apartmentFactory() {
	}

	public static apartmentFactory newApartmentFactory() {
		if (staticApartmentPartFactory == null) {
			locker.lock();
			try {
				if (staticApartmentPartFactory == null) {
					staticApartmentPartFactory = new apartmentFactory();
				}
			} finally {
				locker.unlock();
			}
		}
		return staticApartmentPartFactory;
	}

	@Override
	public City createCityPart(String city_part_name) {
		return new Apartment(city_part_name);
	}
}

class poleFactory implements CityFactory {
	private static volatile poleFactory staticPolePartFactory = null;
	private static final Lock locker = new ReentrantLock();

	private poleFactory() {
	}

	public static poleFactory newPoleFactory() {
		if (staticPolePartFactory == null) {
			locker.lock();
			try {
				if (staticPolePartFactory == null) {
					staticPolePartFactory = new poleFactory();
				}
			} finally {
				locker.unlock();
			}
		}
		return staticPolePartFactory;
	}

	@Override
	public City createCityPart(String city_part_name) {
		return new Poles(city_part_name);
	}
}

//Facade to easily initialize new objects with an easy to use interface
//It will be using thread safe singleton
//There can be only one facade interface,there is no need to make another facades,we can use every method with just one

//We are using facade to give an interface to the user,which makes creating objects really easy for them
//they don't have to know the internal values of each object they just give values that we say

class cityPartFactoryFacade{
	private mainCityFactory mainCity;
	private neighborhoodFactory neighborhood;
	private streetFactory street;
	private apartmentFactory apartment;
	private poleFactory pole;
	private static volatile cityPartFactoryFacade staticCityFacade=null;
	private static final Lock locker=new ReentrantLock();
	private ArrayList<City> allCityParts=new ArrayList<City>();

	
	public static cityPartFactoryFacade newFacade() {
		if(staticCityFacade==null) {
			locker.lock();
			try {
				if(staticCityFacade==null) {
					staticCityFacade=new cityPartFactoryFacade();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticCityFacade;
	}
	
	private cityPartFactoryFacade() {
		mainCity=mainCityFactory.newMainCityFactory();
		neighborhood=neighborhoodFactory.newNeighborhoodFactory();
		street=streetFactory.newStreetFactory();
		apartment=apartmentFactory.newApartmentFactory();
		pole=poleFactory.newPoleFactory();
	}
	public mainCityFactory getMainCityFactory() {
		return mainCity;
	}
	public neighborhoodFactory getNeighborhoodFactory() {
		return neighborhood;
	}
	public streetFactory getStreetFactory() {
		return street;
	}
	public apartmentFactory getApartmentFactory() {
		return apartment;
	}
	public poleFactory getPoleFactory() {
		return pole;
	}
	//Creates city part with type checking
	public City createCityPart(String city_part_name,String type){
		City newCityPart=null;
		if(type.equalsIgnoreCase("city")) {
			newCityPart=mainCity.createCityPart(city_part_name);
		}else if(type.equalsIgnoreCase("neighborhood")) {
			newCityPart=neighborhood.createCityPart(city_part_name);
		}else if(type.equalsIgnoreCase("street")) {
			newCityPart=street.createCityPart(city_part_name);
		}else if(type.equalsIgnoreCase("apartment")) {
			newCityPart=apartment.createCityPart(city_part_name);
		}else if(type.equalsIgnoreCase("pole")) {
			newCityPart=pole.createCityPart(city_part_name);
		}else {
			System.out.println("Illegal type");
		}
		allCityParts.add(newCityPart);
		return newCityPart;
	}
	//check types and addCity part accordingly
	public void addCityPartToCityPart(City addToCity,City addedCity) {
		if(addToCity.getName().contains("City")) {
			if(addedCity.getName().contains("Neighborhood") || addedCity.getName().contains("Street") 
				|| addedCity.getName().contains("Apartment") || addedCity.getName().contains("Pole")) {
				addToCity.addCityPart(addedCity);
			}else {
				System.out.println("You can only add neighborhood,street,apartment or poles to the city\n");
			}
		}else if(addToCity.getName().contains("Neighborhood")){
			if(addedCity.getName().contains("Street") 
					|| addedCity.getName().contains("Apartment") || addedCity.getName().contains("Pole")) {
					addToCity.addCityPart(addedCity);
				}else {
					System.out.println("You can only add street,apartment or poles to the neighborhood\n");
				}
		}else if(addToCity.getName().contains("Street")){
			if(addedCity.getName().contains("Apartment") || addedCity.getName().contains("Pole")) {
					addToCity.addCityPart(addedCity);
				}else {
					System.out.println("You can only add apartment or poles to the street\n");
				}
		}else if(addToCity.getName().contains("Apartment")){
			System.out.println("You cannot add anything to the apartment\n");
		}else if(addToCity.getName().contains("Pole")){
			System.out.println("You cannot add anything to the pole\n");
		}else {
			System.out.println("Illegal type\n");
		}
	}
	//Attaches city to all of the given sensors inside the ArrayList
	public void attachSensorListSmart(City smartCity,ArrayList<Sensor> sensorList) {
		locker.lock();
		for(int i=0;i<sensorList.size();i++) {
			smartCity.addSensor(sensorList.get(i));
		}
		locker.unlock();
	}
	//Displays city parts
	public void displayCityParts() {
	    allCityParts.forEach((value)->value.toString());	
	}
	//displays city parts of given city part
	public void displayCityPartsOfGivenPart(City city) {
		city.displayParts();
	}
}
