package de.hdm.ITProjekt.shared.bo;

import java.util.Vector;

public class Organisationseinheit extends BusinessObject{
	private static final long serialVersionUID = 1L;
	private String anschrift;
	Person p1 = new Person();
	Vector o1 = new Vector();
	
	public String getAnschrift() {
		return anschrift;
	}
	public void setAnschrift(String anschrift) {
		this.anschrift = anschrift;
	}
	public void addPerson(){
		if (Person.getOrgeinheit() == "Person"){
			
		p1.setAnrede("Herr");		
		o1.add(p1);
		
	}
	}
	

	
	

}

