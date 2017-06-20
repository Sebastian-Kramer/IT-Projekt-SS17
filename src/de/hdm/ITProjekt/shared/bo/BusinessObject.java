package de.hdm.ITProjekt.shared.bo;

//import java.io.Serializable; //Serialisierung wandelt Objekte in Bytes um und umgekehrt, wir ben�tigen 
							//dies um Objekte in einer Datenbank zu speichern

import com.google.gwt.user.client.rpc.IsSerializable;

public abstract class BusinessObject implements IsSerializable {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * eindeutige Identifikationsnummer einer Instanz der Klasse
	 */
	
	private int ID = 0;
	
	/*
	 * Auslesen der ID
	 * @return ID
	 */

	public int getID() {
		return ID;
	}
	
	/*
	 * Setzen der ID
	 * @param ID
	 */

	public void setID(int id) {
		this.ID = id;
	}
	
	/*
	 * Auslesen des Klassennemans und der ID des Objektes 
	 */
	
	public String toString(){
		return this.getClass().getName() + "#" + this.ID; 
	}
	
	/*
	 * Es wird �berpr�ft ob ein Objekt ungleich NULL ist und gecastet werden kann
	 * instanceof �berpr�ft ob o zuweisungskompatibel zu BusinessObject ist
	 * @return true
	 * @return false wenn keine Gleichheit besteht
	 */
	
	public boolean equals(Object o){						
		if (o != null && o instanceof BusinessObject){	
			BusinessObject bo = (BusinessObject) o;
			try {
				if(bo.getID() == this.ID)
					return true;
			}
			catch(IllegalArgumentException e){
				return false;
			}
			}
		return false;  
		}
	
	/*
	 * Diese Methode sollte immer definiert werden wenn auch equals definiert wird. Sind objekte gleich, haben sie denselben HashWert.(non-Javadoc)
	 * equals Methode �berpr�ft die Gleichheit der hashCodes
	 * @return this.ID
	 */
	public int hashCode(){ 
		return this.ID; 	 
	}
	}


