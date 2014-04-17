/*Sham Dorairaj, 25-Nov-2013, 15:23 
 *Version: Lab Assignment for CSE 6431
 */
package Buckeye;

import Buckeye.Machine;

public class Machine {

	
	private boolean mAvailable;
	private Machine[] machines;
		
	
	public Machine(Machine[] m) {
		super();
		mAvailable=true;
		machines=m;
	}
	
	public synchronized Machine[] getMachines() {
		return machines;
	}

	public synchronized void setMachines(Machine[] machines) {
		this.machines = machines;
	}

	public synchronized boolean isMAvailable() {
		
		return mAvailable;
	}
	public synchronized void setMAvailable(boolean m1Available) {
		this.mAvailable = m1Available;
	}
	
	
	public synchronized boolean getMachine()
	{
		synchronized (this) {
			if(isMAvailable()==true)
			{
			
				setMAvailable(false);
				return true;
			}
			else
				return false;
		}
		
	}
	public synchronized void leaveMachine()
	{
		synchronized (this) {
		setMAvailable(true);
		this.notifyAll();
		synchronized (machines) {
		machines.notifyAll();
		}
		}
	}	
	
	
}
