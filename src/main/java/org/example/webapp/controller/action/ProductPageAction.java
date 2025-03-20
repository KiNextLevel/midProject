package org.example.webapp.controller.action;

import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.controller.common.Action;
import org.example.webapp.controller.common.ActionForward;

public class ProductPageAction implements Action {
    @Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: PRODUCTPAGE ACTION 도착");
		ActionForward forward = new ActionForward();
	    forward.setPath("productPage.do");
		forward.setRedirect(true);
		return forward;
	}
}
