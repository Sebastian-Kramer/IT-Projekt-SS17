package de.hdm.ITProjekt.shared.report;

import java.util.Vector;

import com.ibm.icu.impl.Row;

public class SimpleReport extends Report {

		private static final long serialVersionUID = 1L;
		
		//Vector-Objekt mit allen Zeilen
		private Vector<Row> table = new Vector<Row>();
		
		// Hinzufügen Zeile
		public void addRow(Row r){
			this.table.addElement(r);
		}
		
		// Entfernen Zeile
		public void removeRow(Row r){
			this.table.removeElement(r);
		}
		
		// Auslesen aller Zeilen.
		
		public Vector<Row> getRows(){
			return this.table;
		}
	}

