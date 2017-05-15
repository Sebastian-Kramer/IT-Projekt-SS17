package de.hdm.ITProjekt.server.db;

import de.hdm.ITProjekt.server.db.ProjektmarktplatzMapper;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Beteiligung;
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

//		
//		Projektmarktplatz p1 = new Projektmarktplatz();

//		
		
//		/* Test Methodenaufrufe:
//		 	p1.setBez("Vertrieb");
//			t1.addMarktplatz(p1);		
//			p1.setID(2);
//			p1.setBez("Rechnungswesen");
//			t1.deleteMarktplatz(p1);
//		*/
//

//
//	
//		p.addProjekt(projekt);
//		
//		System.out.println(p.findByKey(2));
		
		/*
		 * Test f�r PersonMapper Einzelaufruf �ber findbykey Methode
		 */
//		Person p1 = PersonMapper.perMapper().findByKey(1);
//		System.out.println(p1.getID());
//		System.out.println(p1.getAnrede());
//		System.out.println(p1.getAnschrift());
//		System.out.println(p1.getVorname());
//		System.out.println(p1.getNachname());
		
		String start 	= "2017-08-01";
		String end 		= "2017-12-31";
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startdate 	= format.parse(start);
		Date enddate	= format.parse(end);
		
		/*Test mit Beteiligung
		BeteiligungMapper b = BeteiligungMapper.bMapper();
		Beteiligung b1 = new Beteiligung();
		b1.setID(1);
		b1.setUmfang("Das ist ein Test zum ändern des Inhalts");
		b1.setEnddatum(enddate);
		b.update(b1);*/
		
		//Test mit Projekt
		ProjektMapper p = ProjektMapper.pMapper();
		Projekt pmp =  new Projekt();
		
		pmp.setName("test");
		pmp.setBeschreibung("test");
		pmp.setStartdatum(startdate);
		pmp.setEnddatum(enddate);
		pmp.setProjektmarktplatz_ID(1);
		
		p.insert(pmp);
		
		System.out.println(p.getAll());
						
		}
}
		
	

