package de.hdm.ITProjekt.shared.bo;

public class Team extends Organisationseinheit{
	private String name;
	private int anzahlMitglieder;
	private final static String orgeinheit = "Team"; 
	
	public static String getOrgeinheit() {
		return orgeinheit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAnzahlMitglieder() {
		return anzahlMitglieder;
	}
	public void setAnzahlMitglieder(int anzahlMitglieder) {
		this.anzahlMitglieder = anzahlMitglieder;
	}
	

}
