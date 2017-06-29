package de.hdm.ITProjekt.shared.bo;

/*
 * Jeder Bewerber und jede Ausschreibung hat Eigenschaften. Bei einem Bewerber bilden die Eigenschaften
 * das 'Können' des Bewerbers ab. Bei einer Ausschreibung bilden die Eigenschaften die Anforderungen ab, 
 * die ein Bewerber für diese Stelle haben sollte. Eine oder mehrere Eigenschaften gehören zu einem Partnerprofil.
 * Eine Eigenschaft hat einen Namen und einen Wert.
 */

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
	
	private Integer Partnerprofil_ID = 0 ;

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
	
	public Integer getPartnerprofil_ID() {
		return Partnerprofil_ID;
	}
	
	/*
	 * Setzen des Fremdschlüssels Partnerprofil ID
	 * @param Partnerprofil_ID
	 */
	
	public void setPartnerprofil_ID(Integer partnerprofil_ID) {
		if (partnerprofil_ID == 0){
			this.Partnerprofil_ID = null;
		} else {
			this.Partnerprofil_ID = partnerprofil_ID;
		}
		
}
	/*
	 * Zum Testen der Klasse in der Konsole
	 * @return ID + " " + bez
	 */
	public String toString(){
		return name + " " + wert;
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