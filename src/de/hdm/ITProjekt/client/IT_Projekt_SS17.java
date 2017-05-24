//package de.hdm.ITProjekt.client;
//
//import com.google.gwt.core.client.EntryPoint;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.client.ui.VerticalPanel;
//
//public class IT_Projekt_SS17 implements EntryPoint {
//  private VerticalPanel mainPanel = new VerticalPanel();
//  private FlexTable stocksFlexTable = new FlexTable();
//  private HorizontalPanel addPanel = new HorizontalPanel();
//  private TextBox newSymbolTextBox = new TextBox();
//  private Button addStockButton = new Button("Erstellen");
//  private Label lastUpdatedLabel = new Label();
//
//  /**
//   * Entry point method.
//   */
//  public void onModuleLoad() {
//    // TODO Create table for stock data.
//	    stocksFlexTable.setText(0, 0, "Organisationseinheit");
//	    stocksFlexTable.setText(0, 1, "Berufserfahrung");
//	    stocksFlexTable.setText(0, 2, "Variable3");
//	    stocksFlexTable.setText(0, 3, "Variable4");
//	    
//    // TODO Assemble Add Stock panel.
//	    addPanel.add(newSymbolTextBox);
//	    addPanel.add(addStockButton);
//    // TODO Assemble Main panel.
//	    mainPanel.add(stocksFlexTable);
//	    mainPanel.add(addPanel);
//	    mainPanel.add(lastUpdatedLabel);
//    // TODO Associate the Main panel with the HTML host page.
//	    RootPanel.get("projektListe").add(mainPanel);
//    // TODO Move cursor focus to the input box.
//	    newSymbolTextBox.setFocus(true);
//	    
//  }
//}

package de.hdm.ITProjekt.client;

import com.google.gwt.core.client.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.ITProjekt.shared.Administration.ProjektmarktplatzAsync;
//import de.hdm.thies.bankProjekt.shared.ReportGeneratorAsync;
import de.hdm.ITProjekt.shared.bo.Projektmarktplatz;

/**
 * Entry-Point-Klasse des Projekts <b>BankProjekt</b>.
 */
public class IT_Projekt_SS17 implements EntryPoint {

