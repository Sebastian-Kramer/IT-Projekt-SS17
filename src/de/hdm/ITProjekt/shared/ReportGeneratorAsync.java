package de.hdm.ITProjekt.shared;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.ITProjekt.shared.bo.Person;


public interface ReportGeneratorAsync {

	void init(AsyncCallback<Void> callback);

	void getAllPersonen(AsyncCallback<Vector<Person>> callback);

}
