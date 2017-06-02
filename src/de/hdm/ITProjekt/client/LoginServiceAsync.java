package de.hdm.ITProjekt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

	void login(String requestUrl, AsyncCallback<LogInInfo> callback);

}
