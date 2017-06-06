package de.hdm.ITProjekt.shared.bo;

//import java.io.Serializable; //Serialisierung wandelt Objekte in Bytes um und umgekehrt, wir ben�tigen 
							//dies um Objekte in einer Datenbank zu speichern

import com.google.gwt.user.client.rpc.IsSerializable;

public abstract class BusinessObject implements IsSerializable {
	
	private static final long serialVersionUID = 1L;
	
	private int ID = 0;

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		this.ID = id;
	}
	
	public String toString(){
		//Gibt den Klassennamen und die ID des Objekts aus
		return this.getClass().getName() + "#" + this.ID; 
	}
	
	public boolean equals(Object o){						//Es wird �berpr�ft ob ein Objekt ungleich NULL ist und gecastet werden kann
		if (o != null && o instanceof BusinessObject){	//instanceof �berpr�ft ob o zuweisungskompatibel zu BusinessObject ist
			BusinessObject bo = (BusinessObject) o;
			try {
				if(bo.getID() == this.ID)
					return true;
			}
			catch(IllegalArgumentException e){
				return false;
			}
			}
		return false;  //Wenn keine Gleichheit bestimmt wird, false zur�ckgeben
		}
	
	public int hashCode(){ //Diese Methode sollte immer definiert werden wenn auch equals definiert wird. Sind objekte gleich, haben sie denselben HashWert.
		return this.ID; 	// equals Methode �berpr�ft die Gleichheit der hashCodes, 
	}
	}


