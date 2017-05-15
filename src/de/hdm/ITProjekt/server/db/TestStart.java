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
//		ProjektMapper p = ProjektMapper.pMapper();
//		
//		Projektmarktplatz p1 = new Projektmarktplatz();
//		Projekt projekt =  new Projekt();
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
		 * Test f�r PersonMapper Einzelaufruf �ber findbykey Methode
		 */
//		Person p1 = PersonMapper.perMapper().findByKey(1);
//		System.out.println(p1.getID());
//		System.out.println(p1.getAnrede());
//		System.out.println(p1.getAnschrift());
//		System.out.println(p1.getVorname());
//		System.out.println(p1.getNachname());
		
		String inputDate = "2017-08-01";
		SimpleDateFormat format =
                new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(inputDate);
		
		BeteiligungMapper b = BeteiligungMapper.bMapper();
		
		Beteiligung b1 = new Beteiligung();
		b1.setID(1);
		b1.setUmfang("Das ist ein Test zum ändern des Inhalts");
		//b1.setEnddatum(date);
		b.update(b1);
		
		System.out.println(b.getAll());
						
		}
}
		
	

