package smartCity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//FARUK BURAK GÜREL
//ERŞEN PAMUK
//MELTEM YANOĞLU

public class Main {

	public static void main(String[] args) {
		
		//declare facades
		cityPartFactoryFacade cityInterface=cityPartFactoryFacade.newFacade();
		//these are all the factories inside cityInterface facade
		/*
		mainCityFactory mainCityInterface=cityInterface.getMainCityFactory();
		
		neighborhoodFactory neighborhoodInterface=cityInterface.getNeighborhoodFactory();
		
		streetFactory streetInterface=cityInterface.getStreetFactory();
		
		apartmentFactory apartmentInterface=cityInterface.getApartmentFactory();
		
		poleFactory poleInterface=cityInterface.getPoleFactory();
		*/
		sensorFactoryFacade sensorInterface=sensorFactoryFacade.newFacade();
		
		//there are all the factories inside sensorInterface facade
		/*
		temperatureSensorFactory temperatureSensorInterface=sensorInterface.getTemperatureSensorFactory();
		
		pollutionSensorFactory pollutionSensorInterface=sensorInterface.getPollutionSensorFactory();
		
		noiseSensorFactory noiseSensorInterface=sensorInterface.getNoiseSensorFactory();
		
		congestionSensorFactory congestionSensorInterface=sensorInterface.getCongestionSensorFactory();
		*/
		
		//Create 6 citizens
		
		Citizen citizenOne=new techyCitizen("Faruk Burak Gürel");
		
		Citizen citizenTwo=new techyCitizen("Ersen Pamuk");
		
		Citizen citizenThree=new techyCitizen("Meltem Yanoglu");
		
		Citizen citizenFour=new techyCitizen("Ali Rıza Bey");
		
		Citizen citizenFive=new techyCitizen("Tıslayan Ferhunde");
		
		Citizen citizenFerhat=new techyCitizen("Ferhat");
				
		
		//Create 2 of each normal city part
		
		//2 Cities
		City angara=cityInterface.createCityPart("Angara", "City");
		
		City istanbul=cityInterface.createCityPart("Istanbul", "City");
		
		//2 Neighborhoods
		City cincin=cityInterface.createCityPart("Çinçin", "Neighborhood");
		
		City fatih=cityInterface.createCityPart("Fatih", "Neighborhood");
		
		//2 Streets
		City baglari=cityInterface.createCityPart("Bağları","Street");
		
		City cennet=cityInterface.createCityPart("Cennet", "Street");
		
		//2 Apartments
		City inci=cityInterface.createCityPart("Inci", "Apartment");
		
		City faruk=cityInterface.createCityPart("Faruk", "Apartment");		
		
		//2 Poles
		City karakizil=cityInterface.createCityPart("Karakızıl", "Pole");
		
		City cennetPole=cityInterface.createCityPart("Cennet Mahallesi", "Pole");
		
		//Create 16 sensors 4 of each type
		
				//4 temperature sensors
				Sensor temp1=sensorInterface.createSensor("1T", "Temperature");
				
				Sensor temp2=sensorInterface.createSensor("2T", "Temperature");
				
				Sensor temp3=sensorInterface.createSensor("3T", "Temperature");
				
				Sensor temp4=sensorInterface.createSensor("4T", "Temperature");
				
				//4 pollution sensors
				Sensor pollution1=sensorInterface.createSensor("1P", "Pollution");
				
				Sensor pollution2=sensorInterface.createSensor("2P", "Pollution");
				
				Sensor pollution3=sensorInterface.createSensor("3P", "Pollution");
				
				Sensor pollution4=sensorInterface.createSensor("4P", "Pollution");
				
				//4 noise sensors
				Sensor noise1=sensorInterface.createSensor("1N", "Noise");
				
				Sensor noise2=sensorInterface.createSensor("2N", "Noise");
				
				Sensor noise3=sensorInterface.createSensor("3N", "Noise");
				
				Sensor noise4=sensorInterface.createSensor("4N", "Noise");
				
				//4 congestion sensors
				Sensor congestion1=sensorInterface.createSensor("1C", "Congestion");
				
				Sensor congestion2=sensorInterface.createSensor("2C", "Congestion");
				
				Sensor congestion3=sensorInterface.createSensor("3C", "Congestion");
				
				Sensor congestion4=sensorInterface.createSensor("4C", "Congestion");
				
				//4 Arraylist of sensors to deposit,each will be attached to a city part
				
				ArrayList<Sensor> tempSensors=new ArrayList<Sensor>();
				tempSensors.add(temp1);
				tempSensors.add(temp2);
				tempSensors.add(temp3);
				tempSensors.add(temp4);
				
				ArrayList<Sensor> pollutionSensors=new ArrayList<Sensor>();
				pollutionSensors.add(pollution1);
				pollutionSensors.add(pollution2);
				pollutionSensors.add(pollution3);
				pollutionSensors.add(pollution4);
				
				ArrayList<Sensor> noiseSensors=new ArrayList<Sensor>();
				noiseSensors.add(noise1);
				noiseSensors.add(noise2);
				noiseSensors.add(noise3);
				noiseSensors.add(noise4);
				
				ArrayList<Sensor> congestionSensors=new ArrayList<Sensor>();
				congestionSensors.add(congestion1);
				congestionSensors.add(congestion2);
				congestionSensors.add(congestion3);
				congestionSensors.add(congestion4);
				
		
		//First value of each type is in Angara the others are in Istanbul
		cityInterface.addCityPartToCityPart(angara,cincin);
		cityInterface.addCityPartToCityPart(angara,baglari);
		cityInterface.addCityPartToCityPart(angara,inci);
		cityInterface.addCityPartToCityPart(angara,karakizil);
		
		cityInterface.addCityPartToCityPart(cincin,angara);
		cityInterface.addCityPartToCityPart(cincin,baglari);
		cityInterface.addCityPartToCityPart(cincin,inci);
		cityInterface.addCityPartToCityPart(cincin,karakizil);
		
		cityInterface.addCityPartToCityPart(baglari,angara);
		cityInterface.addCityPartToCityPart(baglari,cincin);
		cityInterface.addCityPartToCityPart(baglari,inci);
		cityInterface.addCityPartToCityPart(baglari,karakizil);
		
		cityInterface.addCityPartToCityPart(inci,karakizil);
		
		cityInterface.addCityPartToCityPart(karakizil,inci);
		
		cityInterface.displayCityPartsOfGivenPart(angara);
		
		cityInterface.displayCityPartsOfGivenPart(cincin);
		
		cityInterface.displayCityPartsOfGivenPart(baglari);
		
		cityInterface.displayCityPartsOfGivenPart(inci);
		
		cityInterface.displayCityPartsOfGivenPart(karakizil);
		//second value is Istanbul
		cityInterface.addCityPartToCityPart(istanbul, fatih);
		cityInterface.addCityPartToCityPart(istanbul, cennet);
		cityInterface.addCityPartToCityPart(istanbul, faruk);
		cityInterface.addCityPartToCityPart(istanbul, cennetPole);
		
		cityInterface.addCityPartToCityPart(fatih,istanbul);
		cityInterface.addCityPartToCityPart(fatih, cennet);
		cityInterface.addCityPartToCityPart(fatih, faruk);
		cityInterface.addCityPartToCityPart(fatih, cennetPole);
		
		cityInterface.addCityPartToCityPart(cennet,istanbul);
		cityInterface.addCityPartToCityPart(cennet, fatih);
		cityInterface.addCityPartToCityPart(cennet, faruk);
		cityInterface.addCityPartToCityPart(cennet, cennetPole);
		
		cityInterface.addCityPartToCityPart(faruk, cennetPole);
		
		cityInterface.addCityPartToCityPart(cennetPole, faruk);
		
		cityInterface.displayCityPartsOfGivenPart(istanbul);
		
		cityInterface.displayCityPartsOfGivenPart(fatih);
		
		cityInterface.displayCityPartsOfGivenPart(cennet);
		
		cityInterface.displayCityPartsOfGivenPart(faruk);
		
		cityInterface.displayCityPartsOfGivenPart(cennetPole);
		
		//ASSUMPTION,THE FIRST VALUES WON'T BE REPORTED TO THE OBSERVERS
		//BECAUSE THEY ARE CREATED AND THE FIRST VALUES ARE RANDOM TO CHECK IF THEY ARE
		//RUNNING CORRECTLY OR MALFUNCTIONING
		
		sensorInterface.assignRandomObservedValueToSensorList(tempSensors, "temperature");
		
		sensorInterface.assignRandomObservedValueToSensorList(pollutionSensors, "pollution");
		
		sensorInterface.assignRandomObservedValueToSensorList(noiseSensors,"noise");
		
		sensorInterface.assignRandomObservedValueToSensorList(congestionSensors, "congestion");
		
		ArrayList<Sensor> firstParty=new ArrayList<Sensor>(4);
		firstParty.add(tempSensors.get(0));
		firstParty.add(pollutionSensors.get(0));
		firstParty.add(noiseSensors.get(0));
		firstParty.add(congestionSensors.get(0));
		
		ArrayList<Sensor> secondParty=new ArrayList<Sensor>(4);
		secondParty.add(tempSensors.get(1));
		secondParty.add(pollutionSensors.get(1));
		secondParty.add(noiseSensors.get(1));
		secondParty.add(congestionSensors.get(1));
		
		ArrayList<Sensor> thirdParty=new ArrayList<Sensor>(4);
		thirdParty.add(tempSensors.get(2));
		thirdParty.add(pollutionSensors.get(2));
		thirdParty.add(noiseSensors.get(2));
		thirdParty.add(congestionSensors.get(2));
		
		ArrayList<Sensor> fourthParty=new ArrayList<Sensor>(4);
		fourthParty.add(tempSensors.get(3));
		fourthParty.add(pollutionSensors.get(3));
		fourthParty.add(noiseSensors.get(3));
		fourthParty.add(congestionSensors.get(3));
		
		ArrayList<observedValues>tempValues=new ArrayList<observedValues>();
		
		ArrayList<observedValues>pollutionValues=new ArrayList<observedValues>();
		
		ArrayList<observedValues>noiseValues=new ArrayList<observedValues>();
		
		ArrayList<observedValues>congestionValues=new ArrayList<observedValues>();
		
		for(int i=0;i<firstParty.size();i++) {
			sensorInterface.attachCity(firstParty.get(i), inci);
			sensorInterface.attachCity(secondParty.get(i), faruk);
			sensorInterface.attachCity(thirdParty.get(i), karakizil);
			sensorInterface.attachCity(fourthParty.get(i), cennetPole);
		}
		//Attach sensors to each smart city part
		cityInterface.attachSensorListSmart(inci,firstParty);
		cityInterface.attachSensorListSmart(karakizil, secondParty);
        cityInterface.attachSensorListSmart(faruk, thirdParty);
        cityInterface.attachSensorListSmart(cennetPole, fourthParty);
		
        
		for(int i=0;i<tempSensors.size();i++) {
			tempValues.add(tempSensors.get(i).getObserved());
			pollutionValues.add(pollutionSensors.get(i).getObserved());
			noiseValues.add(noiseSensors.get(i).getObserved());
			congestionValues.add(congestionSensors.get(i).getObserved());
		}
		//WAIT FOR A WHILE THAN START RUNNING THE THREADS
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//add citizens to observe
		sensorInterface.attachCitizenList(sensorInterface.getAllSensors(), citizenOne);
		sensorInterface.attachCitizenList(sensorInterface.getAllSensors(), citizenTwo);
		sensorInterface.attachCitizenList(sensorInterface.getAllSensors(), citizenThree);
		sensorInterface.attachCitizenList(sensorInterface.getAllSensors(), citizenFour);
		sensorInterface.attachCitizenList(sensorInterface.getAllSensors(), citizenFive);
		sensorInterface.attachCitizenList(sensorInterface.getAllSensors(), citizenFerhat);
		
		//FIRST RUNNABLE bigBrother WATCHES SENSORS AND LOOKS IF THEY ARE MALFUNCTIONING
		
		Runnable runnableChecker=new bigBrother(sensorInterface.getAllSensors(),"MIT");
		
		//SECOND RUNNABLE timedThreadUpdater UPDATES THE OBSERVED VALUES INSIDE EACH SENSOR
		
		Runnable runnableUpdater=new timedThreadUpdater(sensorInterface.getAllSensors());
		
		ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
		
		//THESE TWO THREADS WILL BE CALLED PERIOCIDALLY
		
		service.scheduleAtFixedRate(runnableChecker,0,3,TimeUnit.SECONDS);
		
		service.scheduleAtFixedRate(runnableUpdater,0,5,TimeUnit.SECONDS);
		
		
		
		
		

	}

}
