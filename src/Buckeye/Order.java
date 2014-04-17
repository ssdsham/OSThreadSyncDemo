/*Sham Dorairaj, 25-Nov-2013, 15:23 
 *Version: Lab Assignment for CSE 6431
 */

package Buckeye;

public class Order {
	private int burger;
	private int fries;
	private int coke;
	public int getBurger() {
		return burger;
	}
	public void setBurger(int burger) {
		this.burger = burger;
	}
	public int getFries() {
		return fries;
	}
	public void setFries(int fries) {
		this.fries = fries;
	}
	public int getCoke() {
		return coke;
	}
	public void setCoke(int coke) {
		this.coke = coke;
	}
	public Order(int burger, int fries, int coke) {
		super();
		this.burger = burger;
		this.fries = fries;
		this.coke = coke;
	}
	//@Override
	public String toString() {
		return "Ordered Items [Burger=" + burger + ", Fries=" + fries + ", Coke="
				+ coke + "]";
	}
	
	
	
}
