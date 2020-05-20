package smartCity;

import java.util.ArrayList;
import java.util.Random;

//Each methods name have been designed to tell what it does explicitly

//abstract class that implements Observer interface,all of the common used methods are defined here
//Two primitive functions are declared as abstract methods to implement in the concrete classes
//Template design pattern will be implemented in this class with a template method which has basic skeleton of the algorithm


//By implementing mandatory methods inside the abstract class
//We save a lot of time and the redundant code is drastically reduced
abstract class Sensor implements valueObserver {
	protected boolean broken = false;
	protected String sensor_name;
	protected City city;
	// observers which observes sensors
	protected ArrayList<Citizen> citizens = new ArrayList<Citizen>();
	// sensors each observe observedValues which are types of values
	// them and their internal values will be kept here
	protected observedValues observedValue;
	protected int spesificValue;
	protected String observedValueType;

	public ArrayList<Citizen> getCitizens() {
		return citizens;
	}

	public int getSpesificValue() {
		return spesificValue;
	}

	public Sensor(String sensor_name) {
		this.sensor_name = sensor_name;
	}

	public observedValues getObserved() {
		return observedValue;
	}

	public void setObservedValue(observedValues observedValue) {
		this.observedValue = observedValue;
		this.spesificValue = observedValue.getValue();
		this.observedValueType = observedValue.getType();
	}

	public String getName() {
		return sensor_name;
	}

	public void setName(String sensor_name) {
		this.sensor_name = sensor_name;
	}

	public void displaySensor() {
		this.report();
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	// Template method which defines the skeleton of algorithm with primitive
	// functions and implemented methods,overrides update of observer
	//Template methods algorithm changes depending on the concrete class
	//But the user shouldn't modify the existing code of the abstract class
	//Therefore usage of template method follows OCP

	public void update(observedValues observedValue) {
		this.observedValue = observedValue;
		this.displaySensor();
		this.getObserved().displayObserved();
		this.setSpesificValue(observedValue.getValue());
		this.getObserved().displayObserved();
		this.displaySensor();
	}

	// malfunction method which corrupts the sensor based on rng
	public void malfunction() {
		// Random number generator which generates a number between 0-100
		Random generator = new Random();
		int randomNumber = generator.nextInt(100);
		// if the random number is greater than 20 it will not malfunction else it will
		// malfunction
		if (randomNumber > 20) {
			broken = false;
		} else {
			broken = true;
		}
	}

	public boolean status() {
		return broken;
	}

	public void reset() {
		broken = false;
	}

	// Citizens observes sensors,their methods are defined here
	// Register the Observers
	public void Attach(Citizen person) {
		citizens.add(person);
	}

	// Unregister from the list of Observers.
	public void Detach(Citizen person) {
		for (int i = 0; i < citizens.size(); i++) {
			if (citizens.get(i).getCitizenName() == person.getCitizenName()) {
				citizens.remove(i);
				return;
			}
		}
	}

	// Notify the Observers.
	public void Notify() {
		// set argument to something that helps
		// tell the Observers what happened
		for (int i = 0; i < citizens.size(); i++) {
			citizens.get(i).update(this);
		}
	}

	// primitive functions that will be implemented in the concrete class
	public abstract void report();

	abstract void setSpesificValue(int spesificValue);
}

//Now we will implement concrete sensors for each type

//there are 4 types of sensor temperature,pollution,noise and congestion
//temperature sensor notifies when temperature falls below 0
//pollution sensor notifies when pollution AQI value is above 100
//noise sensor notifies when noise by desibel is above 85db.
//congestion sensor notifies when car speed by km/h is below 10km/h

//Each concrete class defines the primitive functions depending on its type
//Therefore the main algorithm will run by not depending on the concrete classes
//It is open for extension but closed for modification

//Creating another sensor type is just making another class that inherits Sensor
//As each method used in this project uses polymorphism
//No matter the type of the concrete classes 
//Every object will be the type of top most abstract class

class temperatureSensor extends Sensor {

	public temperatureSensor(String sensor_name) {
		super("Temperature " + sensor_name);
	}

	public void setSpesificValue(int spesificValue) {
		System.out.println("Updating the temperature value \n");
		if (broken) {
			System.out.println("The sensor is broken can't update the value\n");
			System.out.println("The old value is still persistent\n");
		} else {
			this.spesificValue = spesificValue;
			System.out.println("Temperature is updated succesfully\n");
			Notify();
		}
	}

