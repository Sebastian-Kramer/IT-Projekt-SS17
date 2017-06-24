package de.hdm.ITProjekt.shared.bo;

import java.util.Date;

public class Ausschreibung extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	private int ID = 0; 
	private String ausschreibungstext = "";
	private String bezeichnung = "";
	private Date datum = null;
	
	/*
	 * Realisierung der Beziehungen zu Projekt mittels Fremdschlüssel
	 */
	
	private Integer Projekt_ID = 0;
	
	/*
	 * Realisierung der Beziehung zu einem Partnerprofil mittels Fremdschlüssel
	 */
	
	private Integer Partnerprofil_ID = 0;
	
	/*
	 * Realisierung der Beziehung zu Organisationseinheit mittels Fremdschlüssel
	 */
	
	private Integer Orga_ID = 0;
	
	/*
	 * 
	 * @param selectedObject
	 */
	
	
	public Ausschreibung(Ausschreibung selectedObject) {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * @param 
	 */
	
	public Ausschreibung() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * @return ID
	 */
	
	public int getID() {
		return ID;
	}
	
	/*
	 *  @param ID
	 */
	
	public void setID(int iD) {
		ID = iD;
	}
	
	/*
	 * 
	 * @return ausschreibungstext
	 */
	
	public String getAusschreibungstext() {
		return ausschreibungstext;
	}
	
	/*
	 * 
	 * @param ausschreibungstext
	 */
	
	public void setAusschreibungstext(String ausschreibungstext) {
		this.ausschreibungstext = ausschreibungstext;
	}
	/*
	 * 
	 * @return
	 */
	
	public String getBezeichnung() {
		return bezeichnung;
	}
	
	/*
	 * 
	 * @param bezeichnung
	 */
	
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	/*
	 * 
	 * @return
	 */
	
	public Date getDatum() {
		return datum;
	}
	
	/*
	 * 
	 * @param datum
	 */
	
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
	/*
	 * 
	 * @return
	 */
	
	public Integer getProjekt_ID() {
		return Projekt_ID;
	}
	
	/*
	 * 
	 * @param projekt_ID
	 */
	
	public void setProjekt_ID(Integer projekt_ID) {
		if(projekt_ID == 0){
			this.Projekt_ID = null;
		}else{
			this.Projekt_ID = projekt_ID;
				
			}
		
	}
	
	/*
	 * 
	 * @return
	 */
	
	public Integer getPartnerprofil_ID() {
		return Partnerprofil_ID;
	}
	
	/*
	 * 
	 * @param partnerprofil_ID
	 */
	
	public void setPartnerprofil_ID(Integer partnerprofil_ID) {
		if(partnerprofil_ID == 0){
			this.Partnerprofil_ID = null;
		}else{
			this.Partnerprofil_ID = partnerprofil_ID;
		}
	}
	
	/*
	 * 
	 * @return
	 */
	
	public Integer getOrga_ID() {
		return Orga_ID;
	}
	
	/*
	 * 
	 * @param orga_ID
	 */
	
	public void setOrga_ID(Integer orga_ID) {
		if(orga_ID == 0){
			this.Orga_ID = null;
		}else{
			this.Orga_ID = orga_ID;
		}
	}
	
	/*
	 * zum Testen der Mapper in der Konsole
	 * @return "\n" + "ID: " + ID + "\n" + "Ausschreibungstext: " + ausschreibungstext + "\n"
	 * 			+ "Bezeichnung: " + bezeichnung + "\n" + "Datum: " + datum;
	 */
	
	public String toString(){
		return "\n" + "ID: " + ID + "\n" + "Ausschreibungstext: " + ausschreibungstext + "\n"
				+ "Bezeichnung: " + bezeichnung + "\n" + "Datum: " + datum;
	}
	
	/*
	 * @param
	 * @return super.equals(c)
	 * @return false
	 */
	
	public boolean equals(Object o) {

	    if (o != null && o instanceof Ausschreibung) {
	      Ausschreibung c = (Ausschreibung) o;
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
