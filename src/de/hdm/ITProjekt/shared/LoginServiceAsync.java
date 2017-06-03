package de.hdm.ITProjekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.ITProjekt.client.LogInInfo;

public interface LoginServiceAsync {

	void login(String requestUrl, AsyncCallback<LogInInfo> callback);

}
