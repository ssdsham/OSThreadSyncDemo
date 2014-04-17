/*Sham Dorairaj, 25-Nov-2013, 15:23 
 *Version: Lab Assignment for CSE 6431
 */


package Buckeye;

import Buckeye.Chef;
import Buckeye.Diner;
import Buckeye.Machine;
import Buckeye.Table;
import Buckeye.Timing;

public class Chef extends Thread{
	private String chefName;
	private Table table;
	private Machine[] machines;
	private Timing timer;
	private int myTime;
	public synchronized Machine[] getMachines() {
		return machines;
	}
	public synchronized void setMachines(Machine[] machines) {
		this.machines = machines;
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public String getChefName() {
		return chefName;
	}
	public void setChefName(String chefName) {
		this.chefName = chefName;
	}
	
	public Chef(String chefName, Table t, Machine[] m,Timing ti) {
		super(chefName);
		this.chefName = chefName;
		this.table=t;
		this.machines=m;
		this.timer=ti;
		this.myTime=0;
		start();
	}
	public void run() {
		
	    while(timer.getcCounter()>1)
	    {
	    	
	    getAnOrder(this);
	    
	    }
	    //System.out.println();
	    //System.out.println("Customer was served by " + this.getChefName() );
	}
	
	public synchronized void getAnOrder(Chef c)
	{
		while(!table.dAvailable())
		{
			try {
	    		
	    		synchronized (table) {
	    			table.wait(3000);
	    			if(!table.dAvailable())
	    			{
	    				return;
	    			}
				}
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		Diner myCust=null;
		synchronized (table) {
			myCust=table.getD();
		}
		if(myTime<myCust.getTimeSeated())
		myTime=myCust.getTimeSeated();
		System.out.println();
			System.out.println(c.getChefName() + " : Currently Serving " + myCust.getCustName() + " at: " + myTime + " minute(s) and customer had to wait  "+(myTime-myCust.getTimeSeated()) + " minute(s) before being served");
			try {
				
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
		cookOrder(myCust);	
	} 
	public synchronized void cookOrder(Diner m)
	{
		int burgers=m.getOrder().getBurger();
		int fries=m.getOrder().getFries();
		int coke=m.getOrder().getCoke();
		int orderCompletionTime=myTime;	
		while(burgers>0 || fries > 0 || coke>0)
		{
			if((!machines[0].isMAvailable() && burgers > 0)  && 
					(!machines[1].isMAvailable() && fries > 0) && 
						(!machines[2].isMAvailable() && coke > 0))
			{
			try {
				synchronized (machines) {
					System.out.println();
					System.out.println(this.chefName + " is waiting for cooking resources to become available");
				machines.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
			
			
			if(machines[0].isMAvailable() && burgers > 0)
			{
				
				
				boolean gotIt=machines[0].getMachine();
				if(gotIt==false)
				{
					
					continue;
				}
				int temp=0;
				try {
					
					if( myTime>timer.getM1() && timer.getM1()>0 )
					{
						synchronized (machines[0]) {
							machines[0].wait(500);	
						}
					}
					if( myTime>timer.getM1() )
					{System.out.println();
						System.out.println(this.chefName + " is preparing " + burgers + " burgers for " + m.getCustName() + " at time " + myTime);
					}
					else
					{System.out.println();
						System.out.println(this.chefName + " is preparing " + burgers + " burgers for " + m.getCustName() + " at time " + timer.getM1());
					}
					synchronized (timer) {
						
						if( myTime>timer.getM1())
							temp=timer.setM1(myTime+5*burgers);
						else
							temp=timer.setM1(timer.getM1()+5*burgers);
						myTime=temp;
						if(temp>orderCompletionTime)
						{
							orderCompletionTime=temp;
						}
					}
							Thread.sleep(5000);
						} catch (InterruptedException e) {
						}
				System.out.println(this.chefName + " finished preparing " + burgers + " burgers for " + m.getCustName() + " at time " + temp);
				burgers=0;
				synchronized (machines[0]) {
				machines[0].leaveMachine();
				}
			}	
			
			
			if(machines[1].isMAvailable() && fries > 0)
			{
				
				boolean gotIt=machines[1].getMachine();
				if(gotIt==false)
				{
					continue;
				}
				int temp=0;
				try {
					
					if( myTime>timer.getM2() && timer.getM2()>0)
					{
						synchronized (machines[1]) {
						machines[1].wait(500);
						}
					}
					if( myTime>timer.getM2())
					{	System.out.println(this.chefName + " is preparing " + fries + " fries for " + m.getCustName() + " at time " + myTime);
						System.out.println();
					}
					else
						{
												System.out.println(this.chefName + " is preparing " + fries + " fries for " + m.getCustName() + " at time " + timer.getM2() );
												System.out.println();
						}
					synchronized (timer) {
						
						if( myTime>timer.getM2())
							temp=timer.setM2(myTime+3*fries);
						else
							temp=timer.setM2(timer.getM2()+3*fries);
					myTime=temp;
					if(temp>orderCompletionTime)
					{
						orderCompletionTime=temp;
					}
					}
							Thread.sleep(3000);
						} catch (InterruptedException e) {
						}
				System.out.println(this.chefName + " finished preparing " + fries + " fries for " + m.getCustName() + " at " + temp);
				fries=0;
				synchronized (machines[1]) {
					machines[1].leaveMachine();
					}
			}	
			
			
			
			if(machines[2].isMAvailable() && coke > 0)
			{
				
				boolean gotIt=machines[2].getMachine();
				if(gotIt==false)
				{
					continue;
				}
				int temp=0;
				try {
					
					if( myTime>timer.getM3()  && timer.getM3()>0)
					{	
						synchronized (machines[2]) {
						machines[2].wait(500);
					}
					}
					if( myTime>timer.getM3())
					{
						System.out.println();
						System.out.println(this.chefName + " is brewing " + coke + " coke for " + m.getCustName() + " at " + myTime);
					}
					else{
						System.out.println();
						System.out.println(this.chefName + " is brewing " + coke + " coke for " + m.getCustName() + " at " + timer.getM3());
					}
					
					synchronized (timer) {
					
					if( myTime>timer.getM3())
						temp=timer.setM3(myTime+1);
					else
						temp=timer.setM3(timer.getM3()+1);
					myTime=temp;
					if(temp>orderCompletionTime)
					{
						orderCompletionTime=temp;
					}
					}
							Thread.sleep(1000);
						} catch (InterruptedException e) {
						}
				System.out.println();
				System.out.println(this.chefName + " finished brewing " + coke + " coke for " + m.getCustName() + " at " + temp);
				coke=0;
				synchronized (machines[2]) {
					machines[2].leaveMachine();
					}
			}	
			
		}
		System.out.println();
		System.out.println("Order for " + m.getCustName() +  " for "+ m.getOrder().getBurger()+" Burgers " + m.getOrder().getFries()+" Fries "+ m.getOrder().getCoke()
					+" Coke"+ "  is Compeleted at: " + orderCompletionTime);
		System.out.println();
		System.out.println(m.getCustName() + " was served at : " + (orderCompletionTime));
		synchronized (m) {
			m.notify();
		}
		synchronized (timer) {
			timer.setdCounter(orderCompletionTime + 30);
			timer.setcCounter(timer.getcCounter()-1);
		}
		
		System.out.println(m.getCustName() + " finished eating food at: " + (orderCompletionTime+30));
		System.out.println(m.getCustName() + " paid the bill amount and left");
	}
	
}
