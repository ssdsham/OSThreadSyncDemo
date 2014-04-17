/*Sham Dorairaj, 25-Nov-2013, 15:23 
 *Version: Lab Assignment for CSE 6431
 */

package Buckeye;

public class Timing {
 private int dCounter;
 private int cCounter;
 private int m1;
 private int m2;
 private int m3;
 

public synchronized int getM1() {
	return m1;
}
public synchronized int setM1(int m1) {
	this.m1 = m1;
	return this.m1;
}
public synchronized int getM2() {
	return m2;
}
public synchronized int setM2(int m2) {
	this.m2 = m2;
	return this.m2;
}
public synchronized int getM3() {
	return m3;
}
public synchronized int setM3(int m3) {
	this.m3 = m3;
	return this.m3;
}
public synchronized int getdCounter() {
	return dCounter;
}
public synchronized void setdCounter(int dCounter) {
	this.dCounter = dCounter;
}
public synchronized int getcCounter() {
	return cCounter;
}
public synchronized void setcCounter(int cCounter) {
	this.cCounter = cCounter;
}
public Timing(int c) {
	super();
	dCounter=0;
	cCounter=c;
	m1=0;
	m2=0;
	m3=0;
}
 
 
}
