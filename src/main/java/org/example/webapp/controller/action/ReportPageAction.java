package org.example.webapp.controller.action;

import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.controller.common.Action;
import org.example.webapp.controller.common.ActionForward;

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
