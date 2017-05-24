package de.hdm.ITProjekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProjektBoerse implements EntryPoint {
  private VerticalPanel mainPanel = new VerticalPanel();
  private FlexTable stocksFlexTable = new FlexTable();
  private HorizontalPanel addPanel = new HorizontalPanel();
  private TextBox newSymbolTextBox = new TextBox();
  private Button addStockButton = new Button("Hinzufügen");
  private Label lastUpdatedLabel = new Label();

  /**
   * Entry point method.
   */
  public void onModuleLoad() {
    // TODO Create table for stock data.
	    stocksFlexTable.setText(0, 0, "Variable1");
	    stocksFlexTable.setText(0, 1, "Variable2");
	    stocksFlexTable.setText(0, 2, "Variable3");
	    stocksFlexTable.setText(0, 3, "Variable4");

    // TODO Assemble Add Stock panel.
	    addPanel.add(newSymbolTextBox);
	    addPanel.add(addStockButton);
    // TODO Assemble Main panel.
	    mainPanel.add(stocksFlexTable);
	    mainPanel.add(addPanel);
	    mainPanel.add(lastUpdatedLabel);
    // TODO Associate the Main panel with the HTML host page.
	    RootPanel.get("projektListe").add(mainPanel);
    // TODO Move cursor focus to the input box.
	    newSymbolTextBox.setFocus(true);
  }
}