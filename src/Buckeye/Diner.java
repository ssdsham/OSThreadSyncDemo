/*Sham Dorairaj, 25-Nov-2013, 15:23 
 *Version: Lab Assignment for CSE 6431
 */
package Buckeye;

import Buckeye.Diner;
import Buckeye.Order;
import Buckeye.Table;
import Buckeye.Timing;

public class Diner extends Thread{
	private String custName;
	private int time;
	private Order order;
	private Table table;
	private Timing timer;
	private boolean orderServed;
	private int timeSeated;
	
	public synchronized int getTimeSeated() {
		return timeSeated;
	}
	public synchronized void setTimeSeated(int timeSeated) {
		this.timeSeated = timeSeated;
	}
	public synchronized boolean isOrderServed() {
		return orderServed;
	}
	public synchronized void setOrderServed(boolean orderServed) {
		this.orderServed = orderServed;
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Diner(String name, int time, Order order,Table t,Timing ti) {
		super(name);
		this.custName = name;
		this.time = time;
		this.order = order;
		this.table=t;
		this.timer=ti;
	}
	
	
	@Override
	public String toString() {
		return "Diner [name= " + custName + ", time=" + time + ", order=" + order
				+ "]";
	}
	public void run() {
		
	 boolean success=getATable(this);
	 if(success)
	 releaseATable();
	}
	
	public synchronized boolean getATable(Diner d)
	{
		synchronized (timer) {
		if(d.getTime()>=120)
		{
			timer.setcCounter(timer.getcCounter()-1);
			System.out.println(d.getCustName() + " needs to wait, can't be assigned a table at present");
			return false;
		}
		}
		 while(!table.isAvailabe())
		    {
		    	try {
		    		
		    		synchronized (table) {
		    			table.wait();	
					}
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
		    	
		    }
		 synchronized (table) {
			 timeSeated=table.assignTable(this);
			 if(time<0)
			 {
				 return false;
			 }
			 
			 table.setFilledTables(table.getFilledTables()+1);
			 if(table.getFilledTables()==table.getTables().length)
			 {
				 table.setAvailabe(false);
			 }
		 }
		  
		   
		    synchronized (this) {
		    	try {
					this.wait();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}	
			}
		    
		    try {
				Thread.currentThread();
						
						Thread.sleep(900);
					} catch (InterruptedException e) {
					}
		   return true;
	}
	public synchronized void releaseATable()
	{
		System.out.println();
		 System.out.println("Cleared the table which was used by : " + Thread.currentThread().getName());
	    	table.setFilledTables(table.getFilledTables()-1);
	    	table.setAvailabe(true);
	    	table.releaseTable(Thread.currentThread().getName());
	    	synchronized (table) {
	    	table.notify();
	    	}
	}
}
