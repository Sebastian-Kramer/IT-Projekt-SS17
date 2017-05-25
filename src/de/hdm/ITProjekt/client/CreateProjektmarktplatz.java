package de.hdm.ITProjekt.client;



import org.mortbay.log.Log;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatz;
import de.hdm.ITProjekt.shared.AdministrationProjektmarktplatzAsync;

public class CreateProjektmarktplatz{

public static void createProjektmarktplatz() {
	  // (1) Create the client proxy. Note that although you are creating the
	  // service interface proper, you cast the result to the asynchronous
	  // version of the interface. The cast is always safe because the
	  // generated proxy implements the asynchronous interface automatically.
	  //
	  AdministrationProjektmarktplatzAsync adminService = (AdministrationProjektmarktplatzAsync) GWT.create(AdministrationProjektmarktplatz.class);

	  // (2) Create an asynchronous callback to handle the result.
	  //
	  AsyncCallback callback = new AsyncCallback() {
	    public void onSuccess(Void result) {
	      // do some UI stuff to show success
	    	
	    }

	    public void onFailure(Throwable caught) {
	      // do some UI stuff to show failure

	    }

		@Override
		public void onSuccess(Object result) {
			// TODO Auto-generated method stub

		}

		
	  };

	  // (3) Make the call. Control flow will continue immediately and later
	  // 'callback' will be invoked when the RPC completes.
	  //
	 
	  adminService.createProjektmarktplatz("test", callback);
	}

}
