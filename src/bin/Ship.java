package bin;

import java.util.ArrayList;

public class Ship {
	private int length;
	private String name;
	private int number;
	
	public Ship() {
		length=genLength();
	}
	
	public Ship(String name, int number) {
		length=genLength();
		this.name=name;
		this.number=number;
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int genLength () {
		return (2+(int)(Math.random()*3)); 
	}
	
	public static void main(String[] args) {
		Ship test=new Ship();
		System.out.println(test.getLength());
	}
	
	
}
