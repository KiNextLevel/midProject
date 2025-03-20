package org.example.webapp.controller.action;

import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.controller.common.Action;
import org.example.webapp.controller.common.ActionForward;

public class PaymentPageAction implements Action {
    @Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: PATMENTPAGE ACTION 도착");
		ActionForward forward = new ActionForward();
		request.setAttribute("productNum", request.getAttribute("productNum"));
	    forward.setPath("paymentPage.do");
		forward.setRedirect(false);
		return forward;
	}
}