  /**
   * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
   * zusichert, benötigen wir eine Methode
   * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
   * <code>main()</code>-Methode normaler Java-Applikationen.
   */
  @Override
public void onModuleLoad() {
    /*
     * Zunächst weisen wir der BankAdministration eine Bank-Instanz zu, die das
     * Kreditinstitut repräsentieren soll, für das diese Applikation arbeitet.
     */
    AdministrationProjektmartplatzAsync bankVerwaltung = ClientsideSettings.getBankVerwaltung();
    Bank bank = new Bank();
    bank.setName("HdM Bank");
    bank.setStreet("Nobelstr. 10");
    bank.setZip(70569);
    bank.setCity("Stuttgart");
    bankVerwaltung.setBank(bank, new SetBankCallback());

    /*
     * Auch dem Report-Generator weisen wir dieses Bank-Objekt zu. Es wird dort
     * für die Darstellung der Adressdaten des Kreditinstituts benötigt.
     */
    ReportGeneratorAsync reportGenerator = ClientsideSettings
        .getReportGenerator();
    reportGenerator.setBank(bank, new SetBankCallback());

    /*
     * Wir bereiten nun die Erstellung eines bescheidenen Navigators vor, der
     * einige Schaltflächen (Buttons) für die Ausführung von Unterprogrammen
     * enthalten soll.
     * 
     * Die jeweils ausgeführten Unterprogramme sind Demonstratoren
     * exemplarischer Anwendungsfälle des Systems. Auf eine professionelle
     * Gestaltung der Benutzungsschnittstelle wurde bewusst verzichtet, um den
     * Blick nicht von den wesentlichen Funktionen abzulenken. Eine
     * exemplarische GUI-Realisierung findet sich separat.
     * 
     * Die Demonstratoren werden nachfolgend als Showcase bezeichnet. Aus diesem
     * Grund existiert auch eine Basisklasse für sämtliche Showcase-Klassen
     * namens Showcase.
     */

    /*
     * Der Navigator ist als einspaltige Aneinanderreihung von Buttons
     * realisiert. Daher bietet sich ein VerticalPanel als Container an.
     */
    VerticalPanel navPanel = new VerticalPanel();

    /*
     * Das VerticalPanel wird einem DIV-Element namens "Navigator" in der
     * zugehörigen HTML-Datei zugewiesen und erhält so seinen Darstellungsort.
     */
    RootPanel.get("Navigator").add(navPanel);

    /*
     * Ab hier bauen wir sukzessive den Navigator mit seinen Buttons aus.
     */

    /*
     * Neues Button Widget erzeugen und eine Beschriftung festlegen.
     */
    final Button findCustomerButton = new Button("Finde Kunde");

    /*
     * Unter welchem Namen können wir den Button durch die CSS-Datei des
     * Projekts formatieren?
     */
    findCustomerButton.setStylePrimaryName("bankproject-menubutton");

    /*
     * Hinzufügen des Buttons zum VerticalPanel.
     */
    navPanel.add(findCustomerButton);

    /*
     * Natürlich benötigt der Button auch ein Verhalten, wenn man mit der Maus
     * auf ihn klickt. Hierzu registrieren wir einen ClickHandler, dessen
     * onClick()-Methode beim Mausklick auf den zugehörigen Button aufgerufen
     * wird.
     */
    findCustomerButton.addClickHandler(new ClickHandler() {
      @Override
	public void onClick(ClickEvent event) {
        /*
         * Showcase instantiieren.
         */
        Showcase showcase = new FindCustomersByNameDemo();

        /*
         * Für die Ausgaben haben wir ein separates DIV-Element namens "Details"
         * in die zugehörige HTML-Datei eingefügt. Bevor wir den neuen Showcase
         * dort einbetten, löschen wir vorsichtshalber sämtliche bisherigen
         * Elemente dieses DIV.
         */
        RootPanel.get("Details").clear();
        RootPanel.get("Details").add(showcase);
      }
    });

    /*
     * Ab hier folgen weitere Button-Definitionen, die nach exakt der gleichen
     * Methode erfolgen wie beim ersten Button.
     * 
     * Da das Muster dazu sich mehrfach wiederholt, könnte man hier schon von
     * einem unerwünschte Code Clone sprechen. Um dies stilistisch zu optimieren
     * wäre z.B. die Verwendung des Factory oder Builder Pattern denkbar. 
     * Hierauf wurde jedoch bewusst verzichtet, um den Komplexitätsgrad dieses
     * Demonstrators nicht unnötig zu erhöhen. 
     */
    final Button createAccountButton = new Button("Konto anlegen");
    createAccountButton.setStylePrimaryName("bankproject-menubutton");
    navPanel.add(createAccountButton);

    createAccountButton.addClickHandler(new ClickHandler() {
      @Override
	public void onClick(ClickEvent event) {
        Showcase showcase = new CreateAccountDemo();
        RootPanel.get("Details").clear();
        RootPanel.get("Details").add(showcase);
      }
    });

    // Nächste Button-Definition
    final Button deleteAccountButton = new Button("Konto löschen");
    deleteAccountButton.setStylePrimaryName("bankproject-menubutton");
    navPanel.add(deleteAccountButton);

    deleteAccountButton.addClickHandler(new ClickHandler() {
      @Override
	public void onClick(ClickEvent event) {
        Showcase showcase = new DeleteAccountDemo();
        RootPanel.get("Details").clear();
        RootPanel.get("Details").add(showcase);
      }
    });

    // Nächste Button-Definition
    final Button deleteCustomerButton = new Button("Kunden löschen");
    deleteCustomerButton.setStylePrimaryName("bankproject-menubutton");
    navPanel.add(deleteCustomerButton);

    deleteCustomerButton.addClickHandler(new ClickHandler() {
      @Override
	public void onClick(ClickEvent event) {
        Showcase showcase = new DeleteCustomerDemo();
        RootPanel.get("Details").clear();
        RootPanel.get("Details").add(showcase);
      }
    });

    // Nächste Button-Definition
    final Button showAllCustomersAndAccountsButton = new Button(
        "Kd. & Konten zeigen");
    showAllCustomersAndAccountsButton
        .setStylePrimaryName("bankproject-menubutton");
    navPanel.add(showAllCustomersAndAccountsButton);

    showAllCustomersAndAccountsButton.addClickHandler(new ClickHandler() {
      @Override
	public void onClick(ClickEvent event) {
        Showcase showcase = new ShowAllCustomersAndTheirAccountsDemo();
        RootPanel.get("Details").clear();
        RootPanel.get("Details").add(showcase);
      }
    });

    // Nächste Button-Definition
    final Button showReportButton = new Button("Report zu Kd. 1");
    showReportButton.setStylePrimaryName("bankproject-menubutton");
    navPanel.add(showReportButton);

    showReportButton.addClickHandler(new ClickHandler() {
      @Override
	public void onClick(ClickEvent event) {
        Showcase showcase = new ShowReportDemo();
        RootPanel.get("Details").clear();
        RootPanel.get("Details").add(showcase);
      }
    });

  }

  /**
   * Diese Nested Class wird als Callback für das Setzen des Bank-Objekts bei
   * der BankAdministration und bei dem ReportGenerator benötigt.
   * 
   * @author thies
   * @version 1.0
   */
  class SetBankCallback implements AsyncCallback<Void> {

    @Override
    public void onFailure(Throwable caught) {
      /*
       * Wenn ein Fehler auftritt, dann geben wir eine kurze Log Message aus.
       */
      ClientsideSettings.getLogger().severe("Setzen der Bank fehlgeschlagen!");
    }

    @Override
    public void onSuccess(Void result) {
      /*
       * Wir erwarten diesen Ausgang, wollen aber keine Notifikation ausgeben.
       */
    }

  }
}
