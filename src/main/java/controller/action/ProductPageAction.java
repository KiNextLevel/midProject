package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public class ProductPageAction implements Action {
    @Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: PRODUCTPAGE ACTION 도착");
		ActionForward forward = new ActionForward();
	    forward.setPath("/Metronic-Shop-UI-master/theme/ProductPage.jsp");
		forward.setRedirect(true);
		return forward;
	}
}
