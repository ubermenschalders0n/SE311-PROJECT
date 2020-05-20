package smartCity;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



//ABSTRACT FACTORY WHICH DEFINES CREATING SENSOR
interface SensorFactory{
	abstract public Sensor createSensor(String sensorName);
}

//Concrete Factory for each type of a sensor
//Each factory will be using thread safe singleton design pattern
//There cannot be more than one factory for each type,it is not needed and would be a waste of memory



class temperatureSensorFactory implements SensorFactory{
	private static volatile temperatureSensorFactory staticTemperatureSensor=null;
	private static final Lock locker = new ReentrantLock(); 
	private temperatureSensorFactory() {}
	public static temperatureSensorFactory newTemperatureSensorFactory() {
		if(staticTemperatureSensor==null) {
			locker.lock();
			try {
				if(staticTemperatureSensor==null) {
					staticTemperatureSensor=new temperatureSensorFactory();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticTemperatureSensor;
	}
	@Override
	public temperatureSensor createSensor(String sensorName) {
		return new temperatureSensor(sensorName);
	}
}

class pollutionSensorFactory implements SensorFactory{
	private static volatile pollutionSensorFactory staticPollutionSensor=null;
	private static final Lock locker = new ReentrantLock(); 
	private pollutionSensorFactory() {}
	public static pollutionSensorFactory newPollutionSensorFactory() {
		if(staticPollutionSensor==null) {
			locker.lock();
			try {
				if(staticPollutionSensor==null) {
					staticPollutionSensor=new pollutionSensorFactory();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticPollutionSensor;
	}
	@Override
	public pollutionSensor createSensor(String sensorName) {
		return new pollutionSensor(sensorName);
	}
}

class noiseSensorFactory implements SensorFactory{
	private static volatile noiseSensorFactory staticNoiseSensor=null;
	private static final Lock locker = new ReentrantLock(); 
	private noiseSensorFactory() {}
	public static noiseSensorFactory newNoiseSensorFactory() {
		if(staticNoiseSensor==null) {
			locker.lock();
			try {
				if(staticNoiseSensor==null) {
					staticNoiseSensor=new noiseSensorFactory();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticNoiseSensor;
	}
	@Override
	public noiseSensor createSensor(String sensorName) {
		return new noiseSensor(sensorName);
	}
}

class congestionSensorFactory implements SensorFactory{
	private static volatile congestionSensorFactory staticCongestionSensor=null;
	private static final Lock locker = new ReentrantLock(); 
	private congestionSensorFactory() {}
	public static congestionSensorFactory newCongestionSensorFactory() {
		if(staticCongestionSensor==null) {
			locker.lock();
			try {
				if(staticCongestionSensor==null) {
					staticCongestionSensor=new congestionSensorFactory();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticCongestionSensor;
	}
	@Override
	public congestionSensor createSensor(String sensorName) {
		return new congestionSensor(sensorName);
	}
}

//Facade to easily initialize new objects with an easy to use interface
//It will be using thread safe singleton
//There can be only one facade interface,there is no need to make another facades,we can use every method with just one


//Assumption,the interface for the making of the all types of sensors has all the values of sensors ever created
//Sensors will not be destroyed,only malfunction and if they malfunction just resetting them will fix everything
//Therefore sensorFactoryFacade has all of the created sensors inside it as arraylist
//And they are indestructible 
//We are using facade to give an interface to the user,which makes creating objects really easy for them
//they don't have to know the internal values of each object they just give values that we say
class sensorFactoryFacade{
	private temperatureSensorFactory temperature;
	private pollutionSensorFactory pollution;
	private noiseSensorFactory noise;
	private congestionSensorFactory congestion;
	private static volatile sensorFactoryFacade staticSensorFacade=null;
	private static final Lock locker=new ReentrantLock();
	private ArrayList<Sensor> sensors=new ArrayList<Sensor>();

	
	public static sensorFactoryFacade newFacade() {
		if(staticSensorFacade==null) {
			locker.lock();
			try {
				if(staticSensorFacade==null) {
					staticSensorFacade=new sensorFactoryFacade();
				}
			}finally {
				locker.unlock();
			}
		}
		return staticSensorFacade;
	}
	
	private sensorFactoryFacade() {
		temperature=temperatureSensorFactory.newTemperatureSensorFactory();
		pollution=pollutionSensorFactory.newPollutionSensorFactory();
		noise=noiseSensorFactory.newNoiseSensorFactory();
		congestion=congestionSensorFactory.newCongestionSensorFactory();
	}
	public temperatureSensorFactory getTemperatureSensorFactory() {
		return temperature;
	}
	public pollutionSensorFactory getPollutionSensorFactory() {
		return pollution;
	}
	public noiseSensorFactory getNoiseSensorFactory() {
		return noise;
	}
	public congestionSensorFactory getCongestionSensorFactory() {
		return congestion;
	}
	public ArrayList<Sensor> getAllSensors(){
		return sensors;
	}
	//add city part parameter,citizen parameter
	public Sensor createSensor(String sensorName,String type){
		Sensor newSensor=null;
		if(type.equalsIgnoreCase("temperature")) {
			newSensor=temperature.createSensor(sensorName);
		}else if(type.equalsIgnoreCase("pollution")) {
			newSensor=pollution.createSensor(sensorName);
		}else if(type.equalsIgnoreCase("noise")) {
			newSensor=noise.createSensor(sensorName);
		}else if(type.equalsIgnoreCase("congestion")) {
			newSensor=congestion.createSensor(sensorName);
		}
		sensors.add(newSensor);
		return newSensor;
	}
	public void attachCity(Sensor sensor,City city) {
		sensor.setCity(city);		
	}
	public void attachCitizen(Sensor sensor,Citizen citizen) {
		sensor.Attach(citizen);
	}
	public void attachCitizenList(ArrayList<Sensor> sensorList,Citizen citizen) {
		if(citizen.getCitizenName().equalsIgnoreCase("ferhat")) {
			System.out.println("PEMBE SAYS\n");
			System.out.println("Ferhatlar ve küpekler giremez\n");
			return;
		}
		for(int i=0;i<sensorList.size();i++) {
			sensorList.get(i).Attach(citizen);
		}
	}
	public void setSpesificObservedValue(Sensor sensor,int spesificValue) {
		sensor.getObserved().setValue(spesificValue);
	}
	public void setSpesificValueToObservedValue(observedValues observedValue,int spesificValue ) {
		observedValue.setValue(spesificValue);
	}
	public void displaySensors() {
	    sensors.forEach((value)->System.out.println(value.toString()));	
	}
	public void setObservedValueListToSensorList(ArrayList<observedValues>observedValuesList,ArrayList<Sensor>sensorList) {
		if(observedValuesList.size()!=sensorList.size()) {
			System.out.println("The size of each array list should be equal\n");
			return;
		}
		else {
			for(int i=0;i<sensorList.size();i++) {
				sensorList.get(i).setObservedValue(observedValuesList.get(i));
			}
		}
	}
	//TAKES ARRAY LIST OF SENSOR TYPE AND THE TYPE TO ASSIGN VALUES
	//THAN CREATES RANDOM VALUES WITH RNG AND ASSIGNS THEM TO THE OBSERVED VALUE
	//WHICH IS THE VALUE OBSERVED BY SENSOR
	//EACH OBSERVED VALUE IS CREATED DEPENDING ON THE STRING PARAMETER type
	public void assignRandomObservedValueToSensorList(ArrayList<Sensor>sensorList,String type) {
		int lowerBound;
		int upperBound;
		int randomNumber;
		Random generator=new Random();
		
		for(int i=0;i<sensorList.size();i++) {
			if(type.equalsIgnoreCase("temperature")) {
				lowerBound=-40;
				upperBound=60;
				randomNumber = generator.nextInt(upperBound - lowerBound) + lowerBound;
				observedValues observedTemp=new temperatureValue(randomNumber);
				observedTemp.Attach(sensorList.get(i));
				sensorList.get(i).setObservedValue(observedTemp);
				sensorList.get(i).getObserved().displayObserved();
			}else if(type.equalsIgnoreCase("pollution")) {
				lowerBound=0;
				upperBound=200;
				randomNumber = generator.nextInt(upperBound - lowerBound) + lowerBound;
				observedValues observedTemp=new pollutionValue(randomNumber);
				observedTemp.Attach(sensorList.get(i));
				sensorList.get(i).setObservedValue(observedTemp);	
				sensorList.get(i).getObserved().displayObserved();
			}else if(type.equalsIgnoreCase("noise")) {
				lowerBound=0;
				upperBound=300;
				randomNumber = generator.nextInt(upperBound - lowerBound) + lowerBound;
				observedValues observedTemp=new noiseValue(randomNumber);
				observedTemp.Attach(sensorList.get(i));
				sensorList.get(i).setObservedValue(observedTemp);
				sensorList.get(i).getObserved().displayObserved();
			}else if(type.equalsIgnoreCase("congestion")) {
				lowerBound=0;
				upperBound=150;
				randomNumber = generator.nextInt(upperBound - lowerBound) + lowerBound;
				observedValues observedTemp=new congestionValue(randomNumber);
				observedTemp.Attach(sensorList.get(i));
				sensorList.get(i).setObservedValue(observedTemp);	
				sensorList.get(i).getObserved().displayObserved();
			}else {
				System.out.println("Illegal value\n");
			}
		}		
	}
}
//TIMED THREAD UPDATER CLASS WHICH TAKES IN ARRAY LIST OF SENSORS
//AND DEPENDING ON THE TYPE OF THE OBSERVERS SETS THE VALUE OF 
//THE OBSERVED WITH RNG NUMBER
//THE THREAD UPDATES SENSOR VALUES BECAUSE SENSORS ARE GETTING THE VALUE OF THE
//OBSERVED VALUE FROM THE ENVIROMENT
//AND OBSERVED VALUES ARE KEPT INSIDE THE SENSORS BECAUSE 
//EACH SENSOR CAN OBSERVE 1 TYPE AND 1 LOCATION
class timedThreadUpdater implements Runnable{
	ArrayList<Sensor> sensorList=new ArrayList<Sensor>();
	Random generator=new Random();	
	private int lowerBound;
	private int upperBound;
	private int randomNumber;
	public timedThreadUpdater(ArrayList<Sensor>sensorList) {
		this.sensorList=sensorList;
	}
	@Override
	public synchronized void run() {
		for(int i=0;i<sensorList.size();i++) {
			if(sensorList.get(i).getObserved().getType().equalsIgnoreCase("temperature")) {
				lowerBound=-40;
				upperBound=60;
				randomNumber = generator.nextInt(upperBound - lowerBound) + lowerBound;
				sensorList.get(i).getObserved().setValue(randomNumber);
			}else if(sensorList.get(i).getObserved().getType().equalsIgnoreCase("pollution")) {
				lowerBound=0;
				upperBound=200;
				randomNumber = generator.nextInt(upperBound - lowerBound) + lowerBound;
				sensorList.get(i).getObserved().setValue(randomNumber);
			}else if(sensorList.get(i).getObserved().getType().equalsIgnoreCase("noise")) {
				lowerBound=0;
				upperBound=300;
				randomNumber = generator.nextInt(upperBound - lowerBound) + lowerBound;
				sensorList.get(i).getObserved().setValue(randomNumber);
			}else if(sensorList.get(i).getObserved().getType().equalsIgnoreCase("congestion")) {
				lowerBound=0;
				upperBound=150;
				randomNumber = generator.nextInt(upperBound - lowerBound) + lowerBound;
				sensorList.get(i).getObserved().setValue(randomNumber);
			}else {
				System.out.println("Illegal type\n");
			}
		}
	}
	
}

//THESE CLASSES ARE ALL CONCRETE THREADS THAT UPDATES EACH VALUE TYPE
//BECAUSE OF CODE REUSE CONCATENATED THEM TO A SINGLE CLASS WITH TYPE CHECKING
class timedThreadUpdaterTemperature implements Runnable{
	ArrayList<observedValues> observedValuesList=new ArrayList<observedValues>();
	sensorFactoryFacade facade;
	private int lowerBound=-40;
	private int upperBound=60;
	public timedThreadUpdaterTemperature(sensorFactoryFacade facade,ArrayList<observedValues>observedValuesList) {
		this.facade=facade;
		this.observedValuesList=observedValuesList;
	}
	@Override
	public synchronized void run() {
		Random generator=new Random();
		for(int i=0;i<observedValuesList.size();i++) {
			int randomNumber = generator.nextInt(upperBound - lowerBound) + lowerBound;
			facade.setSpesificValueToObservedValue(observedValuesList.get(i), randomNumber);
		}		
	}
	
}
class timedThreadUpdaterPollution implements Runnable{
	ArrayList<observedValues> observedValuesList=new ArrayList<observedValues>();
	sensorFactoryFacade facade;
	private int lowerBound=0;
	private int upperBound=200;
	public timedThreadUpdaterPollution(sensorFactoryFacade facade,ArrayList<observedValues>observedValuesList) {
		this.facade=facade;
		this.observedValuesList=observedValuesList;
	}
	@Override
	public synchronized void run() {
		Random generator=new Random();
		for(int i=0;i<observedValuesList.size();i++) {
			int randomNumber = generator.nextInt(upperBound - lowerBound) + lowerBound;
			facade.setSpesificValueToObservedValue(observedValuesList.get(i), randomNumber);
		}		
	}
	
}
class timedThreadUpdaterNoise implements Runnable{
	ArrayList<observedValues> observedValuesList=new ArrayList<observedValues>();
	sensorFactoryFacade facade;
	private int lowerBound=0;
	private int upperBound=300;
	public timedThreadUpdaterNoise(sensorFactoryFacade facade,ArrayList<observedValues>observedValuesList) {
		this.facade=facade;
		this.observedValuesList=observedValuesList;
	}
	@Override
	public synchronized void run() {
		Random generator=new Random();
		for(int i=0;i<observedValuesList.size();i++) {
			int randomNumber = generator.nextInt(upperBound - lowerBound) + lowerBound;
			facade.setSpesificValueToObservedValue(observedValuesList.get(i), randomNumber);
		}		
	}
	
}

class timedThreadUpdaterCongestion implements Runnable{
	ArrayList<observedValues> observedValuesList=new ArrayList<observedValues>();
	sensorFactoryFacade facade;
	int spesificValue;
	private int lowerBound=0;
	private int upperBound=150;
	
	public timedThreadUpdaterCongestion(sensorFactoryFacade facade,ArrayList<observedValues>observedValuesList) {
		this.facade=facade;
		this.observedValuesList=observedValuesList;
	}
	@Override
	public synchronized void run() {
		Random generator=new Random();
		for(int i=0;i<observedValuesList.size();i++) {
			int randomNumber = generator.nextInt(upperBound - lowerBound) + lowerBound;
			facade.setSpesificValueToObservedValue(observedValuesList.get(i), randomNumber);
		}		
	}
	
}
