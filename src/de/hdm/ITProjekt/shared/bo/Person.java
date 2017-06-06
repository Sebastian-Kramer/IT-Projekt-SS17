package de.hdm.ITProjekt.shared.bo;

public class Person extends Organisationseinheit{
	
	private static final long serialVersionUID = 1L;
	
	private String vorname = "";
	
	private String name = "";
	
	private String anrede = "";
	
	//Fremdschlüsselbeziehungen werden hier realisiert
	
	private Integer Team_ID = null; 
	
	private Integer UN_ID = null;
	
	
	
	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getName() {
		return name;
	}
	public void setName(String nachname) {
		this.name = nachname;
	}
	public String getAnrede() {
		return anrede;
	}
	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}
	
	public Integer getTeam_ID() {
		return Team_ID;
	}
	
	public void setTeam_ID(Integer team_ID) {
		if(Team_ID==0){
			this.Team_ID=null;
		}else{
			this.Team_ID = team_ID;	
		}
	}
	public Integer getUN_ID() {
		return UN_ID;
	}
	public void setUN_ID(Integer uN_ID) {
		if(UN_ID==0){
			this.UN_ID=null;
		}else{
			this.UN_ID = uN_ID;
		}
	}
	

	

}
