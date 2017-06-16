package de.hdm.ITProjekt.shared.bo;

public class Eigenschaft extends BusinessObject{
	private static final long serialVersionUID = 1L;

	private String name = "" ;
	private String wert = "" ;
	private int Partnerprofil_ID = 0 ;

	
	public String getName() {
		return name;
		
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWert() {
		return wert;
	}
	public void setWert(String wert) {
		this.wert = wert;
	
	}
	
	public int getPartnerprofil_ID() {
		return Partnerprofil_ID;
	}
	public void setPartnerprofil_ID(int partnerprofil_ID) {
		Partnerprofil_ID = partnerprofil_ID;
}

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