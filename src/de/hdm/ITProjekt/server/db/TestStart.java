package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.server.db.ProjektmarktplatzMapper;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.*;
import java.util.*;
import java.text.*;

import com.ibm.icu.text.SimpleDateFormat;


public class TestStart {
	
	public static void main(String[] args) throws ParseException {
		
//		ProjektmarktplatzMapper t1 = ProjektmarktplatzMapper.pmpMapper();
//		ProjektMapper p = ProjektMapper.pMapper();
//		
//		Projektmarktplatz p1 = new Projektmarktplatz();
//		Projekt projekt =  new Projekt();
//		
//		
//		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//		Date startdatum = date.parse("2017-01-01");
//		Date enddatum = date.parse("2017-12-31");
//		
//		
//		/* Test Methodenaufrufe:
//		 	p1.setBez("Vertrieb");
//			t1.addMarktplatz(p1);		
//			p1.setID(2);
//			p1.setBez("Rechnungswesen");
//			t1.deleteMarktplatz(p1);
//		*/
//
//		projekt.setName("test");
//		projekt.setBeschreibung("test");
//		projekt.setStartdatum(startdatum);
//		projekt.setEnddatum(enddatum);
//
//	
//		p.addProjekt(projekt);
//		
//		System.out.println(p.findByKey(2));
		
		/*
		 * Test für PersonMapper Einzelaufruf über findbykey Methode
		 */
//		Person p1 = PersonMapper.perMapper().findByKey(1);
//		System.out.println(p1.getID());
//		System.out.println(p1.getAnrede());
//		System.out.println(p1.getAnschrift());
//		System.out.println(p1.getVorname());
//		System.out.println(p1.getNachname());
		
		Person p1= new Person();
		p1.setID(1);
	
		
		
		PersonMapper.perMapper().deletePerson(p1);

								
		}
}
		
	

