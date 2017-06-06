package de.hdm.ITProjekt.shared.bo;

public class Team extends Organisationseinheit{

	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	//Fermdschlüssel Beziehung zu Unternehmen, da jedes Team zu einem UN gehört
	private Integer UN_ID = null;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getUN_ID() {
		return UN_ID;
	}
	
	
	public void setUN_ID(Integer uN_ID) {
		if (UN_ID == 0){
			this.UN_ID = null;
		}else{
			this.UN_ID = uN_ID;
		}
	}		

}
