package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.server.db.ProjektmarktplatzMapper;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

public class TestStart {
	
	public static void main(String[] args) {
		
		ProjektmarktplatzMapper t1 = ProjektmarktplatzMapper.pmpMapper();
		
		Projektmarktplatz p1 = new Projektmarktplatz();
		
		//p1.setBez("Vertrieb");

		//t1.addMarktplatz(p1);
		
		p1.setID(2);
		
		//p1.setBez("Rechnungswesen");
		
		t1.deleteMarktplatz(p1);
		
		System.out.println(t1.getAll());
	}

}
