package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.server.db.ProjektmarktplatzMapper;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

public class TestStart {
	
	public static void main(String[] args) {
		
		ProjektmarktplatzMapper t1 = ProjektmarktplatzMapper.pmpMapper();
		
		Projektmarktplatz p1 = new Projektmarktplatz();
		
		p1.setBez("bez");

		t1.addMarktplatz(p1);
		
		System.out.println(t1.findByKey(1) + " " +  t1.findByKey(2));
	}

}
