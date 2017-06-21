package de.hdm.ITProjekt.shared.bo;

public class Eigenschaft extends BusinessObject{
	private static final long serialVersionUID = 1L;

	private String name = "" ;
	
	/*
	 * Wert der Eigenschaft
	 */
	
	private String wert = "" ;
	
	/*
	 * Realisierung einer Beziehung zu Partnerprofil mitels Fremdschlüssel
	 */
	
	private int Partnerprofil_ID = 0 ;

	/*
	 * Auslesen des Namens
	 * @return name
	 */
	
	public String getName() {
		return name;
		}
	
	/*
	 * Setzen des Namens
	 * @param name
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * Auslesen des Werts
	 * @return wert
	 */
	
	public String getWert() {
		return wert;
	}
	
	/* 
	 * Setzen des Werts
	 * @param wert
	 */
	
	public void setWert(String wert) {
		this.wert = wert;
	}
	
	/*
	 * Auslesen des Fremdschlüssels Partnerprofil ID
	 * @return Partnerprofil_ID
	 */
	
	public int getPartnerprofil_ID() {
		return Partnerprofil_ID;
	}
	
	/*
	 * Setzen des Fremdschlüssels Partnerprofil ID
	 * @param Partnerprofil_ID
	 */
	
	public void setPartnerprofil_ID(int partnerprofil_ID) {
		Partnerprofil_ID = partnerprofil_ID;
}

	/*
	 * Es wird �berpr�ft ob ein Objekt ungleich NULL ist und gecastet werden kann
	 * instanceof �berpr�ft ob o zuweisungskompatibel zu BusinessObject ist
	 * @return true
	 * @return false wenn keine Gleichheit besteht
	 */
	
	public boolean equals(Object o) {

	    if (o != null && o instanceof Eigenschaft) {
	      Eigenschaft c = (Eigenschaft) o;
	      try {
	        return super.equals(c);
	      }
	      catch (IllegalArgumentException e) {
	        return false;
	      }
	    }
	    return false;
	  }
		
}	