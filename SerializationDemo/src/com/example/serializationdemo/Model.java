package com.example.serializationdemo;

import java.io.Serializable;

public class Model implements Serializable
{
   private String name;
   private String number;
   private String gender;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
}
