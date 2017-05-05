package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.server.db.ProjektmarktplatzMapper;

public class TestStart {
	
	public static void main(String[] args) {
		
		ProjektmarktplatzMapper t1 = ProjektmarktplatzMapper.pmpMapper();

		System.out.println(t1.findByKey(1));
	}

}
