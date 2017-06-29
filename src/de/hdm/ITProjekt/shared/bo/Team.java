package de.hdm.ITProjekt.shared.bo;

/*
 * Ein Team ist eine Organisationseinheit und hat außer den Attributen, die es erbt noch einen
 * Namen, mit dem es auf dem Projektmarktplatz aktiv ist.
 */

public class Team extends Organisationseinheit{

	private static final long serialVersionUID = 1L;
	
	/*
	 * Name des Teams
	 */
	
	private String name;
	
	/*
	 * Realisierung der Beziehung zu einem Unternehmen mittels Fremdschlüssel
	 */
	
	private Integer UN_ID = null;
	
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
	 * Auslesen der Unternehmens ID
	 * @return UN_ID
	 */
	
	public Integer getUN_ID() {
		return UN_ID;
	}
	
	/*
	 * Setzen der UnternehmensID
	 * @param uN_ID
	 */
	
	public void setUN_ID(Integer uN_ID) {
		if (uN_ID == 0){
			this.UN_ID = null;
		}else{
			this.UN_ID = uN_ID;
		}
	}		

}
