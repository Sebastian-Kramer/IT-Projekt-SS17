package de.hdm.ITProjekt.shared.bo;

public class Person extends Organisationseinheit{
	
	private static final long serialVersionUID = 1L;

	/*
	 * Vorname der Organisationseinheit
	 */
	
	private String vorname;
	
	/*
	 * Emailadresse der Organisationseinheit
	 */
	
	private String email;

	/*
	 * Name der Organisationseinheit
	 */
	
	private String name;
	
	/*
	 * Anrede der Organisationseinheit
	 */
	
	private String anrede;
	/*
	 * isAdmin der Organisationseinheit
	 */
	
	private boolean isAdmin;
	
	/*
	 * Realisierung der Beziehung zu einem Team mittels Fremdschlüsselbeziehung
	 */
	
	private Integer Team_ID = null; 
	
	/*
	 * Realisierung der Beziehung zu einem Unternehmen mittels Fremdschlüssel
	 */
	
	private Integer UN_ID = null;
	
	/*
	 * Auslesen der Emailadresse
	 * @return email
	 */
	
	
	
	
	public String getEmail() {
		return email;
	}
	
	/*
	 * Setzen der Emailadresse
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/*
	 * Auslesen isAdmin
	 * @return isAdmin 
	 */
	public boolean getisAdmin() {
		return isAdmin;
	}
	/*
	 * Setzen des isAdmin Befehls
	 * @param isAdmin
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	
	/*
	 * Auslesen des Vornamens
	 * @return vorname 
	 */
	
	public String getVorname() {
		return vorname;
	}
	
	/*
	 * Setzen des Vornamens
	 * @param vorname
	 */
	
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
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
	 * Auslesen der Anrede
	 * @return anrede
	 */
	
	public String getAnrede() {
		return anrede;
	}
	
	/*
	 * Setzen der Anrede 
	 * @param anrede
	 */
	
	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}
	
	/*
	 * Auslesen des Fremdschlüssels Team Id
	 * @return Team_ID
	 */
	
	public Integer getTeam_ID() {
		return Team_ID;
	}
	
	/*
	 * Setzen des Fremdschlüssels Team ID
	 * @param Team_ID
	 */
	
	public void setTeam_ID(Integer team_ID) {
		if(team_ID==0){
			this.Team_ID = null;
		}else{
			this.Team_ID = team_ID;	
		}
	}
	
	/*
	 * Auslesen des Fremdschlüssels Unternehmens ID
	 * @return UN_ID
	 */
	
	public Integer getUN_ID() {
		return UN_ID;
	}
	
	/*
	 * Setzen des Fremdschlüssels Unternehmens ID
	 * @param uN_ID
	 * 
	 */
	
	public void setUN_ID(Integer uN_ID) {
		if(uN_ID==0){
			this.UN_ID = null;
		}else{
			this.UN_ID = uN_ID;
		}
	}
	

	

}
