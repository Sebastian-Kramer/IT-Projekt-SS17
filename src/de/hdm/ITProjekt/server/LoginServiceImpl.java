//package de.hdm.ITProjekt.server;
//import de.hdm.ITProjekt.client.LogInInfo;
//import de.hdm.ITProjekt.shared.LoginService;
//import de.hdm.ITProjekt.shared.LoginServiceAsync;
//
//import com.google.appengine.api.users.User;
//import com.google.appengine.api.users.UserServiceFactory;
//import com.google.appengine.api.users.UserService;
//import com.google.gwt.user.server.rpc.RemoteServiceServlet;
//
//public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
//	private static final long serialVersionUID = 1L;
//	
//	public LogInInfo login(String requestUrl){
//			UserService userService = UserServiceFactory.getUserService();
//				User user = userService.getCurrentUser();
//				LogInInfo logInInfo = new LogInInfo();
//			
//				if (user != null){
//					logInInfo.setLoggedIn(true);
//					logInInfo.setEmailAddress(user.getEmail());
//					logInInfo.setNickname(user.getNickname());
//					logInInfo.setLogoutUrl(userService.createLogoutURL(requestUrl));
//				}
//				else {
//					logInInfo.setLoggedIn(false);
//					logInInfo.setLoginUrl(userService.createLoginURL(requestUrl));
//				}
//				return logInInfo;
//				
//				}
//	}
//
//
