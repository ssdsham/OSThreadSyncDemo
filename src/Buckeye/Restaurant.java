/*Sham Dorairaj, 25-Nov-2013, 15:23 
 *Version: Lab Assignment for CSE 6431
 */

package Buckeye;

public class Restaurant {
	private int noOfDiners;
	private int noOfTables;
	private int noOfCooks; 
	
	
	public Restaurant(int noOfDinners, int noOfTables, int noOfCooks) {
		super();
		this.noOfDiners = noOfDinners;
		this.noOfTables = noOfTables;
		this.noOfCooks = noOfCooks;
	}


	public int getNoOfDiners() {
		return noOfDiners;
	}


	public void setNoOfDiners(int noOfDiners) {
		this.noOfDiners = noOfDiners;
	}


	public int getNoOfTables() {
		return noOfTables;
	}


	public void setNoOfTables(int noOfTables) {
		this.noOfTables = noOfTables;
	}


	public int getNoOfCooks() {
		return noOfCooks;
	}


	public void setNoOfCooks(int noOfCooks) {
		this.noOfCooks = noOfCooks;
	}

	
		
	}


