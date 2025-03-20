package controller.action;

import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.controller.common.Action;
import org.example.webapp.controller.common.ActionForward;

public class UserDetailPageAction implements Action {
    @Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: USERDETAILPAGE ACTION 도착");
		ActionForward forward = new ActionForward();
		request.setAttribute("userEmail",request.getParameter("userEmail"));
	    forward.setPath("UserDetail.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
