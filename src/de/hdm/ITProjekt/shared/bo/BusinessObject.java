package de.hdm.ITProjekt.shared.bo;

import java.io.Serializable; //Serialisierung wandelt Objekte in Bytes um und umgekehrt, wir benötigen 
							//dies um Objekte in einer Datenbank zu speichern

import com.google.gwt.user.client.rpc.IsSerializable;

public abstract class BusinessObject implements IsSerializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString(){
		//Gibt den Klassennamen und die ID des Objekts aus
		return this.getClass().getName() + "#" + this.id; 
	}
	
	public boolean equals(Object o){						//Es wird überprüft ob ein Objekt ungleich NULL ist und gecastet werden kann
		if (o != null && o instanceof BusinessObject){	//instanceof überprüft ob o zuweisungskompatibel zu BusinessObject ist
			BusinessObject bo = (BusinessObject) o;
			try {
				if(bo.getId() == this.id)
					return true;
			}
			catch(IllegalArgumentException e){
				return false;
			}
			}
		return false;  //Wenn keine Gleichheit bestimmt wird, false zurückgeben
		}
	
	public int hashCode(){ //Diese Methode sollte immer definiert werden wenn auch equals definiert wird. Sind objekte gleich, haben sie denselben HashWert.
		return this.id; 	// equals Methode überprüft die Gleichheit der hashCodes, 
	}
	}


