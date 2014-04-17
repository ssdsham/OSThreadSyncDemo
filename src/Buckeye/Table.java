/*Sham Dorairaj, 25-Nov-2013, 15:23 
 *Version: Lab Assignment for CSE 6431
 */

package Buckeye;

import java.util.ArrayList;

public class Table {
	
	private int[] tables;
	private String[] custtable;
	private boolean availabe;
	private int filledTables;
	private ArrayList<Diner> d;
	private Timing timer;
	private Restaurant restruant;
	public synchronized Diner getD() {
		
		return d.remove(0);
	}
	public void setD(ArrayList<Diner> d) {
		this.d = d;
	}
	public synchronized boolean dAvailable()
	{
		if(d.size()>0)
		{
			return true;
		}
		else 
			return false;
	}
	public synchronized Timing getTimer() {
		return timer;
	}
	public synchronized void setTimer(Timing timer) {
		this.timer = timer;
	}
	public synchronized int getFilledTables() {
		return filledTables;
	}
	public synchronized void setFilledTables(int filledTables) {
		this.filledTables = filledTables;
	}
	public synchronized boolean isAvailabe() {
	
		return availabe;
	}

	public synchronized void setAvailabe(boolean availabe) {
		synchronized (restruant) {
			restruant.notifyAll();	
		}
		
		this.availabe = availabe;
	}

	public String[] getCusttable() {
		return custtable;
	}

	public void setCusttable(String[] custtable) {
		this.custtable = custtable;
	}

	public synchronized int[] getTables() {
		return tables;
	}

	public synchronized void setTables(int[] tables) {
		this.tables = tables;
	}
	public void releaseTable(String n)
	{
		for (int i=0;i<tables.length;i++)
		{
			if(n.equals(custtable[i]))
			{
				tables[i]=0;
			}
			
		}
		
	}

	public Table(int table,Timing t,Restaurant r) {
		super();
		this.tables = new int[table];
		this.custtable = new String[table];
		this.filledTables=0;
		this.availabe=true;
		this.d=new ArrayList<Diner>();
		this.timer=t;
		this.restruant=r;
	}
	
	
	public int assignTable(Diner x)
	{
		int time=0;
		for(int i=0;i<tables.length;i++)
		{
			if(tables[i]==0)
			{
				tables[i]=1;
				custtable[i]=x.getCustName();
				synchronized (timer) {
				if(timer.getdCounter()<x.getTime())
				{
					time=x.getTime();
					
					System.out.println("Table" +i + " : reserved for " + x.getCustName() +  " at time " +  x.getTime() + " minute(s). Customer waited for 0 minute(s) for getting table");
				}
					else
					{
						time=timer.getdCounter();
						System.out.println("Table" +i + " : reserved for " + x.getCustName() +  " at time " +  timer.getdCounter()+ " minute(s). Customer waited for " + (timer.getdCounter()-x.getTime()) +" minute(s) for getting table");
					}
				}
				d.add(x);
				synchronized (this) {
					this.notifyAll();
				}
				break;
			}
			
		}
		return time;
	}

}
