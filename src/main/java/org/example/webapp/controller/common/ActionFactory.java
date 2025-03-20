package org.example.webapp.controller.common;

import org.example.webapp.controller.action.*;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
	Map<String, Action> factory;
	
	ActionFactory() {
		factory = new HashMap<>();
		factory.put("loginPage.do", new LoginPageAction());
		factory.put("login.do", new LoginAction());
		factory.put("logout.do", new LogoutAction());
		factory.put("join.do", new JoinAction());
		factory.put("joinPage.do", new JoinNextAction());
		factory.put("mainPage.do",new MainPageAction());
		factory.put("paymentPage.do",new PaymentPageAction());
		factory.put("payment.do",new PaymentAction());
		factory.put("productPage.do",new ProductPageAction());
		factory.put("report.do",new ReportAction());
		factory.put("reportPage.do",new ReportPageAction());
		factory.put("userDetailPage.do",new UserDetailPageAction());
	}
	
	public Action getAction(String command) {
		System.out.println("ActionFactory 로그 : command ["+command+"]");
		return this.factory.get(command);
	}
}
