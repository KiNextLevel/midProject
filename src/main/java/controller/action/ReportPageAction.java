package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public class ReportPageAction implements Action {
    @Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: REPORTPAGE ACTION 도착");
		ActionForward forward = new ActionForward();
		request.setAttribute("userEmail",request.getParameter("userEmail"));
	    forward.setPath("report.do");
		forward.setRedirect(false);
		return forward;
	}
}
