package de.hdm.ITProjekt.shared;

import com.google.gwt.user.client.rpc.RemoteService;

public interface ReportGenerator extends RemoteService {

	void init() throws IllegalArgumentException;

}
