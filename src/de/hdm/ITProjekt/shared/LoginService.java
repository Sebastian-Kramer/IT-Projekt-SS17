package de.hdm.ITProjekt.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.ITProjekt.client.LogInInfo;

/*
 * Dieses Interface ist für die Login-Funktion des Systems notwendig.
 * Es beinhaltet nur eine Methode die für diese Ausführung benötigt wird.
 */

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	public LogInInfo login(String requestUrl);

	}
