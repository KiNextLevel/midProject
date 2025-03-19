package controller.common;

import java.util.HashMap;
import java.util.Map;

import controller.action.JoinAction;
import controller.action.LoginAction;
import controller.action.LogoutAction;
import controller.action.RegistrationPageAction;

public class ActionFactory {
	Map<String, Action> factory;
	
	ActionFactory() {
		factory = new HashMap<>();
		factory.put("LOGIN", new LoginAction());
		factory.put("LOGOUT", new LogoutAction());
		factory.put("REGPAGE", new RegistrationPageAction());
		factory.put("JOIN", new JoinAction());
	}
	
	public Action getAction(String command) {
		return this.factory.get(command);
	}
}
