package deneme;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Abstract factory that defines each and every factory method that will be implemented differently
interface CityFactory{
	abstract public City createCityPart(String city_part_name);
}

//Concrete Factory for each type of a city part
//Each factory will be using thread safe singleton design pattern
//There cannot be more than one factory for each type,it is not needed and would be a waste of memory



class mainCityFactory implements CityFactory{
	private static volatile mainCityFactory staticCityPartFactory=null;
	private static final Lock locker = new ReentrantLock(); 
	private mainCityFactory() {}
	public static mainCityFactory newMainCityFactory() {
		if(staticCityPartFactory==null) {
			locker.lock();
			try {
				if(staticCityPartFactory==null) {
					staticCityPartFactory=new mainCityFactory();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticCityPartFactory;
	}
	@Override
	public City createCityPart (String city_part_name) {
		return new mainCity(city_part_name);
	}
}

class neighborhoodFactory implements CityFactory{
	private static volatile neighborhoodFactory staticCityPartFactory=null;
	private static final Lock locker = new ReentrantLock(); 
	private neighborhoodFactory() {}
	public static neighborhoodFactory newNeighborhoodFactory() {
		if(staticCityPartFactory==null) {
			locker.lock();
			try {
				if(staticCityPartFactory==null) {
					staticCityPartFactory=new neighborhoodFactory();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticCityPartFactory;
	}
	@Override
	public City createCityPart (String city_part_name) {
		return new Neighborhood(city_part_name);
	}
}

class streetFactory implements CityFactory{
	private static volatile streetFactory staticCityPartFactory=null;
	private static final Lock locker = new ReentrantLock(); 
	private streetFactory() {}
	public static streetFactory newStreetFactory() {
		if(staticCityPartFactory==null) {
			locker.lock();
			try {
				if(staticCityPartFactory==null) {
					staticCityPartFactory=new streetFactory();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticCityPartFactory;
	}
	@Override
	public City createCityPart (String city_part_name) {
		return new Street(city_part_name);
	}
}

class apartmentFactory implements CityFactory{
	private static volatile apartmentFactory staticCityPartFactory=null;
	private static final Lock locker = new ReentrantLock(); 
	private apartmentFactory() {}
	public static apartmentFactory newApartmentFactory() {
		if(staticCityPartFactory==null) {
			locker.lock();
			try {
				if(staticCityPartFactory==null) {
					staticCityPartFactory=new apartmentFactory();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticCityPartFactory;
	}
	@Override
	public City createCityPart (String city_part_name) {
		return new Apartment(city_part_name);
	}
}

class poleFactory implements CityFactory{
	private static volatile poleFactory staticCityPartFactory=null;
	private static final Lock locker = new ReentrantLock(); 
	private poleFactory() {}
	public static poleFactory newPoleFactory() {
		if(staticCityPartFactory==null) {
			locker.lock();
			try {
				if(staticCityPartFactory==null) {
					staticCityPartFactory=new poleFactory();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticCityPartFactory;
	}
	@Override
	public City createCityPart (String city_part_name) {
		return new Poles(city_part_name);
	}
}

//Facade to easily initialize new objects with an easy to use interface
//It will be using thread safe singleton
//There can be only one facade interface,there is no need to make another facades,we can use every method with just one

class cityPartFactoryFacade{
	private mainCityFactory mainCity;
	private neighborhoodFactory neighborhood;
	private streetFactory street;
	private apartmentFactory apartment;
	private poleFactory pole;
	private static volatile cityPartFactoryFacade staticFacade=null;
	private static final Lock locker=new ReentrantLock();
	private ArrayList<City> allCityParts=new ArrayList<City>();

	
	public static cityPartFactoryFacade newFacade() {
		if(staticFacade==null) {
			locker.lock();
			try {
				if(staticFacade==null) {
					staticFacade=new cityPartFactoryFacade();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticFacade;
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
	public void createCityPart(CityFactory cityFac,String city_part_name){
		allCityParts.add(cityFac.createCityPart(city_part_name));	
	}
	void displaySensors() {
		for(int i=0;i<allCityParts.size();i++) {
			System.out.println("The name of the city part is "+allCityParts.get(i).getName());
		}	
	}
}
