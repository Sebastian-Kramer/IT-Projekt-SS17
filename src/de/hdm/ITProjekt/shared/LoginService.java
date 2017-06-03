package de.hdm.ITProjekt.shared;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.ITProjekt.client.LogInInfo;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	public LogInInfo login(String requestUrl);
	
	
	

}
