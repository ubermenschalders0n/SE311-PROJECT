package smartCity;

import java.util.ArrayList;

//citizens subscribe to the sensors which means they are observing them
//sensors are both observer and observed because citizens observe sensors and sensors observe poles and aparments.
//observer

interface sensorObserver {
	public void update(Sensor sensor);
}

abstract class Citizen implements sensorObserver {
	protected String citizenName;
	protected ArrayList<Sensor> observedSensors = new ArrayList<Sensor>(); // internal object,all sensors citizen
																			// subscribed to
	protected ArrayList<City> allSensorsCityPart = new ArrayList<City>(); // internal value,sensors inside each city
																			// part
	protected ArrayList<Integer> allSensorValues = new ArrayList<Integer>(); // internal sensor values

	public Citizen(String citizenName) {
		this.citizenName = citizenName;
	}

	public ArrayList<Sensor> getObservedSensors() {
		return observedSensors;
	}

	public void addObservedSensor(Sensor sensor) {
		observedSensors.add(sensor);
		allSensorsCityPart.add(sensor.getCity());
		allSensorValues.add(sensor.getSpesificValue());
	}

	public String getCitizenName() {
		return citizenName;
	}
    //update function which checks if there are any violation of the rules
	//if so it notifies the citizen
	public void update(Sensor sensor) {

		System.out.println("Preparing report for the citizen "+citizenName);
		// check for violations
		if (sensor.getObserved().getType().equalsIgnoreCase("temperature") && sensor.getObserved().getValue() < 0) {
			System.out.println("Temperature is less than 0 degrees!\n");
			System.out.println("The new temperature is " + sensor.getObserved().getValue() + " degrees\n");
		} else if (sensor.getObserved().getType().equalsIgnoreCase("temperature")
				&& sensor.getObserved().getValue() >= 0) {
			System.out.println("The new temperature is " + sensor.getObserved().getValue() + " degrees\n");
		}
		if (sensor.getObserved().getType().equalsIgnoreCase("pollution") && sensor.getObserved().getValue()  > 100) {
			System.out.println("Pollution is greater than 100 AQI!\n");
			System.out.println("The new pollution is " + sensor.getObserved().getValue() + " AQI\n");
		} else if (sensor.getObserved().getType().equalsIgnoreCase("pollution")
				&& sensor.getObserved().getValue() <= 100) {
			System.out.println("The new pollution is " + sensor.getObserved().getValue() + " AQI\n");
		}
		if (sensor.getObserved().getType().equalsIgnoreCase("noise") && sensor.getObserved().getValue()  > 85) {
			System.out.println("Noise is greater than 85 desibels !\n");
			System.out.println("The new noise is " + sensor.getObserved().getValue() + " desibels\n");
		} else if (sensor.getObserved().getType().equalsIgnoreCase("noise") && sensor.getObserved().getValue() <= 85) {
			System.out.println("The new noise is " + sensor.getObserved().getValue() + " desibels\n");
		}
		if (sensor.getObserved().getType().equalsIgnoreCase("congestion") && sensor.getObserved().getValue() < 10) {
			System.out.println("The slowest car is going slower than 10 km/h!\n");
			System.out.println("The slowest car is going  " + sensor.getObserved().getValue() + " km/h\n");
		} else if (sensor.getObserved().getType().equalsIgnoreCase("congestion")
				&& sensor.getObserved().getValue() >= 10) {
			System.out.println("The slowest car is going  " + sensor.getObserved().getValue() + " km/h\n");
		}

		// update internal values
		for (int i = 0; i < observedSensors.size(); i++) {
			if (observedSensors.get(i).getName().equalsIgnoreCase(sensor.getName())) {
				observedSensors.set(i, sensor);
				allSensorValues.set(i, sensor.getSpesificValue());
			} else {
				System.out.println("Error");
			}
		}
	}
}

class techyCitizen extends Citizen {

	public techyCitizen(String citizenName) {
		super(citizenName);
	}

}
