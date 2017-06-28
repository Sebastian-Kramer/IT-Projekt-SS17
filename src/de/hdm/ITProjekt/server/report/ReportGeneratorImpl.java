package de.hdm.ITProjekt.server.report;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.ITProjekt.server.AdministrationProjektmarktplatzImpl;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.ReportGenerator;
import de.hdm.ITProjekt.shared.bo.Ausschreibung;
import de.hdm.ITProjekt.shared.bo.Beteiligung;
import de.hdm.ITProjekt.shared.bo.Bewerbung;
import de.hdm.ITProjekt.shared.bo.Eigenschaft;
import de.hdm.ITProjekt.shared.bo.Organisationseinheit;
import de.hdm.ITProjekt.shared.bo.Partnerprofil;
import de.hdm.ITProjekt.shared.bo.Person;
import de.hdm.ITProjekt.shared.bo.Projekt;
import de.hdm.ITProjekt.shared.bo.Team;
import de.hdm.ITProjekt.shared.bo.Unternehmen;
import de.hdm.ITProjekt.shared.report.AllAusschreibungenByPartnerprofilReport;
import de.hdm.ITProjekt.shared.report.AllAusschreibungenReport;
import de.hdm.ITProjekt.shared.report.AllBeteiligungenUsersReport;
import de.hdm.ITProjekt.shared.report.AllBewerbungenByAusschreibungReport;
import de.hdm.ITProjekt.shared.report.AllBewerbungenByOrganisationseinheitReport;
import de.hdm.ITProjekt.shared.report.AllBewerbungenToOneAusschreibungReport;
import de.hdm.ITProjekt.shared.report.AllBewerbungenWithAusschreibungenReport;
import de.hdm.ITProjekt.shared.report.Column;
import de.hdm.ITProjekt.shared.report.FanIn;
import de.hdm.ITProjekt.shared.report.FanInFanOutReport;
import de.hdm.ITProjekt.shared.report.FanOut;
import de.hdm.ITProjekt.shared.report.ProjektverflechtungReport;
import de.hdm.ITProjekt.shared.report.Row;

	@SuppressWarnings("serial")
	public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator{

		private AdministrationProjektmarktplatz adminService = null;
		
		public ReportGeneratorImpl() throws IllegalArgumentException {
		}
		
		public void init() throws IllegalArgumentException{
			
			
			AdministrationProjektmarktplatzImpl ap = new AdministrationProjektmarktplatzImpl();
			ap.init();
			this.adminService = ap;
		}
		
		protected AdministrationProjektmarktplatz getAdminservice(){
			return this.adminService;
		}
		
		public Vector<Person> getAllPersonen() throws IllegalArgumentException{		
			return adminService.getAllPerson();
		}
		
		
	
	@Override
	public AllAusschreibungenByPartnerprofilReport createAllAusschreibungenByPartnerprofilReport(Organisationseinheit o)
			throws IllegalArgumentException {

		Vector<Ausschreibung> alleAusschreibungen = adminService.getAllAusschreibungen();
		
		Partnerprofil referenzPartnerprofil = adminService.getPartnerprofilOfOrganisationseinheit(o);
		
		Vector<Eigenschaft> referenzEigenschaften = adminService.getAllEigenschaftenbyPartnerprofilID(referenzPartnerprofil.getID());	
	
		
		
		for (Ausschreibung ausschreibung : alleAusschreibungen) {
			
			Vector<Eigenschaft> eigenschaftenDerAusschreibung = adminService.getAllEigenschaftenbyPartnerprofilID(ausschreibung.getPartnerprofil_ID());
		}
		
		for (Eigenschaft eigenschaft : referenzEigenschaften) {
			
		}
		
		
		return null;
	}

	@Override
	public AllAusschreibungenByPartnerprofilReport getAusschreibungByMatchingPartnerprofil(Organisationseinheit o)
			throws IllegalArgumentException {
		if(this.getAdminservice()== null){
			return null;
			
			}
			
		AllAusschreibungenByPartnerprofilReport result = new AllAusschreibungenByPartnerprofilReport();
			
			result.setTitel("");
			result.setErstelldatum(new Date());
			
			
			Row headline = new Row();
			headline.addColumn(new Column("Bezeichnung"));
			headline.addColumn(new Column("Ausschreibender"));
			headline.addColumn(new Column("Ausschreibungstext"));
			headline.addColumn(new Column("Bewerbungsfrist"));
			
			
			result.addRow(headline);
			

		Vector<Ausschreibung> matchingAusschreibungen = new Vector<Ausschreibung>();
		
		Partnerprofil partnerprofil = adminService.getPartnerprofilOfOrganisationseinheit(o);
		
		Vector<Eigenschaft> eigenschaften = adminService.getAllEigenschaftenbyPartnerprofilID(partnerprofil.getID());
		
		Vector<Ausschreibung> allAusschreibungen = adminService.getAllAusschreibungen();
		System.out.println("Anzahl der Ausschreibungen: "+allAusschreibungen.size());
		
		for(Ausschreibung ausschreibung : allAusschreibungen){
			System.out.println("");
			
			Partnerprofil partnerprofilByAusschreibung = adminService.getPartnerprofilByAusschreibung(ausschreibung);
			System.out.println("Partnerprofil ID: "+partnerprofilByAusschreibung.getID());
			/**
			 * @param id, welche aus dem partnerprofilOfAusschreibung-Objekt gelesen wird
			 * @return Vector mit allen Eigenschaften zu dem übergebnen Partnerprofil
			 */
			Vector<Eigenschaft> eigenschaftenByAusschreibung = adminService.getAllEigenschaftenbyPartnerprofilID(partnerprofilByAusschreibung.getID());
			System.out.println("Anzahl der Eigenschaften zu diesem Partnerprofil: "+eigenschaftenByAusschreibung.size());
			if(eigenschaften.size()==eigenschaftenByAusschreibung.size()){
				System.out.println("Anzahl der Eigenschaften passt");
				int matchCounter = 0;
				for(int i=0;i<eigenschaften.size();i++){
					for(Eigenschaft fremdeEigenschaft : eigenschaftenByAusschreibung){
						if(eigenschaften.get(i).getName().equals(fremdeEigenschaft.getName()) && eigenschaften.get(i).getWert().equals(fremdeEigenschaft.getWert())){
							matchCounter++;
						}
					}
				}
				
				Projekt projektByAusschreibung = adminService.getProjektByID(ausschreibung.getProjekt_ID());
				
				Person projektleiterByProjekt = this.findPersonByKey(projektByAusschreibung.getProjektleiter_ID());
				if(matchCounter==eigenschaften.size()){
					if(projektleiterByProjekt.getPartnerprofil_ID() != partnerprofil.getID()){
						System.out.println("Partnerprofil passt, füge zur Menge hinzu!");
						matchingAusschreibungen.add(ausschreibung);
					}
				}
			}
		}
	
		for (Ausschreibung ausschreibung : matchingAusschreibungen) {
			
			 Row ausschreibungRow = new Row();
			 
			 ausschreibungRow.addColumn(new Column(ausschreibung.getBezeichnung()));
			 ausschreibungRow.addColumn(new Column(adminService.getPersonbyID(ausschreibung.getOrga_ID()).getVorname() + " " 
			 + adminService.getPersonbyID(ausschreibung.getOrga_ID())));
			 ausschreibungRow.addColumn(new Column(ausschreibung.getAusschreibungstext()));
			 ausschreibungRow.addColumn(new Column(ausschreibung.getDatum().toString()));
			 
			 result.addRow(ausschreibungRow);
			 
		}
		
		
		return result;
	}	

	@Override
	public AllAusschreibungenReport createAllAusschreibungenReport() throws IllegalArgumentException {
		if(this.getAdminservice() == null){
			return null;
			
			}
			
			AllAusschreibungenReport result = new AllAusschreibungenReport();
			
			result.setTitel("Alle Ausschreibungen");
			result.setErstelldatum(new Date());
			
			
			Row headline = new Row();
			headline.addColumn(new Column("Ausschreibender"));
			headline.addColumn(new Column("Zugehöriges Projekt"));
			headline.addColumn(new Column("Bezeichnung"));
			headline.addColumn(new Column("Bewerbungsfrist"));
			headline.addColumn(new Column("Ausschreibungstext"));
			headline.addColumn(new Column("Ausschreibungsstatus"));
			
			result.addRow(headline);
			
			
			Vector<Ausschreibung> alleAusschreibungen = this.adminService.getAllAusschreibungen();
		
			for(Ausschreibung a : alleAusschreibungen){
			      Row ausschreibungRow = new Row();
			            
			      
			   
			      Organisationseinheit ausschreibender = adminService.getOrgaeinheitByID(a.getID());
			      
			      Projekt zugehoerigesProjekt = adminService.getProjektByID(a.getProjekt_ID());
			      
			      
			      
			      if(ausschreibender instanceof Person){
						ausschreibungRow.addColumn(new Column(((Person) ausschreibender).getVorname() 
								+ " " + ((Person) ausschreibender).getName()));
					
					} else if(ausschreibender instanceof Team){
						ausschreibungRow.addColumn(new Column(((Team) ausschreibender).getName()));
					
					} else if(ausschreibender instanceof Unternehmen){

						ausschreibungRow.addColumn(new Column(((Unternehmen) ausschreibender).getName()));
					}	

			      
			      ausschreibungRow.addColumn(new Column(zugehoerigesProjekt.getName()));
			      
			      ausschreibungRow.addColumn(new Column(a.getBezeichnung()));
			      
			      ausschreibungRow.addColumn(new Column(a.getDatum().toString()));
			      
			      ausschreibungRow.addColumn(new Column(a.getAusschreibungstext()));
			      
//			      ausschreibungRow.addColumn(new Column(a..toString()));		
			      
			      result.addRow(ausschreibungRow);
			}
			
			return result;	
	}

	@Override
	public AllBeteiligungenUsersReport createAllBeteiligungenToProjectReport(int id)
			throws IllegalArgumentException {
		if(this.getAdminservice()== null){
			return null;
		}
		
		AllBeteiligungenUsersReport result = new AllBeteiligungenUsersReport();
		
		result.setTitel("Alle Beteiligungen des Users");
		
		result.setErstelldatum(new Date());
		
		Row headline = new Row();
		
		headline.addColumn(new Column("Projekt"));
		headline.addColumn(new Column("Startdatum"));
		headline.addColumn(new Column("Enddatum"));
		headline.addColumn(new Column("Umfang"));
		
		result.addRow(headline);

		
		Vector<Beteiligung> alleBeteiligungen = adminService.getBeteiligungByOrgaeinheit(adminService.getOrgaeinheitByID(id));
		
		for(Beteiligung b : alleBeteiligungen){
			
			
			Projekt projekt = adminService.getProjektByID(b.getProjekt_ID());
			
		      Row beteiligungRow = new Row();
		      
		      beteiligungRow.addColumn(new Column(projekt.getName()));
		      
		      beteiligungRow.addColumn(new Column(b.getStartdatum().toString()));
		        
		      beteiligungRow.addColumn(new Column(b.getEnddatum().toString()));
		        
		      beteiligungRow.addColumn(new Column(b.getUmfang()));
		     
 
		      result.addRow(beteiligungRow);
		}
		
		
		
		return result;
	}

	@Override
	public AllBewerbungenByAusschreibungReport createAllBewerbungenByAusschreibungReport(Organisationseinheit o)
			throws IllegalArgumentException {

		
		if(this.getAdminservice()== null){
			return null;
		}
		
		AllBewerbungenByAusschreibungReport result = new AllBewerbungenByAusschreibungReport();
		
		result.setTitel("Alle eigenen Bewerbungen mit den zugehörigen Ausschreibungen");
		
		result.setErstelldatum(new Date());
		

		
		Row headline = new Row();
		
		headline.addColumn(new Column("Bewerbungstext"));
		headline.addColumn(new Column("Erstellungsdatum"));
		headline.addColumn(new Column("Bewerbungsstatus"));
		headline.addColumn(new Column("Zugehörige Ausschreibung"));
		headline.addColumn(new Column("Ausschreibender"));
		headline.addColumn(new Column("Bewerbungsfrist"));
		headline.addColumn(new Column("Ausschreibungstext"));
		
		result.addRow(headline);

		Vector<Bewerbung> alleEigeneBewerbungen = adminService.getBewerbungByOrgaeinheit(o);
		
		for(Bewerbung b : alleEigeneBewerbungen){
			
			Ausschreibung ausschreibung = adminService.getAusschreibungByID(b.getAusschreibungs_ID());
			
			Person ausschreibender = adminService.getPersonbyID(ausschreibung.getOrga_ID());
			
		      Row bewerbungRow = new Row();
		       
		      bewerbungRow.addColumn(new Column(b.getBewerbungstext()));
		        
		      bewerbungRow.addColumn(new Column(b.getErstelldatum().toString()));
		        
		      bewerbungRow.addColumn(new Column(b.getStatus().toString()));
		        
		      bewerbungRow.addColumn(new Column(ausschreibung.getBezeichnung()));
		      
		      bewerbungRow.addColumn(new Column(ausschreibender.getVorname() + " " + ausschreibender.getName()));
		      
		      bewerbungRow.addColumn(new Column(ausschreibung.getDatum().toString()));
		      
		      bewerbungRow.addColumn(new Column(ausschreibung.getAusschreibungstext()));
			
		      
		      result.addRow(bewerbungRow);
		}
		
		return result;
	}
	
	@Override
	public AllBewerbungenWithAusschreibungenReport createAllBewerbungenWithAusschreibungenReport(Organisationseinheit o)
			throws IllegalArgumentException {
		if(this.getAdminservice()== null){
			return null;
		}
		
		AllBewerbungenWithAusschreibungenReport result = new AllBewerbungenWithAusschreibungenReport();
		
		result.setTitel("Alle eigenen Bewerbungen mit den zugehörigen Ausschreibungen");
		
		result.setErstelldatum(new Date());
		

		
		Row headline = new Row();
		
		headline.addColumn(new Column("Bewerbungstext"));
		headline.addColumn(new Column("Erstellungsdatum"));
		headline.addColumn(new Column("Bewerbungsstatus"));
		headline.addColumn(new Column("Zugehörige Ausschreibung"));
		headline.addColumn(new Column("Ausschreibender"));
		headline.addColumn(new Column("Bewerbungsfrist"));
		headline.addColumn(new Column("Ausschreibungstext"));
		
		result.addRow(headline);

		
		Vector<Bewerbung> alleEigeneBewerbungen = adminService.getBewerbungByOrgaeinheit(o);
		
		for(Bewerbung b : alleEigeneBewerbungen){
			
			
			Ausschreibung ausschreibung = adminService.getAusschreibungByID(b.getAusschreibungs_ID());
			
			
			Person ausschreibender = adminService.getPersonbyID(ausschreibung.getOrga_ID());
			
		      Row bewerbungRow = new Row();
		      
		      bewerbungRow.addColumn(new Column(b.getBewerbungstext()));
		        
		      bewerbungRow.addColumn(new Column(b.getErstelldatum().toString()));
		      
		      bewerbungRow.addColumn(new Column(b.getStatus().toString()));
		       
		      bewerbungRow.addColumn(new Column(ausschreibung.getBezeichnung()));
		      
		      bewerbungRow.addColumn(new Column(ausschreibender.getVorname() + " " + ausschreibender.getName()));

		      bewerbungRow.addColumn(new Column(ausschreibung.getAusschreibungstext()));
			
		      
		      result.addRow(bewerbungRow);
		}
		
		return result;
	
	}

	@Override
	public AllBewerbungenToOneAusschreibungReport createAllBewerbungenToOneAusschreibungReport(Organisationseinheit o){
		if(this.getAdminservice()== null){
			return null;
		}
		
		AllBewerbungenToOneAusschreibungReport result = new AllBewerbungenToOneAusschreibungReport();
		
		result.setTitel("Alle eigenen Bewerbungen mit den zugehörigen Ausschreibungen");
		
		result.setErstelldatum(new Date());

		Row headline = new Row();
		
		headline.addColumn(new Column("Bewerbungstext"));
		headline.addColumn(new Column("Erstellungsdatum"));
		headline.addColumn(new Column("Bewerbungsstatus"));
		headline.addColumn(new Column("Zugehörige Ausschreibung"));
		headline.addColumn(new Column("Ausschreibender"));
		headline.addColumn(new Column("Bewerbungsfrist"));
		headline.addColumn(new Column("Ausschreibungstext"));
		
		result.addRow(headline);
		
		Vector<Bewerbung> alleEigeneBewerbungen = adminService.getBewerbungByOrgaeinheit(o);
		
		for(Bewerbung b : alleEigeneBewerbungen){
			
			Ausschreibung ausschreibung = adminService.getAusschreibungByID(b.getAusschreibungs_ID());
			
			Person ausschreibender = adminService.getPersonbyID(ausschreibung.getOrga_ID());
			
		      Row bewerbungRow = new Row();
		        
		      bewerbungRow.addColumn(new Column(b.getBewerbungstext()));
		      
		      bewerbungRow.addColumn(new Column(b.getErstelldatum().toString()));
		       
		      bewerbungRow.addColumn(new Column(b.getStatus().toString()));
		      
		      bewerbungRow.addColumn(new Column(ausschreibung.getBezeichnung()));
		      
		      bewerbungRow.addColumn(new Column(ausschreibender.getVorname() + " " + ausschreibender.getName()));
		    // Sechste Zeile: Be ewerbungsfrist der Ausschreibung
		      bewerbungRow.addColumn(new Column(ausschreibung.getDatum().toString()));
		      
		    // Siebte Zeile: Ausschreibungstext hinzufügen
		      bewerbungRow.addColumn(new Column(ausschreibung.getAusschreibungstext()));
			
		      
		      result.addRow(bewerbungRow);
		}
		
		return result;
	}

	@Override
	public FanInFanOutReport createFanInFanOutReport() throws IllegalArgumentException {
		if(this.getAdminservice()== null){
			return null;
		}
		
		FanInFanOutReport result = new FanInFanOutReport();
		
		result.setTitel("Fan-In/ Fan-Out-Analyse");
		
		result.setErstelldatum(new Date());
		
			
			result.addSubReport(this.createFanInAnalyse());
			result.addSubReport(this.createFanOutAnalyse());
		
		return result;
	}

	@Override
	public FanIn createFanInAnalyse() throws IllegalArgumentException {
		
		if(this.getAdminservice()== null){
			return null;
		}
		
		
		FanIn result = new FanIn();
		
		result.setTitel("Anzahl der Bewerbungen");
		
		
		result.setErstelldatum(new Date());
		
		Row headline = new Row();
		headline.addColumn(new Column("ID"));
		headline.addColumn(new Column("Organisationseinheit"));
		headline.addColumn(new Column("laufend"));
		headline.addColumn(new Column("abgelehnt"));
		headline.addColumn(new Column("angenommen"));
		
		result.addRow(headline);
		
		Vector<Organisationseinheit> alleOrganisationseinheiten = adminService.getAllOrganisationseinheiten();
		
		for (Organisationseinheit orga : alleOrganisationseinheiten) {
			
			Vector<Bewerbung> laufendeBewerbungen = new Vector<Bewerbung>();
			Vector<Bewerbung> abgelehnteBewerbungen = new Vector<Bewerbung>();
			Vector<Bewerbung> angenommeneBewerbungen = new Vector<Bewerbung>();
		
			Vector<Bewerbung> alleBewerbungen = adminService.getBewerbungByOrgaeinheit(orga);
			
			for(Bewerbung b: alleBewerbungen){
				

				
				if(b.getStatus().toString().equals("laufend")){
					laufendeBewerbungen.add(b);
				} else if(b.getStatus().toString().equals("abgelehnt")){
					abgelehnteBewerbungen.add(b);
				} else if(b.getStatus().toString().equals("angenommen")){
					angenommeneBewerbungen.add(b);
				}
			}
			
			Row anzahlRow = new Row();
			
			anzahlRow.addColumn(new Column(String.valueOf(orga.getID())));
			
			if(orga instanceof Person){
				anzahlRow.addColumn(new Column(((Person)orga).getVorname() + " "+((Person)orga).getName()));
			} else if(orga instanceof Team){
				anzahlRow.addColumn(new Column(((Team)orga).getName()));
			} else if(orga instanceof Unternehmen){
				anzahlRow.addColumn(new Column(((Unternehmen)orga).getName()));		
			}
			
			anzahlRow.addColumn(new Column(String.valueOf(laufendeBewerbungen.size())));
			anzahlRow.addColumn(new Column(String.valueOf(abgelehnteBewerbungen.size())));
			anzahlRow.addColumn(new Column(String.valueOf(angenommeneBewerbungen.size())));
			
			result.addRow(anzahlRow);
			
		}
		
		return result;	
	}
	

	@Override
	public FanOut createFanOutAnalyse() throws IllegalArgumentException {

		if(this.getAdminservice()== null){
			return null;
		}
		
		
		FanOut result = new FanOut();
		
		result.setTitel("Anzahl der Ausschreibungen");
		
		
		
		result.setErstelldatum(new Date());
		
		Row headline = new Row();
		headline.addColumn(new Column("ID"));
		headline.addColumn(new Column("Organisationseinheit"));
		headline.addColumn(new Column("besetzt"));
		headline.addColumn(new Column("abgebrochen"));
		headline.addColumn(new Column("laufend"));
		
		result.addRow(headline);
		
		/**
		 * @return Vector mit allen Organisationseinheiten
		 */
		Vector<Organisationseinheit> alleOrganisationseinheiten = adminService.getAllOrganisationseinheiten();
		
	
		for(Organisationseinheit orga : alleOrganisationseinheiten) {
			
			Vector<Ausschreibung> besetzteAusschreibungen = new Vector<Ausschreibung>();
			Vector<Ausschreibung> abgebrocheneAusschreibungen = new Vector<Ausschreibung>();
			Vector<Ausschreibung> laufendeAusschreibungen = new Vector<Ausschreibung>();
			
			/**
			 * @param Organisationseinheit-Objekt orga
			 * @return Ausschreibung-Objekt zu dem jeweils übergebenen Organisationseinheit-Objekt
			 */
			Vector<Ausschreibung> alleAusschreibungen = adminService.getAusschreibungByOrgaeinheit(orga);
				
//-------------------------------------- AUSSCHREIBUNG 
//			for(Ausschreibung a: alleAusschreibungen){
//				
//				if(a.getStatus().toString().equals("besetzt")){
//					besetzteAusschreibungen.add(a);
//				} else if(a.getStatus().toString().equals("abgebrochen")){
//					abgebrocheneAusschreibungen.add(a);
//				} else if(a.getStatus().toString().equals("laufend")){
//					laufendeAusschreibungen.add(a);
//				}
//				
//			}
			
			Row anzahlRow = new Row();
			
			anzahlRow.addColumn(new Column(String.valueOf(orga.getID())));
			
			if(orga instanceof Person){
				anzahlRow.addColumn(new Column(((Person)orga).getVorname() + " "+((Person)orga).getName()));
			} else if(orga instanceof Team){
				anzahlRow.addColumn(new Column(((Team)orga).getName()));
			} else if(orga instanceof Unternehmen){
				anzahlRow.addColumn(new Column(((Unternehmen)orga).getName()));		
			}
			
			anzahlRow.addColumn(new Column(String.valueOf(besetzteAusschreibungen.size())));
			anzahlRow.addColumn(new Column(String.valueOf(abgebrocheneAusschreibungen.size())));
			anzahlRow.addColumn(new Column(String.valueOf(laufendeAusschreibungen.size())));		
			
			result.addRow(anzahlRow);
			
		}	
		return result;		
	}
	

	@Override
	public ProjektverflechtungReport createProjektverflechtungReport(int id) throws IllegalArgumentException {

		if(this.getAdminservice()== null){
			return null;
		}
		
		
		ProjektverflechtungReport result = new ProjektverflechtungReport();
		
		result.setTitel("Projektverflechtung");
		
		result.setErstelldatum(new Date());
		
	
	      result.addSubReport(this.createAllBeteiligungenToProjectReport(id));
	      result.addSubReport(this.createAllBewerbungenByPersonReport(id));
		
		
		return result;
	}
	

	@Override
	public Person findPersonByKey(int id) throws IllegalArgumentException {
		return this.adminService.getPersonbyID(id);
	}

	@Override
	public Team findTeamByKey(int id) throws IllegalArgumentException {
		return this.adminService.getTeamByID(id);
	}

	@Override
	public Unternehmen findUnternehmenByKey(int id) throws IllegalArgumentException {
		return this.adminService.getUnByID(id);
	}

	@Override
	public Vector<Organisationseinheit> getBewerberByEigeneAusschreibungen(Organisationseinheit o)
			throws IllegalArgumentException {

		Vector<Organisationseinheit> bewerber = new Vector<Organisationseinheit>();
		
		Vector<Ausschreibung> meineAusschreibungen = adminService.getAusschreibungByOrgaeinheit(o);
		
		for (Ausschreibung ausschreibung : meineAusschreibungen) {
			
			Vector<Bewerbung> bewerbungen =  adminService.findBewerbungByAusschreibungId(ausschreibung.getID());
			
				for (Bewerbung bewerbung : bewerbungen) {
					
					if(bewerber.contains(adminService.getOrgaeinheitByID(bewerbung.getOrga_ID()))){
						
					}else{
						
						bewerber.add(adminService.getOrgaeinheitByID(bewerbung.getOrga_ID()));
					}
					
					
				}
			}
		return bewerber;
	}

	
	@Override
	public AllBewerbungenByOrganisationseinheitReport createAllBewerbungenByPersonReport(int id) throws IllegalArgumentException {

		if(this.getAdminservice()== null){
			return null;
		}
		
		
		Ausschreibung ausschreibung = adminService.getAusschreibungByID(id);
		
		
		AllBewerbungenByOrganisationseinheitReport result = new AllBewerbungenByOrganisationseinheitReport();
		
		result.setTitel("Alle Bewerbungen auf die Ausschreibung: " + ausschreibung.getBezeichnung() 
		+ ", ID: " + ausschreibung.getID());
		
		
		result.setErstelldatum(new Date());
		
		
		Row headline = new Row();
		headline.addColumn(new Column("Bewerber"));
		headline.addColumn(new Column("Erstellungsdatum"));
		headline.addColumn(new Column("Bewerbungstext"));
		headline.addColumn(new Column("Bewerbungsstatus"));
		
		result.addRow(headline);
		
		/**
		 * @param ausschreibungId
		 * @return Bewerbung-Objekt anhand der übergebenen ausschreibungID
		 */
		Vector<Bewerbung> bewerbungen = adminService.findBewerbungByAusschreibungId(id);
		
		for(Bewerbung b : bewerbungen){
			
		      Row bewerbungRow = new Row();
		
		      
		      Organisationseinheit bewerber = adminService.getOrgaeinheitByID(b.getOrga_ID());
		     
		      
		      if(bewerber instanceof Person){
		    	  bewerbungRow.addColumn(new Column(((Person) bewerber).getVorname() 
							+ " " + ((Person) bewerber).getName()));
				
				} else if(bewerber instanceof Team){
					bewerbungRow.addColumn(new Column(((Team) bewerber).getName()));
				
				} else if(bewerber instanceof Unternehmen){
					bewerbungRow.addColumn(new Column(((Unternehmen) bewerber).getName()));
				}
		      
		      //Zweite Spalte: Erstellungsdatum
		      	bewerbungRow.addColumn(new Column(b.getErstelldatum().toString()));
		      	
		      // Dritte Spalte: Bewerbungstext hinzufügen
		      	bewerbungRow.addColumn(new Column(b.getBewerbungstext()));
		      	
		      // Vierte Spalte: Bewerbungsstatus
		      	bewerbungRow.addColumn(new Column(b.getStatus().toString()));
		   
		      	
		      result.addRow(bewerbungRow);
		}
		
		return result;
	}

	

	@Override
	public Team getTeamByKey(int teamID) throws IllegalArgumentException {
		return adminService.getTeamByID(teamID);
	}

	@Override
	public Unternehmen getUnternehmenByKey(int unternehmenID) throws IllegalArgumentException {
		return adminService.getUnByID(unternehmenID);
	}

	



	
}
