package com.freemarkerdemo.model;

public class Person {

	private String name;
	private String place;
	
	public Person() {
		super();
	}
	
	public Person(String name, String place) {
		super();
		this.name = name;
		this.place = place;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	
}
