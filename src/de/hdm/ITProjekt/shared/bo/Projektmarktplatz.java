package de.hdm.ITProjekt.shared.bo;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Projektmarktplatz extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	
	 
	private int ID = 0;  //eventeull löschen da ID von BO übergeben wird
	
	/*
	 * Bezeichnung des Projektmarktplatzes
	 */
	
	private String bez = "";	

	public Projektmarktplatz(){
		
	}
	
	/*
	 * Konstruktor
	 * 
	 */
	public Projektmarktplatz(String bez){
		this.bez = bez;
	}
	
	/*
	 * Auslesen der ID
	 * @return ID
	 */
	public int getID() {
		return ID;
	}
	
	/*
	 * Setzen der ID
	 * @param iD
	 */

	public void setID(int iD) {
		ID = iD;
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
		return ID + " " + bez;
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