	public void report() {
		System.out.println("Preparing report\n");
		if (broken) {
			System.out.println("The sensor is broken can't report\n");
		} else {
			System.out.println("The sensor is running okay\n");
			System.out.println("The type of the sensor is " + observedValueType+"\n");
			System.out.println("Temperature is right now " + spesificValue+"\n");
			System.out.println("The sensor is currently installed on " + this.getCity().getName() + "\n");
			System.out.println("There are totally " + this.getCity().getAllSensors().size()
					+ " sensors in that part of the city\n");
		}

	}

}

class pollutionSensor extends Sensor {

	public pollutionSensor(String sensor_name) {
		super("Pollution " + sensor_name);
	}

	public void setSpesificValue(int spesificValue) {
		System.out.println("Updating the pollution value \n");
		if (broken) {
			System.out.println("The sensor is broken can't update the value\n");
			System.out.println("The old value is still persistent\n");
		} else {
			this.spesificValue = spesificValue;
			System.out.println("Pollution is updated succesfully\n");
			Notify();
		}
		System.out.println("The sensor is currently installed on " + this.getCity().getName() + "\n");
		System.out.println(
				"There are totally " + this.getCity().getAllSensors().size() + " sensors in that part of the city\n");
	}

	public void report() {
		System.out.println("Preparing report\n");
		if (broken) {
			System.out.println("The sensor is broken can't report\n");
		} else {
			System.out.println("The sensor is running okay\n");
			System.out.println("The type of the sensor is " + observedValueType+"\n");
			System.out.println("Pollution is right now " + spesificValue+"\n");
			System.out.println("The sensor is currently installed on " + this.getCity().getName() + "\n");
			System.out.println("There are totally " + this.getCity().getAllSensors().size()
					+ " sensors in that part of the city\n");
		}

	}

}

class noiseSensor extends Sensor {

	public noiseSensor(String sensor_name) {
		super("Noise " + sensor_name);
	}

	public void setSpesificValue(int spesificValue) {
		System.out.println("Updating the noise value \n");
		if (broken) {
			System.out.println("The sensor is broken can't update the value\n");
			System.out.println("The old value is still persistent\n");
		} else {
			this.spesificValue = spesificValue;
			System.out.println("Noise is updated succesfully\n");
			Notify();
		}
		System.out.println("The sensor is currently installed on " + this.getCity().getName() + "\n");
		System.out.println(
				"There are totally " + this.getCity().getAllSensors().size() + " sensors in that part of the city\n");
	}

	public void report() {
		System.out.println("Preparing report\n");
		if (broken) {
			System.out.println("The sensor is broken can't report\n");
		} else {
			System.out.println("The sensor is running okay\n");
			System.out.println("The type of the sensor is " + observedValueType+"\n");
			System.out.println("Noise is right now " + spesificValue+"\n");
			System.out.println("The sensor is currently installed on " + this.getCity().getName() + "\n");
			System.out.println("There are totally " + this.getCity().getAllSensors().size()
					+ " sensors in that part of the city\n");
		}

	}

}

class congestionSensor extends Sensor {

	public congestionSensor(String sensor_name) {
		super("Congestion " + sensor_name);
	}

	public void setSpesificValue(int spesificValue) {
		System.out.println("Updating the temperature value \n");
		if (broken) {
			System.out.println("The sensor is broken can't update the value\n");
			System.out.println("The old value is still persistent\n");
		} else {
			this.spesificValue = spesificValue;
			System.out.println("Congestion is updated succesfully\n");
			Notify();
		}
	}

	public void report() {
		System.out.println("Preparing report\n");
		if (broken) {
			System.out.println("The sensor is broken can't report\n");
		} else {
			System.out.println("The sensor is running okay\n");
			System.out.println("The type of the sensor is " + observedValueType+"\n");
			System.out.println("Congestion is right now " + spesificValue+"\n");
			System.out.println("The sensor is currently installed on " + this.getCity().getName() + "\n");
			System.out.println("There are totally " + this.getCity().getAllSensors().size()
					+ " sensors in that part of the city\n");
		}
	}
}
