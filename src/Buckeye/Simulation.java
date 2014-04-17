/*Sham Dorairaj, 25-Nov-2013, 15:23 
 *Version: Lab Assignment for CSE 6431
 *Program: Restaurant Simulator. Simulates the restaurant scenario of customers arriving, 
 *occupying tables and ordering burgers, fries, coke and chefs preparing these. 
 *Customers leave and the table is avilable for next waiting customer 
 */
package Buckeye;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {

	
	
	public static void main(String[] args) {
		
		int diners=0;
		int tables=0;
		int chefs=0;
		Restaurant restaurant=null;
		Thread chef;
		
		ArrayList<Diner> dinersList=new ArrayList<Diner>();
		Table table=null;
		Timing timing=null;
		Machine[] machines=new Machine[3];
		for (int i = 0; i < 3; i++)
		{
		    machines[i] = new Machine(machines);
		}
		File file = new File("input.txt");
		Scanner input1;
		try {
			input1 = new Scanner(file);
			int a=0;
			while(input1.hasNext()) {
			    
			    String nextLine = input1.nextLine().trim();
			    if(a==0)
			    {
			    	diners=Integer.parseInt(nextLine);
			    	timing=new Timing(diners);
			    }
			    if(a==1)
			    {
			    	tables=Integer.parseInt(nextLine);
			    	 
			    }
			    if(a==2)
			    {
			    	chefs=Integer.parseInt(nextLine);
			    	
			    }
			    if(a==3)
			    {
			    	restaurant=new Restaurant(diners, tables, chefs);
			    	table=new Table(tables,timing,restaurant);
			    	for(int i=0;i<chefs;i++)
			    	{
			    	chef=new Chef("Chef"+(i+1),table,machines,timing);
			    	}
			    }
			    if(a>=3)
			    {
			    	String[] input =(nextLine.split("\\s+"));
					Order tempOrder= new Order(Integer.parseInt(input[1]),Integer.parseInt(input[2]),Integer.parseInt(input[3]));
		        	String tempName="Customer"+ (a-2);
		        	
		        	dinersList.add(new Diner(tempName,Integer.parseInt(input[0]),tempOrder,table,timing ));
			    }
			    
			    a++;
			    
			}

			input1.close();
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}
			
		while(!dinersList.isEmpty())
		{
			while(!table.isAvailabe())
			{
				synchronized (restaurant) {
				try {
					
					restaurant.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
				}
			}
			int temp=dinersList.get(0).getTime();
			dinersList.remove(0).start();
			
			try {
						if(!dinersList.isEmpty())
						temp=((dinersList.get(0).getTime()-temp))%10;
						else
							temp=1;
						Thread.sleep(1000*temp);
					} catch (InterruptedException e) {
					}
		}
		
		while(timing.getcCounter()>0);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		System.out.println("Resturant Simulation Completed at : " + timing.getdCounter());
		}

}
