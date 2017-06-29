package de.hdm.ITProjekt.shared.bo;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/*
 * Die Klasse Projektmarktplatz bezieht nur eine Bezeichnung, mit der der jeweilige Projektmarktplatz
 * im System dargestellt wird
 */

public class Projektmarktplatz extends BusinessObject {
	
	/*
	 * Null-Argument Konstructor der für andere Klassen benötigt wird
	 */
	public Projektmarktplatz(){
		
	}
	
	private static final long serialVersionUID = 1L;
	 
	/*
	 * Bezeichnung des Projektmarktplatzes
	 */
	
	private String bez = "";	

	/*
	 * Konstruktor
	 * 
	 */
	public Projektmarktplatz(String bez){
		this.bez = bez;
	}
	
	/*
	 * Auslesen der Bezeichnung
	 * @return bez
	 */

	public String getBez() {
		return bez;
	}
	
	/*
	 * Setzen der Bezeichnung
	 * @param bez
	 */

	public void setBez(String bez) {
		this.bez = bez;
	}
	
	/*
	 * Zum Testen der Klasse in der Konsole
	 * @return ID + " " + bez
	 */
	
	public String toString(){
		return bez;
	}
		
	public boolean equals(Object o) {

	    if (o != null && o instanceof Projektmarktplatz) {
	      Projektmarktplatz c = (Projektmarktplatz) o;
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
