package smartCity;

import java.util.ArrayList;

//5 different classes city,neighborhood,street,apartments and poles
//city has composite object of neighborhood with a list
//neighborhood has composite object of street data structure used will be a list
//street has 2 composite objects inside it,each will be used with the list data structure. Street has apartments and poles.
//apartments are primitive type which is observed by sensors
//poles are also primitive type which is observed by sensors
//apartment and pole are both special because they are observed they have to implement methods such as add observer,remove observer etc. 
//Sensors will be installed on apartments and poles.
//it will have a interface and abstract class which is used to reduce redundancy of code and also avoid redundancy.
//Each part has composite objects of the subtypes,the subtype checking to adding parts will be implemented inside the facade
//It is a has a relationship
//City has a neighborhood,street,apartments,poles
//Neighborhood has a street,apartment and pole
//Street has a apartment and pole
//Apartment and pole are both primitive they can't have any composite object


//We can have as much city parts as we want its open for extension

abstract class City {
	protected ArrayList<City> cityParts = new ArrayList<City>();
	protected String city_part_name;

	public City(String city_part_name) {
		this.city_part_name = city_part_name;
		cityParts.add(this);
	}

	public void displayParts() {
		System.out.println("Listing all city parts contained in this class\n");
		for (int i = 0; i < cityParts.size(); i++) {
			if (cityParts.get(i).getName().equalsIgnoreCase(this.getName())) {
				System.out.println("This parts name is " + cityParts.get(i).toString() + "\n");
			} else {
				System.out.println(i + ". part of this city part is\n" + cityParts.get(i).toString() + "\n");
			}
		}
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
		String report = "The city parts name is " + getName() + "\n";
		return report;
	}

	// primitive functions
	protected Sensor getSensor(String name) {
		return null;
	}

	protected ArrayList<Sensor> getAllSensors() {
		return null;
	}
	public void addSensor(Sensor sensor) {
		
	}
}

//Smart city is for city parts which have sensors in it
//It is a good practice to differentiate normal city parts with smart city parts
//If we want to add more smart city parts we only have to extend smartCity
//Therefore every class has a single resposibility
//And each smart city implements has a relationship,smart city parts has a sensor of each type
abstract class smartCity extends City {
	//there can be only 4 sensors,one of each type
    ArrayList<Sensor> attachedSensors=new ArrayList<Sensor>(4);
	public smartCity(String city_part_name) {
		super(city_part_name);
	}
	public void addSensor(Sensor sensor) {
		if(attachedSensors.size()<4) {
			attachedSensors.add(sensor);
		}else {
			System.out.println("The number of sensors cannot exceed the maximum value 4\n");
		}
	}
	protected ArrayList<Sensor>getAllSensors(){
		return attachedSensors;
	}
	
	protected Sensor getSensor(String name) {
		Sensor foundSensor=null;
		boolean found=false;
		for(int i=0;i<attachedSensors.size();i++) {
			if(attachedSensors.get(i).getName().equalsIgnoreCase(name)) {
				foundSensor=attachedSensors.get(i);
				found=true;
			}
		}
		if(found) {
			System.out.println("Sensor with the name "+name+" found\n");
		}else {
			System.out.println("Couldn't find the sensor with the name "+name+"\n");
		}
		return foundSensor;
	}
	

}

class mainCity extends City {
	public mainCity(String city_part_name) {
		super("City " + city_part_name);
		// TODO Auto-generated constructor stub
	}

}

class Neighborhood extends City {

	public Neighborhood(String city_part_name) {
		super("Neighborhood " + city_part_name);
		// TODO Auto-generated constructor stub
	}

}

class Street extends City {

	public Street(String city_part_name) {
		super("Street " + city_part_name);
		// TODO Auto-generated constructor stub
	}

}

class Apartment extends smartCity {

	public Apartment(String city_part_name) {
		super("Apartment " + city_part_name);
		// TODO Auto-generated constructor stub
	}

}

class Poles extends smartCity {

	public Poles(String city_part_name) {
		super("Pole " + city_part_name);
		// TODO Auto-generated constructor stub
	}

}
interface valueObserver {
	public void update(observedValues observedValue);
}
interface observedValues {
	int getValue();

	void setValue(int value);

	String getType();

	void displayObserved();

	void Notify();

	void displaySensors();

	void Attach(Sensor sensor);

	void Detach();
}

class temperatureValue implements observedValues {
	private int value;
	private String type;
	private Sensor sensor;

	public temperatureValue(int spesificValue) {
		this.type = "Temperature";
		this.value=spesificValue;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public void setValue(int value) {
		this.value = value;
		Notify();
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void Notify() {
		sensor.update(this);
	}

	@Override
	public void displaySensors() {
		sensor.displaySensor();
	}

	@Override
	public void Attach(Sensor sensor) {
		this.sensor = sensor;

	}

	@Override
	public void Detach() {
		sensor = null;
	}

	@Override
	public void displayObserved() {
		System.out.println(
				"The type of the observed value is " + this.getType() + " and the current value is " + this.getValue()+"\n");

	}

}

class pollutionValue implements observedValues {
	private int value;
	private String type;
	private Sensor sensor;

	public pollutionValue(int spesificValue) {
		this.type = "Pollution";
		this.value=spesificValue;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public void setValue(int value) {
		this.value = value;
		Notify();
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void Notify() {
		sensor.update(this);
	}

	@Override
	public void displaySensors() {
		sensor.displaySensor();
	}

	@Override
	public void Attach(Sensor sensor) {
		this.sensor = sensor;

	}

	@Override
	public void Detach() {
		sensor = null;
	}

	public void displayObserved() {
		System.out.println(
				"The type of the observed value is " + this.getType() + " and the current value is " + this.getValue()+"\n");

	}

}

class noiseValue implements observedValues {
	private int value;
	private String type;
	private Sensor sensor;

	public noiseValue(int spesificValue) {
		this.type = "Noise";
		this.value=spesificValue;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public void setValue(int value) {
		this.value = value;
		Notify();
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void Notify() {
		sensor.update(this);
	}

	@Override
	public void displaySensors() {
		sensor.displaySensor();
	}

	@Override
	public void Attach(Sensor sensor) {
		this.sensor = sensor;

	}

	@Override
	public void Detach() {
		sensor = null;
	}

	public void displayObserved() {
		System.out.println(
				"The type of the observed value is " + this.getType() + " and the current value is " + this.getValue()+"\n");

	}

}

class congestionValue implements observedValues {
	private int value;
	private String type;
	private Sensor sensor;

	public congestionValue(int spesificValue) {
		this.type = "Congestion";
		this.value=spesificValue;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public void setValue(int value) {
		this.value = value;
		Notify();
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void Notify() {
		sensor.update(this);
	}

	@Override
	public void displaySensors() {
		sensor.displaySensor();
	}

	@Override
	public void Attach(Sensor sensor) {
		this.sensor = sensor;

	}

	@Override
	public void Detach() {
		sensor = null;
	}

	public void displayObserved() {
		System.out.println(
				"The type of the observed value is " + this.getType() + " and the current value is " + this.getValue()+"\n");

	}

}
