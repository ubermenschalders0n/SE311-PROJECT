package smartCity;

import java.util.ArrayList;

//Data Monitoring Service that runs on a thread and sends periodic queries to the sensors
//the periodic query gives the amount of malfunctioning sensors and adds them to an arraylist
//engineer can reset all malfunctioning sensors
//engineer can also send reset command to a spesific sensor by taking its name as a parameter and sending it

//abstract class which defines basic functions of DataMonitoring Service and it implements Runnable
//this class will run on a thread to send periodical queries
//the timer value will be given by the creator of this class
//I will use scheduled executor service to implement periodical function calls
//It was defined in Java SE 5 so it will be okay with java 7
//There will be only one thread that runs the data monitoring service,and all the others will run on the main thread
//inside runnable this class will only call the checkMalfunction method
abstract class DataMonitoringService implements Runnable{
	String name;
	
	public DataMonitoringService(String name) {
		this.name=name;
	}
	//with each check inside the for loop malfunction class will be called if the sensor is not broken
	//every sensor has 0.2 probability of malfunctioning each time the function is called
	public void checkMachineMalfunction() {
	}
	public void resetSpesificMachine(String machineName) {
	
	}
	
	public void resetAllMalfunctioningMachines() {
		
	}
	public void malfunctionReport(){
	}
}

//it inherits the DataMonitoringService and implements unimplemented methods
//DataMonitoringService can be like NSA
//They might just check Sensors today but if they wanted to spy on their citizens(of course they wouldn't why would they)
//They might need to extend their invasion of privacy,so basic functions that will be used in every NSA method
//is defined in the abstract class
//And each concrete class will implement their own invasion of privacy depending on their mood
//Just inheriting the DataMonitoringService is the only thing they need
//So it follows OCP and single responsibility
//Each concrete class is only responsible of their own invasion of privacy

class bigBrother extends DataMonitoringService{
	public bigBrother(ArrayList<Sensor> allSensors,String name) {
		super(name);
		this.allSensors=allSensors;
	}


	ArrayList<Sensor> malfunctioningSensors=new ArrayList<Sensor>();
	ArrayList<Sensor> allSensors=new ArrayList<Sensor>();
	
	//with each check inside the for loop malfunction class will be called if the sensor is not broken
	//every sensor has 0.2 probability of malfunctioning each time the function is called
	public void checkMachineMalfunction() {
		malfunctioningSensors.clear();
		for(int i=0;i<allSensors.size();i++) {
			if(allSensors.get(i).status()) {
				System.out.println("The sensor "+allSensors.get(i).getName()+" is malfunctioning\n");
				malfunctioningSensors.add(allSensors.get(i));
			}else if(allSensors.get(i).status()==false) {
				System.out.println("The sensor "+allSensors.get(i).getName()+" is running okay\n");
				allSensors.get(i).malfunction();
			}
		}
	}
	public void resetSpesificMachine(String sensorName) {
		for(int i=0;i<malfunctioningSensors.size();i++) {
			if(malfunctioningSensors.get(i).getName().equalsIgnoreCase(sensorName)) {
				System.out.println("Resetting\n");
				malfunctioningSensors.get(i).toString();
				malfunctioningSensors.get(i).reset();
			}else {
				System.out.println("Sensor with the name "+sensorName+" can't be found\n");
			}
		}
	}
	
	public void resetAllMalfunctioningMachines() {
		for(int i=0;i<malfunctioningSensors.size();i++) {
			malfunctioningSensors.get(i).reset();
		}
	}
	public void malfunctionReport(){
		System.out.println("There are currently "+malfunctioningSensors.size()+" sensors malfunctioning\n");
	}


	@Override
	public synchronized void run() {
		checkMachineMalfunction();
		malfunctionReport();
		resetAllMalfunctioningMachines();
	}
	
}
