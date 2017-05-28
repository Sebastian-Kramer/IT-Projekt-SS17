package de.hdm.ITProjekt.shared.bo;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Projektmarktplatz extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	private int ID = 0; 
	private String bez = "";	

	public Projektmarktplatz(){
		
	}
	public Projektmarktplatz(String bez){
		this.bez = bez;
	}
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getBez() {
		return bez;
	}

	public void setBez(String bez) {
		this.bez = bez;
	}
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
