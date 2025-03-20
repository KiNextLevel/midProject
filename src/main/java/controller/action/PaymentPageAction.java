package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

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
