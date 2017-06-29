package de.hdm.ITProjekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.ITProjekt.client.LogInInfo;

/*
 * Dieses Interface ist das Gegenstück des Interface LoginService.
 * Es besteht nur aus dem Methodenkopf der Methode login 
 * mit den Übergabeparametern der angeforderten  URL
 */

public interface LoginServiceAsync {

	void login(String requestUrl, AsyncCallback<LogInInfo> callback);

}
