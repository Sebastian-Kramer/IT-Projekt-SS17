package de.hdm.ITProjekt.shared.bo;

/*
 * Ein Unternehmen ist eine Organisationseinheit und hat außer den Attributen, die es erbt noch einen
 * Namen, mit dem es auf dem Projektmarktplatz aktiv ist.
 */

public class Unternehmen extends Organisationseinheit{
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * Unternehmensname
	 */
	
	private String name;
	
	/*
	 * Auslesen des Unternehmensnamens
	 * @return name
	 */

	public String getName() {
		return name;
		}
	
	/*
	 * Setzen des Unternehmensnamens
	 * @param name
	 */
	
	public void setName(String name) {
		this.name = name;
	}

}
